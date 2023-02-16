package gov.iti.jets.mappers;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.Admin;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.AdminDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.utils.ImageUtils;

import java.util.Optional;

public class AdminMapper {
    public AdminEntity modelToEntity(Admin admin){
        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setAdminName(admin.getAdminName());
        adminEntity.setPhoneNumber(admin.getPhoneNumber());
        adminEntity.setPassword(admin.getPassword());

        return adminEntity;
    }
    public Admin entityToModel(AdminEntity adminEntity){
        Admin admin = new Admin();

        admin.setAdminName(adminEntity.getAdminName());
        admin.setPhoneNumber(adminEntity.getPhoneNumber());
        admin.setPassword(adminEntity.getPassword());

        return admin;
    }
}
