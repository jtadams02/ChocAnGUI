package jt.chocolic;

import java.util.*;

/**
 * This is the Provider Directory Class.
 * It accesses all the service names, codes, and fees that make up the Provider Directory (from the database)
 * An array of object type Service is used to to this
 * 
 * @author Patrick Dean 
 */
public class ProviderDirectory {

    /**
     * Prints the list of all services to terminal
     */
    public static void requestProviderDirectory() {
        List<Service> serviceList = Service.getServices(); //This pulls the service list from the database
        System.out.println("Displaying All Services");
        for (int i = 0; i < serviceList.size(); i++) {    
            System.out.println(serviceList.get(i).name);
            System.out.println(serviceList.get(i).serviceCode);
            System.out.println(serviceList.get(i).dateOfService);
            System.out.println(serviceList.get(i).fee);
            System.out.println("");
        }
    }
}
