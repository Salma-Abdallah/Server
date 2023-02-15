package gov.iti.jets.mappers;

import gov.iti.jets.models.User;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.utils.ImageUtils;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserMapper {
    private UserDao userDao;

    public UserMapper() {
        userDao = new UserDao();
    }

    public Optional<UserEntity> getUserByPhoneNumber(String phoneNumber){
        return userDao.findUserByPhoneNumber(phoneNumber);
    }

    public Optional<UserEntity> getUserByEmail(String email){
        return userDao.findUserByEmail(email);
    }

    public User save(User user){
        UserEntity entity = modelToEntity(user);
        entity.setBio(null);
        entity.setOnlineStatus("Available");
        entity.setSalt(user.getSalt());
        return entityToModel(userDao.save(entity));
    }
    public int updateStatusByUserPhoneNumber(String phoneNumber, String onlineStatus){
        return userDao.updateStatusByUserPhoneNumber(phoneNumber, onlineStatus);
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
