package hotel.hotelapplicatie.userinterface;

import hotel.hotelapplicatie.HotelApp;
import hotel.hotelapplicatie.model.Boeking;
import hotel.hotelapplicatie.model.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.time.LocalDate;

public class HotelOverzichtController {
    @FXML private Label hotelnaamLabel;
    @FXML private ListView boekingenListView;
    @FXML private DatePicker overzichtDatePicker;


    private final Hotel hotel = Hotel.getHotel();

    public void initialize() {
        hotelnaamLabel.setText("Boekingen hotel " + hotel.getNaam());
        overzichtDatePicker.setValue(LocalDate.now());
        toonBoekingen();
    }

    public void toonVorigeDag(ActionEvent actionEvent) {
        LocalDate dagEerder = overzichtDatePicker.getValue().minusDays(1);
        overzichtDatePicker.setValue(dagEerder);
    }

    public void toonVolgendeDag(ActionEvent actionEvent) {
        LocalDate dagLater = overzichtDatePicker.getValue().plusDays(1);
        overzichtDatePicker.setValue(dagLater);
    }

    public void nieuweBoeking(ActionEvent actionEvent) {
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/hotel/hotelapplicatie/Boekingen.fxml"));
            FXMLLoader loader = new FXMLLoader(HotelApp.class.getResource("Boekingen.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Nieuwe Boeking");
            stage.setScene(new Scene(root));
//            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            toonBoekingen();
        } catch (Exception e) {
//            System.out.println("Het lukt niet om de pagina te laden.");
            e.printStackTrace();
        }
    }

    public void toonBoekingen() {
        LocalDate geselecteerdeDatum = overzichtDatePicker.getValue();
        ObservableList<String> boekingen = FXCollections.observableArrayList();

        for (Boeking boeking : hotel.getBoekingen()) {
            if (!geselecteerdeDatum.isBefore(boeking.getAankomstDatum()) && (!geselecteerdeDatum.isAfter(boeking.getVertrekDatum()))) {
                String boekingInfo = String.format(
                    "Kamernummer: %s, Naam: %s, Aankomst: %s, Vertrek: %s",
                    boeking.getKamer().getKamerNummer(),
                    boeking.getBoeker().getNaam(),
                    boeking.getAankomstDatum(),
                    boeking.getVertrekDatum()
                );
                boekingen.add(boekingInfo);
            }
        }
        boekingenListView.setItems(boekingen);
    }
}