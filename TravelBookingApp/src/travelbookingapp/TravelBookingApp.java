package travelbookingapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
Κύρια κλάση της εφαρμογής Σύστημα Ταξιδιωτικών Κρατήσεων.
Διαχειρίζεται τη φόρτωση, αποθήκευση δεδομένων και τη διεπαφή χρήστη.
 */
public class TravelBookingApp extends Application {
    private TravelData travelData;
    private BorderPane root;
    private StackPane centerPane;

    /**
     Ξεκινά την εφαρμογή, ρυθμίζοντας το UI και φορτώνοντας τα δεδομένα.
     @param primaryStage Το κύριο stage της εφαρμογής.
     */
    @Override
    public void start(Stage primaryStage) {
        // Φόρτωση δεδομένων με χειρισμό σφαλμάτων
        try {
            travelData = DataManager.loadData();
        } catch (JAXBException e) {
            travelData = new TravelData();
            showErrorAlert("Σφάλμα κατά τη φόρτωση δεδομένων. Ξεκινάμε με κενά δεδομένα.");
        }

        // Ρύθμιση βασικής διάταξης με BorderPane
        root = new BorderPane();
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // Ρύθμιση StackPane για εικόνα φόντου και κεντρικό περιεχόμενο
        centerPane = new StackPane();
        setupBackgroundImage();

        // Αρχικό κεντρικό περιεχόμενο
        Label welcomeLabel = new Label("Καλώς ήρθατε στο Σύστημα Ταξιδιωτικών Κρατήσεων");
        centerPane.getChildren().add(welcomeLabel);
        root.setCenter(centerPane);

        // Ρύθμιση σκηνής με CSS
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Σύστημα Ταξιδιωτικών Κρατήσεων");

        // Χειρισμός κλεισίματος παραθύρου
        primaryStage.setOnCloseRequest(event -> {
            try {
                DataManager.saveData(travelData);
            } catch (JAXBException e) {
                showErrorAlert("Σφάλμα κατά την αποθήκευση δεδομένων.");
            }
        });

        primaryStage.show();
    }

    /**
     Δημιουργεί το MenuBar με τις επιλογές για τις διάφορες λειτουργίες.
     @return Το MenuBar της εφαρμογής.
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        // Μενού Πελατών
        Menu customerMenu = new Menu("Πελάτες");
        MenuItem manageCustomers = new MenuItem("Διαχείριση Πελατών");
        manageCustomers.setOnAction(event -> updateCenterPane(new CustomerManager(travelData).getPane()));
        customerMenu.getItems().add(manageCustomers);

        // Μενού Δρομολογίων
        Menu itineraryMenu = new Menu("Δρομολόγια");
        MenuItem manageItineraries = new MenuItem("Διαχείριση Δρομολογίων");
        manageItineraries.setOnAction(event -> updateCenterPane(new ItineraryManager(travelData).getPane()));
        itineraryMenu.getItems().add(manageItineraries);

        // Μενού Κρατήσεων
        Menu bookingMenu = new Menu("Κρατήσεις");
        MenuItem manageBookings = new MenuItem("Διαχείριση Κρατήσεων");
        manageBookings.setOnAction(event -> updateCenterPane(new BookingManager(travelData).getPane()));
        bookingMenu.getItems().add(manageBookings);

        // Μενού Αναφορών
        Menu reportMenu = new Menu("Αναφορές");
        MenuItem viewReports = new MenuItem("Προβολή Αναφορών");
        viewReports.setOnAction(event -> updateCenterPane(new ReportManager(travelData).getPane()));
        reportMenu.getItems().add(viewReports);

        menuBar.getMenus().addAll(customerMenu, itineraryMenu, bookingMenu, reportMenu);
        return menuBar;
    }

    //Ρυθμίζει την εικόνα φόντου στο StackPane
    private void setupBackgroundImage() {
        Image image = new Image(getClass().getResourceAsStream("/resources/travel_image.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);
        centerPane.getChildren().add(0, imageView); // Προσθήκη στο index 0 για να είναι φόντο
    }

    /**
     Ενημερώνει το κεντρικό περιεχόμενο του StackPane, διατηρώντας την εικόνα φόντου.
     @param newContent Το νέο pane που θα εμφανιστεί.
     */
    private void updateCenterPane(javafx.scene.Node newContent) {
        if (centerPane.getChildren().size() > 1) {
            centerPane.getChildren().set(1, newContent); // Αντικατάσταση του περιεχομένου
        } else {
            centerPane.getChildren().add(newContent); // Προσθήκη αν δεν υπάρχει
        }
    }

    /**
     Εμφανίζει ένα μήνυμα σφάλματος στον χρήστη.
     @param message Το μήνυμα σφάλματος που θα εμφανιστεί.
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}