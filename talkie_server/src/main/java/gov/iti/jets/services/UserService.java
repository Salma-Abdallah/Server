package gov.iti.jets.services;

import gov.iti.jets.models.User;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;

import java.util.Optional;

public class UserService {
    private UserMapper userMapper;

    public UserService() {
        this.userMapper = new UserMapper();
    }
    public Optional<User> getUserByPhoneNumber(String phoneNumber){
        Optional<UserEntity> entity = userMapper.getUserByPhoneNumber(phoneNumber);
        if(entity.isPresent()){
            return Optional.of(userMapper.entityToModel(entity.get()));
        }
        return Optional.empty();
    }
    public int updateStatusByUserPhoneNumber(String phoneNumber, String onlineStatus){
        return userMapper.updateStatusByUserPhoneNumber(phoneNumber, onlineStatus);
    }
    public User insertUser(User user) throws RuntimeException{
        return userMapper.save(user);
    }

}
