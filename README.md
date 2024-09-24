Before you start, ensure you have the following installed on your system:
* Java Development Kit (JDK) 17 or higher
* Maven
* MySQL
* JavaFX SDK
* An IDE like IntelliJ IDEA or Eclipse
**1. Pull the Latest Changes from the Remote Repository**
**2. Project Setup**
**2.1. JavaFX Configuration**
Download JavaFX SDK:

Go to the JavaFX website and download the SDK for your operating system.
Set Up JavaFX in Your IDE:

IntelliJ IDEA:
Open your project in IntelliJ IDEA.
Go to File > Project Structure > Modules.
Click on + to add a new module and select JavaFX.
Set the path to the JavaFX SDK you downloaded.
Add VM options to your run configuration:
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml

**2.2. Backend Configuration**
Clone the Repository:

Clone the project repository to your local machine.
Configure MySQL Database:

Create a new database in MySQL, for example, news_db.
Update the application.properties file with your MySQL credentials:

**2.3 Run the Spring Boot Application**

**3. Using the Application**
**3.1. Backend**
The backend service will automatically fetch news from the kun.uz website every 20 minutes and store it in the MySQL database.
**3.2. Frontend**
The frontend application (NewsApp) allows users to view news articles.
Users can select from three time periods: Morning, Day, and Evening to filter news articles based on the publication time.
**4. Summary**
Install JavaFX SDK and configure it in your IDE.
Set up and configure the MySQL database.
Run the Spring Boot backend to start collecting news.
Run the JavaFX frontend application to view and filter news articles.
