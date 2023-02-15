package gov.iti.jets;

import gov.iti.jets.dto.requests.SendFriendReqRequest;
import gov.iti.jets.network.controllers.impl.BlockListControllerSingleton;
import gov.iti.jets.network.controllers.impl.FriendRequestControllerSingleton;
import gov.iti.jets.network.manager.NetworkManager;

public class TalkieApplication {
    public static void main(String[] args) {

        NetworkManager.start();
        System.out.println(BlockListControllerSingleton.getInstance());
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}