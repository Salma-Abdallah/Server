package gov.iti.jets.dto.responses;

import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.mappers.BlockListMapper;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.BlockListDao;

public class BlockListResponse {

    private Integer userId;
    private Integer blockedUserId;

    private BlockListMapper blockListMapper;
    private BlockListDao blockListDao;

    public BlockListResponse() {}

    public BlockListResponse(Integer userId ,Integer blockedUserId) {
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId ;
    }

    public Integer getUserId () {
        return userId;
    }

    public void setBlockedUserId(Integer blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    public Integer getBlockedUserId() {
        return blockedUserId;
    }

    public String addToBlockList(BlockList blockList, User blockedUser ) {
        BlockListEntity userEntity = blockListMapper.modelToEntity(blockList);


        //blockListDao.save(blockedEntity.getId() );

        return "";
    };
}
