public class OnlineCourse extends Course {
    private String platform;
    
    // Constructor using super()
    public OnlineCourse(String courseCode, String title, String platform) {
        super(courseCode, title);
        this.platform = platform;
    }
    
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    
    // Method overriding
    @Override
    public void showDetails() {
        super.showDetails(); // Call parent method
        System.out.println("Platform: " + platform);
    }
}
