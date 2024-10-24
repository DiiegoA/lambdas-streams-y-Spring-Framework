
# ScreenMatch and Logger Applications

## Overview
The **ScreenMatch**, **Logger** and **Ejercicio1** applications are Java-based systems designed to manage and organize multimedia content such as episodes, series, and anime, along with robust logging functionality. These systems include capabilities for consuming external APIs, converting data formats, and error handling with comprehensive logging.

The applications are ideal for users who want to:
- Manage large collections of series, episodes, and anime.
- Convert and structure data obtained from external sources like APIs.
- Efficiently handle multimedia content metadata.
- Implement robust logging and error management in applications.

## Key Features

### **1. Multimedia Content Management**
The applications provide tools for managing multimedia content, particularly for series, episodes, and anime:
- **com.aluracursos.screenmatch.menu.MenuPrincipal**: Central class for interacting with series, episodes, anime, and seasons.
- **com.aluracursos.screenmatch.model.DatosEpisodio, DatosSerie, DatosTemporadas, DatosAnime, DatosTitulo**: Handle metadata related to episodes, series, anime, and general titles.

### **2. Anime-Specific Features**
- **com.aluracursos.ejercicio1.model.Anime**: Special class dedicated to managing anime-specific content.
- **com.aluracursos.ejercicio1.model.DatosAnime**: Handles metadata related to anime series, allowing the management of specific attributes unique to anime.

### **3. Data Conversion and API Integration**
- **com.aluracursos.screenmatch.service.ConsumoApi** and **com.aluracursos.ejercicio1.service.ConsumoApi**: Provides methods to consume and process data from external APIs.
- **com.aluracursos.screenmatch.service.ConvierteDatos and IConvierteDatos**: Converts JSON data into Java objects using a generic interface for flexibility across different data types. The recent update enhances conversion efficiency and extends support for additional formats.

### **4. Logging and Error Handling**
- **com.aluracursos.logger.loggerbase.LoggerBase and LoggerBaseImpl**: Classes that handle logging throughout the application. These ensure robust error handling and provide a detailed log of system events and user interactions.

### **5. Streams and Functional Programming**
- **com.aluracursos.screenmatch.menu.EjemploStreams**: Demonstrates the usage of Java Streams for handling lists and applying filters, sorting, and transformations.

## Folder Structure
The project follows a structured format with the following key files and directories:

### ScreenMatch Application
- **com.aluracursos.screenmatch.ScreenmatchApplication**: Main application class, bootstraps the Spring application and handles the menu execution.
- **com.aluracursos.screenmatch.menu.MenuPrincipal**: Core menu functionality where users interact with multimedia management features.
- **com.aluracursos.screenmatch.menu.EjemploStreams**: Provides examples on how to handle Java Streams for filtering and sorting content.
- **com.aluracursos.screenmatch.model.DatosEpisodio, DatosSerie, DatosTemporadas**: Handle metadata related to series, episodes, and seasons.

### Logger Application
- **com.aluracursos.logger.loggerbase.LoggerBase and LoggerBaseImpl**: Handle logging and error management for robust application performance.

### Ejercicio1 Application (Anime and Additional Features)
- **com.aluracursos.ejercicio1.main.Main**: Main application entry point for the anime and additional functionality.
- **com.aluracursos.ejercicio1.model.Anime, DatosAnime, DatosEpisodio, DatosTitulo**: Manage anime-related content and general title metadata.

## System Requirements
To run these applications, ensure your system meets the following requirements:
- **Java SDK 8 or higher**: The applications rely on Java for their core functionalities.
- **Spring Boot**: Used to bootstrap and run the application.
- **Internet connection**: Required for API calls when using `ConsumoApi`.

## How to Run

### Step 1: Clone the Repository
Begin by cloning the project repository to your local machine using the following command:
```bash
git clone https://github.com/DiiegoA/persistencia-de-datos-y-consultas-con-Spring-Data-JPA.git
```

### Step 2: Open and Build the Project
1. **Open IntelliJ IDEA**: Import the project as a Maven/Gradle project if needed.
2. **Build the Project**: Go to `Build > Build Project` or press `Ctrl + F9`.

### Step 3: Run the Application
- Navigate to the relevant `ScreenmatchApplication.java` or `Main.java` and run the application by right-clicking and selecting `Run` or by using the play button.

## Future Features
- Additional media management functionalities.
- Enhanced recommendation engine based on user preferences.
- Advanced error handling and logging.

---

This document has been updated to include all the files and functionalities across the applications provided.
