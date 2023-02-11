package gov.iti.jets.dto.requests;


import java.io.Serializable;

public class BlockListRequest implements Serializable {
    private static final long serialVersionUID = -754635156489433394L;

    private String phoneNumber;
    public BlockListRequest(){ };

    public BlockListRequest(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    @Override
    public String toString() {
        return "BlockListRequest{"+
                "phoneNumber='"+ phoneNumber + '\'';
    }

}
