package jt.chocolic;
/**
 * @author Sophie Anderson & Patrick Dean
 * @version 1.0
 *
 * This class is used to organize and print the summary reports when called to. The class is called when the manager
 * calls for the Report to run.
 *
 * isEmpty() checks if the summary report is empty
 *
 * getinfo() prints out the summary reports
 */

import java.util.*;

public class SummaryReport
{
    boolean empty = true;
    // Variables listed on class diagram
    private List<Integer> numConsultations = new ArrayList<Integer>();
    List<ProviderAccount> providerAccounts = new ArrayList<ProviderAccount>();
    float fees;
    int consultations;

    int providerCount;


    public boolean isEmpty() // Check if the Summary report is empty
    {
        if (numConsultations.isEmpty() == true)
        {
            return true;
        }
        return false;
    }

    public static List<SummaryReport> getInformation()
    {
        List<SummaryReport> returnList = new ArrayList<SummaryReport>();

        SummaryReport totalReport = new SummaryReport();
        totalReport.fees=0;
        totalReport.consultations=0;

        List<Service> services = Service.getServices();

        for(Service selSer : services)
        {
            totalReport.fees+=selSer.fee;
            totalReport.consultations++;

            int provID = selSer.providerId;
            boolean isPresent = false;
            for(int i=0;i< totalReport.numConsultations.size();i++)
            {
                if(provID==totalReport.numConsultations.get(i))
                {
                    isPresent=true;
                }
            }
            if(!isPresent)
            {
                totalReport.numConsultations.add(provID);
            }
        }
        totalReport.providerCount = totalReport.numConsultations.size();

        returnList.add(totalReport);

        List<ProviderAccount> list = ProviderAccount.getProviderAccounts();
        SummaryReport otherReports = new SummaryReport();
        for(int i=0;i<list.size();i++)
        {
            boolean toAdd = false;
            ProviderAccount tempProvider = list.get(i);

            for(Service k : services)
            {
                if(k.providerId==tempProvider.idNumber)
                {
                    tempProvider.feesPayable+=k.fee;
                    tempProvider.numConsultations++;
                    toAdd=true;
                }
            }
            otherReports.providerAccounts.add(tempProvider);
        }
        returnList.add(otherReports);
        return returnList;
    }


    public static void getinfo()
    {
        int numOfProviders = 0;
        int totalFees = 0;
        int totalConsultations = 0;

        List<ProviderAccount> list = ProviderAccount.getProviderAccounts();

        //Printing out the summary report details
        // System.out.println("Summary Report Details:");
        System.out.println("\nIndivual Provider Details \n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Provider Name: " + list.get(i).name);
            System.out.println("Each Provider Consultations #: " + list.get(i).numConsultations);
            System.out.println("Provider ID: " + list.get(i).idNumber);
            System.out.println("Provider Fee: " + list.get(i).feesPayable);

        }

        //getting size of list
        for (int i = 0; i < list.size(); i++) {
            numOfProviders++;
        }

        //getting total fees
        for (int i = 0; i < list.size(); i++) {
            totalFees += list.get(i).feesPayable;
        }

        //getting total number of consultations
        for (int i = 0; i < list.size(); i++) {
            totalConsultations += list.get(i).numConsultations;
        }

        System.out.println("\nOverall Summary Report Details \n");
        System.out.println("The total number of providers who provided services is " + numOfProviders);
        System.out.println("The total number of consultations " + totalConsultations);
        System.out.println("The total fees is " + totalFees);

    }
    /**
     * lists every provider to be paid for that week
     * number of consultations for each provider
     * their total fee for the week
     * total number of providers who provided services
     * total number of consultations
     * total fee of every provider
     */
}
