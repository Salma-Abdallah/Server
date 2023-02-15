package gov.iti.jets.services;

import gov.iti.jets.mappers.FriendRequestMapper;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;
import java.util.List;

public class FriendRequestServices {


    private FriendRequestMapper friendRequestMapper = new FriendRequestMapper();
    public FriendRequestServices(){}

    public List<FriendRequest> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber){
        return friendRequestMapper.getReceivedFriendReqByUserPhoneNumber(userPhoneNumber);

}
    public List<FriendRequest> getSentFriendRequestByUsePhoneNumber (String userPhoneNumber) {
        return friendRequestMapper.getSentFriendRequestByUserPhoneNumber(userPhoneNumber);
    }

    public RegularChat accept (String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.accept(userPhoneNumber, friendPhoneNumber);
    }

    public boolean refuse (String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.refuse(userPhoneNumber, friendPhoneNumber);
    }

    public boolean cancel (String userPhoneNumber, String friendPhoneNumber) {
        return friendRequestMapper.cancel(userPhoneNumber, friendPhoneNumber);
    }

}

