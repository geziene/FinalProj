package dtu.client.ui;

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
import dtu.shared.FieldVerifier;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.HeltalExceptions;
import dtu.shared.DoubleExceptions;
import dtu.shared.TekstExceptions;

public class PharmacistView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel phPanel;
	TextBox navnTxt = null;
	TextBox leverTxt = null;
	TextBox receptidTxt = null;
	TextBox raavareidTxt = null;
	TextBox madebyTxt = null;
	TextBox pdidTxt = null;
	TextBox nettoTxt = null;
	TextBox toleranceTxt = null;
	boolean nettoValid = true;
	boolean toleranceValid = true;
	Anchor previousCancel = null;
	FlexTable t;
	int eventRowIndex;
	boolean nameValid = true;
	boolean levValid = true;
	
	Button addRaavare = new Button("Opret Raavare");
//	Button showRaavare = new Button("Show Raavare");
	Button updateRaavare = new Button("Rediger Raavare");
	
	Button addRecept = new Button("Opret Recept");
	Button showRecept = new Button("Show Recept");

	Button addReceptkomponent = new Button("Opret receptkomponet");
//	Button showReceptkomponent = new Button("Show receptkomponent");
	Button updateReceptkomponent = new Button("rediger receptkomponet");
	
	HeltalExceptions HTest = new HeltalExceptions();
	DoubleExceptions DTest = new DoubleExceptions();
	TekstExceptions TTest = new TekstExceptions();
	
	public PharmacistView(KartotekServiceClientImpl clientImpl)
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
		
		updateRaavare.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateRaavareFields();
			}
			
		});

		addRecept.addClickHandler(new ClickHandler() {
		
			@Override
			public void onClick(ClickEvent event) {
				ReceptFields();
					}
		});
		
		showRecept.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showReceptFields();
					}
		});
		
		addReceptkomponent.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ReceptkomponentFields();
			}
					});
	
		updateReceptkomponent.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				UpdateReceptkomponentFields();
			}
					});
		
		
		
		phPanel.add(addRaavare);
	//	phPanel.add(showRaavare);
		phPanel.add(updateRaavare);
		phPanel.add(addRecept);
		phPanel.add(showRecept);
		phPanel.add(addReceptkomponent);
