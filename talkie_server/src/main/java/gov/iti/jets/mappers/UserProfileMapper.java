package gov.iti.jets.mappers;

import gov.iti.jets.dto.validation.UserProfileValidation;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.utils.ImageUtils;

import java.util.Optional;

public class UserProfileMapper {
    private UserDao userDao;
    private UserProfileValidation userProfileValidation;
    public UserProfileMapper() {
        userDao = new UserDao();
        userProfileValidation = new UserProfileValidation();
    }

    public int update(User user) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(user.getPhoneNumber());
        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            return userDao.update(userEntity.getId(),userEntity );
        }

        return 0;
    }

    public UserEntity modelToEntity(User user){
        UserEntity userEntity = new UserEntity();
        if(user.getPicture() == null){
            userEntity.setPictureUrl("appdata/default.png");
        } else{
            userEntity.setPictureUrl(ImageUtils.storeImage(user));
        }
        userEntity.setUserName(user.getUserName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setSalt(user.getSalt());
        userEntity.setGender(user.getGender());
        userEntity.setCountry(user.getCountry());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setOnlineStatus(user.getOnlineStatus());
        userEntity.setBio(user.getBio());
        return userEntity;
    }
    public User entityToModel(UserEntity userEntity){
        User user = new User();

        user.setUserName(userEntity.getUserName());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setPassword(userEntity.getPassword());
        user.setSalt(userEntity.getSalt());
        user.setEmail(userEntity.getEmail());
        user.setPicture(ImageUtils.loadImage(userEntity.getPictureUrl()));
        user.setPictureExtension(userEntity.getPictureUrl().split("\\.")[1]);
        user.setGender(userEntity.getGender());
        user.setCountry(userEntity.getCountry());
        user.setBirthDate(userEntity.getBirthDate());
        user.setOnlineStatus(userEntity.getOnlineStatus());
        user.setBio(userEntity.getBio());

        return user;
    }
}
