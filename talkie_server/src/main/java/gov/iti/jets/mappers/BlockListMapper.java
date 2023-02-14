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
    private BlockListMapper blockListMapper;

    public BlockListMapper() {
        blockListDao = new BlockListDao();
        userMapper = new UserMapper();
        userDao = new UserDao();
        blockListEntity = new BlockListEntity();
        //blockListMapper = new BlockListMapper();
    }

    public BlockList insert (String phoneNumber ,String blockedUserPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        Optional<UserEntity> blockedUserEntityOptional = userDao.findUserByPhoneNumber(blockedUserPhoneNumber);

        if(userEntityOptional.isPresent() && blockedUserEntityOptional.isPresent()) {

            UserEntity userEntity = userEntityOptional.get();
            UserEntity blockedUserEntity = blockedUserEntityOptional.get();

            blockListEntity.setUser(userEntity);
            blockListEntity.setBlockedUser(blockedUserEntity);

            return blockListMapper.entityToModel(blockListDao.save(blockListEntity));
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

    public List<User> findAllBlockedUsersByUserId(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);

        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            List<UserEntity> userEntityList = blockListDao.findAllBlockedUsersByUserId(userEntity.getId()) ;
            List<User> usersList = new ArrayList<>();

            for(UserEntity user : userEntityList) {
                usersList.add(userMapper.entityToModel(user));
            }

            return  usersList;
        }

        return null;
    }

    public List<User> findAllBlockersByBlockedUserId(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);

        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            List<UserEntity> userEntityList = blockListDao.findAllBlockersByBlockedUserId(userEntity.getId()) ;
            List<User> usersList = new ArrayList<>();

            for(UserEntity user : userEntityList) {
                usersList.add(userMapper.entityToModel(user));
            }

            return  usersList;
        }

        return null;
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
