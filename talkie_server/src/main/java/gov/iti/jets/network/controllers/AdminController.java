package gov.iti.jets.network.controllers;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.models.Admin;

import java.rmi.Remote;
import java.util.Optional;

public interface AdminController extends Remote {
    Optional<Admin> getUserByPhoneNumber(String phoneNumber);
    Admin insertAdmin(AdminEntity adminEntity);
}
