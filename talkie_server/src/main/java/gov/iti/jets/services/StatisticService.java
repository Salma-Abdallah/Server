package gov.iti.jets.services;

import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.persistence.StatisticDao;

import java.util.List;


public class StatisticService {
    private StatisticDao statisticDao;

    public List<StatisticEntity> getOnlineOfflineStatistics(){
        return statisticDao.getOnlineOfflineUsers();
    }
    public List<StatisticEntity> getGenderStatistics(){
        return statisticDao.getDataByGender();
    }
    public List<StatisticEntity> getCountryStatistics(){
        return statisticDao.getDataByCountry();
    }

}
