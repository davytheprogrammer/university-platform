@FunctionalInterface
public interface CourseObserver {
    void onCourseUpdate(Course<?> course, String message);
}
