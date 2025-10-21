package Decorators;
import Interfaces.*;
import java.security.cert.Certificate;
import Certificates.*;

public class CertificateDecorator extends CourseDecorator {
    private iCertificate certificate;

    public CertificateDecorator(iCourse decoratedCourse, iCertificate certificate) {
        super(decoratedCourse);
        this.certificate = certificate;
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        certificate.issueCertificate();
    }

}
