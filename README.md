# Travel Booking System (JavaFX)



## ✈️ Project Overview

The **Travel Booking System** is a robust JavaFX application designed to streamline and automate the core operations of a travel agency. Developed as an academic project for the *Advanced Programming* course, this system provides comprehensive tools for managing customers, travel itineraries, bookings, and generating insightful reports. It aims to reduce manual workload, enhance operational efficiency, and deliver an intuitive, user-friendly experience.

Built with a focus on modern Java development practices, the application leverages JavaFX for its rich user interface capabilities and JAXB (Java Architecture for XML Binding) for reliable and persistent data storage in XML format.

## ✨ Key Features
This system offers a suite of functionalities essential for any travel agency:

* **Customer Management:**
  * Add new customers with details (name, email, phone) and input validation.
  * Edit existing customer information.
  * Delete customer records with confirmation.
* **Itinerary Management:**
  * Register new travel itineraries (destination, date, available seats, cost, transport type) with robust input validation.
  * Modify existing itinerary details.
  * Remove itineraries, with automatic list updates and data persistence.
  * Example: Easily add a new route to "Athens" for "25/10/2023" with "50" seats and a "100.0" cost by "Airplane". 
* **Booking Management:**
  * Create new bookings by linking customers to available itineraries.
  * Automatic real-time seat availability checks and updates upon booking.
  * Cancel existing bookings, restoring seat availability.
 * **Reporting:**
  * Generate and view historical booking reports per customer, providing valuable data analysis capabilities.
* **Enhanced User Experience (UX):**
  * **Clear Layout:** Logically organized input fields and buttons for intuitive navigation.
  * **Instant Feedback:** Clear alerts for successful operations ("The itinerary was successfully added") and error messages ("Please fill in all fields").
  * **Automation:** Input fields automatically clear after successful actions; lists update in real-time.
  * **Aesthetic Design:** Visually appealing interface with CSS styling and background imagery.

## 🚀 Technologies Used
The application is built upon a modern Java technology stack:
* **JavaFX:** Utilized for building the rich, interactive, and visually appealing Graphical User Interface (GUI). It provides structured layout components like GridPane and StackPane.
* **JAXB (Java Architecture for XML Binding):** Employed for seamless serialization and deserialization of application data to and from XML files, ensuring reliable data persistence without the need for an external database. Chosen for its simplicity, readability, and compatibility with Java.
* **CSS (Cascading Style Sheets):** Applied to enhance the application's visual aesthetics, providing custom styling for elements such as button colors, text fields, and overall layout.

## ⚙️ Algorithmic Solutions & Design Principles
The system incorporates key algorithmic and design patterns for robust operation:
* **Data Persistence (XML via JAXB):**
  * *Problem:* Ensure data is saved permanently across application sessions.
  * *Solution:* JAXB is used to serialize application objects (Customers, Itineraries, Bookings) into travel_data.xml upon application closure or data modification, and deserialize them upon startup. This choice prioritizes simplicity and readability over complex database management.
* **Real-time Availability Updates:**
  * *Problem:* Maintain accurate seat availability and reflect changes instantly in the UI.
  * *Solution:* JavaFX bindings are strategically used to link UI elements directly to data model properties. For instance, when a booking is made, the availableSeats property of an Itinerary object is updated, and the UI automatically reflects this change without explicit refresh calls.
* **Robust Error Handling:**
  * *Problem:* Prevent incorrect operations due to invalid user input or problems during data storage.
  * *Solution:* Comprehensive input validation checks are implemented for all fields (e.g., non-empty, valid numerical values, correct formats for email/phone). try-catch blocks manage exceptions during data loading/saving, providing user-friendly error messages that guide the user to correct issues.

## 🏁 Getting Started
Follow these steps to set up and run the Travel Booking System on your local machine.

**Prerequisites**
* **Java Development Kit (JDK):**
  * Recommended: Java 8 or Java 11.
   * Important Note for Java 15+ Users: The Nashorn JavaScript engine, crucial for JAXB's XML file generation in some older configurations, was removed in Java 15. If using Java 15 or newer, you must include the GraalVM JavaScript JAR as a project dependency.
    * Download the org.graalvm.js:js:23.0.0 (or latest compatible) JAR from Maven Central Repository.
    * Add the JAR to your project: In Netbeans, navigate to Project Properties > Libraries > Add JAR/Folder and select the downloaded JAR.
* **Netbeans IDE:** Version 8 or newer is recommended for project compatibility.

