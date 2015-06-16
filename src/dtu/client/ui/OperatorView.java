package dtu.client.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.DALException;

public class OperatorView extends Composite {
		KartotekServiceClientImpl clientImpl;
		VerticalPanel phPanel;
		double brutto = 0, tare = 0, netto = 0;
		int batchNr;
		
		FlexTable vaegtVindue = new FlexTable();
		FlexTable oversigt = new FlexTable();
		
		Label oprId = new Label("Operator ID");
		TextBox oprTxt = new TextBox();
		
		Button oprLogin = new Button("OK");
		Button annuller = new Button("Annuller");
		
		public OperatorView(final KartotekServiceClientImpl clientImpl) {
			this.clientImpl = clientImpl;

			phPanel = new VerticalPanel();
			initWidget(this.phPanel);
			
			oprLogin.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int id = Integer.parseInt(oprTxt.getText());
					clientImpl.service.userName(id, new AsyncCallback<String>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server error! " + caught.getMessage());
						}

						@Override
						public void onSuccess(String result) {
							if(result.equals("null")) {
								Throwable caught = new DALException("This user does not exsit.");
								onFailure(caught);
							}
							else {
							String name = result;
							AfvejningFields(name);
							}
						}
						
					});
					
				}
			});
			phPanel.add(oprId);
			phPanel.add(oprTxt);
			phPanel.add(oprLogin);
			phPanel.add(annuller);
		}

		public void AfvejningFields(String name) {
			phPanel.clear();

			HorizontalPanel xPanel = new HorizontalPanel();
			VerticalPanel yPanel = new VerticalPanel();
			HorizontalPanel zPanel = new HorizontalPanel();
			
			Label besked = new Label("Welcome " + name);
			besked.setHeight("3em");
			xPanel.add(besked);
			
			Label batchLabel = new Label("Write the ID of the wanted produktbatch");
			final TextBox batchTxt = new TextBox();
			batchTxt.setWidth("35px");
			yPanel.add(batchLabel);
			yPanel.add(batchTxt);
			
			Button batchOK = new Button("OK");
			zPanel.add(batchOK);
			
			phPanel.add(xPanel);
			phPanel.add(yPanel);
			phPanel.add(zPanel);
			
			batchOK.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					phPanel.clear();
					final FlexTable rkTable = new FlexTable();
					final Label receptNavn = new Label();
					batchNr = Integer.parseInt(batchTxt.getText());
					clientImpl.service.findReceptkomponet(batchNr, new AsyncCallback<ArrayList<String>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server error " + caught);
						}

						@Override
						public void onSuccess(ArrayList<String> result) {
							int j = 0;
							receptNavn.setText("");
							rkTable.setText(0, 0, "Raavare ID");
							rkTable.setText(0, 1, "Netto weight");
							rkTable.setText(0, 2, "Tolerance");
							for(int i = 0; i < result.size()/3; i++){
							rkTable.setText(i + 1, 0, String.valueOf((result.get(j))));
							rkTable.setText(i + 1, 1, String.valueOf((result.get(j + 1))));
							rkTable.setText(i + 1, 2, String.valueOf((result.get(j + 2))));
							j = j + 3;
							}
							AfvejningStart();
						}
					});
					rkTable.setBorderWidth(1);
					phPanel.add(rkTable);
				}		
			});
		}
		
		public void AfvejningStart(){// throws UnknownHostException, IOException {
//			int port = 8001;
//			String hostname = "Localhost";
//			  
//	        Socket clientSocket = new Socket(hostname, port); 
//
//	        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//	        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream());      
//	        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			clientImpl.service.statusProduktbatch(batchNr, 1, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server error! Could not update status");
				}

				@Override
				public void onSuccess(Void result) {
					tare = brutto;
				}
				
			});

			final HorizontalPanel buttonPanel = new HorizontalPanel();
			final HorizontalPanel textPanel = new HorizontalPanel();
			
			oversigt.setText(0, 0, "1. After first tare, place a container on scale.");
			oversigt.setText(1, 0, "2. Tare the scale.");
			oversigt.setText(2, 0, "3. Write ID of the first item have to weigh.");
			oversigt.setText(3, 0, "4. Weigh the wanted amount.");
			oversigt.setText(4, 0, "5. Repeat step 1-4 until your done.");
			oversigt.setWidth("400px");
			
			vaegtVindue.setText(0, 0, "Scale is ready for use");
			vaegtVindue.setText(1, 0, "Netto: " + (brutto - tare) + " kg");
			vaegtVindue.setText(2, 0, "Please tare the scale");
			vaegtVindue.setSize("230px", "5em");
			
			
			final Button okBut = new Button("OK");
			Button tareBut = new Button("Tare"); 
			final TextBox input = new TextBox();
			
			okBut.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
						brutto = Double.parseDouble(input.getText());
						scaleMenu("", "", "Netto: " + (brutto - tare) + " kg");
				}
			});
			
			tareBut.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					tare = brutto;
					scaleMenu("Scale has been tared", "Netto: " + (brutto - tare) + " kg", 
							"");
				}
			});
			textPanel.add(vaegtVindue);
			textPanel.add(oversigt);
			buttonPanel.add(okBut);
			buttonPanel.add(tareBut);
			phPanel.add(textPanel);
			phPanel.add(buttonPanel);
			phPanel.add(input);
		}
		
		public void scaleMenu(String line1, String line2, String line3){
			vaegtVindue.setText(0, 0, line1);
			vaegtVindue.setText(1, 0, line2);
			vaegtVindue.setText(2, 0, line3);
		}
		
	}


