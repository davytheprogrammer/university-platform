import java.util.Objects;
import java.util.regex.Pattern;

public class Student implements Comparable<Student> {
    private final String regNo;
    private final String name;
    private final String email;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    private Student(Builder builder) {
        this.regNo = builder.regNo;
        this.name = builder.name;
        this.email = builder.email;
    }
    
    public static class Builder {
        private String regNo;
        private String name;
        private String email;
        
        public Builder regNo(String regNo) {
            if (regNo == null || regNo.trim().isEmpty()) 
                throw new IllegalArgumentException("Registration number cannot be null or empty");
            this.regNo = regNo;
            return this;
        }
        
        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) 
                throw new IllegalArgumentException("Name cannot be null or empty");
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) 
                throw new IllegalArgumentException("Invalid email format");
            this.email = email;
            return this;
        }
        
        public Student build() {
            return new Student(this);
        }
    }
    
    public String getRegNo() { return regNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    @Override
    public int compareTo(Student other) {
        return this.regNo.compareTo(other.regNo);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(regNo, student.regNo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
    
    @Override
    public String toString() {
        return String.format("Student{regNo='%s', name='%s', email='%s'}", regNo, name, email);
    }
}
