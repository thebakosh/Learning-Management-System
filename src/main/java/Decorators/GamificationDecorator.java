package Decorators;
import Interfaces.iCourse;

public class GamificationDecorator extends CourseDecorator {
    public GamificationDecorator(iCourse decoratedCourse) {
        super(decoratedCourse);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        addGamification();
    }

    private void addGamification() {
        System.out.println("Adding points and leaderboards to the course.");
    }
}
