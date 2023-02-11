package gov.iti.jets.mappers;

import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.FriendRequestDao;
import gov.iti.jets.persistence.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendRequestMapper {


    private FriendRequestDao friendRequestDao;
    private UserDao userDao;

    public FriendRequestMapper() {
        friendRequestDao = new FriendRequestDao();
        userDao = new UserDao();

    }

    public List<FriendRequest> getReceivedFriendReqByUserID(String userPhoneNumber) {

        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if(userEntityOptional.isPresent()) {

            UserEntity userEntity = userEntityOptional.get();
            List<FriendRequestEntity> friendEntities = friendRequestDao.findReceivedFriendRequestByUserID(userEntity);
            List<FriendRequest> friendRequestList = new ArrayList<>();
            for (FriendRequestEntity friendRequestEntity : friendEntities) {
                friendRequestList.add(entityToModel(friendRequestEntity));
            }
            return friendRequestList;

        }
        return null;
    }
    public List<FriendRequest> getSentFriendRequestByUserID (String userPhoneNumber) {

        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            List<FriendRequestEntity> friendEntities = friendRequestDao.findSentFriendRequestByUserID(userEntity);
            List<FriendRequest> friendRequestList = new ArrayList<>();
            for (FriendRequestEntity friendRequestEntity : friendEntities) {
                friendRequestList.add(entityToModel(friendRequestEntity));

            }
            return friendRequestList;
        }
        return null;
    }


    public int save(String userPhoneNumber, String friendPhoneNumber) {

        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if(friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                return friendRequestDao.save(userEntity.getId(),friendEntity.getId());
            }
        }
        return 0;
    }
    public int refuse (String userPhoneNumber, String friendPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if (friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                return friendRequestDao.refuse(userEntity.getId(), friendEntity.getId());
            }
        }
        return 0;
    }
    public int cancel (String userPhoneNumber, String friendPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if (friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                return friendRequestDao.cancel(userEntity.getId(), friendEntity.getId());
            }
        }
        return 0;
    }

    public FriendRequest entityToModel(FriendRequestEntity friendRequestEntity) {

        FriendRequest friendRequest = new FriendRequest();

        friendRequest.setStatus(friendRequestEntity.isStatus());
        friendRequest.setSentAt(friendRequestEntity.getSentAt());
        return friendRequest;
    }

    public FriendRequestEntity modelToEntity(FriendRequest friendRequest) {
        FriendRequestEntity friendRequestEntity = new FriendRequestEntity();
        friendRequestEntity.setStatus(friendRequest.isStatus());
        friendRequestEntity.setSentAt(friendRequest.getSentAt());


        return friendRequestEntity;
    }
}




