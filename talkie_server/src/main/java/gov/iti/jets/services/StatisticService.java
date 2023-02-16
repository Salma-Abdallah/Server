package gov.iti.jets.services;

import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.persistence.StatisticDao;

import java.util.List;


public class StatisticService {
    private StatisticDao statisticDao = new StatisticDao();


    public int getNumberOfAllUsers(){
        return statisticDao.getNumberOfAllUsers();
    }
    public int getNumberOfAllMsg(){
        return statisticDao.getNumberOfAllMsg();
    }

    public int getNumberOfOfflineUsers(){
        return statisticDao.getNumberOfOfflineUsers();
    }
    public int getNumberOfMaleUsers(){
        return statisticDao.getNumberOfMaleUsers();
    }
    public List<StatisticEntity> getCountryStatistics(){
        return statisticDao.getDataByCountry();
    }

}
