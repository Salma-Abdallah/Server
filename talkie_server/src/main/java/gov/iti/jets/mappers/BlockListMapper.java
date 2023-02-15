package gov.iti.jets.mappers;

import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.BlockListDao;
import gov.iti.jets.persistence.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockListMapper {
    private BlockListDao blockListDao;
    private UserMapper userMapper;
    private UserDao userDao;
    private BlockListEntity blockListEntity;

    public BlockListMapper() {
        blockListDao = new BlockListDao();
        userMapper = new UserMapper();
        userDao = new UserDao();
        blockListEntity = new BlockListEntity();
    }

    public BlockList insert (String phoneNumber ,String blockedUserPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        Optional<UserEntity> blockedUserEntityOptional = userDao.findUserByPhoneNumber(blockedUserPhoneNumber);

        if(userEntityOptional.isPresent() && blockedUserEntityOptional.isPresent()) {

            UserEntity userEntity = userEntityOptional.get();
            UserEntity blockedUserEntity = blockedUserEntityOptional.get();

            blockListEntity.setUser(userEntity);
            blockListEntity.setBlockedUser(blockedUserEntity);

            return entityToModel(blockListDao.save(blockListEntity));
        }

        return null;
    }

    public int delete (String phoneNumber ,String blockedUserPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        Optional<UserEntity> blockedUserEntityOptional = userDao.findUserByPhoneNumber(blockedUserPhoneNumber);

        if(userEntityOptional.isPresent() && blockedUserEntityOptional.isPresent()) {

            UserEntity userEntity = userEntityOptional.get();
            UserEntity blockedUserEntity = blockedUserEntityOptional.get();

            blockListEntity.setUser(userEntity);
            blockListEntity.setBlockedUser(blockedUserEntity);

            return blockListDao.delete(blockListEntity);
        }

        return 0;
    }

    public List<User> findAllBlockedUsersByUserPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        List<User> usersList = new ArrayList<>();
        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            List<UserEntity> userEntityList = blockListDao.findAllBlockedUsersByUserId(userEntity.getId()) ;


            for(UserEntity user : userEntityList) {
                usersList.add(userMapper.entityToModel(user));
            }

            return  usersList;
        }

        return usersList;
    }

    public List<User> findAllBlockersByBlockedUserPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        List<User> usersList = new ArrayList<>();
        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            List<UserEntity> userEntityList = blockListDao.findAllBlockersByBlockedUserId(userEntity.getId()) ;

            for(UserEntity user : userEntityList) {
                usersList.add(userMapper.entityToModel(user));
            }

            return  usersList;
        }

        return usersList;
    }

    public boolean isUserBlocked(String senderPhoneNumber, String receiverPhoneNumber){
        List<User> users = findAllBlockersByBlockedUserPhoneNumber(senderPhoneNumber).stream()
                .filter((user)-> user.getPhoneNumber().equals(receiverPhoneNumber)
                ).toList();
        return users.size() != 0;
    }

    public BlockListEntity modelToEntity (BlockList blockList) {
        BlockListEntity blockListEntity = new BlockListEntity();

        blockListEntity.setUser(userMapper.modelToEntity(blockList.getUser()));
        blockListEntity.setBlockedUser(userMapper.modelToEntity(blockList.getBlockedUser()));

        return blockListEntity;

    }


    public BlockList entityToModel (BlockListEntity blockListEntity ) {
        BlockList blockList = new BlockList();

        blockList.setUser(userMapper.entityToModel(blockListEntity.getUser()));
        blockList.setBlockedUser(userMapper.entityToModel(blockListEntity.getBlockedUser()));

        return blockList;
    }


}
