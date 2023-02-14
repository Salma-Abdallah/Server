package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.models.Admin;
import gov.iti.jets.network.controllers.AdminController;
import gov.iti.jets.network.controllers.MessageController;

import java.rmi.RemoteException;

public class AdminControllerSingleton implements AdminController {

    @Override
    public Admin register(AdminEntity adminEntity) {
        return null;
    }

    @Override
    public void sendMessage(MessageController messageController) throws RemoteException {
        messageController.send();
        messageController.receive();
    }
}
