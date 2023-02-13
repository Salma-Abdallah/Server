package gov.iti.jets.services;

import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.mappers.UserProfileMapper;
import gov.iti.jets.models.User;

import java.util.Optional;

public class UserProfileServices {
    private UserProfileMapper userProfileMapper;
    public UserProfileServices () {
        userProfileMapper = new UserProfileMapper();
    }

    public int update (User user) {
        return userProfileMapper.update(user);
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber){
        UserMapper userMapper = new UserMapper();
        Optional<UserEntity> entity = userMapper.getUserByPhoneNumber(phoneNumber);
        if(entity.isPresent()){
            return Optional.of(userMapper.entityToModel(entity.get()));
        }
        return Optional.empty();
    }
}
