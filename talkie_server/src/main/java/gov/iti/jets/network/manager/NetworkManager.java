package gov.iti.jets.network.manager;

import gov.iti.jets.network.controllers.impl.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

public class NetworkManager {

    private static Registry registry;
    private static int port =2400;

    static {
        try {
            registry = LocateRegistry.createRegistry(port);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void start(){
        try {
            AuthenticationControllerSingleton.getInstance();
            ChatControllerSingleton.getInstance();
            MessageControllerSingleton.getInstance();
            OnlineStatusControllerSingleton.getInstance().ping();
            FriendRequestControllerSingleton.getInstance();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void stop(){
        try {
            List<String> controllers = Arrays.asList(registry.list());
            for (String controller : controllers) {
                registry.unbind(controller);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Registry getRegistry() {
        return registry;
    }
}
