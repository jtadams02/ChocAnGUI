package jt.chocolic;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * This is the ServiceClass
 * This class is the object class for all services used in the provider directory
 * All service attributes are listed below as well as all the methods dealing with services
 * 
 * @author Patrick Dean
 */
public class Service {
    protected String name;
    protected int serviceCode;
    protected Date dateOfService;
    protected float fee;
    protected boolean isDeleted;
    public int providerId;
    protected int memberId;

	/**
	 * Verifies the service exists and is an appropriate length
	 * 
	 * @param serviceToVerify the service that is to be verified
	 * @return true if the service is verified, false otherwise
	 */
	public static boolean verifyService(Service serviceToVerify) {
		if (serviceToVerify.name.length() > 20) {
            System.out.println("Name length not supported.");
            return false;
        }
		
		List<Service> checkList = new ArrayList<Service>();
		boolean inList = false;
        for (int j = 0; j < checkList.size(); j++) {
        	if (serviceToVerify.name == checkList.get(j).name) {
        		inList = true;
        		break;
            }
        }
        
        if (inList == true) {
            System.out.println("Service is Verified.");
            return true;
        }
        
        else {
        	System.out.println("Name not in ServiceList.");
        	return false;
        }
	}
	
	/**
	 * Retrieves a list of services from the database
	 * @return the list of services
	 */
	public static List<Service> getServices() {
		List<Service> services = new ArrayList<Service>();
		Database db = new Database();
		
		try {
			ResultSet rlt = db.GetData("service");
			while (rlt.next()) {
				Service s = new Service();
				s.serviceCode = rlt.getInt("serviceCode");
				s.dateOfService = rlt.getDate("dateOfService");
				s.name = rlt.getString("name");
				s.fee = rlt.getFloat("fee");
				s.isDeleted = rlt.getBoolean("isDeleted");
				s.providerId = rlt.getInt("providerId");
				s.memberId = rlt.getInt("memberId");
				
				if (!s.isDeleted) services.add(s);
			}
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		
		return services;
	}
	
	/**
	 * Takes new info and updates an existing service in the service list in the database
	 */
	public void updateService() {
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("UPDATE service set name=?, dateOfService=?, fee=?, providerId=?, memberId=? where serviceCode=?");
			stm.setString(1, this.name);
			stm.setDate(2, this.dateOfService);
			stm.setFloat(3, this.fee);
			stm.setInt(4, this.serviceCode);
			stm.setInt(5, this.providerId);
			stm.setInt(7,  this.memberId);
			
			stm.setInt(8,  this.serviceCode);
			
			stm.executeUpdate();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
	
	/**
	 * Creates a new service in the database
	 */
	public void addService() {
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("INSERT INTO service (name, dateOfService, fee, providerId, memberId) VALUES (?,?,?,?,?)");
			stm.setString(1, this.name);
			stm.setDate(2, this.dateOfService);
			stm.setFloat(3, this.fee);
			stm.setInt(4,  this.providerId);
			stm.setInt(5, this.memberId);
			
			stm.executeUpdate();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
	
	/**
	 * Deletes a service from the database
	 */
	public void deleteService() {
		Database db = new Database();
		
		try {
			PreparedStatement stm = db.connection.prepareStatement("UPDATE service set isDeleted=? where serviceCode=?");
			stm.setBoolean(1, true);
			stm.setInt(2, this.serviceCode);
			
			stm.executeUpdate();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
}