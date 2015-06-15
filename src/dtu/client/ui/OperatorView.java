package dtu.client.ui;

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
		
		Label oprId = new Label("Operat\u00F8r ID");
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
								Throwable caught = new DALException("This user does not exsit anymore.");
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

			batchOK.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					phPanel.clear();
					int batchNr = Integer.parseInt(batchTxt.getText());
					clientImpl.service.findReceptkomponet(batchNr, new AsyncCallback<ArrayList<String>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server error " + caught);							
						}

						@Override
						public void onSuccess(ArrayList<String> result) {
							FlexTable rkTable = null;
							int j = 0;
							rkTable.setText(0, 0, "Recept ID");
							rkTable.setText(0, 1, "Netto weight");
							rkTable.setText(0, 2, "Tolerance");
							for(int i = 0; i < result.size()/3; i++){
							rkTable.setText(i + 1, 0, String.valueOf((result.get(j))));
							rkTable.setText(i + 1, 1, String.valueOf((result.get(j + 1))));
							rkTable.setText(i + 1, 2, String.valueOf((result.get(j + 2))));
							j = j + 3;
							}
							phPanel.add(rkTable);
						}
						
					});
					
				        }		
			} );
			
			phPanel.add(xPanel);
			phPanel.add(yPanel);
			phPanel.add(zPanel);

		}
		
		
	}


