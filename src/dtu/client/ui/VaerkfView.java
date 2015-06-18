package dtu.client.ui;

import java.text.*;
import java.util.ArrayList;

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
import dtu.shared.DoubleExceptions;
import dtu.shared.FieldVerifier;
import dtu.shared.HeltalExceptions;
import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavarebatchDTO;
	
	public class VaerkfView extends Composite{
		KartotekServiceClientImpl clientImpl;
		VerticalPanel phPanel;
		TextBox Pb_IdTxt = null;
		TextBox statusTxt = null;
		TextBox receptTxt = null;
		TextBox made_byTxt = null;

		Anchor previousCancel = null;
		FlexTable t;
		int eventRowIndex;
		boolean Pb_IdValid = true;
		boolean statusValid = true;
		boolean receptValid = true;
		boolean made_byValid = true;

		
		Button addRaavarebatches = new Button("Create Raavarebatches");
//        Button showRaavarebatches = new Button("Show Raavarebatches");
        
		Button addProduktbatches = new Button("Create Produktbatches");
//		Button showProduktbatches = new Button("Show Produktbatches");
		Button updateProduktbatches = new Button("Update Produktbatches");
		
		HeltalExceptions HTest = new HeltalExceptions();
		DoubleExceptions DTest = new DoubleExceptions();
		
		//BrowseView bv = new BrowseView(clientImpl);
		
		public VaerkfView(KartotekServiceClientImpl clientImpl) // pramtriz cnsrctr
		{
			this.clientImpl = clientImpl;

			phPanel = new VerticalPanel();
			initWidget(this.phPanel);
			
			addProduktbatches.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					ProduktbatchesFields();
				}
			});
			
/*			showProduktbatches.addClickHandler(new ClickHandler(){
				
				@Override
				public void onClick(ClickEvent event){
				showProduktbatchesFields();
				}
			});*/
			
			addRaavarebatches.addClickHandler(new ClickHandler() {
				 
                @Override
                public void onClick(ClickEvent event) {
                    RaavarebatchesFields();
                }
            });
			updateProduktbatches.addClickHandler(new ClickHandler() {
				 
                @Override
                public void onClick(ClickEvent event) {
                	ProduktbatchesFields();
                }
            });

             
            phPanel.add(addRaavarebatches);
             
     //       phPanel.add(showRaavarebatches);
			
			phPanel.add(addProduktbatches);
			
		//	phPanel.add(showProduktbatches);
			phPanel.add(updateProduktbatches);
			
		}
		
		 public void RaavarebatchesFields() 
	        {
	            phPanel.clear();
	             
	            Label raavarebatch_idLbl;
	            final TextBox raavarebatch_idTxt;
	            Label raavare_idLbl;
	            final TextBox raavare_idTxt;
	            Label maengdeLbl;
	            final TextBox maengdeTxt;
	             
	            Button save = new Button("Create");

	            // total height of widget. Components are distributed evenly
	            phPanel.setHeight("120px"); 
	 
	            HorizontalPanel maengdePanel = new HorizontalPanel();
	            HorizontalPanel raavare_idPanel = new HorizontalPanel();
	            HorizontalPanel raavarebatch_idPanel = new HorizontalPanel();
	 
	            raavarebatch_idLbl = new Label("raavarebatch_id:");
	            raavarebatch_idLbl.setWidth("100px");
	            raavarebatch_idTxt = new TextBox();
	            raavarebatch_idTxt.setHeight("1em");
	            raavarebatch_idPanel.add(raavarebatch_idLbl);
	            raavarebatch_idPanel.add(raavarebatch_idTxt);
	             
	            raavare_idLbl = new Label("raavare_id:");
	            raavare_idLbl.setWidth("100px");
	            raavare_idTxt = new TextBox();
	            raavare_idTxt.setHeight("1em");
	            raavare_idPanel.add(raavare_idLbl);
	            raavare_idPanel.add(raavare_idTxt);
	             
	            maengdeLbl = new Label("Maengde:");
	            maengdeLbl.setWidth("100px");
	            maengdeTxt = new TextBox();
	            maengdeTxt.setHeight("1em");
	            maengdePanel.add(maengdeLbl);
	            maengdePanel.add(maengdeTxt);
	            
	            save.addClickHandler(new ClickHandler()  {
	 
	                @Override
	                public void onClick(ClickEvent event)   {
	                     try
	                        {
	                    	 if (HTest.HeltalStringTest(raavarebatch_idTxt.getText()) && HTest.HeltalStringTest(raavare_idTxt.getText())){
	                    	 if (HTest.HeltalTest(Integer.parseInt(raavarebatch_idTxt.getText())) && HTest.HeltalTest(Integer.parseInt(raavare_idTxt.getText())) ){
	                    	 if (DTest.DoubleStringTest(maengdeTxt.getText())){
	                    	 	 double maengde = DTest.DoubleDecimalTest(maengdeTxt.getText());
	                    		 RaavarebatchDTO rb = new RaavarebatchDTO(Integer.parseInt(raavarebatch_idTxt.getText()), Integer.parseInt(raavare_idTxt.getText()), maengde);
	                    		 clientImpl.service.saveRaavarebatch(rb, new AsyncCallback<Void>() {
	                    		 @Override
	             				public void onFailure(Throwable caught) {
	             					Window.alert("Server fejl!" + caught.getMessage());
	             						}

	             				@Override
	             				public void onSuccess(Void result) {
	             					Window.alert("Raavarebatch gemt i kartotek");
	             						}
	                    	 });
	                        }
	                     }
	                        }
	                       }
	                        
	                        catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                        }       
	            } );
	             
	            phPanel.add(raavarebatch_idPanel);
	            phPanel.add(raavare_idPanel);
	            phPanel.add(maengdePanel);
	            phPanel.add(save);
	            
	     
	 
	        }
		 
		 
