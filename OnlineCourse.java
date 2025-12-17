import java.util.EnumSet;

public class OnlineCourse<T extends Student> extends Course<T> {
    private final Platform platform;
    private final EnumSet<Feature> features;
    
    public enum Platform {
        ZOOM("Zoom", "https://zoom.us"),
        MOODLE("Moodle", "https://moodle.org"),
        TEAMS("Microsoft Teams", "https://teams.microsoft.com");
        
        private final String name;
        private final String url;
        
        Platform(String name, String url) {
            this.name = name;
            this.url = url;
        }
        
        public String getName() { return name; }
        public String getUrl() { return url; }
    }
    
    public enum Feature {
        VIDEO_RECORDING, LIVE_CHAT, SCREEN_SHARING, BREAKOUT_ROOMS, WHITEBOARD
    }
    
    private OnlineCourse(String courseCode, String title, Platform platform, EnumSet<Feature> features) {
        super(courseCode, title);
        this.platform = platform;
        this.features = features.clone();
    }
    
    public static <T extends Student> OnlineCourse<T> create(String courseCode, String title, Platform platform) {
        return new OnlineCourse<>(courseCode, title, platform, EnumSet.noneOf(Feature.class));
    }
    
    public OnlineCourse<T> withFeatures(Feature... features) {
        EnumSet<Feature> newFeatures = this.features.clone();
        for (Feature feature : features) {
            newFeatures.add(feature);
        }
        return new OnlineCourse<>(getCourseCode(), getTitle(), platform, newFeatures);
    }
    
    public Platform getPlatform() { return platform; }
    public EnumSet<Feature> getFeatures() { return features.clone(); }
    
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Platform: " + platform.getName() + " (" + platform.getUrl() + ")");
        System.out.println("Features: " + features);
    }
}
