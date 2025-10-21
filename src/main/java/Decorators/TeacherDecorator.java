package Decorators;
import Interfaces.iCourse;
import Interfaces.iTeacher;
public class TeacherDecorator extends CourseDecorator{
    protected iTeacher decoratedTeacher;

    public TeacherDecorator(iCourse decoratedCourse, iTeacher decoratedTeacher) {
        super(decoratedCourse);
        this.decoratedTeacher = decoratedTeacher;
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        decoratedTeacher.teachCourse();

    }
}
