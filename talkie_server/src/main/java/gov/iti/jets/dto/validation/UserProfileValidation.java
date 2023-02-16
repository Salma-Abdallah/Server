package gov.iti.jets.dto.validation;

import gov.iti.jets.dto.requests.UserProfileRequest;
import gov.iti.jets.dto.responses.UserProfileResponse;
import gov.iti.jets.mappers.UserMapper;

public class UserProfileValidation {
    private UserProfileRequest request;
    private String phoneNumberError;
    private String emailError;


    public UserProfileValidation() {
    }

    public UserProfileValidation(UserProfileRequest request) {
        this.request = request;
    }

    public boolean validate() {
        boolean isValid = true;
        UserMapper userMapper = new UserMapper();
        if(userMapper.getUserByPhoneNumber(request.getNewPhoneNumber()).isPresent() ){
            if(userMapper.getUserByPhoneNumber(request.getNewPhoneNumber()).get() == userMapper.getUserByPhoneNumber(request.getOldPhoneNumber()).get()){
                phoneNumberError = "your request is done successfully";
                isValid = true;
            }else {
                phoneNumberError = "Phone number already exists";
                isValid = false;
            }
        }
        if(userMapper.getUserByEmail(request.getNewEmail()).isPresent()){
            if(userMapper.getUserByEmail(request.getNewEmail()).get() == userMapper.getUserByEmail(request.getOldEmail()).get()) {
                emailError = "your request is done successfully";
                isValid = true;
            }
            else {
                emailError = "Email already exists";
                isValid = false;
            }
        }
        return isValid;
    }


}
