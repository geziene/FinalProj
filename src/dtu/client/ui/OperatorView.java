package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;

// \u00E6 = æ \u00C6 = Æ
// \u00F8 = ø \u00D8 = Ø
// \u00E5 = å \u00C5 = Å

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
							Window.alert("Server fejl!" + caught.getMessage());
						}

						@Override
						public void onSuccess(String result) {
							String name = result;
							AfvejningFields(name);
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
			
			Label besked = new Label("Velkommen " + name);
			besked.setHeight("3em");
			xPanel.add(besked);
			
			Label batchLabel = new Label("Indtast ID p\u00E5 den \u00F8nskede produktbatch");
			final TextBox batchTxt = new TextBox();
			batchTxt.setWidth("35px");
			yPanel.add(batchLabel);
			yPanel.add(batchTxt);
			
			Button batchOK = new Button("OK");
			zPanel.add(batchOK);

			// total height of widget. Components are distributed evenly
			/*phPanel.setHeight("120px");	

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
			
			*/

			batchOK.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					phPanel.clear();
					try {
						 String batchNr = batchTxt.getText();
						 if(batchNr.equals("1")) {
//							 connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
//							 
//							 getProduktbatchReceptStmt = connection.prepareStatement
//									 ("SELECT recept_id FROM produktbatch WHERE pb_id = 1 ");
//							 getProduktbatchReceptStmt.executeQuery();
//							 
//							 String result = getProduktbatchReceptStmt.toString(); 
//							 Label b = new Label(result);
//							 HorizontalPanel hPanel = new HorizontalPanel();
//							 hPanel.add(b);
						 }
						 /*RaavareDTO r = new RaavareDTO( Integer.parseInt(idTxt.getText()), nameTxt.getText(), levTxt.getText());
						 connection = 
									DriverManager.getConnection( URL, USERNAME, PASSWORD );

							// create query that add/create a raavare to kartotek
							saveRaavareStmt = 
									connection.prepareStatement( "INSERT INTO raavare " + 
											"( raavare_id, raavare_navn, leverandoer ) " + 
											"VALUES (?, ?, ? )" );
							saveRaavareStmt.setInt(1, r.getRaavareId());
							saveRaavareStmt.setString(2, r.getRaavareNavn());
							saveRaavareStmt.setString(3, r.getLeverandoer());

							saveRaavareStmt.executeUpdate();*/
				        }
				        catch (Exception e) {
				            e.printStackTrace();
				        }
				        }		
			} );
			
			phPanel.add(xPanel);
			phPanel.add(yPanel);
			phPanel.add(zPanel);

		}
		
		
	}


