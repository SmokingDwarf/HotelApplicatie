package hotel.hotelapplicatie;

import hotel.hotelapplicatie.model.Hotel;
import hotel.hotelapplicatie.model.Kamer;
import hotel.hotelapplicatie.model.KamerType;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApp.class.getResource("HotelOverzicht.fxml"));
//                getClass().getResource("/hotel/hotelapplicatie/’hotelOverzicht.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("HotelApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        KamerType kt1 = new KamerType("Standaard", 2, 60.00);
        KamerType kt2 = new KamerType("DeLuxe", 2, 85.00);

        List<Kamer> kamers = new ArrayList<>();
		kamers.add(new Kamer(1, kt2));
		kamers.add(new Kamer(2, kt1));
		kamers.add(new Kamer(3, kt1));
		kamers.add(new Kamer(4, kt2));

        Hotel nieuwHotel = new Hotel("Avondrust", kamers);
		nieuwHotel.voegBoekingToe(LocalDate.now(), LocalDate.now().plusWeeks(2), "Mark Rutte", "Torentje 1", kt2);
		nieuwHotel.voegBoekingToe(LocalDate.now(), LocalDate.now().plusDays(3), "Toos Hopeloos", "Botersloot 85", kt1);
		nieuwHotel.voegBoekingToe(LocalDate.now().plusDays(3), LocalDate.now().plusDays(13), "Trammetje Westerflat", "Studio 100", kt1);

		Hotel.setHotel(nieuwHotel);

        launch(args);
}}