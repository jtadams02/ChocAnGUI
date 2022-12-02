package jt.chocolic;
import java.lang.reflect.Member;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Runs the weekly reports indicated in the ChocAn description as being run on Friday at midnight by the timer.
 * Run from the Main class in this implementation.
 *
 * @author Everybody
 */
public class MainAccountingProcedure {
    public List<MemberReport> memberReports;
    public List<ProviderReport> providerReports;
    public List<SummaryReport> summaryReports;

    public void runMainAccountingGUI(){
        List<MemberAccount> members = MemberAccount.getMemberAccounts();
        List<Service>  services = Service.getServices();
        List<ProviderAccount> pro = ProviderAccount.getProviderAccounts();
        // We need a list of pretty much everything
        // This is for member reports

        this.memberReports = new ArrayList<MemberReport>();

        for(int i=0;i<members.size();i++)
        {
            MemberReport tempMember = new MemberReport();

            tempMember.memberName = members.get(i).name;
            tempMember.memberNumber = members.get(i).idNumber;
            tempMember.memberAdress = members.get(i).address;
            tempMember.memberCity = members.get(i).city;
            tempMember.memberZip = members.get(i).zip;

            for(Service service : services){
                if(service.memberId==tempMember.memberNumber)
                {
                    ProviderService sp = new ProviderService();
                    for(ProviderAccount providerAccount : pro){
                        sp.setService(service);
                        if(service.providerId == providerAccount.idNumber){
                            sp.setProvider(providerAccount);
                            tempMember.serviceProvided.add(sp);
                        }
                    }
                }
            }
            this.memberReports.add(tempMember);
        }

        // Provider Report time
        this.providerReports = new ArrayList<ProviderReport>();
        for(ProviderAccount prov: pro)
        {
            ProviderReport tempProvider = new ProviderReport();

            tempProvider.providerName = prov.name;
            tempProvider.providerAddress = prov.address;
            tempProvider.providerCity = prov.city;
            tempProvider.providerZip = prov.zip;
            tempProvider.providerNumber = prov.idNumber;
            for(Service service : services)
            {
                if(service.providerId==tempProvider.providerNumber)
                {
                    MemberService ms = new MemberService();
                    ms.setService(service);
                    for(MemberAccount member : members)
                    {
                        if(service.memberId == member.idNumber){
                            ms.setMember(member);
                            tempProvider.servicesProvided.add(ms);
                        }
                    }
                }
            }
            this.providerReports.add(tempProvider);
        }

        // Summary Report BABY
        this.summaryReports = new ArrayList<SummaryReport>();
        this.summaryReports = SummaryReport.getInformation();



    }

    public static void runMainAccountingProcedure() {
        //     System.out.println("Enter member ID number");
        //     Scanner sc = new Scanner(System.in);
        //     int i = sc.nextInt();
        //    // System.out.println(i); // will print the variable
        //     //Run member report

        //     System.out.println("Enter Provider ID number");
        //     Scanner pr = new Scanner(System.in);
        //     int n = pr.nextInt();
        //System.out.println(n);


        MemberReport.print(421);

        System.out.println("----------------------");
        System.out.println("Provider Report\n");
        // Run Provider Report
        ProviderReport.PrintReport(422);
        System.out.println("----------------------");
        System.out.println("Summary Report");
        // Run Summary Report
        SummaryReport.getinfo();

        System.out.println("----------------------");

        return;

    }

}
