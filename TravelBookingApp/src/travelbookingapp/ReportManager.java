package travelbookingapp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.stream.Collectors;

// Διαχειρίζεται το UI για την προβολή αναφορών, εμφανίζοντας το ιστορικό κρατήσεων ενός επιλεγμένου πελάτη.
public class ReportManager {
    private final TravelData travelData;
    private final StackPane pane;
    private final ComboBox<Customer> customerCombo;
    private final TextArea reportArea;

    /**
     Κατασκευαστής που αρχικοποιεί το UI για τις αναφορές.
     @param travelData Τα δεδομένα της εφαρμογής που περιέχουν πελάτες και κρατήσεις.
     */
    public ReportManager(TravelData travelData) {
        this.travelData = travelData;

        // Δημιουργία GridPane για τη διάταξη των στοιχείων UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Φόρτωση εικόνας φόντου
        Image image = new Image(getClass().getResourceAsStream("/resources/travel_image.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);

        // Δημιουργία ετικέτας και ComboBox για επιλογή πελάτη
        Label customerLabel = new Label("Επιλέξτε Πελάτη για Ιστορικό Κρατήσεων:");
        customerCombo = new ComboBox<>();
        customerCombo.getItems().addAll(travelData.getCustomers());
        customerCombo.setPromptText("Επιλέξτε πελάτη");

        // Δημιουργία TextArea για την εμφάνιση των αναφορών
        reportArea = new TextArea();
        reportArea.setEditable(false);
        reportArea.setPromptText("Επιλέξτε έναν πελάτη για να δείτε το ιστορικό κρατήσεων.");
        reportArea.setPrefRowCount(10); // Ρύθμιση ύψους για καλύτερη εμφάνιση

        // Λογική εμφάνισης αναφορών κατά την επιλογή πελάτη
        customerCombo.setOnAction(event -> {
            Customer selectedCustomer = customerCombo.getValue();
            if (selectedCustomer != null) {
                String report = travelData.getBookings().stream()
                        .filter(booking -> booking.getCustomer().equals(selectedCustomer))
                        .map(Booking::toString)
                        .collect(Collectors.joining("\n"));
                reportArea.setText(report.isEmpty() ? "Δεν υπάρχουν κρατήσεις για αυτόν τον πελάτη." : report);
            } else {
                reportArea.setText("");
            }
        });

        // Προσθήκη στοιχείων στο GridPane
        gridPane.add(customerLabel, 0, 0);
        gridPane.add(customerCombo, 1, 0);
        gridPane.add(reportArea, 0, 1, 2, 1);

        // Συνδυασμός εικόνας και GridPane σε StackPane
        pane = new StackPane();
        pane.getChildren().addAll(imageView, gridPane);
    }

    /**
     Επιστρέφει το StackPane που περιέχει το UI των αναφορών.
     @return Το StackPane με την εικόνα φόντου και τα στοιχεία UI.
     */
    public StackPane getPane() {
        return pane;
    }
}