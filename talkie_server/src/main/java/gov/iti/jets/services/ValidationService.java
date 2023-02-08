package gov.iti.jets.services;

import gov.iti.jets.dto.validation.Validation;

public class ValidationService {

    public boolean validate(Validation validation){
        return validation.validate();
    }
}
