package jt.chocolic;
import java.util.*;

/**
 * Manages the compilation and printing of member reports
 *
 * @author Kate Witherspoon
 */
public class MemberReport {
    boolean empty = true;
    public String memberName;
    public int memberNumber;
    public String memberAdress;
    public String memberCity;
    public String memberSate;
    public int memberZip;
    public List<ProviderService> serviceProvided  = new ArrayList<ProviderService>();

    // public String toString() // Used for printing the SummaryReport
    // {
    //     String x = "ITS THE FUCKING Member REPORT BABY";
    //     return x;
    // }

    public boolean isEmpty()
    {
        return memberName.isEmpty();
    }

    public void getinfo(int desiredID){



        List<MemberAccount> acc = MemberAccount.getMemberAccounts();
        List<Service> services = Service.getServices();
        List<ProviderAccount> pro = ProviderAccount.getProviderAccounts();


        for (MemberAccount memberAccount : acc) {
            if (memberAccount.idNumber == desiredID){
                memberNumber = memberAccount.idNumber;
                memberName = memberAccount.name;
                memberAdress = memberAccount.address;
                memberCity = memberAccount.city;
                memberSate = memberAccount.state;
                memberZip = memberAccount.zip;
                for (Service service : services) {
                    if (service.memberId == desiredID){
                        ProviderService sp = new ProviderService();
                        for(ProviderAccount providerAccount : pro){
                            sp.setService(service);
                            if(service.providerId == providerAccount.idNumber){
                                sp.setProvider(providerAccount);
                                this.serviceProvided.add(sp);
                            }
                        }
                    }

                }
                return;
            }



        }

        // for (int i=0; i<acc.size(); i++){
        //     System.out.println("Name is " + acc.get(i).name);
        // }

    }

    public static void print(int desiredID){
        MemberReport rep = new MemberReport();
        rep.getinfo(desiredID);

        System.out.println("Name: " + rep.memberName);
        System.out.println("Number: " + rep.memberNumber);
        System.out.println("Street Adress: " + rep.memberAdress);
        System.out.println("City: " + rep.memberCity);
        System.out.println("ZIP Code: " + rep.memberZip);
        for (ProviderService ps : rep.serviceProvided) {
            System.out.println("Date: " + ps.getService().dateOfService);
            System.out.println("Provider: " + ps.getProvider().name);
            System.out.println("Servicd : " + ps.getService().name);
        }

    }
}