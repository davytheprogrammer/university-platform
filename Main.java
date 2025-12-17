import java.util.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // Dynamic Binding Demonstration
        Course courseRef = new OnlineCourse("COMP305", "OOP II", "Zoom");
        // This calls OnlineCourse's overridden method due to dynamic binding
        // The JVM determines at runtime which method to call based on the actual object type
        courseRef.showDetails();
        
        // HashMap Implementation
        HashMap<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("John Doe", 85);
        studentGrades.put("Jane Smith", 92);
        studentGrades.put("Bob Johnson", 78);
        
        // Iteration using enhanced for loop
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
        
        // Exception Handling
        try {
            String studentName = "Alice Brown";
            if (!studentGrades.containsKey(studentName)) {
                throw new GradeNotFoundException("Grade not found for student: " + studentName);
            }
        } catch (GradeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // File processing
        processFile();
        
        // Comparable demonstration
        List<Student> students = Arrays.asList(
            new Student("S003", "Charlie", "charlie@email.com"),
            new Student("S001", "Alice", "alice@email.com"),
            new Student("S002", "Bob", "bob@email.com")
        );
        Collections.sort(students); // Uses compareTo method
        System.out.println("Sorted students by regNo:");
        for (Student s : students) {
            System.out.println(s.getRegNo() + " - " + s.getName());
        }
    }
    
    // File Processing Method
    public static void processFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line.toUpperCase());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("File processing error: " + e.getMessage());
        }
    }
    
    // JDBC Login Validation Method
    public static boolean loginButton_Click(String regNo, String password) {
        String url = "jdbc:mysql://localhost:3306/university";
        String query = "SELECT * FROM students WHERE regNo = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(url, "username", "password");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, regNo);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if login successful
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
