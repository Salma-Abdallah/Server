package gov.iti.jets.mappers;

import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.FriendRequestDao;
import gov.iti.jets.utils.ImageUtils;

import java.util.List;
import java.util.Optional;

public class FriendRequestMapper {


    private FriendRequestDao friendRequestDao;

    public FriendRequestMapper() {
        friendRequestDao = new FriendRequestDao();
    }

    public List<FriendRequestEntity> getReceivedFriendReqByUserID(UserEntity receiver) {
        return friendRequestDao.findReceivedFriendRequestByUserID(receiver);
    }


    public List<FriendRequestEntity> getSentFriendRequestByUserID(UserEntity user) {

        return friendRequestDao.findSentFriendRequestByUserID(user);
    }


    public int save(Integer senderId, Integer receiverId) {

        return friendRequestDao.save(senderId, receiverId);
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




