package gov.iti.jets.services;

import gov.iti.jets.entities.BlockListEntity;
import gov.iti.jets.mappers.BlockListMapper;
import gov.iti.jets.models.BlockList;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.BlockListDao;

import java.util.List;

public class BlockListServices {
    private BlockListMapper blockListMapper;
    private BlockListDao blockListDao ;
    private BlockListEntity entity ;
    public BlockListServices() {
        this.blockListMapper= new BlockListMapper();
        blockListDao = new BlockListDao();
        entity = new BlockListEntity();
    }


    public BlockList insert(String phoneNumber ,String blockedUserPhoneNumber){
        return blockListMapper.insert(phoneNumber,blockedUserPhoneNumber);
    }

    public int delete (String phoneNumber ,String blockedUserPhoneNumber) {
        return blockListMapper.delete(phoneNumber,blockedUserPhoneNumber);
    }

    public List<User> findAllBlockedUsersByUserPhoneNumber(String phoneNumber){
        return blockListMapper.findAllBlockedUsersByUserPhoneNumber(phoneNumber);
    }

    public List<User> findAllBlockersByBlockedUserPhoneNumber(String phoneNumber) {
        return blockListMapper.findAllBlockersByBlockedUserPhoneNumber(phoneNumber);
    }
}
