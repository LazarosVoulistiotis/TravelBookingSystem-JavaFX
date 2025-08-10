package travelbookingapp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 Προσαρμοσμένος adapter για τη σειριοποίηση και αποσειριοποίηση του LocalDate σε XML.
 Μετατρέπει το LocalDate σε συμβολοσειρά μορφής ISO_LOCAL_DATE (YYYY-MM-DD) και αντίστροφα.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // Μορφή: YYYY-MM-DD

    /**
     Μετατρέπει μια συμβολοσειρά ημερομηνίας σε LocalDate.
     @param v Η συμβολοσειρά που αντιπροσωπεύει την ημερομηνία.
     @return Το αντικείμενο LocalDate.
     @throws Exception Αν η συμβολοσειρά δεν είναι έγκυρη ημερομηνία.
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, formatter);
    }

    /**
     Μετατρέπει ένα LocalDate σε συμβολοσειρά ημερομηνίας.
     @param v Το αντικείμενο LocalDate.
     @return Η συμβολοσειρά που αντιπροσωπεύει την ημερομηνία.
     @throws Exception Αν προκύψει σφάλμα κατά τη μετατροπή.
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }
}