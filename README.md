# 🐾 Huellas Felices – Pet Report Manager (GUI Version)

A Java Swing application developed as part of the **Programming II (Intermediate)** course at UNED.  
This project evolves the console-based system into a graphical interface, applying **inheritance, polymorphism, and file persistence** to manage reports of lost and found pets.

## 🚀 Features
- Register lost or found pets with validated input fields:
  - Unique Report ID (`REP-0001`)
  - Costa Rican ID (`1-1111-1111`)
  - Full name (≥ 7 characters)
  - Type of report (`PDR` = Lost, `ENC` = Found)
  - Species (`DOG` or `CAT`)
  - Zone (≤ 30 characters)
  - Phone (`####-####`)
  - Color, distinctive marks (≥ 10 characters)
  - Optional microchip code
- Consult reports by ID, species, or zone.
- Generate reports:
  - **General**: full list of cases
  - **Grouped**: counts by type (PDR/ENC) and species (DOG/CAT)
  - **Coincidences**: detect matches between lost and found pets (species, color, zone, date ≤ 7 days, microchip priority).
- Update reports (edit fields except Report ID, Reporter ID, and Date).
- Visual coincidence detection displayed in tables (`JTable`).
- Data persistence using plain text files (`ReportesPerdidas.txt`, `ReportesEncontradas.txt`).

## 🛠️ Technologies
- Java (NetBeans IDE)
- Swing GUI (built with NetBeans GUI Builder)
- Object-Oriented Programming (inheritance, polymorphism, encapsulation)
- File I/O with `FileReader`, `BufferedReader`, `FileWriter`, `BufferedWriter`, `PrintWriter`
- Exception handling (`IOException`, `NumberFormatException`, etc.)
- Input validation with regular expressions

## 📂 Project Structure
- `Mascota.java` → base class with common attributes (species, color, marks, microchip)
- `Perro.java` / `Gato.java` → subclasses with specific attributes
- `ReporteMascota.java` → base class for reports
- `ReportePerdida.java` / `ReporteEncontrada.java` → subclasses fixing type (PDR/ENC)
- GUI forms built with NetBeans GUI Builder (registration, consultation, reports, coincidences)

## 📊 Example Outputs
- **General Report**: table with ID, name, date, zone, type.
- **Grouped Report**: counts by type and species.
- **Coincidence Report**: pairs of lost/found pets, highlighting priority matches (microchip).

## 🎯 Learning Outcomes
This project demonstrates:
- Applying inheritance and polymorphism in Java
- Designing modular, well-structured OOP systems
- Building GUIs with Swing and NetBeans GUI Builder
- Managing persistence with text files (non-binary, non-serialized)
- Implementing robust validation and exception handling

---

👩‍💻 Author: Elvia Sánchez Leiva  
📚 Course: Programming II – UNED  
