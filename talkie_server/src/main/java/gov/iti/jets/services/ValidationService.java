package gov.iti.jets.services;

import gov.iti.jets.dto.validation.UserProfileValidation;
import gov.iti.jets.dto.validation.Validation;

public class ValidationService {

    public boolean validate(Validation validation){
        return validation.validate();
    }

    public boolean userProfileValidate(UserProfileValidation validation) {
        return validation.validate();
    }
}
