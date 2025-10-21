package Certificates;
import Courses.*;
import Decorators.*;
import Interfaces.*;

public class MathCertificate implements iCertificate{
    @Override
    public void issueCertificate() {
        System.out.println("Issuing Math Course Certificate.");
    }
}
