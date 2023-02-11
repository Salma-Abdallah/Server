package gov.iti.jets.services;

import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.mappers.BlockListMapper;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.BlockListDao;
import gov.iti.jets.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockListServices {
    private BlockListMapper blockListMapper;
    private BlockListDao blockListDao = new BlockListDao();
    private BlockListEntity entity = new BlockListEntity();
    public BlockListServices() {
        this.blockListMapper= new BlockListMapper();
    }


    public BlockList save (BlockList blockList) {
        BlockListEntity blockListEntity = blockListMapper.modelToEntity(blockList);

        return blockListMapper.entityToModel(blockListDao.save(blockListEntity));
    }

    public int delete (BlockList blockList) {
        BlockListEntity blockListEntity = blockListMapper.modelToEntity(blockList);

        return blockListDao.delete(blockListEntity);
    }

    public List<User> findAllBlockedUsersByUserId (Integer userId) {
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = blockListDao.findAllBlockedUsersByUserId(userId);
        UserMapper userMapper = new UserMapper();

        for(UserEntity user:userEntities) {
            users.add(userMapper.entityToModel(user));
        }

        return users;
    }

    public List<User> findAllBlockersByBlockedUserId (Integer userId) {
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = blockListDao.findAllBlockersByBlockedUserId(userId);
        UserMapper userMapper = new UserMapper();

        for(UserEntity user:userEntities) {
            users.add(userMapper.entityToModel(user));
        }

        return users;
    }
}
