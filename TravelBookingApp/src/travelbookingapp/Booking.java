package travelbookingapp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

/**
 Αναπαριστά μια κράτηση στο σύστημα ταξιδιωτικών κρατήσεων.
 Περιλαμβάνει στοιχεία για τον πελάτη, το δρομολόγιο, την ημερομηνία κράτησης
 και την κατάσταση ακύρωσης.
 */
@XmlRootElement
public class Booking {
    private Customer customer;
    private Itinerary itinerary;
    private LocalDate bookingDate;
    private boolean cancelled;

    /**
     Προεπιλεγμένος κατασκευαστής για JAXB. Απαιτείται για τη σωστή
     σειριοποίηση και αποσειριοποίηση σε XML.
     */
    public Booking() {}

    /**
     Κατασκευαστής που αρχικοποιεί μια νέα κράτηση με τα παρεχόμενα στοιχεία.
     @param customer    Ο πελάτης που πραγματοποιεί την κράτηση.
     @param itinerary   Το δρομολόγιο που κρατήθηκε.
     @param bookingDate Η ημερομηνία που έγινε η κράτηση.
     @throws IllegalArgumentException Αν κάποιο από τα ορίσματα είναι null.
     */
    public Booking(Customer customer, Itinerary itinerary, LocalDate bookingDate) {
        if (customer == null || itinerary == null || bookingDate == null) {
            throw new IllegalArgumentException("Τα πεδία customer, itinerary και bookingDate δεν μπορούν να είναι null.");
        }
        this.customer = customer;
        this.itinerary = itinerary;
        this.bookingDate = bookingDate;
        this.cancelled = false;
    }

    /**
     Επιστρέφει τον πελάτη που σχετίζεται με την κράτηση.
     @return Ο πελάτης της κράτησης.
     */
    @XmlElement
    public Customer getCustomer() {
        return customer;
    }

    /**
     Ορίζει τον πελάτη για την κράτηση.
     @param customer Ο νέος πελάτης για την κράτηση.
     @throws IllegalArgumentException Αν ο πελάτης είναι null.
     */
    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Ο πελάτης δεν μπορεί να είναι null.");
        }
        this.customer = customer;
    }

    /**
     Επιστρέφει το δρομολόγιο που σχετίζεται με την κράτηση.
     @return Το δρομολόγιο της κράτησης.
     */
    @XmlElement
    public Itinerary getItinerary() {
        return itinerary;
    }

    /**
     Ορίζει το δρομολόγιο για την κράτηση.
     @param itinerary Το νέο δρομολόγιο για την κράτηση.
     @throws IllegalArgumentException Αν το δρομολόγιο είναι null.
     */
    public void setItinerary(Itinerary itinerary) {
        if (itinerary == null) {
            throw new IllegalArgumentException("Το δρομολόγιο δεν μπορεί να είναι null.");
        }
        this.itinerary = itinerary;
    }

    /**
     Επιστρέφει την ημερομηνία κράτησης.
     @return Η ημερομηνία κράτησης.
     */
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     Ορίζει την ημερομηνία κράτησης.
     @param bookingDate Η νέα ημερομηνία κράτησης.
     @throws IllegalArgumentException Αν η ημερομηνία είναι null.
     */
    public void setBookingDate(LocalDate bookingDate) {
        if (bookingDate == null) {
            throw new IllegalArgumentException("Η ημερομηνία κράτησης δεν μπορεί να είναι null.");
        }
        this.bookingDate = bookingDate;
    }

    /**
     Επιστρέφει την κατάσταση ακύρωσης της κράτησης.
     @return true αν η κράτηση έχει ακυρωθεί, false αλλιώς.
     */
    @XmlElement
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     Ορίζει την κατάσταση ακύρωσης της κράτησης.
     @param cancelled Η νέα κατάσταση ακύρωσης.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     Επιστρέφει μια συμβολοσειρά που αναπαριστά την κράτηση.
     @return Μια μορφοποιημένη συμβολοσειρά με τα στοιχεία της κράτησης.
     */
    @Override
    public String toString() {
        String status = cancelled ? " (Ακυρωμένη)" : "";
        return String.format("%s -> %s (%s)%s",
                customer.getName(),
                itinerary.getDestination(),
                bookingDate,
                status);
    }

    /**
     Ελέγχει αν δύο κρατήσεις είναι ίδιες με βάση τα στοιχεία τους.
     @param o Το αντικείμενο προς σύγκριση.
     @return true αν οι κρατήσεις είναι ίδιες, false αλλιώς.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return cancelled == booking.cancelled &&
                Objects.equals(customer, booking.customer) &&
                Objects.equals(itinerary, booking.itinerary) &&
                Objects.equals(bookingDate, booking.bookingDate);
    }

    /**
     Επιστρέφει τον κωδικό hash της κράτησης.
     @return Ο κωδικός hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customer, itinerary, bookingDate, cancelled);
    }
}