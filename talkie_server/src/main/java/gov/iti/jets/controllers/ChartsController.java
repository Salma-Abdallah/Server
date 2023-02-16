package gov.iti.jets.controllers;


import gov.iti.jets.entities.StatisticEntity;
import gov.iti.jets.services.StatisticService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.net.URL;
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

    ObservableList<PieChart.Data> genderData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> countryData = FXCollections.observableArrayList();
    ObservableList<XYChart.Series<String, Number>> Online_OfflineUsers = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateData();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            updateData();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateData(){
        userCount.setText(statisticService.getNumberOfAllUsers() + " Users");
        msgCount.setText(statisticService.getNumberOfAllMsg() + " Messages");

        genderData.clear();
        genderData.addAll(new PieChart.Data("Male", statisticService.getNumberOfMaleUsers()),
                new PieChart.Data("Female", statisticService.getNumberOfAllUsers() - statisticService.getNumberOfMaleUsers()));
        genderPieChart.setData(genderData);

        countryData.clear();
        for (StatisticEntity countryStatistic : statisticService.getCountryStatistics())
            countryData.add(new PieChart.Data(countryStatistic.getCountry(), countryStatistic.getCount()));
        countryPieChart.setData(countryData);

        Online_OfflineUsers.clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(new XYChart.Data<>("Online", statisticService.getNumberOfAllUsers() - statisticService.getNumberOfOfflineUsers()),
                new XYChart.Data<>("Offline", statisticService.getNumberOfOfflineUsers()));
        Online_OfflineUsers.add(series);
        barChart.setData(Online_OfflineUsers);
    }
}