package gov.iti.jets.mappers;

import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.models.FriendRequest;
import gov.iti.jets.models.RegularChat;
import gov.iti.jets.models.User;
import gov.iti.jets.persistence.RegularChatDao;
import gov.iti.jets.persistence.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegularChatMapper {

    private final RegularChatDao regularChatDao;
    private UserDao userDao;
    private UserMapper userMapper;

    public RegularChatMapper() {
        this.userDao = new UserDao();
        this.regularChatDao = new RegularChatDao();
        this.userMapper = new UserMapper();
    }

    public Optional<RegularChat> insert(String firstPhoneNumber, String secondPhoneNumber) {
        Optional<UserEntity> firstParticipantOptional = userDao.findUserByPhoneNumber(firstPhoneNumber);
        Optional<UserEntity> secondParticipantOptional = userDao.findUserByPhoneNumber(secondPhoneNumber);
        if (firstParticipantOptional.isPresent() && secondParticipantOptional.isPresent()) {
            UserEntity firstParticipant = firstParticipantOptional.get();
            UserEntity secondParticipant = secondParticipantOptional.get();
            List<RegularChatEntity> regularChatEntities = regularChatDao.findAllRegChatsByUserID(firstParticipant.getId());

            long count = regularChatEntities.stream().filter((entity) ->
                    secondParticipant.getId() == entity.getFirstParticipantId() || secondParticipant.getId() == entity.getSecondParticipantId()
            ).count();

            if (count == 0) {
                RegularChatEntity regularChatEntity = regularChatDao.save(new RegularChatEntity(firstParticipant.getId(), secondParticipant.getId()));
                return Optional.of(new RegularChat(regularChatEntity.getId(),
                        userMapper.entityToModel(firstParticipant),
                        userMapper.entityToModel(secondParticipant)));
            } else return Optional.empty();
        }
        return Optional.empty();
    }

    public List<RegularChat> findAllRegularChatsByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(phoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return regularChatDao.findAllRegChatsByUserID(userEntity.getId())
                    .stream()
                    .map((regularChatEntity) -> {
                        UserEntity firstParticipant = userDao.findUserById(regularChatEntity.getFirstParticipantId()).get();
                        UserEntity secondParticipant = userDao.findUserById(regularChatEntity.getSecondParticipantId()).get();
                        if (phoneNumber.equals(firstParticipant.getPhoneNumber())) {
                            return new RegularChat(regularChatEntity.getId(),
                                    userMapper.entityToModel(secondParticipant));
                        } else {
                            return new RegularChat(regularChatEntity.getId(),
                                    userMapper.entityToModel(firstParticipant));
                        }
                    })
                    .toList();
        } else return new ArrayList<>();
    }
    public int delete(String firstPhoneNumber, String secondPhoneNumber) {
        Optional<UserEntity> firstParticipantOptional = userDao.findUserByPhoneNumber(firstPhoneNumber);
        Optional<UserEntity> secondParticipantOptional = userDao.findUserByPhoneNumber(secondPhoneNumber);
        if (firstParticipantOptional.isPresent() && secondParticipantOptional.isPresent()) {
            UserEntity firstParticipant = firstParticipantOptional.get();
            UserEntity secondParticipant = secondParticipantOptional.get();
            Optional<RegularChatEntity> regularChatEntityOptional = regularChatDao.findRegularChatByParticipantsIds(firstParticipant.getId(), secondParticipant.getId());
            if (regularChatEntityOptional.isPresent()) {
                return regularChatDao.delete(regularChatEntityOptional.get().getId());
            }
            return 0;
        }
        return 0;
    }

}
