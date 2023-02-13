package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OnlineStatusControllerSingleton extends UnicastRemoteObject implements OnlineStatusController {

    private static OnlineStatusControllerSingleton instance;
    private ConcurrentMap<String, CallbackController> users = new ConcurrentHashMap<>();
    private OnlineStatusControllerSingleton() throws RemoteException {}
    public static OnlineStatusControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new OnlineStatusControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("OnlineStatusController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }


    @Override
    public void connect(User user, CallbackController callbackController) throws RemoteException {
        users.put(user.getPhoneNumber(), callbackController);
    }

    @Override
    public void disconnect(User user) throws RemoteException {
        users.remove(user.getPhoneNumber());
    }

    @Override
    public void ping() throws RemoteException {
        new Thread(()->{
            while (true){
                users.forEach((key, value)->{
                    try {
                        System.out.println(key);
                        value.respond();
                    } catch (RemoteException e) {
                        System.out.println("[INFO] Connection: " + "["+key+"]" + "disconnected.");
                        users.remove(key);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
