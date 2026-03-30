# Quantity Measurement App

### ✅ UC1
Implemented value-based equality comparison for Feet objects only, comparing measurements using overridden equals() and hashCode().

### ✅ UC2
Extended equality comparison to support both Feet and Inches, enabling cross-unit comparison (e.g., 12 inches = 1 foot).

### ✅ UC3
Enabled comparison between different length units by converting values to a common base unit before checking equality.

### ✅ UC4
Added support for multiple length units such as inches, feet, yards, and centimeters with defined conversion factors.

### ✅ UC5
Implemented unit conversion functionality to convert values between supported length units.

### ✅ UC6
Added addition functionality for quantities of the same unit while maintaining immutability.

### ✅ UC7
Enhanced addition to support different units by converting values to a common base unit before summation.

### ✅ UC8
Improved design by enforcing constructor validation, immutability, and correct implementation of equals() and hashCode().

### ✅ UC9
Introduced weight measurement support with units like kilogram, gram, and pound.
Enabled conversion and comparison of weight values using a common base unit.

### ✅ UC10
Implemented a generic Quantity class with a Unit interface to support multiple measurement categories.
Improved flexibility and reusability by handling length, weight, and volume in a unified structure.

### ✅ UC11
Added volume measurement support including litre, millilitre, and gallon with conversion logic.
Enabled equality checks and addition operations across different volume units.

### ✅ UC12
Implemented subtraction and division operations for quantity measurements.
Ensured accurate results by converting values to a common base unit before performing operations.

### ✅ UC13
Centralized arithmetic logic to enforce DRY principles across all quantity operations.
Reduced code duplication by handling addition, subtraction, and division in a common utility.

### ✅ UC14
Introduced temperature measurement with selective arithmetic support and measurable refactoring.
Restricted invalid operations while improving design using a common measurable abstraction.

### ✅ UC15
Refactored the application into an N-Tier architecture by separating it into presentation, service, and data access layers.
Improved code maintainability, scalability, and testability through clear separation of concerns.

### ✅ UC16
Integrated database persistence using JDBC to store and retrieve quantity measurement data.
Implemented CRUD operations ensuring efficient data handling and seamless interaction with the backend.

### ✅ UC17
Developed a Spring Boot backend for the Quantity Measurement application with RESTful APIs for handling measurement operations.
Implemented layered architecture (Controller, Service, Repository) for better scalability, maintainability, and separation of concerns.

### ✅ UC18
Implemented secure user authentication and authorization using Spring Security with JWT for stateless session management.
Integrated Google OAuth2 login and developed user management features for registration, login, and profile handling.
