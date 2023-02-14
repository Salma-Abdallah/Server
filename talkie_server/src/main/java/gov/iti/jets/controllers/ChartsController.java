package gov.iti.jets.controllers;

import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.services.StatisticService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {
    StatisticService statisticService = new StatisticService();
    @FXML
    private PieChart countryPieChart;
    @FXML
    private PieChart genderPieChart;
    @FXML
    private BarChart barChart;
    @FXML
    private Text userCount;
    @FXML
    private Text msgCount;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userCount.setText(statisticService.getNumberOfAllUsers() + " Users");
        msgCount.setText(statisticService.getNumberOfAllMsg() + " Messages");

        ObservableList<PieChart.Data> genderData = FXCollections.observableArrayList();
        genderData.addAll(new PieChart.Data("Male", statisticService.getNumberOfMaleUsers()),
                new PieChart.Data("Female", statisticService.getNumberOfAllUsers()-statisticService.getNumberOfMaleUsers()));
        genderPieChart.setData(genderData);

        ObservableList<PieChart.Data> countryData = FXCollections.observableArrayList();
        for(StatisticEntity countryStatistic : statisticService.getCountryStatistics())
            countryData.add(new PieChart.Data(countryStatistic.getCountry(), countryStatistic.getCount()));
        countryPieChart.setData(countryData);

        ObservableList<XYChart.Series<String,Number>> Online_OfflineUsers = FXCollections.observableArrayList();
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().addAll(new XYChart.Data<>("Online", statisticService.getNumberOfAllUsers()-statisticService.getNumberOfOfflineUsers()),
                new XYChart.Data<>("Offline",statisticService.getNumberOfOfflineUsers()));
        Online_OfflineUsers.add(series);
        barChart.setData(Online_OfflineUsers);
    }
}