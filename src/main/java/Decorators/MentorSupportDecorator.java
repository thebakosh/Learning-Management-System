package Decorators;

import Interfaces.iCourse;

public class MentorSupportDecorator extends CourseDecorator {
    public MentorSupportDecorator(iCourse decoratedCourse) {
        super(decoratedCourse);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        addMentorSupport();
    }

    private void addMentorSupport() {
        System.out.println("Adding mentor support to the course.");
    }
}
