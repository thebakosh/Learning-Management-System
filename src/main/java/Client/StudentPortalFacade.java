package Client;

import Interfaces.*;

public class StudentPortalFacade {
    private iCourse course;
    private iCertificate certificate;

    public StudentPortalFacade(iCourse course, iCertificate certificate) {
        this.course = course;
        this.certificate = certificate;
    }
    public void enrollInCourse() {
        System.out.println("Enrolling in course...");
    }
    public void startLearning() {
        System.out.println("Starting learning process...");
        course.deliverContent();
    }
    public void completeCourse() {
        certificate.issueCertificate();
        System.out.println("Completing the course...");
        sendCompletionNotification();
    }
    private void sendCompletionNotification() {
        System.out.println("Course completed! Congratulations!");
    }

}
