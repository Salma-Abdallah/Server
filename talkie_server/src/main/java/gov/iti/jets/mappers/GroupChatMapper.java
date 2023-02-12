package gov.iti.jets.mappers;

import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.GroupChat;
import gov.iti.jets.persistence.GroupChatDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.UserGroupDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupChatMapper {

    private final UserMapper userMapper = new UserMapper();
    private GroupChatDao groupChatDao;
    private UserDao userDao;
    private UserGroupDao userGroupDao;

    public GroupChatMapper() {
        this.groupChatDao = new GroupChatDao();
        this.userDao = new UserDao();
        this.userGroupDao = new UserGroupDao();
    }

    public Optional<GroupChat> insert(String phoneNumber, String groupName){
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            Optional<GroupChatEntity> groupChatEntityOptional = groupChatDao.findGroupChatByOwnerIdAndChatName(userEntity.getId(), groupName);
            if(groupChatEntityOptional.isEmpty()){
                GroupChatEntity groupChatEntity = groupChatDao.save(new GroupChatEntity(userEntity.getId(), groupName));
                return Optional.of(new GroupChat(groupChatEntity.getChatId(), userMapper.entityToModel(userEntity), groupName, new ArrayList<>()));
            }
        }
        return Optional.empty();
    }

    public List<GroupChat> findAllGroupChatsByOwnerId(String phoneNumber){
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            List<GroupChatEntity> groupChatEntities = groupChatDao.findAllGroupChatsByOwnerId(userEntity.getId());
            return groupChatEntities.stream().map((groupChatEntity)-> {
                List<UserEntity> userEntities = userGroupDao.findAllUsersByGroupChatId(groupChatEntity.getChatId());
                return new GroupChat(groupChatEntity.getChatId(), userMapper.entityToModel(userEntity), groupChatEntity.getName(),
                                userEntities.stream().map(userMapper::entityToModel).toList());
            }).toList();
        }
        return new ArrayList<>();
    }
    public Optional<GroupChat> findGroupChatByOwnerPhoneNumberAndChatName(String phoneNumber, String groupChatName){
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            Optional<GroupChatEntity> groupChatEntityOptional =groupChatDao.findGroupChatByOwnerIdAndChatName(userEntity.getId(), groupChatName);
            if(groupChatEntityOptional.isPresent()){
                GroupChatEntity groupChatEntity = groupChatEntityOptional.get();
                List<UserEntity> userEntities = userGroupDao.findAllUsersByGroupChatId(groupChatEntity.getChatId());
                return Optional.of(new GroupChat(groupChatEntity.getChatId(), userMapper.entityToModel(userEntity),
                                    groupChatName, userEntities.stream()
                                                                .map(userMapper::entityToModel)
                                                                .toList())
                );
            }
        }
        return Optional.empty();
    }
}