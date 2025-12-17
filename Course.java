import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String title;
    private ArrayList<Student> students;
    
    // Default constructor
    public Course() {
        this.students = new ArrayList<>();
    }
    
    // Overloaded constructor
    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.students = new ArrayList<>();
    }
    
    // Getters and setters
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public ArrayList<Student> getStudents() { return students; }
    
    // Enroll student method
    public void enrollStudent(Student s) {
        students.add(s);
    }
    
    // Method to be overridden
    public void showDetails() {
        System.out.println("Course Code: " + courseCode + ", Title: " + title);
    }
}
