package Teachers;
import Interfaces.iTeacher;
import Decorators.TeacherDecorator;
public class ProgrammingTeacher implements iTeacher{
    @Override
    public void teachCourse() {
        System.out.println("Teaching Programming Course by Dimash Temirgaly");
    }
}
