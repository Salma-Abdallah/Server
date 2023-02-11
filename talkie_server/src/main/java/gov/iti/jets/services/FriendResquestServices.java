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




    FriendResquestServices (UserEntity userEntity, UserEntity friendEntity, FriendRequest friendRequest, FriendRequestDao friendRequestDao, ContactDao contactDao, FriendRequestMapper friendRequestMapper, FriendRequestEntity friendRequestEntity){
        this.friendRequestEntity = friendRequestEntity;
        this.contactDao = contactDao;
        this.friendRequestDao = friendRequestDao;
        this.friendRequestMapper = friendRequestMapper;
        this.friendEntity = friendEntity;
        this.userEntity = userEntity;
        this.userMapper =  userMapper;
    }

    public String sendFriendRequest(User user, User friend) {
        UserEntity userEntity = userMapper.modelToEntity(user);
        UserEntity friendEntity = userMapper.modelToEntity(friend);



        if (friendRequestDao.findReceivedFriendRequestByUserID(userEntity).contains(friendEntity)) {
            friendRequestDao.delete(friendEntity.getId(), userEntity.getId());
            contactDao.save(userEntity.getId(), friendEntity.getId());
            return "Added to your contacts";
        } else {
            friendRequestDao.save(userEntity.getId(), friendEntity.getId());
            return "Send Friend Request";
        }
    }
    public String acceptFriendRequest (User user, User friend){

        UserEntity userEntity = userMapper.modelToEntity(user);
        UserEntity friendEntity = userMapper.modelToEntity(friend);
        friendRequestDao.delete(friendEntity.getId(), userEntity.getId());
        contactDao.save(userEntity.getId(), friendEntity.getId());
        return "Accepted Friend Request";
    }
    public String refuseFriendRequest (User user, User friend){

        UserEntity userEntity = userMapper.modelToEntity(user);
        UserEntity friendEntity = userMapper.modelToEntity(friend);

        friendRequestDao.delete(friendEntity.getId(), userEntity.getId());
        return "refused Friend Request";
    }

    public List<FriendRequest> findAllFriendRequests(User receiver) {
        UserEntity receiverEntity = userMapper.modelToEntity(receiver);


        List<FriendRequestEntity> friendRequestEntities = friendRequestDao.findReceivedFriendRequestByUserID(receiverEntity);

        if (friendRequestDao.updateStatus(receiverEntity) > 0) {
            List<FriendRequest> friendRequest = friendRequestEntities.stream().map(request ->
                    friendRequestMapper.entityToModel(friendRequestEntity)).collect(Collectors.toList());
            return friendRequest;
        }
        //to be asked
        return null;

    }
    public String cancelRequest(User user, User friend) {
        UserEntity userEntity = userMapper.modelToEntity(user);
        UserEntity friendEntity = userMapper.modelToEntity(friend);

        friendRequestDao.delete(friendEntity.getId(), userEntity.getId());
        return "Canceled Successfully";
    }
}
