package gov.iti.jets.network.controllers.impl;

import gov.iti.jets.dto.responses.StatisticResponse;
import gov.iti.jets.services.StatisticService;

public class StatisticControllerSingleton {
    public StatisticResponse getStatistics() {
        StatisticService statisticService = new StatisticService();
        StatisticResponse statisticResponse = new StatisticResponse(statisticService.getNumberOfAllUsers(),statisticService.getNumberOfOfflineUsers(), statisticService.getNumberOfMaleUsers(),statisticService.getCountryStatistics());
        return statisticResponse;
    }

}
