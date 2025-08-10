package travelbookingapp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

//Συγκεντρώνει όλα τα δεδομένα της εφαρμογής, όπως πελάτες, δρομολόγια και κρατήσεις
@XmlRootElement
public class TravelData {
    private List<Customer> customers = new ArrayList<>();
    private List<Itinerary> itineraries = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    @XmlElement(name = "customer")
    /**
     Επιστρέφει τη λίστα των πελατών.
     @return Λίστα πελατών.
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     Ορίζει τη λίστα των πελατών, δημιουργώντας αντίγραφο για ασφάλεια.
     @param customers Η νέα λίστα πελατών.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = new ArrayList<>(customers);
    }

    /**
     Προσθέτει έναν πελάτη στη λίστα.
     @param customer Ο πελάτης που θα προστεθεί.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     Αφαιρεί έναν πελάτη από τη λίστα.
     @param customer Ο πελάτης που θα αφαιρεθεί.
     */
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @XmlElement(name = "itinerary")
    /**
     Επιστρέφει τη λίστα των δρομολογίων.
     @return Λίστα δρομολογίων.
     */
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     Ορίζει τη λίστα των δρομολογίων, δημιουργώντας αντίγραφο.
     @param itineraries Η νέα λίστα δρομολογίων.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = new ArrayList<>(itineraries);
    }

    /**
     Προσθέτει ένα δρομολόγιο στη λίστα.
     @param itinerary Το δρομολόγιο που θα προστεθεί.
     */
    public void addItinerary(Itinerary itinerary) {
        itineraries.add(itinerary);
    }

    /**
     Αφαιρεί ένα δρομολόγιο από τη λίστα.
     @param itinerary Το δρομολόγιο που θα αφαιρεθεί.
     */
    public void removeItinerary(Itinerary itinerary) {
        itineraries.remove(itinerary);
    }
    
    @XmlElement(name = "booking")
    /**
     Επιστρέφει τη λίστα των κρατήσεων.
     @return Λίστα κρατήσεων.
     */
    public List<Booking> getBookings() {
        return bookings;
    }
    
    /**
     Ορίζει τη λίστα των κρατήσεων, δημιουργώντας αντίγραφο.
     @param bookings Η νέα λίστα κρατήσεων.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = new ArrayList<>(bookings);
    }
    
    /**
     Προσθέτει μια κράτηση στη λίστα.
     @param booking Η κράτηση που θα προστεθεί.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    /**
     Αφαιρεί μια κράτηση από τη λίστα.
     @param booking Η κράτηση που θα αφαιρεθεί.
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}