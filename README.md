
# Price Comparator

**Price Comparator** is a Java console application designed to analyze and compare product prices across various retailers. It leverages JPA for database interactions and Maven for project management, providing insights into pricing trends and helping users identify the best deals available.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)

## Features
- **Best Discounts:** List products with the biggest percentage discounts on a given date, helping users identify the best deals available at any given time.
- **New Discounts:** Displays discounts recently introduced within a specific time frame (for example, the last 24 hours), allowing users to quickly detect promotional offers. You can filter promotional offers based on promotions that are active at the time of the search or those that are inactive, upcoming, or have been active in the past.
- **Product Substitutes & Recommendations:** Highlight products with better value per unit (e.g., per kilogram or per liter), enabling users to make informed decisions when comparing different packaging sizes or brands.
- **Create Price Alert:** Allows users to define a target price for certain products. The system monitors prices and notifies users upon accessing View Price Alert when the price drops to or below the set threshold.
- **View Price Alert:** Enables controlled access to alerts for products whose prices have dropped to or below the specified target.
- **SQL Data Import Scripts:** Includes a migrations/ directory under resources/, containing versioned SQL scripts used to populate the database with data from csv files about products and discounts data for various retailers. These are executed automatically at startup when necessary, simulating a real-world data feed.

## Technologies Used

- **Java 21**
- **Jakarta Persistence API (JPA)**
- **Maven** for build and dependency management
- **PostgreSQL** as the relational database
- **Lombok** to reduce boilerplate code 

### Installation

```bash
git clone https://github.com/denisa110/price-comparator.git
```
Update the `persistence.xml` file with your local database credentials.

### Build and Run

```bash
mvn clean install
```
Start the application from the mainClass="ro.accesa.Main"

## Configure the Database
   - A PostgreSQL database named `priceComparatorDB`.
   - In the `persistence.xml` file located in `src/main/resources/META-INF/` I configured JDBC connection:

     ```xml
     <properties>
     <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>
     <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/priceComparatorDB"/>
     <property name="jakarta.persistence.jdbc.user" value="postgres"/>
     <property name="jakarta.persistence.jdbc.password" value="****"/>
     </properties>
     ```

## Project Structure
- The project structure represented here is created using https://tree.nathanfriend.com/ . 
```
PriceComparator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ro.accesa/
│   │   │       ├── dto/              # Data transfer object   
│   │   │       ├── entity/           # JPA entity classes (Product, Retailer, PriceHistory, DiscountHistory, PriceAlert, PersistenceEntity, Category)
│   │   │       ├── repository/       # Data access layers
│   │   │       ├── service/          # Business logic
│   │   │       ├── util/                   # util classes (ConsoleUtils)
│   │   │       ├── Main.java               # Application entry point
│   │   │       └── MenuHandler.java        # Menu controller logic
│   │   └── resources/
│   │       ├── META-INF/
│   │       │     └── persistence.xml    # JPA configuration
│   │       │                      
│   │       └── migrations        # Data migration scripts
├── pom.xml                       # Maven configuration file   
└── README.md                     # Project documentation        

```

## How to Use the Implemented Features

After running the application, a console-based interactive menu will be displayed. The following options are available:

1. **Display Best Discounts**
     - Choose option `1` from the menu.
     - Enter the date (`YYYY-MM-DD`) to view active discounts.
     - Optionally enter a retailer name or leave blank to search all.
     - Specify the number of results to view top discounts by percentage.

2. **Display New Discounts**
     - Choose option `2`.
     - Enter the number of hours to look back (e.g., 400).
     - Specify whether to view only active discounts (`Y/N`).

3. **Create a Price Alert**
     - Choose option `3`.
     - Provide the product name and a target price in RON.
     - The alert is saved and triggered when the product's price drops to or below the threshold.

4. **Check Triggered Alerts**
     - Choose option `4`.
     - Enter the product name to check which alerts have been triggered.

5. **Show Best Value Products by Category**
     - Choose option `5`.
     - Enter the name of the product category (e.g., “băuturi”).
     - The app will display recommended products sorted by value per unit (kg, liter, etc.).

## Future Enhancements

- **Spring Boot Integration**: Migrate the current structure into a Spring Boot project to enable easier RESTful API creation, dependency management, and scalability.
- **RESTful API Endpoints**: Implement REST controllers to expose core functionalities (e.g., best discounts, new discounts, product alerts) via HTTP endpoints.
- **User Authentication**: Add user login and personalized tracking features.
- **User Interface**: Connect the backend to a modern frontend framework like React or Angular.
- **Real-Time Notifications**: Add scheduled background jobs or WebSocket-based notifications to alert users in real time about new discounts or price alerts.
- **Dynamic Price History Graphs:** Implement interactive charts to visualize the price evolution of products over time, allowing users to identify trends and make informed purchasing decisions.