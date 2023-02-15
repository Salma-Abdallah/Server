package gov.iti.jets.services;

import gov.iti.jets.mappers.FriendRequestMapper;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;
import java.util.List;
import java.util.Optional;

public class FriendRequestServices {


    private FriendRequestMapper friendRequestMapper = new FriendRequestMapper();
    public FriendRequestServices(){}

    public List<FriendRequest> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber){
        return friendRequestMapper.getReceivedFriendReqByUserPhoneNumber(userPhoneNumber);

}
    public List<FriendRequest> getSentFriendRequestByUsePhoneNumber (String userPhoneNumber) {
        return friendRequestMapper.getSentFriendRequestByUserPhoneNumber(userPhoneNumber);
    }
    public Optional<FriendRequest> findFriendRequestBySenderPhoneNumberAndReceiverPhoneNumber (String senderPhoneNumber, String receiverPhoneNumber){
        return friendRequestMapper.findFriendRequestBySenderPhoneNumberAndReceiverPhoneNumber(senderPhoneNumber, receiverPhoneNumber);
    }
    public RegularChat accept (String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.accept(userPhoneNumber, friendPhoneNumber);
    }

    public String refuse (String userPhoneNumber, String friendPhoneNumber) {
        if(friendRequestMapper.refuse(userPhoneNumber, friendPhoneNumber)){
            return friendPhoneNumber;
        }
        return null;
    }

    public String cancel (String userPhoneNumber, String friendPhoneNumber) {
        if(friendRequestMapper.cancel(userPhoneNumber, friendPhoneNumber)){
            return friendPhoneNumber;
        }
        return null;
    }
    public int delete (String senderPhoneNumber, String receiverPhoneNumber){
        return friendRequestMapper.delete(senderPhoneNumber, receiverPhoneNumber);
    }
    public Optional<FriendRequest> insert(String senderPhoneNumber, String receiverPhoneNumber){
        return friendRequestMapper.insert(senderPhoneNumber, receiverPhoneNumber);
    }
}
