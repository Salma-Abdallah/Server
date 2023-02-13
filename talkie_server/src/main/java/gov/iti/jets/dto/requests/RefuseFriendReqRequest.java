public class AcceptFriendRequest implements Serializable{


    private static final long serialVersionUID = -3339255849433394L;

    private String friendPhoneNumber;

    public AcceptFriendRequest(String friendPhoneNumber, String userPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
        this.userPhoneNumber = userPhoneNumber;
    }

    private String userPhoneNumber;

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }


    public String toString() {
        return "AcceptFriendRequest" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}



