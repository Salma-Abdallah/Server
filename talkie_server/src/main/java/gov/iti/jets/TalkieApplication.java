package gov.iti.jets;

import gov.iti.jets.connection.DataSourceSingleton;
import gov.iti.jets.entities.*;
import gov.iti.jets.models.Contact;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TalkieApplication {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.save(new UserEntity(1, "KhaledMHisham", "01278158298",
//                "khaled.m.hisham@gmail.com", "shitters", "M", "Egypt",
//                LocalDate.of(1996, 10, 17), "Available", "Fully dedicated dev",
//                "/home/khaled/Desktop/ussr.jpg", Timestamp.valueOf(LocalDateTime.now()), "SADSASAD"));
//        System.out.println(userDao.findUserById(17));

//        List<UserEntity> usersEntities = userDao.findAll();
//        System.out.println(usersEntities.get(0));
//        System.out.println(userDao.findUserById(17));
//        UserEntity mahmoud = new UserEntity(1, "asasdadasdas", "01258298",
//                "khaled.m.asam@gmail.com", "shi", "M", "Egypt",
//                LocalDate.of(1996, 10, 17), "Avaiale", "Fully dedicdasded dev",
//                "/home/khaled/Desktewqweqeqwwop/ussr.jpg", Timestamp.valueOf(LocalDateTime.now()), "SADSAqwqqwSAD");
//        userDao.update(17, mahmoud);
//        System.out.println(userDao.findUserById(17));
        GroupChatDao groupChatDao = new GroupChatDao();
//        groupChatDao.save(new GroupChatEntity(UUID.randomUUID().toString(), 20, "JetS Group"));
//        groupChatDao.save(new GroupChatEntity(UUID.randomUUID().toString(), 21, "Sub Homie"));
//        groupChatDao.save(new GroupChatEntity(UUID.randomUUID().toString(), 24, "Really ?"));
//        System.out.println(groupChatDao.findAllGroupChatsByOwnerId(22));

//        Optional<GroupChatEntity> optional = groupChatDao.findGroupChatByOwnerIdAndChatName(21, "Sub Homie");
//        groupChatDao.delete(optional.get().getChatId());


//        ContactDao contactDao = new ContactDao();
//        contactDao.save(new ContactEntity(17, 22));
//        System.out.println(contactDao.getContactsByUserID(22));
//        contactDao.delete(new ContactEntity(22, 17));
//        FriendRequestDao friendRequestDao = new FriendRequestDao();
//        friendRequestDao.save(new FriendRequestEntity(17, 22));
//        friendRequestDao.save(new FriendRequestEntity(20, 22));
//        friendRequestDao.save(new FriendRequestEntity(22,  17));
//        friendRequestDao.save(new FriendRequestEntity(20, 17));
//        System.out.println(friendRequestDao.findSentFriendRequestByUserID(20));
//        friendRequestDao.delete(new FriendRequestEntity(20, 22));
//        BlockListDao blockListDao = new BlockListDao();
//        blockListDao.save(new BlockListEntity(17, 20));
//        blockListDao.save(new BlockListEntity(17, 22));
//        System.out.println(blockListDao.findAllBlockedUsersByUserId(17));
//        System.out.println(blockListDao.findAllBlockersByBlockedUserId(22));
    }
}