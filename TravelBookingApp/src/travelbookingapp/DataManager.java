package travelbookingapp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

// Διαχειρίζεται την αποθήκευση και φόρτωση δεδομένων σε XML.
public class DataManager {
    private static final String DATA_FILE = "travel_data.xml";

    /**
     Αποθηκεύει τα δεδομένα της εφαρμογής σε αρχείο XML.
     @param data Το αντικείμενο TravelData που θα αποθηκευτεί.
     @throws JAXBException Αν προκύψει σφάλμα κατά την αποθήκευση.
     */
    public static void saveData(TravelData data) throws JAXBException {
        try {
            System.out.println("Προσπάθεια αποθήκευσης στο αρχείο: " + DATA_FILE);
            JAXBContext context = JAXBContext.newInstance(TravelData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(DATA_FILE));
            System.out.println("Η αποθήκευση ολοκληρώθηκε επιτυχώς!");
        } catch (JAXBException e) {
            System.err.println("Σφάλμα JAXB κατά την αποθήκευση: " + e.getMessage());
            e.printStackTrace();
            throw new JAXBException("Σφάλμα κατά την αποθήκευση δεδομένων: " + e.getMessage(), e);
        }
    }

    /**
     Φορτώνει τα δεδομένα της εφαρμογής από αρχείο XML.
     @return Το αντικείμενο TravelData με τα φορτωμένα δεδομένα ή νέο αντικείμενο αν το αρχείο δεν υπάρχει.
     @throws JAXBException Αν προκύψει σφάλμα κατά τη φόρτωση.
     */
    public static TravelData loadData() throws JAXBException {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Το αρχείο δεν υπάρχει, επιστρέφεται νέο TravelData.");
            return new TravelData();
        }
        try {
            System.out.println("Φόρτωση δεδομένων από: " + DATA_FILE);
            JAXBContext context = JAXBContext.newInstance(TravelData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (TravelData) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.err.println("Σφάλμα JAXB κατά τη φόρτωση: " + e.getMessage());
            e.printStackTrace();
            throw new JAXBException("Σφάλμα κατά τη φόρτωση δεδομένων: " + e.getMessage(), e);
        }
    }
}