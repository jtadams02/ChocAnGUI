package jt.chocolic;
import java.util.*;

/**
 * Manages the compilation and printing of provider reports
 *
 * @author Kate Witherspoon
 */
public class ProviderReport {
    boolean empty = true;

    public String providerName;
    public int providerNumber;
    public String providerAddress;
    public String providerCity;
    public int providerZip;
    public List<MemberService> servicesProvided = new ArrayList<MemberService>();
    public static int totalConsultations;
    public static float totalFee;

    // public String toString() // Used for printing the SummaryReport
    // {
    //     String x = "ITS THE FUCKING provider REPORT BABY";
    //     return x;
    // }
    public boolean isEmpty()
    {
        return providerName.isEmpty();
    }

    public void getinfo(int desiredID){

        List<ProviderAccount> acc = ProviderAccount.getProviderAccounts();
        List<Service> services = Service.getServices();
        List<MemberAccount> mem = MemberAccount.getMemberAccounts();

        for (int i =0; i<acc.size(); i++){
            totalConsultations+= acc.get(i).numConsultations;
        }

        for (int i=0; i<acc.size(); i++){
            totalFee += acc.get(i).feesPayable;
        }

        for (ProviderAccount prov : acc) {
            if (prov.idNumber == desiredID){
                this.providerName = prov.name;
                this.providerAddress = prov.address;
                this.providerCity = prov.city;
                this.providerZip = prov.zip;
                this.providerNumber = prov.idNumber;
                for (Service service : services) {
                    if (service.providerId == desiredID){
                        MemberService ms = new MemberService();
                        ms.setService(service);
                        for(MemberAccount member : mem){
                            if(service.memberId == member.idNumber){
                                ms.setMember(member);
                                this.servicesProvided.add(ms);
                            }
                        }
                    }

                }
            }

        }

    }

    public static void PrintReport(int desiredId) {
        ProviderReport rep = new ProviderReport();
        rep.getinfo(desiredId);

        System.out.println("Name: " + rep.providerName);
        System.out.println("Number: " + rep.providerNumber);
        System.out.println("Street Address: " + rep.providerAddress);
        System.out.println("City: " + rep.providerCity);
        System.out.println("ZIP Code: " + rep.providerZip);

        for (MemberService ms : rep.servicesProvided) {
            System.out.println("Date: " + ms.getService().dateOfService);
            System.out.println("Date and time data received by the computer: ");
            System.out.println("Member name: "+ ms.getMember().name);
            System.out.println("Member Number: " + ms.getMember().idNumber);
            System.out.println("Service Code: " + ms.getService().serviceCode);
            System.out.println("Fee: " + ms.getService().fee);
            System.out.println("Total number of consultations: " + totalConsultations);
            System.out.println("Total fee: "+ totalFee);

        }

    }
}
