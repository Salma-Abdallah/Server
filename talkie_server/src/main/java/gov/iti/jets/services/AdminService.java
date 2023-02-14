package gov.iti.jets.services;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.mappers.AdminMapper;
import gov.iti.jets.models.Admin;
import gov.iti.jets.persistence.AdminDao;

import java.util.Optional;

public class AdminService {
    private AdminMapper adminMapper;
    private AdminDao adminDao;


    public Optional<Admin> getAdminByPhoneNumber(String phoneNumber){
        Optional<AdminEntity> adminEntity = adminDao.findAdminByPhoneNumber(phoneNumber);
        if(adminEntity.isPresent()){
            return Optional.of(adminMapper.entityToModel(adminEntity.get()));
        }
        return Optional.empty();
    }

    public Admin insertAdmin(AdminEntity adminEntity){
        return adminMapper.entityToModel(adminDao.save(adminEntity));
    }
}
