package gov.iti.jets.dto.responses;

import gov.iti.jets.entities.StatisticEntity;

import java.util.List;

public class StatisticResponse {
    private int numberOfAllUsers;
    private int numberOfOfflineUsers;
    private int numberOfMaleUsers;
    private List<StatisticEntity> countryStatistic;

    public StatisticResponse(int numberOfAllUsers, int numberOfOfflineUsers, int numberOfMaleUsers, List<StatisticEntity> countryStatistic) {
        this.numberOfAllUsers = numberOfAllUsers;
        this.numberOfOfflineUsers = numberOfOfflineUsers;
        this.numberOfMaleUsers = numberOfMaleUsers;
        this.countryStatistic = countryStatistic;
    }

    public int getNumberOfAllUsers() {
        return numberOfAllUsers;
    }

    public void setNumberOfAllUsers(int numberOfAllUsers) {
        this.numberOfAllUsers = numberOfAllUsers;
    }

    public int getNumberOfOfflineUsers() {
        return numberOfOfflineUsers;
    }

    public void setNumberOfOfflineUsers(int numberOfOfflineUsers) {
        this.numberOfOfflineUsers = numberOfOfflineUsers;
    }

    public int getNumberOfMaleUsers() {
        return numberOfMaleUsers;
    }

    public void setNumberOfMaleUsers(int numberOfMaleUsers) {
        this.numberOfMaleUsers = numberOfMaleUsers;
    }

    public List<StatisticEntity> getCountryStatistic() {
        return countryStatistic;
    }

    public void setCountryStatistic(List<StatisticEntity> countryStatistic) {
        this.countryStatistic = countryStatistic;
    }

    @Override
    public String toString() {
        return "StatisticResponse{" +
                "numberOfAllUsers=" + numberOfAllUsers +
                ", numberOfOfflineUsers=" + numberOfOfflineUsers +
                ", numberOfMaleUsers=" + numberOfMaleUsers +
                ", countryStatistic=" + countryStatistic +
                '}';
    }
}
