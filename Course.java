import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Course<T extends Student> {
    private final String courseCode;
    private final String title;
    private final Set<T> students;
    private final Map<String, Double> grades;
    private final List<CourseObserver> observers;
    
    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.students = ConcurrentHashMap.newKeySet();
        this.grades = new ConcurrentHashMap<>();
        this.observers = new ArrayList<>();
    }
    
    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public Set<T> getStudents() { return Collections.unmodifiableSet(students); }
    
    public void enrollStudent(T student) {
        if (students.add(student)) {
            notifyObservers("Student enrolled: " + student.getName());
        }
    }
    
    public void assignGrade(String regNo, double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        grades.put(regNo, grade);
        notifyObservers("Grade assigned: " + regNo + " -> " + grade);
    }
    
    public Optional<Double> getGrade(String regNo) {
        return Optional.ofNullable(grades.get(regNo));
    }
    
    public List<T> getTopStudents(int count) {
        return students.stream()
            .filter(s -> grades.containsKey(s.getRegNo()))
            .sorted((s1, s2) -> Double.compare(
                grades.get(s2.getRegNo()), 
                grades.get(s1.getRegNo())
            ))
            .limit(count)
            .collect(Collectors.toList());
    }
    
    public void addObserver(CourseObserver observer) {
        observers.add(observer);
    }
    
    private void notifyObservers(String message) {
        observers.forEach(observer -> observer.onCourseUpdate(this, message));
    }
    
    public void showDetails() {
        System.out.println("Course Code: " + courseCode + ", Title: " + title);
        System.out.println("Enrolled Students: " + students.size());
    }
}
