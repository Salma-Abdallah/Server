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

    public List<User> findAllBlockedUsersByUserId(String phoneNumber){
        return blockListMapper.findAllBlockedUsersByUserId(phoneNumber);
    }

    public List<User> findAllBlockersByBlockedUserId(String phoneNumber) {
        return blockListMapper.findAllBlockersByBlockedUserId(phoneNumber);
    }
}
