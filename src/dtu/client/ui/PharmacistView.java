package dtu.client.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.DALException;
import dtu.shared.FieldVerifier;
import dtu.client.ui.WelcomeView;
import dtu.shared.RaavareDTO;
import dtu.shared.DALException;

public class PharmacistView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel phPanel;
	
	/*private static final String URL = "jdbc:mysql://localhost:3306/Raavaredb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	private Connection connection = null; // manages connection

	private PreparedStatement saveRaavareStmt = null;*/

	Button addRaavare = new Button("Create Raavare");
	Button showRaavare = new Button("Show Raavare");
	Button updateRaavare = new Button("Update Raavare");
	
	//BrowseView bv = new BrowseView(clientImpl);
	
	
	public PharmacistView(KartotekServiceClientImpl clientImpl) // pramtriz cnsrctr
	{
		this.clientImpl = clientImpl;

		phPanel = new VerticalPanel();
		initWidget(this.phPanel);
		
		addRaavare.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RaavareFields();
			}
		});
		
		phPanel.add(addRaavare);
		
		phPanel.add(showRaavare);
		phPanel.add(updateRaavare);
		
		

	}

	public void RaavareFields() 
	{
		phPanel.clear();
		
		Label idLbl;
		final TextBox idTxt;
		Label nameLbl;
		Label levLbl;
		final TextBox nameTxt;
		final TextBox levTxt;
		Button save = new Button("Create");
		
		
		
	

		// total height of widget. Components are distributed evenly
		phPanel.setHeight("120px");	

		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel idPanel = new HorizontalPanel();
		HorizontalPanel levPanel = new HorizontalPanel();

		idLbl = new Label("ID:");
		idLbl.setWidth("100px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		nameLbl = new Label("Navn:");
		nameLbl.setWidth("100px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);


		levLbl = new Label("Leverandoer:");
		levLbl.setWidth("100px");
		levTxt = new TextBox();
		//levTxt.setWidth("5em");
		levTxt.setHeight("1em");
		levPanel.add(levLbl);
		levPanel.add(levTxt);
		
		

		save.addClickHandler(new ClickHandler()  {

			@Override
			public void onClick(ClickEvent event)   {
//			   idTxt.getText();
//			   nameTxt.getText();
//			   levTxt.getText();
				
			 RaavareDTO newRaavare = new RaavareDTO( Integer.parseInt(idTxt.getText()), nameTxt.getText(), levTxt.getText());	 
			 clientImpl.service.saveRaavare(newRaavare, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
						
						
					}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Raavare gemt i kartotek");
						
						
					}
				});
	
			        }		
		} );
		
		phPanel.add(idPanel);
		phPanel.add(namePanel);
		phPanel.add(levPanel);
		phPanel.add(save);

	}
	
	
}
