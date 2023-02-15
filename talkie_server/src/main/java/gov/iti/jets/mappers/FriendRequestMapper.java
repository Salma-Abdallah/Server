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
    private UserDao userDao;
    private UserMapper userMapper;
    private RegularChatDao regularChatDao;

    public FriendRequestMapper() {
        friendRequestDao = new FriendRequestDao();
        userDao = new UserDao();
        userMapper = new UserMapper();
        regularChatDao = new RegularChatDao();
    }

    public List<FriendRequest> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber) {
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
    public List<FriendRequest> getSentFriendRequestByUserPhoneNumber (String userPhoneNumber) {

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
    public RegularChat accept (String senderPhoneNumber, String receiverPhoneNumber) {
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNumber).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNumber).get();
        friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId());
        RegularChatEntity regularChatEntity = regularChatDao.save(new RegularChatEntity(senderUserEntity.getId(), receiverUserEntity.getId()));
        return new RegularChat(regularChatEntity.getId(), userMapper.entityToModel(senderUserEntity), userMapper.entityToModel(receiverUserEntity));
    }

    public boolean refuse (String senderPhoneNumber, String receiverPhoneNumber){
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNumber).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNumber).get();
        if(friendRequestDao.delete(receiverUserEntity.getId(), senderUserEntity.getId()) != 0){
            return true;
        }
        return false;
    }
    public boolean cancel (String senderPhoneNumber, String receiverPhoneNumber) {
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNumber).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNumber).get();
        if(friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId()) != 0){
            return true;
        }
        return false;
    }

    public int delete (String senderPhoneNumber, String receiverPhoneNumber) {
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNumber).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNumber).get();
        return friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId());

    }
    public Optional<FriendRequest> insert(String senderPhoneNumber, String receiverPhoneNumber){
        Optional<UserEntity> senderEntityOptional = userDao.findUserByPhoneNumber(senderPhoneNumber);
        Optional<UserEntity> receiverEntityOptional = userDao.findUserByPhoneNumber(receiverPhoneNumber);
        if(senderEntityOptional.isPresent() && receiverEntityOptional.isPresent()){
            UserEntity senderEntity = senderEntityOptional.get();
            UserEntity receiverEntity = receiverEntityOptional.get();
            friendRequestDao.save(senderEntity.getId(), receiverEntity.getId());
            FriendRequestEntity friendRequestEntity = friendRequestDao.findFriendRequestBySenderIdAndReceiverId(senderEntity.getId(), receiverEntity.getId()).get();
            return Optional.of(new FriendRequest(userMapper.entityToModel(friendRequestEntity.getSender()),
                                                    userMapper.entityToModel(friendRequestEntity.getReceiver()),
                                                    friendRequestEntity.isStatus(), friendRequestEntity.getSentAt()));
        }
        return Optional.empty();
    }
    public Optional<FriendRequest> findFriendRequestBySenderPhoneNumberAndReceiverPhoneNumber (String senderPhoneNumber, String receiverPhoneNumber){
        Optional<UserEntity> senderEntityOptional = userDao.findUserByPhoneNumber(senderPhoneNumber);
        Optional<UserEntity> receiverEntityOptional = userDao.findUserByPhoneNumber(receiverPhoneNumber);
        if(senderEntityOptional.isPresent() && receiverEntityOptional.isPresent()){
            UserEntity senderEntity = senderEntityOptional.get();
            UserEntity receiverEntity = receiverEntityOptional.get();
            Optional<FriendRequestEntity> friendRequestEntityOptional = friendRequestDao.findFriendRequestBySenderIdAndReceiverId(senderEntity.getId(), receiverEntity.getId());
            if(friendRequestEntityOptional.isPresent()){
                FriendRequestEntity friendRequestEntity = friendRequestEntityOptional.get();
                return Optional.of(new FriendRequest(userMapper.entityToModel(friendRequestEntity.getSender()),
                        userMapper.entityToModel(friendRequestEntity.getReceiver()),
                        friendRequestEntity.isStatus(), friendRequestEntity.getSentAt()));
            }
        }
        return Optional.empty();
    }
    public FriendRequest entityToModel(FriendRequestEntity friendRequestEntity) {

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderId(userMapper.entityToModel(friendRequestEntity.getSender()));
        friendRequest.setReceiverId(userMapper.entityToModel(friendRequestEntity.getReceiver()));
        friendRequest.setStatus(friendRequestEntity.isStatus());
        friendRequest.setSentAt(friendRequestEntity.getSentAt());
        return friendRequest;
    }

    public FriendRequestEntity modelToEntity(FriendRequest friendRequest) {
        FriendRequestEntity friendRequestEntity = new FriendRequestEntity();
        friendRequestEntity.setSender(userMapper.modelToEntity(friendRequest.getSenderId()));
        friendRequestEntity.setReceiver(userMapper.modelToEntity(friendRequest.getReceiverId()));
        friendRequestEntity.setStatus(friendRequest.isStatus());
        friendRequestEntity.setSentAt(friendRequest.getSentAt());
        return friendRequestEntity;
    }
}