//		phPanel.add(showReceptkomponent);
		phPanel.add(updateReceptkomponent);
		
	}
	
		public void addRaavareFields() 
		{
			phPanel.clear();
			
			Label idLbl;
			final TextBox idTxt;
			Label nameLbl;
			Label levLbl;
			final TextBox nameTxt;
			final TextBox levTxt;
			Button save = new Button("Create");

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
			levTxt.setHeight("1em");
			levPanel.add(levLbl);
			levPanel.add(levTxt);
			
			save.addClickHandler(new ClickHandler()  {

				@Override
				public void onClick(ClickEvent event)   {
				   idTxt.getText();
				   nameTxt.getText();
				   levTxt.getText();
					 
				 RaavareDTO raavare1 = new RaavareDTO(Integer.parseInt(idTxt.getText()), nameTxt.getText(), levTxt.getText());	 
				 clientImpl.service.saveRaavare(raavare1, new AsyncCallback<Void>() {

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
		
		public void showRaavareFields() 
		{
			phPanel.clear();
			
			final FlexTable t1 = new FlexTable();
			t1.getFlexCellFormatter().setWidth(0, 0, "50px");
			t1.getFlexCellFormatter().setWidth(0, 1, "100px");
			t1.getFlexCellFormatter().setWidth(0, 2, "150px");

			t1.addStyleName("FlexTable"); 
			t1.getRowFormatter().addStyleName(0,"FlexTable-Header"); //  it show one big table
			
			// set headers in flextable
			t1.setText(0, 0, "Id");
			t1.setText(0, 1, "Navn");
			t1.setText(0, 2, "Leverandoer");

			// V.2 
			clientImpl.service.getRaavare(new AsyncCallback<ArrayList<RaavareDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl!" + caught.getMessage());
				}

				@Override
				public void onSuccess(ArrayList<RaavareDTO> result) {
					for (int i=0; i < result.size(); i++) {
						t1.setText(i+1, 0, "" + result.get(i).getRaavareId());
						t1.setText(i+1, 1, result.get(i).getRaavareNavn());
						t1.setText(i+1, 2, "" + result.get(i).getLeverandoer());
					}

				}

			});

			phPanel.add(t1);
			
		}
		
		public void updateRaavareFields() 
		{			
			phPanel.clear();
			
			final FlexTable t = new FlexTable();

			// adjust column widths
			t.getFlexCellFormatter().setWidth(0, 0, "50px");
			t.getFlexCellFormatter().setWidth(0, 1, "50px");
			t.getFlexCellFormatter().setWidth(0, 2, "100px");
			t.getFlexCellFormatter().setWidth(0, 3, "50px");

			// style table
			t.addStyleName("FlexTable");
			t.getRowFormatter().addStyleName(0,"FlexTable-Header");

			// set headers in flextable
			t.setText(0, 0, "Id");
			t.setText(0, 1, "Navn");
			t.setText(0, 2, "Leverandoer");
			
			clientImpl.service.getRaavare(new AsyncCallback<ArrayList<RaavareDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl!" + caught.getMessage());
				}

				@Override
				public void onSuccess(ArrayList<RaavareDTO> result) {
					// populate table and add delete anchor to each row
					for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
						t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getRaavareId());
						t.setText(rowIndex+1, 1, result.get(rowIndex).getRaavareNavn());
						t.setText(rowIndex+1, 2, "" + result.get(rowIndex).getLeverandoer());
						Anchor edit = new Anchor("edit");
						t.setWidget(rowIndex+1, 3, edit);

						edit.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								// if previous edit open - force cancel operation�
								if (previousCancel != null)
									previousCancel.fireEvent(new ClickEvent(){});

								// get rowindex where event happened
								eventRowIndex = t.getCellForEvent(event).getRowIndex();

								// populate textboxes
								navnTxt.setText(t.getText(eventRowIndex, 1));
								leverTxt.setText(t.getText(eventRowIndex, 2));

								// show text boxes for editing
								t.setWidget(eventRowIndex, 1, navnTxt);
								t.setWidget(eventRowIndex, 2, leverTxt);

								// start editing here
								navnTxt.setFocus(true);

								// get edit anchor ref for cancel operation
								final Anchor edit =  (Anchor) event.getSource();

								// get textbox contents for cancel operation
								final String name = navnTxt.getText();
								final String lev = leverTxt.getText();
								final Anchor ok = new Anchor("ok");
								
								ok.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {

										// remove inputboxes
										t.setText(eventRowIndex, 1, navnTxt.getText());
										t.setText(eventRowIndex, 2, leverTxt.getText());

										// here you will normally fetch the primary key of the row 
										// and use it for location the object to be edited

										// fill DTO with id and new values 
										RaavareDTO RaavareDTO = new RaavareDTO(
												Integer.parseInt(t.getText(eventRowIndex, 0)), navnTxt.getText(), leverTxt.getText()
											);
									
										// V.2
										clientImpl.service.updateRaavare(RaavareDTO, new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {

											}

											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Server fejl!" + caught.getMessage());
											}

										});

										// restore edit link
										t.setWidget(eventRowIndex, 3, edit);
										t.clearCell(eventRowIndex, 4);

										previousCancel = null;

									}

								});

								Anchor cancel = new Anchor("cancel");
								previousCancel = cancel;
								cancel.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {

										// restore original content of textboxes and rerun input validation
										navnTxt.setText(name);
										leverTxt.fireEvent(new KeyUpEvent() {}); // validation

										navnTxt.setText(lev);
										leverTxt.fireEvent(new KeyUpEvent() {});  // validation

										t.setText(eventRowIndex, 1, name);
										t.setText(eventRowIndex, 2, lev);

										// restore edit link
										t.setWidget(eventRowIndex, 3, edit);
										t.clearCell(eventRowIndex, 4);

										previousCancel = null;
									}

								});
								
								navnTxt.addKeyUpHandler(new KeyUpHandler(){

									@Override
									public void onKeyUp(KeyUpEvent event) {
										if (!FieldVerifier.isValidName(navnTxt.getText())) {
											navnTxt.setStyleName("gwt-TextBox-invalidEntry");
											nameValid = false;
										}
										else {
											navnTxt.removeStyleName("gwt-TextBox-invalidEntry");
											nameValid = true;
										}

										// enable/disable ok depending on form status 
										if (nameValid&&levValid)
											t.setWidget(eventRowIndex, 3, ok);
										else
											t.setText(eventRowIndex, 3, "ok");				
									}

								});

								leverTxt.addKeyUpHandler(new KeyUpHandler(){

									@Override
									public void onKeyUp(KeyUpEvent event) {
										if (!FieldVerifier.isValidName(leverTxt.getText())) {
											leverTxt.setStyleName("gwt-TextBox-invalidEntry");
											levValid = false;
										}
										else {
											leverTxt.removeStyleName("gwt-TextBox-invalidEntry");
											levValid = true;
										}

										// enable/disable ok depending on form status 
										if (nameValid&&levValid)
											t.setWidget(eventRowIndex, 3, ok);
										else
											t.setText(eventRowIndex, 3, "ok");
									}

								});

								// showing ok and cancel widgets
								t.setWidget(eventRowIndex, 3 , ok);
								t.setWidget(eventRowIndex, 4 , cancel);	
							}
						});
					}

				}

			});

			phPanel.add(t);

			// text boxes
			navnTxt = new TextBox();
			leverTxt = new TextBox();
			leverTxt.setWidth("50px");

	
	}
		private void UpdateReceptkomponentFields() {
			phPanel.clear();
			
			final FlexTable t = new FlexTable();

			// adjust column widths
			t.getFlexCellFormatter().setWidth(0, 0, "50px");
			t.getFlexCellFormatter().setWidth(0, 1, "50px");
			t.getFlexCellFormatter().setWidth(0, 2, "100px");
			t.getFlexCellFormatter().setWidth(0, 3, "50px");
			t.getFlexCellFormatter().setWidth(0, 4, "50px");
			t.getFlexCellFormatter().setWidth(0, 5, "100px");
			t.getFlexCellFormatter().setWidth(0, 6, "50px");
			// style table
			t.addStyleName("FlexTable");
			t.getRowFormatter().addStyleName(0,"FlexTable-Header");

			// set headers in flextable
			t.setText(0, 0, "Recept Id");
			t.setText(0, 1, "Raavare ID");
			t.setText(0, 2, "Made by");
			t.setText(0, 3, "Pb Id");
			t.setText(0, 4, "Netto vaegt");
			t.setText(0, 5, "Tolerance");
			
			
			clientImpl.service.getReceptkomponent(new AsyncCallback<ArrayList<ReceptkomponentDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server fejl!" + caught.getMessage());
				}

				@Override
				public void onSuccess(ArrayList<ReceptkomponentDTO> result) {
					// populate table and add delete anchor to each row
					for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
						t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getreceptkomponentId());
						t.setText(rowIndex+1, 1, "" + result.get(rowIndex).getraavarekomponentId());
						t.setText(rowIndex+1, 2, "" + result.get(rowIndex).getmadebykomponent());
						t.setText(rowIndex+1, 3, "" + result.get(rowIndex).getpbidkomponentId());
						t.setText(rowIndex+1, 4, "" + result.get(rowIndex).getnettokomponent());
						t.setText(rowIndex+1, 5, "" + result.get(rowIndex).gettolerancekomponent());
						Anchor edit = new Anchor("edit");
						t.setWidget(rowIndex+1, 6, edit);

						edit.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								// if previous edit open - force cancel operation�
								if (previousCancel != null)
									previousCancel.fireEvent(new ClickEvent(){});

								// get rowindex where event happened
								eventRowIndex = t.getCellForEvent(event).getRowIndex();

								// populate textboxes
								nettoTxt.setText(t.getText(eventRowIndex, 5));
								toleranceTxt.setText(t.getText(eventRowIndex, 6));

								// show text boxes for editing
								t.setWidget(eventRowIndex, 5, nettoTxt);
								t.setWidget(eventRowIndex, 6, toleranceTxt);

								// start editing here
								nettoTxt.setFocus(true);

								// get edit anchor ref for cancel operation
								final Anchor edit =  (Anchor) event.getSource();

								// get textbox contents for cancel operation
								final String netto = nettoTxt.getText();
								final String tolerance = toleranceTxt.getText();
								final Anchor ok = new Anchor("ok");
								
								ok.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {

										// remove inputboxes
										t.setText(eventRowIndex, 5, nettoTxt.getText());
										t.setText(eventRowIndex, 6, toleranceTxt.getText());

										// here you will normally fetch the primary key of the row 
										// and use it for location the object to be edited

										// fill DTO with id and new values 
										
										ReceptkomponentDTO ReceptkomponentDTO = new ReceptkomponentDTO(
												Integer.parseInt(t.getText(eventRowIndex, 0)),
												Integer.parseInt(receptidTxt.getText()),
												Integer.parseInt(raavareidTxt.getText()),
												Integer.parseInt(madebyTxt.getText()),
												Integer.parseInt(pdidTxt.getText()),
												Integer.parseInt(toleranceTxt.getText())
											
											);
									
										// V.2
										clientImpl.service.updateReceptkomponent(ReceptkomponentDTO, new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {

											}

											@Override
											public void onFailure(Throwable caught) {
												Window.alert("Server fejl!" + caught.getMessage());
											}

										});

										// restore edit link
										t.setWidget(eventRowIndex, 3, edit);
										t.clearCell(eventRowIndex, 4);

										previousCancel = null;

									}

								});

								Anchor cancel = new Anchor("cancel");
								previousCancel = cancel;
								cancel.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {

										// restore original content of textboxes and rerun input validation
										nettoTxt.setText(netto);
										toleranceTxt.fireEvent(new KeyUpEvent() {}); // validation

										nettoTxt.setText(tolerance);
										toleranceTxt.fireEvent(new KeyUpEvent() {});  // validation

										t.setText(eventRowIndex, 4, netto);
										t.setText(eventRowIndex, 5, tolerance);

										// restore edit link
										t.setWidget(eventRowIndex, 4, edit);
										t.clearCell(eventRowIndex, 5);

										previousCancel = null;
									}

								});
								
								nettoTxt.addKeyUpHandler(new KeyUpHandler(){

									@Override
									public void onKeyUp(KeyUpEvent event) {
										if (!FieldVerifier.isValidName(nettoTxt.getText())) {
											nettoTxt.setStyleName("gwt-TextBox-invalidEntry");
											nettoValid = false;
										}
										else {
											nettoTxt.removeStyleName("gwt-TextBox-invalidEntry");
											nettoValid = true;
										}

										// enable/disable ok depending on form status 
										if (nettoValid&&toleranceValid)
											t.setWidget(eventRowIndex, 4, ok);
										else
											t.setText(eventRowIndex, 4, "ok");				
									}

								});

								toleranceTxt.addKeyUpHandler(new KeyUpHandler(){

									@Override
									public void onKeyUp(KeyUpEvent event) {
										if (!FieldVerifier.isValidName(toleranceTxt.getText())) {
											toleranceTxt.setStyleName("gwt-TextBox-invalidEntry");
											toleranceValid = false;
										}
										else {
											toleranceTxt.removeStyleName("gwt-TextBox-invalidEntry");
											toleranceValid = true;
										}

										// enable/disable ok depending on form status 
										if (nettoValid&&toleranceValid)
											t.setWidget(eventRowIndex, 4, ok);
										else
											t.setText(eventRowIndex, 4, "ok");
									}

								});

								// showing ok and cancel widgets
								t.setWidget(eventRowIndex, 7 , ok);
								t.setWidget(eventRowIndex, 8 , cancel);	
							}
						});
					}

				}

				

			});

			phPanel.add(t);

			// text boxes
			nettoTxt = new TextBox();
			toleranceTxt = new TextBox();
			toleranceTxt.setWidth("40px");

	
			
		}
			private void ReceptFields() {
				phPanel.clear();
				Label receptidLbl;
				final TextBox receptidTxt;
				Label receptnameLbl;
				final TextBox receptnameTxt;
				Label madebyLbl;
				final TextBox madebyTxt;
				
				Button save = new Button("Opret");

				phPanel.setHeight("120px");	

				HorizontalPanel receptnamePanel = new HorizontalPanel();
				HorizontalPanel receptidPanel = new HorizontalPanel();
				HorizontalPanel madebyPanel = new HorizontalPanel();
				
				
				receptidLbl = new Label("Recept ID:");
				receptidLbl.setWidth("100px");
				receptidTxt = new TextBox();
				receptidTxt.setHeight("1em");
				receptidPanel.add(receptidLbl);
				receptidPanel.add(receptidTxt);
				
				receptnameLbl = new Label("Recept Navn:");
				receptnameLbl.setWidth("100px");
				receptnameTxt = new TextBox();
				receptnameTxt.setHeight("1em");
				receptnamePanel.add(receptnameLbl);
				receptnamePanel.add(receptnameTxt);
				
				madebyLbl = new Label("Recept made by: ");
				madebyLbl.setWidth("100px");
				madebyTxt = new TextBox();
				madebyTxt.setHeight("1em");
				madebyPanel.add(madebyLbl);
				madebyPanel.add(madebyTxt);

				save.addClickHandler(new ClickHandler()  {

					@Override
					public void onClick(ClickEvent event) {
					if (HTest.HeltalStringTest(receptidTxt.getText()) && HTest.HeltalStringTest(madebyTxt.getText()) ){
				    if (HTest.HeltalTest(Integer.parseInt(receptidTxt.getText())) && HTest.MadebyTest(Integer.parseInt(madebyTxt.getText()))){
				    if (TTest.TekstTest(receptnameTxt.getText(),2 ,20)){
					 ReceptDTO newRecept = new ReceptDTO( Integer.parseInt(receptidTxt.getText()), 
							 receptnameTxt.getText(),Integer.parseInt(madebyTxt.getText()));	 
					 clientImpl.service.saveRecept(newRecept, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server error!" + caught.getMessage());
							}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Recept saved in system");
							}
						});
				    }
				    }
					}
					        }		
				} );
				
				phPanel.add(receptidPanel);
				phPanel.add(receptnamePanel);
				phPanel.add(madebyPanel);
				phPanel.add(save);
		
			}

			private void ReceptkomponentFields() {
				phPanel.clear();
				Label receptidLbl;
				final TextBox receptidTxt;
				Label raavareidLbl;
				final TextBox raavareidTxt;
				Label madebyLbl;
				final TextBox madebyTxt;
				Label pbidLbl;
				final TextBox pbidTxt;
				Label nettoLbl;
				final TextBox nettoTxt;
				Label toleranceLbl;
				final TextBox toleranceTxt;
				
		
		
				Button savereceptkomponent = new Button("Opret");
				
				phPanel.setHeight("120px");	

		//		HorizontalPanel receptnamePanel = new HorizontalPanel();
				HorizontalPanel receptidPanel = new HorizontalPanel();
				HorizontalPanel raavareidPanel = new HorizontalPanel();
				HorizontalPanel nettoPanel = new HorizontalPanel();
				HorizontalPanel tolerancePanel = new HorizontalPanel();
				HorizontalPanel madebyPanel = new HorizontalPanel();
				HorizontalPanel pbidPanel = new HorizontalPanel();
				
				receptidLbl = new Label("Recept ID:");
				receptidLbl.setWidth("100px");
				receptidTxt = new TextBox();
				receptidTxt.setHeight("1em");
				receptidPanel.add(receptidLbl);
				receptidPanel.add(receptidTxt);
				
				raavareidLbl = new Label("Raavare ID:");
				raavareidLbl.setWidth("100px");
				raavareidTxt = new TextBox();
				raavareidTxt.setHeight("1em");
				raavareidPanel.add(raavareidLbl);
				raavareidPanel.add(raavareidTxt);
				
				madebyLbl = new Label("Made by:");
				madebyLbl.setWidth("100px");
				madebyTxt = new TextBox();
				madebyTxt.setHeight("1em");
				madebyPanel.add(madebyLbl);
				madebyPanel.add(madebyTxt);
				
				pbidLbl = new Label("Productbatch ID:");
				pbidLbl.setWidth("100px");
				pbidTxt = new TextBox();
				pbidTxt.setHeight("1em");
				pbidPanel.add(pbidLbl);
				pbidPanel.add(pbidTxt);
				
				nettoLbl = new Label("Kvantum(netto):");
				nettoLbl.setWidth("100px");
				nettoTxt = new TextBox();
				nettoTxt.setHeight("1em");
				nettoPanel.add(nettoLbl);
				nettoPanel.add(nettoTxt);
		
				
				toleranceLbl = new Label("Tolerance");
				toleranceLbl.setWidth("100px");
				toleranceTxt = new TextBox();
				toleranceTxt.setHeight("1em");
				tolerancePanel.add(toleranceLbl);
				tolerancePanel.add(toleranceTxt);
				
				
			
				
				savereceptkomponent.addClickHandler(new ClickHandler()  {

					@Override
					public void onClick(ClickEvent event) {
					if (HTest.HeltalStringTest(receptidTxt.getText()) && HTest.HeltalStringTest(raavareidTxt.getText()) && HTest.HeltalStringTest(madebyTxt.getText()) && HTest.HeltalStringTest(pbidTxt.getText())){
	                if (HTest.HeltalTest(Integer.parseInt(receptidTxt.getText())) && HTest.HeltalTest(Integer.parseInt(raavareidTxt.getText())) && HTest.MadebyTest(Integer.parseInt(madebyTxt.getText())) && HTest.HeltalTest(Integer.parseInt(pbidTxt.getText())) ){
	                if (DTest.DoubleStringTest(nettoTxt.getText()) && DTest.DoubleStringTest(toleranceTxt.getText())){
	                if (DTest.DoublenonNettoTest(Double.parseDouble(nettoTxt.getText())) && DTest.DoubletoleranceTest(Double.parseDouble(toleranceTxt.getText()))){
				//	Window.alert("Tester clickhandleren for savereceptkomponent");
					ReceptkomponentDTO newReceptkomponent = new ReceptkomponentDTO(
							 Integer.parseInt(receptidTxt.getText()),
							 Integer.parseInt(raavareidTxt.getText()), 
							 Integer.parseInt(madebyTxt.getText()),
							 Integer.parseInt(pbidTxt.getText()),
							 Double.parseDouble(nettoTxt.getText()), 
							 Double.parseDouble(toleranceTxt.getText()));
					clientImpl.service.saveReceptkomponent(newReceptkomponent, new AsyncCallback<Void>() 
							{

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server error!" + caught.getMessage());
							}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Receptkomponet saved in system");
							}
						});
	                }
	                }
					}
					}	
					}
				} );
				
				phPanel.add(receptidPanel);
				phPanel.add(raavareidPanel);
				phPanel.add(madebyPanel);
				phPanel.add(pbidPanel);
				phPanel.add(nettoPanel);
				phPanel.add(tolerancePanel);
			
				phPanel.add(savereceptkomponent);

			}

	public void RaavareFields() 
	{
		phPanel.clear();
		
		Label idLbl;
		Label nameLbl;
		Label levLbl;
		final TextBox idTxt;
		final TextBox nameTxt;
		final TextBox levTxt ;
		
		Button save = new Button("Create");

		// total height of widget. Components are distributed evenly
		phPanel.setHeight("120px");	

		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel idPanel = new HorizontalPanel();
		HorizontalPanel levPanel = new HorizontalPanel();
		
		idLbl = new Label("Raavare ID:");
		idLbl.setWidth("100px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		nameLbl = new Label("Raavare Navn:");
		nameLbl.setWidth("100px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);
		
		levLbl = new Label("Leverandoer:");
		levLbl.setWidth("100px");
		levTxt = new TextBox();
		levTxt.setHeight("1em");
		levPanel.add(levLbl);
		levPanel.add(levTxt);

		save.addClickHandler(new ClickHandler()  {

			@Override
			public void onClick(ClickEvent event)   {
			if (HTest.HeltalStringTest(idTxt.getText())){
            if (HTest.HeltalTest(Integer.parseInt(idTxt.getText()))){
            if (TTest.TekstTest(nameTxt.getText(),2 ,20) && TTest.TekstTest(levTxt.getText(),2 ,20) ){
			 RaavareDTO newRaavare = new RaavareDTO(Integer.parseInt(idTxt.getText()), nameTxt.getText(), levTxt.getText());	 
			 clientImpl.service.saveRaavare(newRaavare, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server error!" + caught.getMessage());
					}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Raavare saved in system");
					}
				});
            }
			}
			}
			        }		
		} );
		
		phPanel.add(idPanel);
		phPanel.add(namePanel);
		phPanel.add(levPanel);
		phPanel.add(save);

	}
	public void showReceptFields()
	{
		
		phPanel.clear();
		
		final FlexTable t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "50px");
		t.getFlexCellFormatter().setWidth(0, 2, "100px");


		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ReceptId");
		t.setText(0, 1, "ReceptNavn");
		t.setText(0, 2, "Udført af");
		
		clientImpl.service.showRecept(new AsyncCallback<ArrayList<ReceptDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<ReceptDTO> result) {
				// populate table and add delete anchor to each row
				for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
					t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getreceptId());
					t.setText(rowIndex+1, 1, result.get(rowIndex).getreceptNavn());
					t.setText(rowIndex+1, 2, "" + result.get(rowIndex).getmade_by());
		/*			Anchor edit = new Anchor("edit");
					t.setWidget(rowIndex+1, 3, edit);

					edit.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							// if previous edit open - force cancel operation�
							if (previousCancel != null)
								previousCancel.fireEvent(new ClickEvent(){});

							// get rowindex where event happened
							eventRowIndex = t.getCellForEvent(event).getRowIndex();

							// populate textboxes
							navnTxt.setText(t.getText(eventRowIndex, 1));
							leverTxt.setText(t.getText(eventRowIndex, 2));

							// show text boxes for editing
							t.setWidget(eventRowIndex, 1, navnTxt);
							t.setWidget(eventRowIndex, 2, leverTxt);

							// start editing here
							navnTxt.setFocus(true);

							// get edit anchor ref for cancel operation
							final Anchor edit =  (Anchor) event.getSource();

							// get textbox contents for cancel operation
							final String name = navnTxt.getText();
							final String lev = leverTxt.getText();
							final Anchor ok = new Anchor("ok");
							
							ok.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									// remove inputboxes
									t.setText(eventRowIndex, 1, navnTxt.getText());
									t.setText(eventRowIndex, 2, leverTxt.getText());

									// here you will normally fetch the primary key of the row 
									// and use it for location the object to be edited

									// fill DTO with id and new values 
									RaavareDTO RaavareDTO = new RaavareDTO(
											Integer.parseInt(t.getText(eventRowIndex, 0)), navnTxt.getText(), leverTxt.getText()
										);
								
									// V.2
									clientImpl.service.updateRaavare(RaavareDTO, new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {

										}

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Server fejl!" + caught.getMessage());
										}

									});

									// restore edit link
									t.setWidget(eventRowIndex, 3, edit);
									t.clearCell(eventRowIndex, 4);

									previousCancel = null;

								}

							});

							Anchor cancel = new Anchor("cancel");
							previousCancel = cancel;
							cancel.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									// restore original content of textboxes and rerun input validation
									navnTxt.setText(name);
									leverTxt.fireEvent(new KeyUpEvent() {}); // validation

									navnTxt.setText(lev);
									leverTxt.fireEvent(new KeyUpEvent() {});  // validation

									t.setText(eventRowIndex, 1, name);
									t.setText(eventRowIndex, 2, lev);

									// restore edit link
									t.setWidget(eventRowIndex, 3, edit);
									t.clearCell(eventRowIndex, 4);

									previousCancel = null;
								}

							});
							
							navnTxt.addKeyUpHandler(new KeyUpHandler(){

								@Override
								public void onKeyUp(KeyUpEvent event) {
									if (!FieldVerifier.isValidName(navnTxt.getText())) {
										navnTxt.setStyleName("gwt-TextBox-invalidEntry");
										nameValid = false;
									}
									else {
										navnTxt.removeStyleName("gwt-TextBox-invalidEntry");
										nameValid = true;
									}

									// enable/disable ok depending on form status 
									if (nameValid&&levValid)
										t.setWidget(eventRowIndex, 3, ok);
									else
										t.setText(eventRowIndex, 3, "ok");				
								}

							});

							leverTxt.addKeyUpHandler(new KeyUpHandler(){

								@Override
								public void onKeyUp(KeyUpEvent event) {
									if (!FieldVerifier.isValidName(leverTxt.getText())) {
										leverTxt.setStyleName("gwt-TextBox-invalidEntry");
										levValid = false;
									}
									else {
										leverTxt.removeStyleName("gwt-TextBox-invalidEntry");
										levValid = true;
									}

									// enable/disable ok depending on form status 
									if (nameValid&&levValid)
										t.setWidget(eventRowIndex, 3, ok);
									else
										t.setText(eventRowIndex, 3, "ok");
								}

							});

							// showing ok and cancel widgets
							t.setWidget(eventRowIndex, 3 , ok);
							t.setWidget(eventRowIndex, 4 , cancel);	
						}
					});
			*/	}

			}

		});
		

		phPanel.add(t);

		// text boxes
		navnTxt = new TextBox();
		leverTxt = new TextBox();
		leverTxt.setWidth("50px");


}

		
		
	}
	
	
