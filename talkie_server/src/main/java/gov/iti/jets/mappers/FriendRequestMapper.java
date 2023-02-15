package gov.iti.jets.mappers;

import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.ChatDao;
import gov.iti.jets.persistence.FriendRequestDao;
import gov.iti.jets.persistence.RegularChatDao;
import gov.iti.jets.persistence.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendRequestMapper {


    private FriendRequestDao friendRequestDao;
    private ChatDao chatDao;
    private RegularChatDao regularChatDao;
    private RegularChatMapper regularChatMapper;
    private RegularChatEntity regularChatEntity;
    private RegularChat regularChat;
    private UserDao userDao;
    private UserMapper userMapper;

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


    public RegularChat accept (String userPhoneNumber, String friendPhoneNumber) {

        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if(friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                 regularChatEntity.getFirstParticipantId();
                 regularChatEntity.getSecondParticipantId();
                RegularChatEntity regularChatEntity1 = regularChatDao.save(regularChatEntity);
                return regularChatEntity1.getRegularChat();
            }
        }
        return null;
    }

    public String refuse (String userPhoneNumber, String friendPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if (friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                int result = friendRequestDao.refuse(userEntity.getId(), friendEntity.getId());
                if (result != -1) {
                    return "refused successfully";
                }
            }
        }
        return null;
    }

    public String cancel (String userPhoneNumber, String friendPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            Optional<UserEntity> friendEntityOptional = userDao.findUserByPhoneNumber(friendPhoneNumber);
            if (friendEntityOptional.isPresent()) {
                UserEntity friendEntity = friendEntityOptional.get();
                int result = friendRequestDao.cancel(userEntity.getId(), friendEntity.getId());
                if (result != -1) {
                    return "request deleted successfully";
                }
            }
        }
        return "";
    }



    public FriendRequest entityToModel(FriendRequestEntity friendRequestEntity) {

        FriendRequest friendRequest = new FriendRequest();

        friendRequest.setStatus(friendRequestEntity.isStatus());
        friendRequest.setSentAt(friendRequestEntity.getSentAt());
        friendRequest.setSenderId(userMapper.entityToModel(friendRequestEntity.getSender()));
        friendRequest.setReceiverId(userMapper.entityToModel(friendRequestEntity.getReceiver()));
        return friendRequest;
    }

    public FriendRequestEntity modelToEntity(FriendRequest friendRequest) {
        FriendRequestEntity friendRequestEntity = new FriendRequestEntity();
        friendRequestEntity.setStatus(friendRequest.isStatus());
        friendRequestEntity.setSentAt(friendRequest.getSentAt());


        return friendRequestEntity;
    }
}




