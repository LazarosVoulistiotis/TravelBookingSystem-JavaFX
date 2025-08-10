package travelbookingapp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import javax.xml.bind.JAXBException;

// Διαχειρίζεται το UI για τη δημιουργία και ακύρωση κρατήσεων.
public class BookingManager {
    private final TravelData travelData;
    private final StackPane pane;
    private final ComboBox<Customer> customerCombo;
    private final ComboBox<Itinerary> itineraryCombo;
    private final ListView<Booking> bookingListView;

    /**
     Κατασκευαστής που αρχικοποιεί το UI για τη διαχείριση κρατήσεων.
     @param travelData Τα δεδομένα της εφαρμογής που περιέχουν πελάτες, δρομολόγια και κρατήσεις.
     */
    public BookingManager(TravelData travelData) {
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

        // Δημιουργία ετικετών και πεδίων επιλογής
        Label customerLabel = new Label("Πελάτης:");
        customerCombo = new ComboBox<>();
        customerCombo.getItems().addAll(travelData.getCustomers());
        customerCombo.setPromptText("Επιλέξτε πελάτη");
        Label itineraryLabel = new Label("Δρομολόγιο:");
        itineraryCombo = new ComboBox<>();
        itineraryCombo.getItems().addAll(travelData.getItineraries());
        itineraryCombo.setPromptText("Επιλέξτε δρομολόγιο");

        // Κουμπί κράτησης
        Button bookButton = new Button("Κράτηση");

        // Αρχικοποίηση της bookingListView
        bookingListView = new ListView<>();
        bookingListView.getItems().addAll(travelData.getBookings());

        // Λογική κράτησης με έλεγχο διαθεσιμότητας
        bookButton.setOnAction(event -> {
            Customer selectedCustomer = customerCombo.getValue();
            Itinerary selectedItinerary = itineraryCombo.getValue();
            if (selectedCustomer == null || selectedItinerary == null) {
                showErrorAlert("Παρακαλώ επιλέξτε πελάτη και δρομολόγιο.");
                return;
            }
            if (selectedItinerary.getAvailableSeats() <= 0) {
                showErrorAlert("Δεν υπάρχουν διαθέσιμες θέσεις.");
                return;
            }
            Booking newBooking = new Booking(selectedCustomer, selectedItinerary, LocalDate.now());
            travelData.addBooking(newBooking);
            selectedItinerary.setAvailableSeats(selectedItinerary.getAvailableSeats() - 1);
            bookingListView.getItems().add(newBooking);
            itineraryCombo.getItems().setAll(travelData.getItineraries()); // Ανανέωση δρομολογίων
            saveData();
            showInfoAlert("Η κράτηση ολοκληρώθηκε επιτυχώς.");
        });

        // Κουμπί ακύρωσης κράτησης
        Button cancelButton = new Button("Ακύρωση Κράτησης");
        cancelButton.setOnAction(event -> {
            Booking selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
            if (selectedBooking == null) {
                showErrorAlert("Παρακαλώ επιλέξτε μια κράτηση για ακύρωση.");
                return;
            }
            if (selectedBooking.isCancelled()) {
                showErrorAlert("Η κράτηση έχει ήδη ακυρωθεί.");
                return;
            }
            selectedBooking.setCancelled(true);
            Itinerary itinerary = selectedBooking.getItinerary();
            itinerary.setAvailableSeats(itinerary.getAvailableSeats() + 1);
            bookingListView.refresh();
            saveData();
            showInfoAlert("Η κράτηση ακυρώθηκε.");
        });

        // Προσθήκη στοιχείων στο GridPane
        gridPane.add(customerLabel, 0, 0);
        gridPane.add(customerCombo, 1, 0);
        gridPane.add(itineraryLabel, 0, 1);
        gridPane.add(itineraryCombo, 1, 1);
        gridPane.add(bookButton, 0, 2, 2, 1);
        gridPane.add(bookingListView, 0, 3, 2, 1);
        gridPane.add(cancelButton, 0, 4, 2, 1);

        // Συνδυασμός εικόνας και GridPane σε StackPane
        pane = new StackPane();
        pane.getChildren().addAll(imageView, gridPane);
    }

    // Αποθηκεύει τα δεδομένα στο XML αρχείο.
    private void saveData() {
        try {
            DataManager.saveData(travelData);
        } catch (JAXBException e) {
            System.err.println("Σφάλμα JAXB: " + e.getMessage());
            showErrorAlert("Σφάλμα αποθήκευσης: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Γενικό σφάλμα: " + e.getMessage());
            showErrorAlert("Απροσδιόριστο σφάλμα: " + e.getMessage());
        }
    }

    /**
     Εμφανίζει μήνυμα σφάλματος στον χρήστη.
     @param message Το μήνυμα σφάλματος.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    /**
     Εμφανίζει ενημερωτικό μήνυμα στον χρήστη.
     @param message Το ενημερωτικό μήνυμα.
     */
    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }

    /**
     Επιστρέφει το StackPane που περιέχει το UI της διαχείρισης κρατήσεων.
     @return Το StackPane με την εικόνα και τα στοιχεία UI.
     */
    public StackPane getPane() {
        return pane;
    }
}