package travelbookingapp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import javax.xml.bind.JAXBException;

// Διαχειρίζεται το UI για την προσθήκη, επεξεργασία και διαγραφή δρομολογίων.
public class ItineraryManager {
    private final TravelData travelData;
    private final StackPane pane;
    private final TextField destinationField;
    private final DatePicker datePicker;
    private final TextField seatsField;
    private final TextField costField;
    private final ComboBox<String> transportCombo;
    private final ListView<Itinerary> itineraryList;

    /**
     Κατασκευαστής που αρχικοποιεί το UI για τη διαχείριση δρομολογίων.
     @param travelData Τα δεδομένα της εφαρμογής που περιέχουν τα δρομολόγια.
     */
    public ItineraryManager(TravelData travelData) {
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

        // Δημιουργία ετικετών και πεδίων εισαγωγής
        Label destinationLabel = new Label("Προορισμός:");
        destinationField = new TextField();
        destinationField.setPromptText("Εισάγετε προορισμό");
        Label dateLabel = new Label("Ημερομηνία:");
        datePicker = new DatePicker();
        Label seatsLabel = new Label("Διαθέσιμες Θέσεις:");
        seatsField = new TextField();
        seatsField.setPromptText("Εισάγετε αριθμό θέσεων");
        Label costLabel = new Label("Κόστος:");
        costField = new TextField();
        costField.setPromptText("Εισάγετε κόστος");
        Label transportLabel = new Label("Τύπος Μεταφορικού Μέσου:");
        transportCombo = new ComboBox<>();
        transportCombo.getItems().addAll("Αεροπλάνο", "Τρένο", "Λεωφορείο");
        transportCombo.setPromptText("Επιλέξτε μέσο");

        // Δημιουργία κουμπιών
        Button addButton = new Button("Προσθήκη Δρομολογίου");
        Button editButton = new Button("Επεξεργασία Δρομολογίου");
        Button deleteButton = new Button("Διαγραφή Δρομολογίου");

        // Δημιουργία λίστας δρομολογίων
        itineraryList = new ListView<>();
        itineraryList.getItems().addAll(travelData.getItineraries());

        // Προσθήκη στοιχείων στο GridPane
        gridPane.add(destinationLabel, 0, 0);
        gridPane.add(destinationField, 1, 0);
        gridPane.add(dateLabel, 0, 1);
        gridPane.add(datePicker, 1, 1);
        gridPane.add(seatsLabel, 0, 2);
        gridPane.add(seatsField, 1, 2);
        gridPane.add(costLabel, 0, 3);
        gridPane.add(costField, 1, 3);
        gridPane.add(transportLabel, 0, 4);
        gridPane.add(transportCombo, 1, 4);
        gridPane.add(addButton, 0, 5);
        gridPane.add(editButton, 1, 5);
        gridPane.add(deleteButton, 2, 5);
        gridPane.add(itineraryList, 0, 6, 3, 1);

        // Λογική προσθήκης δρομολογίου με χειρισμό σφαλμάτων
        addButton.setOnAction(event -> {
            String destination = destinationField.getText().trim();
            LocalDate date = datePicker.getValue();
            String seatsText = seatsField.getText().trim();
            String costText = costField.getText().trim();
            String transport = transportCombo.getValue();

            if (destination.isEmpty() || date == null || seatsText.isEmpty() || costText.isEmpty() || transport == null) {
                showErrorAlert("Παρακαλώ συμπληρώστε όλα τα πεδία.");
                return;
            }

            try {
                int seats = Integer.parseInt(seatsText);
                double cost = Double.parseDouble(costText);
                if (seats <= 0 || cost < 0) {
                    showErrorAlert("Οι θέσεις πρέπει να είναι θετικές και το κόστος μη αρνητικό.");
                    return;
                }
                Itinerary newItinerary = new Itinerary(destination, date, seats, cost, transport);
                travelData.addItinerary(newItinerary);
                itineraryList.getItems().add(newItinerary);
                saveData();
                clearFields();
                showInfoAlert("Το δρομολόγιο προστέθηκε επιτυχώς.");
            } catch (NumberFormatException e) {
                showErrorAlert("Εισάγετε έγκυρους αριθμούς για θέσεις και κόστος.");
            }
        });

        // Λογική επεξεργασίας δρομολογίου με χειρισμό σφαλμάτων
        editButton.setOnAction(event -> {
            Itinerary selected = itineraryList.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showErrorAlert("Παρακαλώ επιλέξτε ένα δρομολόγιο για επεξεργασία.");
                return;
            }
            String destination = destinationField.getText().trim();
            LocalDate date = datePicker.getValue();
            String seatsText = seatsField.getText().trim();
            String costText = costField.getText().trim();
            String transport = transportCombo.getValue();

            if (destination.isEmpty() || date == null || seatsText.isEmpty() || costText.isEmpty() || transport == null) {
                showErrorAlert("Παρακαλώ συμπληρώστε όλα τα πεδία.");
                return;
            }

            try {
                int seats = Integer.parseInt(seatsText);
                double cost = Double.parseDouble(costText);
                if (seats <= 0 || cost < 0) {
                    showErrorAlert("Οι θέσεις πρέπει να είναι θετικές και το κόστος μη αρνητικό.");
                    return;
                }
                selected.setDestination(destination);
                selected.setDate(date);
                selected.setAvailableSeats(seats);
                selected.setCost(cost);
                selected.setTransportType(transport);
                itineraryList.refresh();
                saveData();
                showInfoAlert("Το δρομολόγιο ενημερώθηκε επιτυχώς.");
            } catch (NumberFormatException e) {
                showErrorAlert("Εισάγετε έγκυρους αριθμούς για θέσεις και κόστος.");
            }
        });

        // Λογική αφαίρεσης δρομολογίου με χειρισμό σφαλμάτων
        deleteButton.setOnAction(event -> {
            Itinerary selected = itineraryList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                travelData.removeItinerary(selected);
                itineraryList.getItems().remove(selected);
                saveData();
                showInfoAlert("Το δρομολόγιο διαγράφηκε επιτυχώς.");
            } else {
                showErrorAlert("Παρακαλώ επιλέξτε ένα δρομολόγιο για διαγραφή.");
            }
        });

        // Συνδυασμός εικόνας και GridPane σε StackPane
        pane = new StackPane();
        pane.getChildren().addAll(imageView, gridPane);
    }

    // Καθαρίζει τα πεδία εισαγωγής μετά από επιτυχή προσθήκη.
    private void clearFields() {
        destinationField.clear();
        datePicker.setValue(null);
        seatsField.clear();
        costField.clear();
        transportCombo.setValue(null);
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
     Επιστρέφει το StackPane που περιέχει το UI της διαχείρισης δρομολογίων.
     @return Το StackPane με την εικόνα και τα στοιχεία UI.
     */
    public StackPane getPane() {
        return pane;
    }
}