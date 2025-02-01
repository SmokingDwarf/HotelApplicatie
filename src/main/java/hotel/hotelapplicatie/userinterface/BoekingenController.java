package hotel.hotelapplicatie.userinterface;

import hotel.hotelapplicatie.model.Hotel;
import hotel.hotelapplicatie.model.KamerType;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class BoekingenController {
    @FXML private javafx.scene.control.TextField naamTextField ;
    @FXML private javafx.scene.control.TextField adresTextField;
    @FXML private javafx.scene.control.DatePicker aankomstdatumDatePicker;
    @FXML private javafx.scene.control.DatePicker vertrekdatumDatePicker;
    @FXML private javafx.scene.control.ComboBox<KamerType> kamertypeComboBox;

    private final Hotel hotel = Hotel.getHotel();

    @FXML
    public void initialize() {
        kamertypeComboBox.getItems().addAll(hotel.getKamerTypen());
    }

    @FXML
    private void handleButtonBoek(ActionEvent actionEvent) {}
    @FXML
    private void handleButtonReset (ActionEvent actionEvent) {}
}