/*		 public void showProduktbatchesFields(){
			 phPanel.clear();
			 final FlexTable pbTable = new FlexTable();
			 clientImpl.service.showProduktbatch(new AsyncCallback<ArrayList<String>>() {
				 
				@Override
				public void onSuccess(ArrayList<String> result) {
					int j = 0;
					pbTable.setText(0, 0, "Produktbatch id");
					pbTable.setText(0, 1, "Status");
					pbTable.setText(0, 2, "Recept id");
					pbTable.setText(0, 3, "Made by");
					for(int i = 0; i < result.size()/4; i++) {
						pbTable.setText(i + 1, 0, result.get(j));
						pbTable.setText(i + 1, 1, result.get(j + 1));
						pbTable.setText(i + 1, 2, result.get(j + 2));
						pbTable.setText(i + 1, 3, result.get(j + 3));
						j = j + 4;
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server error " + caught);
				}
			});
			 pbTable.setBorderWidth(1);
			 phPanel.add(pbTable);
		 } */

			public void ProduktbatchesFields()
			{
				phPanel.clear();
				
				final FlexTable t = new FlexTable();

				// adjust column widths
				t.getFlexCellFormatter().setWidth(0, 0, "50px");
				t.getFlexCellFormatter().setWidth(0, 1, "50px");
				t.getFlexCellFormatter().setWidth(0, 2, "50px");
				t.getFlexCellFormatter().setWidth(0, 3, "80px");
				t.getFlexCellFormatter().setWidth(0, 4, "50px");
				t.getFlexCellFormatter().setWidth(0, 5, "50px");


				// style table
				t.addStyleName("FlexTable");
				t.getRowFormatter().addStyleName(0,"FlexTable-Header");

				// set headers in flextable
				t.setText(0, 0, "Pb_Id");
				t.setText(0, 1, "Status");
				t.setText(0, 2, "Recept");
				t.setText(0, 3, "made_by");
				t.setText(0, 4, "Edit");
				t.setText(0, 5, " ");
				
				clientImpl.service.getProduktbatches(new AsyncCallback<ArrayList<ProduktbatchDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

					@Override
					public void onSuccess(ArrayList<ProduktbatchDTO> result) {
						// populate table and add delete anchor to each row
						for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
							t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getproduktbatchId());
							t.setText(rowIndex+1, 1, "" + result.get(rowIndex).getstatus());
							t.setText(rowIndex+1, 2, "" + result.get(rowIndex).getreceptId());
							t.setText(rowIndex+1, 3, "" + result.get(rowIndex).getmade_by());
							
							Anchor edit = new Anchor("edit");
							t.setWidget(rowIndex+1, 4, edit);

							edit.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									// if previous edit open - force cancel operation�
									if (previousCancel != null)
										previousCancel.fireEvent(new ClickEvent(){});

									// get rowindex where event happened
									eventRowIndex = t.getCellForEvent(event).getRowIndex();

									// populate textboxes
								
									statusTxt.setText(t.getText(eventRowIndex, 1));
									receptTxt.setText(t.getText(eventRowIndex, 2));
									made_byTxt.setText(t.getText(eventRowIndex, 3));
									

									// show text boxes for editing
						
									t.setWidget(eventRowIndex, 1, statusTxt);
									t.setWidget(eventRowIndex, 2, receptTxt);
									t.setWidget(eventRowIndex, 3, made_byTxt);

									// start editing here
									Pb_IdTxt.setFocus(true);

									// get edit anchor ref for cancel operation
									final Anchor edit =  (Anchor) event.getSource();

									// get textbox contents for cancel operation
									final String Pb_Id = Pb_IdTxt.getText();
									final String status = statusTxt.getText();
									final String recept = receptTxt.getText();
									final String made_by = made_byTxt.getText();


									final Anchor ok = new Anchor("ok");
									ok.addClickHandler(new ClickHandler() {

										@Override
										public void onClick(ClickEvent event) {

											// remove inputboxes
							
											t.setText(eventRowIndex, 1, statusTxt.getText());
											t.setText(eventRowIndex, 2, receptTxt.getText());
											t.setText(eventRowIndex, 3, made_byTxt.getText());


											// here you will normally fetch the primary key of the row 
											// and use it for location the object to be edited

											// fill DTO with id and new values 
											ProduktbatchDTO pb= new ProduktbatchDTO(
													Integer.parseInt(t.getText(eventRowIndex, 0)), Integer.parseInt(statusTxt.getText()), Integer.parseInt(receptTxt.getText()), 
													Integer.parseInt(made_byTxt.getText())
												);

									
										
											// V.2
											clientImpl.service.updateProduktbatches(pb, new AsyncCallback<Void>() {

												@Override
												public void onSuccess(Void result) {
												
													
												}

												@Override
												public void onFailure(Throwable caught) {
													Window.alert("Server fejl!" + caught.getMessage());
												}

											});

											// restore edit link
											t.setWidget(eventRowIndex, 4, edit);
											t.clearCell(eventRowIndex, 5);

											previousCancel = null;

										}

									});

									Anchor cancel = new Anchor("cancel");
									previousCancel = cancel;
									cancel.addClickHandler(new ClickHandler() {

										@Override
										public void onClick(ClickEvent event) {

											// restore original content of textboxes and rerun input validation
											Pb_IdTxt.setText(Pb_Id);
											statusTxt.fireEvent(new KeyUpEvent() {}); // validation

											Pb_IdTxt.setText(status);
											statusTxt.fireEvent(new KeyUpEvent() {});  // validation
											
											Pb_IdTxt.setText(recept);
											receptTxt.fireEvent(new KeyUpEvent() {});  // validation
											
											Pb_IdTxt.setText(made_by);
											made_byTxt.fireEvent(new KeyUpEvent() {});  // validation
											
											t.setText(eventRowIndex, 1, status);
											t.setText(eventRowIndex, 2, recept);
											t.setText(eventRowIndex, 3, made_by);

											// restore edit link
											t.setWidget(eventRowIndex, 4, edit);
											t.clearCell(eventRowIndex, 5);

											previousCancel = null;
										}

									});


									Pb_IdTxt.addKeyUpHandler(new KeyUpHandler(){

										@Override
										public void onKeyUp(KeyUpEvent event) {
											if (!FieldVerifier.isValidName(Pb_IdTxt.getText())) {
												Pb_IdTxt.setStyleName("gwt-TextBox-invalidEntry");
												Pb_IdValid = false;
											}
											else {
												Pb_IdTxt.removeStyleName("gwt-TextBox-invalidEntry");
												Pb_IdValid = true;
											}

											// enable/disable ok depending on form status 
											if (Pb_IdValid&&statusValid)
												t.setWidget(eventRowIndex, 4, ok);
											else
												t.setText(eventRowIndex, 4, "ok");				
										}

									});

									statusTxt.addKeyUpHandler(new KeyUpHandler(){

										@Override
										public void onKeyUp(KeyUpEvent event) {
											if (!FieldVerifier.isValidName(statusTxt.getText())) {
												statusTxt.setStyleName("gwt-TextBox-invalidEntry");
												statusValid = false;
											}
											else {
												statusTxt.removeStyleName("gwt-TextBox-invalidEntry");
												statusValid = true;
											}

											// enable/disable ok depending on form status 
											if (Pb_IdValid&&statusValid)
												t.setWidget(eventRowIndex, 5, ok);
											else
												t.setText(eventRowIndex, 5, "ok");
										}

										
									});
								
									receptTxt.addKeyUpHandler(new KeyUpHandler(){

										@Override
										public void onKeyUp(KeyUpEvent event) {
											if (!FieldVerifier.isValidName(receptTxt.getText())) {
												receptTxt.setStyleName("gwt-TextBox-invalidEntry");
												receptValid = false;
											}
											else {
												receptTxt.removeStyleName("gwt-TextBox-invalidEntry");
												receptValid = true;
											}

											// enable/disable ok depending on form status 
											if (Pb_IdValid&&receptValid)
												t.setWidget(eventRowIndex, 4, ok);
											else
												t.setText(eventRowIndex, 4, "ok");
										}

									});
									
									made_byTxt.addKeyUpHandler(new KeyUpHandler(){

										@Override
										public void onKeyUp(KeyUpEvent event) {
											if (!FieldVerifier.isValidName(made_byTxt.getText())) {
												made_byTxt.setStyleName("gwt-TextBox-invalidEntry");
												made_byValid = false;
											}
											else {
												made_byTxt.removeStyleName("gwt-TextBox-invalidEntry");
												made_byValid = true;
											}

											// enable/disable ok depending on form status 
											if (Pb_IdValid&&made_byValid)
												t.setWidget(eventRowIndex, 4, ok);
											else
												t.setText(eventRowIndex, 4, "ok");
										}
									});

									// showing ok and cancel widgets
									t.setWidget(eventRowIndex, 4 , ok);
									t.setWidget(eventRowIndex, 5 , cancel);	
								}
							});
						}

					}

				});

				phPanel.add(t);

				// text boxes
				Pb_IdTxt = new TextBox();
				Pb_IdTxt.setWidth("50px");
				statusTxt = new TextBox();
				statusTxt.setWidth("50px");
				receptTxt = new TextBox();
				receptTxt.setWidth("50px");
				made_byTxt = new TextBox();
				made_byTxt.setWidth("50px");

			addProduktbatches.addClickHandler(new ClickHandler() {
			
				@Override
				public void onClick(ClickEvent event) {
					ProduktbatchesFields();
						}
			});
		}

