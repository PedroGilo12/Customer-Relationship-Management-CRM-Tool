# Customer Relationship Management (CRM) Tool

## Table of Contents

- [Author](#Author)
- [Last Updates](#last-updates)
- [Remaing Updates](#remaining-updates)
- [Usage](#usage)
- [How does it work?](#how-does-it-work)

## Author
Pedro Henrique Vieira Giló (phvg@ic.ufal.br)

## Last Updates
* Contact Management: Storing and managing customer contact information;
* Sales Pipeline Management: Managing the stages of the sales process;
* Reporting and Analytics: Providing sales reports and customer analytics;
* Activity Tracking: Tracking interactions with customers, such as calls and meetings;

## Remaining Updates

* ~~Contact Management: Storing and managing customer contact information;~~
* ~~Sales Pipeline Management: Managing the stages of the sales process~~;
* ~~Activity Tracking: Tracking interactions with customers, such as calls and meetings;~~
* ~~Task and Appointment Scheduling: Organizing tasks and appointments with customers;~~
* Email Integration and Campaign Management: Managing email communications and marketing campaigns;
* Lead Generation and Tracking: Tracking potential leads and their progress;
* Customizable Dashboards: Offering customizable dashboards for different user roles;
* ~~Reporting and Analytics: Providing sales reports and customer analytics~~;
* Document Storage and Management: Storing and managing sales and marketing documents;
* Mobile Access and Integration: Ensuring CRM accessibility and functionality on mobile

# Usage

## Project Setup and Execution Guide

### To run the application using java

Open the terminal and type:

```bash
java -cp "ClientConnect.jar;lib\gson-2.8.2.jar;lib\itextpdf-5.5.9.jar" application.Main
```

Or run it on Windows CMD using the run.bat script:

```bash
run.bat
```

### Compile The Code

Open a terminal, navigate to your project directory, and compile your code:

```bash
javac -d out -cp "lib\gson-2.8.2.jar;lib\itextpdf-5.5.9.jar" application\Main.java utilities\*.java adapter\*.java userInterface\*.java dataInterface\*.java application\*.java
jar -cvfe ClientConnect.jar application.Main -C out .
```
Or compile it on windows CMD using the install.bat script:

```bash
install.bat
```
# How does it work?

The project incorporates two design patterns to enhance its functionality: the adapter and the state pattern. The adapter pattern is employed to modularize the application, rendering it independent of the interfaces used for user interaction. This strategic design allows for the prospective implementation of mobile functionality without requiring alterations to the primary application. In contrast, the state pattern is used to streamline the augmentation of code functionalities through a unified menu interface. This approach facilitates the seamless implementation of new screens within the framework of a finite state machine.

![General Diagram](img\general.jpg)

![Fsm diagram](img\fsm.jpg)
