package gov.iti.jets.services;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.mappers.AdminMapper;
import gov.iti.jets.models.Admin;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.impl.OnlineStatusControllerSingleton;
import gov.iti.jets.persistence.AdminDao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminService {
    private AdminMapper adminMapper;
    private AdminDao adminDao;

    public AdminService() {
        adminMapper = new AdminMapper();
        adminDao = new AdminDao();
    }

    public Optional<Admin> getAdminByPhoneNumber(String phoneNumber){
        Optional<AdminEntity> adminEntity = adminDao.findAdminByPhoneNumber(phoneNumber);
        if(adminEntity.isPresent()){
            return Optional.of(adminMapper.entityToModel(adminEntity.get()));
        }
        return Optional.empty();
    }

}
