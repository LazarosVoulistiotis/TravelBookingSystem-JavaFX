package travelbookingapp;

import javax.xml.bind.annotation.XmlRootElement;

// Αναπαριστά έναν πελάτη στο σύστημα με όνομα, email και τηλέφωνο.
@XmlRootElement
public class Customer {
    private String name;
    private String email;
    private String phone;

    // Προεπιλεγμένος κατασκευαστής για JAXB.
    public Customer() {}

    /**
     Κατασκευαστής με όλες τις ιδιότητες.
     @param name Όνομα του πελάτη.
     @param email Email του πελάτη.
     @param phone Τηλέφωνο του πελάτη.
     */
    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     Επιστρέφει το όνομα του πελάτη.
     @return Το όνομα.
     */
    public String getName() { return name; }

    /**
     Ορίζει το όνομα του πελάτη.
     @param name Το νέο όνομα.
     */
    public void setName(String name) { this.name = name; }

    /**
     Επιστρέφει το email του πελάτη.
     @return Το email.
     */
    public String getEmail() { return email; }

    /**
     Ορίζει το email του πελάτη.
     @param email Το νέο email.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     Επιστρέφει το τηλέφωνο του πελάτη.
     @return Το τηλέφωνο.
     */
    public String getPhone() { return phone; }

    /**
     Ορίζει το τηλέφωνο του πελάτη.
     @param phone Το νέο τηλέφωνο.
     */
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, email, phone);
    }
}