package gov.iti.jets.dto.validation;

import gov.iti.jets.dto.requests.RegisterRequest;
import gov.iti.jets.mappers.UserMapper;
import java.io.Serializable;

public class RegisterValidation implements Serializable, Validation {

    private static final long serialVersionUID = -2449817711737079712L;
    transient private RegisterRequest request;
    private String emailError = null;
    private String phoneNumberError = null;

    public RegisterValidation() {}

    public RegisterValidation(RegisterRequest request) {
        this.request = request;
    }

    @Override
    public boolean validate() {
        boolean isValid = true;
        UserMapper userMapper = new UserMapper();
        if(userMapper.getUserByPhoneNumber(request.getPhoneNumber()).isPresent()){
            phoneNumberError = "Phone number already exists";
            isValid = false;
        }
        if(userMapper.getUserByEmail(request.getEmail()).isPresent()){
            emailError = "Email already exists";
            isValid = false;
        }
        return isValid;
    }
    public String getEmailError() {
        return emailError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    @Override
    public String toString() {
        return "RegisterValidation{" +
                "emailError='" + emailError + '\'' +
                ", phoneNumberError='" + phoneNumberError + '\'' +
                '}';
    }
}
