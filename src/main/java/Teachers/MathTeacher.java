package Teachers;
import Interfaces.iTeacher;
import Decorators.TeacherDecorator;
public class MathTeacher implements iTeacher{
    @Override
    public void teachCourse() {
        System.out.println("Teaching Math Course by Diana Chigambayeva");
    }
}
