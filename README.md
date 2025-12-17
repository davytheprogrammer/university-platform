# University Platform Core Components - COMP 305 Assignment

## Overview
This project implements the backend Java code for a University Online Learning Platform, demonstrating core OOP concepts, abstraction, interfaces, collections, exception handling, and I/O operations.

## Components Implemented

### Part I: Core OOP Concepts
- **Student Class**: Encapsulation with private attributes, constructors, getters/setters
- **Course Class**: ArrayList management, student enrollment
- **OnlineCourse Class**: Inheritance, method overriding, dynamic binding demonstration

### Part II: Abstraction and Interfaces
- **Payable Interface**: Contract for payment calculations
- **FullTimeLecturer & PartTimeLecturer**: Interface implementations
- **Comparable Interface**: Natural ordering for Student objects

### Part III: Collections and Exception Handling
- **HashMap**: Student grades storage and iteration
- **GradeNotFoundException**: Custom exception class
- **Exception Handling**: Try-catch blocks with custom exceptions

### Part IV: I/O and Database Integration
- **File Processing**: Character streams with BufferedReader/Writer
- **JDBC Integration**: Database validation with PreparedStatement

## Key Concepts Demonstrated

### Dynamic Binding
The Main class shows how a Course reference can hold an OnlineCourse object, and the overridden showDetails() method is called at runtime.

### Comparable Interface
Enables natural ordering of Student objects based on regNo, allowing use with Collections.sort().

### Stream Types
- **Byte Streams**: Handle raw binary data (InputStream/OutputStream)
- **Character Streams**: Handle text data with character encoding (Reader/Writer)

### Multithreading Considerations
For concurrent file processing:
- Use Thread class or Runnable interface
- Synchronization needed for shared variables (word count)
- Consider thread-safe collections for shared data

### GUI Components (Login Sketch)
```
JFrame (Main Window)
├── JLabel ("Registration Number:")
├── JTextField (regNoField)
├── JLabel ("Password:")
├── JPasswordField (passwordField)
└── JButton ("Login") + ActionListener
```

## Running the Code
```bash
javac *.java
java Main
```

## Files Structure
- Student.java - Student class with Comparable
- Course.java - Base course class
- OnlineCourse.java - Inherited online course
- Payable.java - Payment interface
- FullTimeLecturer.java - Full-time payment implementation
- PartTimeLecturer.java - Part-time payment implementation
- GradeNotFoundException.java - Custom exception
- Main.java - Main demonstration class
- input.txt - Sample input for file processing
