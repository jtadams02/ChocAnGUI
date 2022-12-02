package jt.chocolic;

import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class reportController implements Initializable 
{

	 @FXML
	 private Button closeButton;

	@FXML
	TreeView memberTreeView;

	@FXML
	TreeView providerTreeView;

	@FXML
	TreeView summaryTreeView;

	public void createTree(TreeView tree, int treeID, MainAccountingProcedure main) {
		// I genuinely have 0 clue why this populates the tree,
		// But when I did it the exact same way in a different place
		// it doesnt work, so here it will stay

		// MemberReport Tree
		if(treeID==1){
			List<MemberReport> list = main.memberReports;
			try{
				if(list.isEmpty());
			} catch (Exception e) {
				return;
			}
			TreeItem<String> root = new TreeItem<>("Root");
			root.setExpanded(true);

			for(int i=0;i<list.size();i++)
			{
				MemberReport dis = list.get(i);
				String id = Integer.toString(dis.memberNumber);
				String idd = ("Member ID: "+ id);

				TreeItem<String> item = new TreeItem<String>(idd);
				item.getChildren().add(new TreeItem<String>("Name: "+dis.memberName));
				item.getChildren().add(new TreeItem<String>("Address: "+dis.memberAdress));
				item.getChildren().add(new TreeItem<String>("City: "+dis.memberCity));
				item.getChildren().add(new TreeItem<String>("Zip: "+dis.memberZip));

				for(int k=0;k<dis.serviceProvided.size();k++)
				{
					ProviderService selected = dis.serviceProvided.get(i);
					TreeItem<String> item1 = new TreeItem<String>("Date: "+selected.getService().dateOfService);
					item1.getChildren().add(new TreeItem<String>("Provider: "+selected.getProvider().name));
					item1.getChildren().add(new TreeItem<String>("Service: "+selected.getService().name));

					item.getChildren().add(item1);
				}
				item.setExpanded(true);
				root.getChildren().add(item);
			}
			memberTreeView.setRoot(root);
			memberTreeView.setShowRoot(false);
		}
		// Provider Reports
		if(treeID==2)
		{
			List<ProviderReport> list = main.providerReports;
			try{
				if(list.isEmpty());
			} catch (Exception e) {
				return;
			}
			TreeItem<String> root = new TreeItem<>("Root");
			root.setExpanded(true);

			for(int i=0;i<list.size();i++)
			{
				ProviderReport dis = list.get(i);
				String id = Integer.toString(dis.providerNumber);
				String idd = ("Provider ID: "+ id);

				TreeItem<String> item = new TreeItem<String>(idd);
				item.getChildren().add(new TreeItem<String>("Name: "+dis.providerName));
				item.getChildren().add(new TreeItem<String>("Address: "+dis.providerAddress));
				item.getChildren().add(new TreeItem<String>("City: "+dis.providerCity));
				item.getChildren().add(new TreeItem<String>("Zip: "+dis.providerZip));

				for(MemberService ms : dis.servicesProvided) {
					TreeItem<String> item1 = new TreeItem<>("Date: " + ms.getService().dateOfService);
					item1.getChildren().add(new TreeItem<String>("Member Number: " + ms.getMember().idNumber));
					item1.getChildren().add(new TreeItem<String>("Member name: " + ms.getMember().name));
					item1.getChildren().add(new TreeItem<String>("Service Code: " + ms.getService().serviceCode));
					item1.getChildren().add(new TreeItem<String>("Fee: " + ms.getService().fee));
					item1.getChildren().add(new TreeItem<String>("Total number of consultations: " + dis.totalConsultations));
					item1.getChildren().add(new TreeItem<String>("Total fee: " + dis.totalFee));

					item.getChildren().add(item1);
				}
				item.setExpanded(true);
				root.getChildren().add(item);
			}
			providerTreeView.setRoot(root);
			providerTreeView.setShowRoot(false);
		}

		// Summary Report Display
		if(treeID==3)
		{
			List<SummaryReport> list = main.summaryReports;
			try{
				if(list.isEmpty());
			} catch (Exception e) {
				return;
			}
			TreeItem<String> root = new TreeItem<>("Root");
			root.setExpanded(true);

			TreeItem<String> reportTotals = new TreeItem<String>("Totals:");
			reportTotals.getChildren().add(new TreeItem<String>("Total Providers: "+list.get(0).providerCount));
			reportTotals.getChildren().add(new TreeItem<String>("Total Fees: "+list.get(0).fees));
			reportTotals.getChildren().add(new TreeItem<String>("Total Consultations: "+list.get(0).consultations));

			reportTotals.setExpanded(true);
			root.getChildren().add(reportTotals);

			// List other providers and their costs
			SummaryReport others = list.get(1);
			for(ProviderAccount provAccount : others.providerAccounts)
			{
				TreeItem<String> newBranch = new TreeItem<String>("ID to be Paid: "+provAccount.idNumber);
				newBranch.getChildren().add(new TreeItem<String>("Total Consultations: "+provAccount.numConsultations));
				newBranch.getChildren().add(new TreeItem<String>("Total Fee: "+provAccount.feesPayable));

				newBranch.setExpanded(true);
				root.getChildren().add(newBranch);
			}
			summaryTreeView.setRoot(root);
			summaryTreeView.setShowRoot(false);
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		MainAccountingProcedure main = new MainAccountingProcedure();
		main.runMainAccountingGUI();

		// Populate memberview
		createTree(memberTreeView,1, main);
		createTree(providerTreeView,2, main);
		createTree(summaryTreeView,3, main);




	}
	
   public void exitTerminal() throws Exception {
	   // Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("MyScene.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
	   
   }

   public void goBack() throws IOException 
   {
	   Parent newParent = FXMLLoader.load(getClass().getResource("managerAccess.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
   }
 
	
	
	
}