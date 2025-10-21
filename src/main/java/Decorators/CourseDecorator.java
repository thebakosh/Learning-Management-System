package Decorators;

import Interfaces.iCourse;

public class CourseDecorator implements iCourse {
    protected iCourse decoratedCourse;

    public CourseDecorator(iCourse decoratedCourse) {
        this.decoratedCourse = decoratedCourse;
    }

    @Override
    public void deliverContent() {
        decoratedCourse.deliverContent();
    }
}