**Running the Application**
1. **Clone the Repository:**

>bash
>
>git clone
>
>cd TravelBookingSystem-JavaFX

2. Run *TravelBookingApp:* Locate the *TravelBookingApp* class (likely in *src/main/java/com/travelbookingsystem/app/*) and run it directly from the Netbeans Run menu.
3. **Data Loading:** The application will automatically attempt to load existing data from *travel_data.xml* if the file is present in the project root.
4. **Troubleshooting:** If you encounter any errors, check the Netbeans console for detailed exception messages.

## 📚 Basic Usage Guide
The application is designed for intuitive use. Here's a quick guide to its main sections:
* **Main Window:** Upon launch, you'll see the main menu. 
* **Customer Management:** Navigate to Πελάτες > Διαχείριση Πελατών. Add, edit, or delete customer details. 
* **Itinerary Management:** Go to Δρομολόγια > Διαχείριση Δρομολογίων. Add new routes, modify existing ones, or remove them. 
  * Example: Adding an Itinerary: Fill in Destination (e.g., "Αθήνα"), Date (use DatePicker, e.g., "25/10/2023"), Seats (e.g., "50"), Cost (e.g., "100.0"), Medium (e.g., "Αεροπλάνο"), then click "Προσθήκη Δρομολογίου".
  * Example: Error Handling: Try adding an itinerary without filling all fields to see the "Παρακαλώ συμπληρώστε όλα τα πεδία" (Please fill in all fields) message. 
* **Booking Management:** Access via Κρατήσεις > Διαχείριση Κρατήσεων. Select a customer and itinerary to create a booking, or cancel an existing one. 
**Reports Viewing:** Find this under Αναφορές > Προβολή Αναφορών. Select a customer to view their booking history. 

## 📊 System Architecture & Visuals
* **UML Diagrams**
Understanding the system's structure and behavior is crucial for developers and evaluators.

* **Class Diagram (Figure 1)**: Illustrates the static structure of the application, showing key classes like Customer, Itinerary, Booking, and TravelData, along with their relationships (e.g., composition, association). This provides a foundational understanding of the data model.

![Image](https://github.com/user-attachments/assets/66547d60-ca43-40c7-bfb7-3fff7b1777b9) 

Sequence Diagram (Figure 2): Depicts the dynamic behavior of a critical process: the creation of a booking. It visualizes the flow of messages exchanged between objects, from user interaction to data persistence, offering insights into the system's runtime logic.

<img width="576" height="628" alt="Image" src="https://github.com/user-attachments/assets/5256e35f-a01d-4f31-83d4-cb5f1f3e751c" /> 

**Screenshots**

A visual tour of the application's user interface, showcasing its design and key functionalities:

**Main Window:**

<img width="576" height="451" alt="Image" src="https://github.com/user-attachments/assets/14505603-13e7-4c6c-afef-c3253181bf3b" />

**Customer Management:**

<img width="576" height="333" alt="Image" src="https://github.com/user-attachments/assets/f33941b0-1c15-4815-a2ae-deb62b566a34" />

**Itinerary Management:**

<img width="576" height="455" alt="Image" src="https://github.com/user-attachments/assets/56aee8ca-862d-4249-a329-27fb22823038" />

**Booking Management:**

<img width="576" height="339" alt="Image" src="https://github.com/user-attachments/assets/2a943cd0-7cdc-46e2-9446-6b498eec6624" />

**Reports Viewing:**

<img width="576" height="452" alt="Image" src="https://github.com/user-attachments/assets/e509f223-3240-45ea-a228-b2e9b3fb852b" /> 

## 🔮 Future Enhancements
The Travel Booking System is designed with extensibility in mind, allowing for continuous improvement and adaptation to evolving business needs. Potential future improvements include:
* **User Role Management:** Implement distinct user roles (e.g., Administrator, Employee) with varying access permissions to specific functionalities, enhancing security and operational control.
* **Advanced Data Validation:** Further strengthen input validation, including regex-based checks for email formats, phone numbers, and more complex business rules.
* **Search and Filtering:** Add search bars and filter options to customer and itinerary lists for easier navigation and data retrieval, especially as data volume grows.
* **Reporting Enhancements:** Introduce more sophisticated reporting features, such as graphical summaries, custom date ranges, and export options.

## 🤝 Contributing
Contributions are welcome! If you have suggestions for improvements or new features, please open an issue or submit a pull request.