/*		public void ProduktbatchesFields()
		{
			phPanel.clear();
			
			Label pb_idLbl;
			final TextBox pb_idTxt;
			Label statusLbl;
			final TextBox statusTxt;
			Label recept_idLbl;
			final TextBox recept_idTxt;
			Label made_byLbl;
			final TextBox made_byTxt;
			
			Button save = new Button("Create");

			// total height of widget. Components are distributed evenly
			phPanel.setHeight("120px");	
			
			HorizontalPanel pb_idPanel = new HorizontalPanel();
			HorizontalPanel statusPanel = new HorizontalPanel();
			HorizontalPanel recept_idPanel = new HorizontalPanel();
			HorizontalPanel made_byPanel = new HorizontalPanel();

			pb_idLbl = new Label("Pb_id:");
			pb_idLbl.setWidth("100px");
			pb_idTxt = new TextBox();
			pb_idTxt.setHeight("1em");
			pb_idPanel.add(pb_idLbl);
			pb_idPanel.add(pb_idTxt);
			
			statusLbl = new Label("status:");
			statusLbl.setWidth("100px");
			statusTxt = new TextBox();
			statusLbl.setHeight("1em");
			statusPanel.add(statusLbl);
			statusPanel.add(statusTxt);
			
			recept_idLbl = new Label("Recept_id:");
			recept_idLbl.setWidth("100px");
			recept_idTxt = new TextBox();
			recept_idLbl.setHeight("1em");
			recept_idPanel.add(recept_idLbl);
			recept_idPanel.add(recept_idTxt);
			
			made_byLbl = new Label("made_by: ");
			made_byLbl.setWidth("100px");
			made_byTxt = new TextBox();
			made_byLbl.setHeight("1em");
			made_byPanel.add(made_byLbl);
			made_byPanel.add(made_byTxt);

			save.addClickHandler(new ClickHandler()  {

				//@Override
				public void onClick(ClickEvent event)   {
				   
				ProduktbatchDTO produktbatch = new ProduktbatchDTO(Integer.parseInt(pb_idTxt.getText()), Integer.parseInt(statusTxt.getText()),Integer.parseInt( recept_idTxt.getText()),Integer.parseInt(made_byTxt.getText()));	 
				clientImpl.service.saveProduktbatch( produktbatch, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl!" + caught.getMessage());
						}

				@Override
				public void onSuccess(Void result) {
					Window.alert("Produktbatch gemt i kartotek");
						}
					});
		
				        }		

				} 
			
			);
			
			phPanel.add(pb_idPanel);
			phPanel.add(statusPanel);
			phPanel.add(recept_idPanel);
			phPanel.add(made_byPanel);
			phPanel.add(save);

		} */
		
	}
