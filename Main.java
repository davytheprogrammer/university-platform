import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.math.BigDecimal;

public class Main {
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    
    public static void main(String[] args) {
        demonstrateAdvancedFeatures();
        demonstrateStreamsAndLambdas();
        demonstrateConcurrency();
        processFilesAsync();
        
        executor.shutdown();
    }
    
    private static void demonstrateAdvancedFeatures() {
        // Builder pattern with validation
        Student student1 = new Student.Builder()
            .regNo("S001")
            .name("Alice Johnson")
            .email("alice@university.edu")
            .build();
        
        // Factory pattern with fluent interface
        OnlineCourse<Student> course = OnlineCourse.<Student>create("COMP305", "Advanced OOP", OnlineCourse.Platform.ZOOM)
            .withFeatures(OnlineCourse.Feature.VIDEO_RECORDING, OnlineCourse.Feature.BREAKOUT_ROOMS);
        
        // Observer pattern
        course.addObserver((c, msg) -> System.out.println("Course Update: " + msg));
        course.enrollStudent(student1);
        course.assignGrade("S001", 95.5);
        
        // Enhanced payment calculation
        FullTimeLecturer fullTime = new FullTimeLecturer(FullTimeLecturer.Rank.ASSOCIATE, 5);
        PartTimeLecturer partTime = new PartTimeLecturer(25, BigDecimal.valueOf(50));
        
        System.out.println("Full-time: " + fullTime.getPaymentSummary(0.25));
        System.out.println("Part-time: " + partTime.getPaymentSummary(0.25));
        
        course.showDetails();
    }
    
    private static void demonstrateStreamsAndLambdas() {
        List<Student> students = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Student.Builder()
                .regNo("S" + String.format("%03d", i))
                .name("Student " + i)
                .email("student" + i + "@university.edu")
                .build())
            .collect(Collectors.toList());
        
        // Advanced stream operations
        Map<String, List<Student>> groupedByFirstLetter = students.stream()
            .collect(Collectors.groupingBy(s -> s.getName().substring(0, 1)));
        
        // Parallel processing with custom collector
        String concatenatedNames = students.parallelStream()
            .map(Student::getName)
            .collect(Collectors.joining(", "));
        
        System.out.println("Grouped students: " + groupedByFirstLetter);
        System.out.println("All names: " + concatenatedNames);
    }
    
    private static void demonstrateConcurrency() {
        ConcurrentHashMap<String, Integer> sharedGrades = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(3);
        
        // Simulate concurrent grade updates
        for (int i = 1; i <= 3; i++) {
            final int courseId = i;
            executor.submit(() -> {
                try {
                    for (int j = 1; j <= 5; j++) {
                        String studentId = "S" + courseId + String.format("%02d", j);
                        int grade = ThreadLocalRandom.current().nextInt(60, 101);
                        sharedGrades.put(studentId, grade);
                        Thread.sleep(100); // Simulate processing time
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            latch.await(5, TimeUnit.SECONDS);
            System.out.println("Concurrent grades processed: " + sharedGrades.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static void processFilesAsync() {
        CompletableFuture<Void> fileProcessing = CompletableFuture
            .supplyAsync(() -> {
                try {
                    return Files.readAllLines(Paths.get("input.txt"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            })
            .thenApply(lines -> lines.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()))
            .thenAccept(processedLines -> {
                try {
                    Files.write(Paths.get("output.txt"), processedLines);
                    System.out.println("File processed asynchronously");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        
        fileProcessing.join(); // Wait for completion
    }
    
    // Advanced JDBC with connection pooling simulation
    public static CompletableFuture<Boolean> authenticateUserAsync(String regNo, String password) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "jdbc:mysql://localhost:3306/university?useSSL=true&serverTimezone=UTC";
            String query = "SELECT COUNT(*) FROM students WHERE regNo = ? AND password = SHA2(?, 256)";
            
            try (Connection conn = DriverManager.getConnection(url, "username", "password");
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setString(1, regNo);
                stmt.setString(2, password);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next() && rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.err.println("Authentication error: " + e.getMessage());
                return false;
            }
        });
    }
}
