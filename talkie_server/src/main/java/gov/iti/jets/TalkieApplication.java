package gov.iti.jets;

import gov.iti.jets.network.manager.NetworkManager;

import static gov.iti.jets.LoadLoginSingleton.IntializeApp;


public class TalkieApplication{
    public static void main(String[] args) {
        NetworkManager.start();
        new Thread(()-> IntializeApp()).start();
   }
}