package gov.iti.jets.mappers;

import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.GroupChatDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.UserGroupDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserGroupMapper {

    private UserMapper userMapper;
    private UserDao userDao;
    private UserGroupDao userGroupDao;
    private GroupChatDao groupChatDao;

    public UserGroupMapper() {
        this.userMapper = new UserMapper();
        this.userDao = new UserDao();
        this.userGroupDao = new UserGroupDao();
        this.groupChatDao = new GroupChatDao();
    }

    public List<User> findAllUsersByGroupChatId(String groupChatId) {
        List<UserEntity> userEntities = userGroupDao.findAllUsersByGroupChatId(groupChatId);
        return userEntities.stream().map(userEntity -> userMapper.entityToModel(userEntity)).toList();
    }
    public Optional<User> addUserToGroupChat(String phoneNumber, String groupChatId){
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            List<GroupChatEntity> groups = groupChatDao.findAllGroupChatsByOwnerId(userEntity.getId());
            for(GroupChatEntity group : groups){
                if(group.getChatId().equals(groupChatId)){
                    return Optional.empty();
                }
            }
            userGroupDao.addUserToGroupChat(userEntity.getId(), groupChatId);
            return Optional.of(userMapper.entityToModel(userEntity));
        }
        return Optional.empty();
    }

    public List<GroupChat> findAllGroupChatsByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            List<GroupChatEntity> groupChatEntities = userGroupDao.findAllGroupChatsByUserID(userEntity.getId());
            return groupChatEntities.stream().map((groupChatEntity) -> {
                List<UserEntity> userEntities = userGroupDao.findAllUsersByGroupChatId(groupChatEntity.getChatId());
                return new GroupChat(groupChatEntity.getChatId(),
                                        userMapper.entityToModel(userDao.findUserById(groupChatEntity.getOwnerId()).get()),
                                        groupChatEntity.getName(),
                                        userEntities.stream().map(userMapper::entityToModel).toList());
            }).toList();
        }
        return new ArrayList<>();
    }
}
