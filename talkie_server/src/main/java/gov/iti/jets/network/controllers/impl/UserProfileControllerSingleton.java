package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.requests.UserProfileRequest;
import gov.iti.jets.dto.responses.UserProfileResponse;
import gov.iti.jets.dto.validation.UserProfileValidation;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.UserProfileController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;

public class UserProfileControllerSingleton extends UnicastRemoteObject implements UserProfileController {
    private static UserProfileControllerSingleton instance;
    protected UserProfileControllerSingleton() throws RemoteException {
        super();
    }

    public static UserProfileControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new UserProfileControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("UserProfileController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public UserProfileResponse update(UserProfileRequest request) {
        UserProfileServices userProfileServices= new UserProfileServices();
        ValidationService validationService = new ValidationService();
        HashService hashService= new HashService();
        EncryptionService encryptionService = new EncryptionService();

        UserProfileValidation validation = new UserProfileValidation(request);
        boolean isValidRequest = validationService.userProfileValidate(validation);
        UserProfileResponse response = new UserProfileResponse();

        if(isValidRequest) {
            byte[] saltBytes =new byte[24];
            new SecureRandom().nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);
            String decryptedPassword = encryptionService.decrypt(request.getPassword());
            String hashedPassword = hashService.getHashedValue(decryptedPassword, salt);

            User user = new User(request.getUserName(), request.getNewPhoneNumber(), request.getEmail(),
                    hashedPassword, salt, request.getGender(), request.getCountry(),
                    request.getBirthdate());

            userProfileServices.update(user);

            response = new UserProfileResponse(user.getUserName() , user.getPhoneNumber(), user.getPassword(),
                    user.getEmail() , user.getCountry(), user.getGender(), user.getBirthDate() ,user.getBio(),user.getPicture());

        }else {
            response.setValidation(validation);
        }

        return response;

    }

}
