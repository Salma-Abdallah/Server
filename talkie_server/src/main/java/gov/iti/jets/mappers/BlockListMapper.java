package gov.iti.jets.mappers;

import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.persistence.BlockListDao;

public class BlockListMapper {
    private BlockListDao blockListDao;
    private UserMapper userMapper;

    public BlockListMapper() {
        blockListDao = new BlockListDao();
    }

    public BlockListEntity modelToEntity (BlockList blockList) {
        BlockListEntity blockListEntity = new BlockListEntity();
        userMapper = new UserMapper();

        blockListEntity.setUser(userMapper.modelToEntity(blockList.getUser()));
        blockListEntity.setBlockedUser(userMapper.modelToEntity(blockList.getBlockedUser()));

        return blockListEntity;
    }

    public BlockList entityToModel (BlockListEntity blockListEntity ) {
        BlockList blockList = new BlockList();
        userMapper = new UserMapper();

        blockList.setUser(userMapper.entityToModel(blockListEntity.getUser()));
        blockList.setBlockedUser(userMapper.entityToModel(blockListEntity.getBlockedUser()));

        return blockList;
    }


}
