package gov.iti.jets.services;

import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.FriendRequestMapper;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.ContactDao;
import gov.iti.jets.persistence.FriendRequestDao;

import java.util.List;
import java.util.stream.Collectors;

public class FriendResquestServices {


    private UserMapper userMapper = new UserMapper();
    private FriendRequestDao friendRequestDao = new FriendRequestDao();
    private ContactDao contactDao = new ContactDao();
    private UserEntity userEntity = new UserEntity();
    private UserEntity friendEntity = new UserEntity();

    private FriendRequestMapper friendRequestMapper = new FriendRequestMapper();

    private FriendRequestEntity friendRequestEntity = new FriendRequestEntity();
    public FriendResquestServices (){}




    FriendResquestServices (UserEntity userEntity, UserEntity friendEntity, FriendRequest friendRequest, FriendRequestDao friendRequestDao, ContactDao contactDao, FriendRequestMapper friendRequestMapper, FriendRequestEntity friendRequestEntity){
        this.friendRequestEntity = friendRequestEntity;
        this.contactDao = contactDao;
        this.friendRequestDao = friendRequestDao;
        this.friendRequestMapper = friendRequestMapper;
        this.friendEntity = friendEntity;
        this.userEntity = userEntity;
        this.userMapper =  userMapper;
    }

    public List<FriendRequest> getReceivedFriendReqByUserID(String userPhoneNumber){
        return friendRequestMapper.getReceivedFriendReqByUserID(userPhoneNumber);

}
    public List<FriendRequest> getSentFriendRequestByUserID (String userPhoneNumber) {
        return friendRequestMapper.getSentFriendRequestByUserID(userPhoneNumber);
    }

    public int save(String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.save(userPhoneNumber, friendPhoneNumber);
    }
    public int refuse (String userPhoneNumber, String friendPhoneNumber) {

        return friendRequestMapper.refuse(userPhoneNumber, friendPhoneNumber);
    }


    public int cancel (String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.cancel(userPhoneNumber, friendPhoneNumber);


    }

}

