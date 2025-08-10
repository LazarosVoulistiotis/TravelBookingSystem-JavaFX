package travelbookingapp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

/**
 Αναπαριστά ένα δρομολόγιο στο σύστημα ταξιδιωτικών κρατήσεων.
 Περιλαμβάνει πληροφορίες για τον προορισμό, την ημερομηνία, τις διαθέσιμες θέσεις,
 το κόστος και τον τύπο μεταφορικού μέσου.
 */
@XmlRootElement
public class Itinerary {
    private String destination;
    private LocalDate date;
    private int availableSeats;
    private double cost;
    private String transportType;

    /**
     Προεπιλεγμένος κατασκευαστής για JAXB.
     Απαιτείται για τη σωστή σειριοποίηση και αποσειριοποίηση σε XML.
     */
    public Itinerary() {}

    /**
     Κατασκευαστής με όλες τις ιδιότητες.
     @param destination     Ο προορισμός του δρομολογίου.
     @param date            Η ημερομηνία του δρομολογίου.
     @param availableSeats  Ο αριθμός των διαθέσιμων θέσεων.
     @param cost            Το κόστος του δρομολογίου.
     @param transportType   Ο τύπος του μεταφορικού μέσου.
     @throws IllegalArgumentException Αν κάποιο από τα ορίσματα είναι μη έγκυρο.
     */
    public Itinerary(String destination, LocalDate date, int availableSeats, double cost, String transportType) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Ο προορισμός δεν μπορεί να είναι κενός ή null.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Η ημερομηνία δεν μπορεί να είναι null.");
        }
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Οι διαθέσιμες θέσεις δεν μπορούν να είναι αρνητικές.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Το κόστος δεν μπορεί να είναι αρνητικό.");
        }
        if (transportType == null || transportType.trim().isEmpty()) {
            throw new IllegalArgumentException("Ο τύπος μεταφορικού μέσου δεν μπορεί να είναι κενός ή null.");
        }

        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
        this.cost = cost;
        this.transportType = transportType;
    }

    /**
     Επιστρέφει τον προορισμό του δρομολογίου.
     @return Ο προορισμός.
     */
    @XmlElement
    public String getDestination() {
        return destination;
    }

    /**
     Ορίζει τον προορισμό του δρομολογίου.
     @param destination Ο νέος προορισμός.
     @throws IllegalArgumentException Αν ο προορισμός είναι κενός ή null.
     */
    public void setDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Ο προορισμός δεν μπορεί να είναι κενός ή null.");
        }
        this.destination = destination;
    }

    /**
     Επιστρέφει την ημερομηνία του δρομολογίου.
     @return Η ημερομηνία.
     */
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    /**
     Ορίζει την ημερομηνία του δρομολογίου.
     @param date Η νέα ημερομηνία.
     @throws IllegalArgumentException Αν η ημερομηνία είναι null.
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Η ημερομηνία δεν μπορεί να είναι null.");
        }
        this.date = date;
    }

    /**
     Επιστρέφει τον αριθμό των διαθέσιμων θέσεων.
     @return Ο αριθμός των θέσεων.
     */
    @XmlElement
    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     Ορίζει τον αριθμό των διαθέσιμων θέσεων.
     @param availableSeats Ο νέος αριθμός θέσεων.
     @throws IllegalArgumentException Αν ο αριθμός θέσεων είναι αρνητικός.
     */
    public void setAvailableSeats(int availableSeats) {
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Οι διαθέσιμες θέσεις δεν μπορούν να είναι αρνητικές.");
        }
        this.availableSeats = availableSeats;
    }

    /**
     Επιστρέφει το κόστος του δρομολογίου.
     @return Το κόστος.
     */
    @XmlElement
    public double getCost() {
        return cost;
    }

    /**
     Ορίζει το κόστος του δρομολογίου.
     @param cost Το νέο κόστος.
     @throws IllegalArgumentException Αν το κόστος είναι αρνητικό.
     */
    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Το κόστος δεν μπορεί να είναι αρνητικό.");
        }
        this.cost = cost;
    }

    /**
     Επιστρέφει τον τύπο του μεταφορικού μέσου.
     @return Ο τύπος του μέσου.
     */
    @XmlElement
    public String getTransportType() {
        return transportType;
    }

    /**
     Ορίζει τον τύπο του μεταφορικού μέσου.
     @param transportType Ο νέος τύπος μέσου.
     @throws IllegalArgumentException Αν ο τύπος μέσου είναι κενός ή null.
     */
    public void setTransportType(String transportType) {
        if (transportType == null || transportType.trim().isEmpty()) {
            throw new IllegalArgumentException("Ο τύπος μεταφορικού μέσου δεν μπορεί να είναι κενός ή null.");
        }
        this.transportType = transportType;
    }

    /**
     Επιστρέφει μια συμβολοσειρά που αναπαριστά το δρομολόγιο.
     @return Μια μορφοποιημένη συμβολοσειρά με τα στοιχεία του δρομολογίου.
     */
    @Override
    public String toString() {
        return String.format("%s - %s (%d θέσεις, %.2f ευρώ, %s)",
                destination, date, availableSeats, cost, transportType);
    }

    /**
     Ελέγχει αν δύο δρομολόγια είναι ίδια με βάση τα στοιχεία τους.
     param o Το αντικείμενο προς σύγκριση.
     return true αν τα δρομολόγια είναι ίδια, false αλλιώς.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerary itinerary = (Itinerary) o;
        return availableSeats == itinerary.availableSeats &&
                Double.compare(itinerary.cost, cost) == 0 &&
                Objects.equals(destination, itinerary.destination) &&
                Objects.equals(date, itinerary.date) &&
                Objects.equals(transportType, itinerary.transportType);
    }

    /**
     Επιστρέφει τον κωδικό hash του δρομολογίου.
     @return Ο κωδικός hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, date, availableSeats, cost, transportType);
    }
}