package gov.iti.jets;

import gov.iti.jets.persistence.MessageDao;


public class TalkieApplication {
    public static void main(String[] args) {

//        NetworkManager.start();

//        ChatService chatService = new ChatService();
//        chatService.getAllChats("01278158298").forEach(System.out::println);
//        UserGroupMapper userGroupMapper = new UserGroupMapper();
//        userGroupMapper.addUserToGroupChat("01278158298", "a70d9e66-a91d-11ed-9d48-94b86d574bf8");
        MessageDao messageDao = new MessageDao();
        System.out.println(messageDao.findMessagesByChatID("83734688-1d3c-4943-8c86-21b1e72af8ae"));
    }
}