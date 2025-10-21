package Courses;
import Interfaces.iCourse;

public class MathCourse implements iCourse {
    @Override
    public void deliverContent() {
        System.out.println("Delivering Math course content.");
    }

}
