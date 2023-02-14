package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.LoginRequest;
import gov.iti.jets.dto.requests.RegisterRequest;
import gov.iti.jets.dto.responses.LoginResponse;
import gov.iti.jets.dto.responses.RegisterResponse;
import gov.iti.jets.dto.validation.RegisterValidation;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.AuthenticationController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.EncryptionService;
import gov.iti.jets.services.HashService;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.ValidationService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;


public class AuthenticationControllerSingleton extends UnicastRemoteObject implements AuthenticationController {

    private static AuthenticationControllerSingleton instance;
    private AuthenticationControllerSingleton() throws RemoteException {}
    public static AuthenticationControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new AuthenticationControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("AuthenticationController", instance);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) throws RemoteException {
        UserService userService = new UserService();
        HashService hashService = new HashService();
        EncryptionService encryptionService = new EncryptionService();

        String decryptedPassword = encryptionService.decrypt(loginRequest.getPassword());
        Optional<User> userOptional = userService.getUserByPhoneNumber(loginRequest.getPhoneNumber());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            String hashedPassword = hashService.getHashedValue(decryptedPassword, user.getSalt());
            if(hashedPassword.equals(user.getPassword())){
                user.setPassword(encryptionService.encrypt(decryptedPassword));
                LoginResponse response = new LoginResponse(user.getUserName(), user.getPhoneNumber(), user.getEmail(), loginRequest.getPassword(),
                        user.getGender(), user.getCountry(), user.getBirthDate(), user.getOnlineStatus(), user.getBio(),
                        user.getPicture(), user.getPictureExtension());
                return response;
            }
        }
        return null;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws RemoteException {
        UserService userService = new UserService();
        ValidationService validationService = new ValidationService();
        HashService hashService = new HashService();
        EncryptionService encryptionService = new EncryptionService();

        RegisterValidation validation = new RegisterValidation(registerRequest);
        boolean isValidRequest = validationService.validate(validation);
        RegisterResponse response = new RegisterResponse();

        if(isValidRequest){
            byte[] saltBytes = new byte[24];
            new SecureRandom().nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);
            String decryptedPassword = encryptionService.decrypt(registerRequest.getPassword());
            String hashedPassword = hashService.getHashedValue(decryptedPassword, salt);
            User user = new User(registerRequest.getUserName(), registerRequest.getPhoneNumber(), registerRequest.getEmail(),
                    hashedPassword, salt, registerRequest.getGender(), registerRequest.getCountry(),
                    registerRequest.getBirthDate());
            userService.insertUser(user);
            user = userService.getUserByPhoneNumber(user.getPhoneNumber()).get();


            response = new RegisterResponse(user.getUserName(), user.getPhoneNumber(), user.getEmail(),
                    registerRequest.getPassword(), user.getGender(), user.getCountry(), user.getBirthDate(),
                    user.getOnlineStatus(), user.getBio(), user.getPicture(), user.getPictureExtension(), null);
        }
        else{
            response.setValidation(validation);
        }
        return response;
    }

    @Override
    public void logout(User user) throws RemoteException {

    }

}
