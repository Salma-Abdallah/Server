package gov.iti.jets.network.controllers;

import gov.iti.jets.dto.responses.StatisticResponse;
import gov.iti.jets.entities.StatisticEntity;

import java.util.List;

public interface StatisticController {
    public StatisticResponse getStatistics();
}
