package gov.iti.jets.network.controllers.impl;


import gov.iti.jets.dto.requests.*;
import gov.iti.jets.dto.responses.*;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.network.controllers.FriendRequestController;

import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatService;
import gov.iti.jets.services.FriendRequestServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;


public class FriendRequestControllerSingleton extends UnicastRemoteObject implements FriendRequestController {

    private static FriendRequestControllerSingleton instance;
    public static FriendRequestControllerSingleton getInstance(){
        try {
            if(instance == null){
                instance = new FriendRequestControllerSingleton();
            }
            NetworkManager.getRegistry().rebind("FriendRequestController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    private FriendRequestControllerSingleton() throws RemoteException {}
    @Override
    public SendFriendReqResponse sendFriendRequest (SendFriendReqRequest request) {
        String senderPhoneNumber = request.getSenderPhoneNumber();
        String receiverPhoneNumber = request.getReceiverPhoneNumber();
        FriendRequestServices friendRequestService = new FriendRequestServices();
        RegularChatMapper regularChatMapper = new RegularChatMapper();
        List<RegularChat> chats = regularChatMapper.findAllRegularChatsByPhoneNumber(senderPhoneNumber);
        chats = chats.stream().filter((chat) -> chat.getFirstParticipant().getPhoneNumber().equals(receiverPhoneNumber)).toList();
        if(friendRequestService.findFriendRequestBySenderPhoneNumberAndReceiverPhoneNumber(senderPhoneNumber, receiverPhoneNumber).isPresent()){
            return new SendFriendReqResponse(null, null);
        }
        else if(friendRequestService.findFriendRequestBySenderPhoneNumberAndReceiverPhoneNumber(receiverPhoneNumber, senderPhoneNumber).isPresent()){
            friendRequestService.delete(receiverPhoneNumber, senderPhoneNumber);
            Optional<RegularChat> regularChat = regularChatMapper.insert(senderPhoneNumber, receiverPhoneNumber);
            return new SendFriendReqResponse(null, regularChat.get());
        } else if (chats.size() == 0) {
            Optional<FriendRequest> friendRequest = friendRequestService.insert(senderPhoneNumber, receiverPhoneNumber);
            if(friendRequest.isPresent()){
                return new SendFriendReqResponse(friendRequest.get(), null);
            }
        }
        return new SendFriendReqResponse(null, null);
    }
    @Override
    public CancelFriendRequestResponse cancel(CancelFriendRequest cancelFriendRequest) {
        return new CancelFriendRequestResponse(new FriendRequestServices().cancel(cancelFriendRequest.getUserPhoneNumber(),cancelFriendRequest.getFriendPhoneNumber()));
    }

    @Override
    public RefuseFriendFriendResponse refuse(RefuseFriendRequest refuseFriendRequest) {
        return new RefuseFriendFriendResponse(new FriendRequestServices().refuse(refuseFriendRequest.getUserPhoneNumber(),refuseFriendRequest.getFriendPhoneNumber()));
    }
    @Override
    public AcceptFriendResponse accept(AcceptFriendRequest acceptFriendRequest) {
        return new AcceptFriendResponse(new FriendRequestServices().accept(acceptFriendRequest.getUserPhoneNumber(), acceptFriendRequest.getFriendPhoneNumber()));
    }
    @Override
    public LoadFriendReqResponse getSentFriendRequestByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getSentFriendRequestByUsePhoneNumber(loadFriendReqRequest.getUserPhoneNumber()));
    }

    @Override
    public LoadFriendReqResponse getReceivedFriendReqByUserID (LoadFriendReqRequest loadFriendReqRequest) {
        return new LoadFriendReqResponse(new FriendRequestServices().getReceivedFriendReqByUserPhoneNumber(loadFriendReqRequest.getUserPhoneNumber()));
    }
}
