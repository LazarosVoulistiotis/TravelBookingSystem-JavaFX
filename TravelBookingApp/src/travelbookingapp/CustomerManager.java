package travelbookingapp;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.xml.bind.JAXBException;

// Διαχειρίζεται το UI για την προσθήκη, επεξεργασία και διαγραφή πελατών.
public class CustomerManager {
    private final TravelData travelData;
    private final StackPane pane;
    private final TextField nameField;
    private final TextField emailField;
    private final TextField phoneField;
    private final ListView<Customer> customerListView;

    /**
     Κατασκευαστής που αρχικοποιεί το UI για τη διαχείριση πελατών.
     @param travelData Τα δεδομένα της εφαρμογής που περιέχουν τους πελάτες.
     */
    public CustomerManager(TravelData travelData) {
        this.travelData = travelData;

        // Δημιουργία GridPane για τα στοιχεία UI
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

        // Δημιουργία labels και fields
        Label nameLabel = new Label("Όνομα:");
        nameField = new TextField();
        nameField.setPromptText("Εισάγετε όνομα πελάτη");
        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText("Εισάγετε email");
        Label phoneLabel = new Label("Τηλέφωνο:");
        phoneField = new TextField();
        phoneField.setPromptText("Εισάγετε τηλέφωνο");

        // Δημιουργία κουμπιών
        Button addButton = new Button("Προσθήκη Πελάτη");
        Button editButton = new Button("Επεξεργασία Πελάτη");
        Button deleteButton = new Button("Διαγραφή Πελάτη");

        // Δημιουργία ListView για πελάτες
        customerListView = new ListView<>();
        customerListView.getItems().addAll(travelData.getCustomers());

        // Προσθήκη στοιχείων στο GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(phoneLabel, 0, 2);
        gridPane.add(phoneField, 1, 2);
        gridPane.add(addButton, 0, 3);
        gridPane.add(editButton, 1, 3);
        gridPane.add(deleteButton, 2, 3);
        gridPane.add(customerListView, 0, 4, 3, 1);

        // Λογική για τα κουμπιά
        // Προσθήκη Πελάτη
        addButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                showErrorAlert("Παρακαλώ συμπληρώστε όλα τα πεδία.");
                return;
            }
            Customer newCustomer = new Customer(name, email, phone);
            travelData.addCustomer(newCustomer);
            customerListView.getItems().add(newCustomer);
            saveData();
            clearFields();
            showInfoAlert("Ο πελάτης προστέθηκε επιτυχώς.");
        });

        // Επεξεργασία Πελάτη
        editButton.setOnAction(event -> {
            Customer selected = customerListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showErrorAlert("Παρακαλώ επιλέξτε έναν πελάτη για επεξεργασία.");
                return;
            }
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                showErrorAlert("Παρακαλώ συμπληρώστε όλα τα πεδία.");
                return;
            }
            selected.setName(name);
            selected.setEmail(email);
            selected.setPhone(phone);
            customerListView.refresh();
            saveData();
            showInfoAlert("Ο πελάτης ενημερώθηκε επιτυχώς.");
        });

        // Διαγραφή Πελάτη
        deleteButton.setOnAction(event -> {
            Customer selected = customerListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                travelData.removeCustomer(selected);
                customerListView.getItems().remove(selected);
                saveData();
                showInfoAlert("Ο πελάτης διαγράφηκε επιτυχώς.");
            } else {
                showErrorAlert("Παρακαλώ επιλέξτε έναν πελάτη για διαγραφή.");
            }
        });

        // Συνδυασμός εικόνας και GridPane σε StackPane
        pane = new StackPane();
        pane.getChildren().addAll(imageView, gridPane);
    }

    // Καθαρίζει τα πεδία εισαγωγής μετά από προσθήκη ή επεξεργασία.
    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
    }

    // Αποθηκεύει τα δεδομένα στο αρχείο.
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
     Επιστρέφει το StackPane που περιέχει το UI της διαχείρισης πελατών.
     @return Το StackPane με την εικόνα και τα στοιχεία UI.
     */
    public StackPane getPane() {
        return pane;
    }
}