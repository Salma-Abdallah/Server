package gov.iti.jets.dto.requests;


public class BlockListRequest  {
    //private static final long serialVersionUID = L;

    private String userPhoneNumber;
    private String blockedUserPhoneNumber;

    public BlockListRequest() { };


    public BlockListRequest(String userPhoneNumber){
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getBlockedUserPhoneNumber() {
        return blockedUserPhoneNumber;
    }

    public void setBlockedUserPhoneNumber(String blockedUserPhoneNumber) {
        this.blockedUserPhoneNumber = blockedUserPhoneNumber;
    }

    @Override
    public String toString() {
        return "BlockListRequest{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", blockedUserPhoneNumber='" + blockedUserPhoneNumber + '\'' +
                '}';
    }
}
