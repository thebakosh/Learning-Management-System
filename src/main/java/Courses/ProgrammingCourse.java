package Courses;
import Interfaces.iCourse;

public class ProgrammingCourse implements iCourse {
    @Override
    public void deliverContent() {
        System.out.println("Delivering Programming course content.");
    }
}
