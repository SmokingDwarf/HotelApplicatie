package hotel.hotelapplicatie.userinterface;

import hotel.hotelapplicatie.model.Hotel;
import hotel.hotelapplicatie.model.KamerType;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class BoekingenController {
    @FXML private javafx.scene.control.TextField naamTextField ;
    @FXML private javafx.scene.control.TextField adresTextField;
    @FXML private javafx.scene.control.DatePicker aankomstdatumDatePicker;
    @FXML private javafx.scene.control.DatePicker vertrekdatumDatePicker;
    @FXML private javafx.scene.control.ComboBox<KamerType> kamertypeComboBox;
    @FXML private javafx.scene.control.Label meldingLabel;

    private final Hotel hotel = Hotel.getHotel();

    @FXML
    public void initialize() {
        kamertypeComboBox.getItems().addAll(hotel.getKamerTypen());
    }

    @FXML
    private void handleButtonBoek(ActionEvent actionEvent) {
        try {
            String naam = naamTextField.getText().trim();
            String adres = adresTextField.getText().trim();
            LocalDate aankomst = aankomstdatumDatePicker.getValue();
            LocalDate vertrek = vertrekdatumDatePicker.getValue();
            KamerType kamertype = kamertypeComboBox.getValue();

            if (naam.isEmpty() || adres.isEmpty() || aankomst == null || vertrek == null || kamertype == null) {
                meldingLabel.setUnderline(true);
                meldingLabel.setText("Niet alle velden zijn ingevuld!");
                return;
            }

            if (aankomst.isBefore(LocalDate.now()) || vertrek.isBefore(LocalDate.now())) {
                meldingLabel.setUnderline(true);
                meldingLabel.setText("De ingevoerde datum ligt in het verleden!");
                return;
            }

            if (!aankomst.isBefore(vertrek)) {
                meldingLabel.setUnderline(true);
                meldingLabel.setText("Datum van aankomst kan niet later zijn dan vertrek!");
                return;
            }

            try {
                hotel.voegBoekingToe(aankomst, vertrek, naam, adres, kamertype);
            } catch (Exception e) {
                meldingLabel.setText(e.getMessage());
                return;
            }

            meldingLabel.setUnderline(false);
            meldingLabel.setText("De boeking is succesvol verwerkt!");

            handleButtonReset(null);
        } catch (Exception e) {
//            System.out.println("De pagina kan niet geladen worden.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonReset (ActionEvent actionEvent) {
        naamTextField.clear();
        adresTextField.clear();
        aankomstdatumDatePicker.setValue(null);
        vertrekdatumDatePicker.setValue(null);
        kamertypeComboBox.getSelectionModel().clearSelection();
    }
}
