package gov.iti.jets;

import gov.iti.jets.dto.requests.GetMessagesRequest;
import gov.iti.jets.dto.responses.GetMessagesResponse;
import gov.iti.jets.models.Chat;
import gov.iti.jets.network.controllers.MessageController;
import gov.iti.jets.network.controllers.impl.MessageControllerSingleton;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.MessageService;

import java.rmi.RemoteException;


public class TalkieApplication {
    public static void main(String[] args) {

        NetworkManager.start();



    }
}