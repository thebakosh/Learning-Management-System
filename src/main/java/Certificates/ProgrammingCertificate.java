package Certificates;
import Interfaces.*;

public class ProgrammingCertificate implements iCertificate{
    @Override
    public void issueCertificate() {
        System.out.println("Issuing Programming Course Certificate.");
    }
}
