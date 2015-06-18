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
import dtu.shared.UserDTO;

public class adminView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel phPanel;
	TextBox navnTxt = null;
	TextBox iniTxt = null;
	TextBox cprTxt = null;
	TextBox pwdTxt = null;
	TextBox grpTxt = null;
	Anchor previousCancel = null;
	FlexTable t;
	int eventRowIndex;
	boolean nameValid = true;
	boolean iniValid = true;
	boolean cprValid = true;
	boolean pwdValid = true;
	boolean grpValid = true;
	
	
	Button addUser = new Button("Opret Bruger");
	Button updateUser = new Button("Redigere Bruger");
	

	
	public adminView(KartotekServiceClientImpl clientImpl)
	{
		this.clientImpl = clientImpl;

		phPanel = new VerticalPanel();
		initWidget(this.phPanel);
		
		addUser.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				UserFields();
			}
		});

		updateUser.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateUserFields();
			}
		});
		
		phPanel.add(addUser);
		phPanel.add(updateUser);
	}

	public void UserFields() 
	{
		phPanel.clear();
		
		Label idLbl;
		Label nameLbl;
		Label iniLbl;
		Label cprLbl;
		Label passwordLbl;
		Label groupLbl;
		final TextBox idTxt;
		final TextBox nameTxt;
		final TextBox iniTxt;
		final TextBox cprTxt;
		final TextBox passwordTxt;
		final TextBox groupTxt;
		
		Button save = new Button("Opret");
		
		
		// total height of widget. Components are distributed evenly
		phPanel.setHeight("120px");	

		HorizontalPanel idPanel = new HorizontalPanel();
		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel iniPanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel passwordPanel = new HorizontalPanel();
		HorizontalPanel groupPanel = new HorizontalPanel();

		idLbl = new Label("Bruger ID:");
		idLbl.setWidth("100px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		nameLbl = new Label("Brugernavn:");
		nameLbl.setWidth("100px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);


		iniLbl = new Label("initialer:");
		iniLbl.setWidth("100px");
		iniTxt = new TextBox();
		//levTxt.setWidth("5em");
		iniTxt.setHeight("1em");
		iniPanel.add(iniLbl);
		iniPanel.add(iniTxt);
		
		
		cprLbl = new Label("CPR:");
		cprLbl.setWidth("100px");
		cprTxt = new TextBox();
		//levTxt.setWidth("5em");
		cprTxt.setHeight("1em");
		cprPanel.add(cprLbl);
		cprPanel.add(cprTxt);

		passwordLbl = new Label("Adgangskode:");
		passwordLbl.setWidth("100px");
		passwordTxt = new TextBox();
		//levTxt.setWidth("5em");
		passwordTxt.setHeight("1em");
		passwordPanel.add(passwordLbl);
		passwordPanel.add(passwordTxt);
		
			
		groupLbl = new Label("Gruppe:");
		groupLbl.setWidth("100px");
		groupTxt = new TextBox();
		//levTxt.setWidth("5em");
		groupTxt.setHeight("1em");
		groupPanel.add(groupLbl);
		groupPanel.add(groupTxt);
		
		save.addClickHandler(new ClickHandler()  {
			
			@Override
			public void onClick(ClickEvent event) {
			 UserDTO newUser = new UserDTO(Integer.parseInt(idTxt.getText()), nameTxt.getText(), iniTxt.getText(), cprTxt.getText(), passwordTxt.getText(), Integer.parseInt(groupTxt.getText()));	 
			 clientImpl.service.saveUser(newUser, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
					}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Bruger er gemt i databasen");
					}
				});
	
			        }		
		} );
		
		phPanel.add(idPanel);
		phPanel.add(namePanel);
		phPanel.add(iniPanel);
		phPanel.add(cprPanel);
		phPanel.add(passwordPanel);
		phPanel.add(groupPanel);
		phPanel.add(save);
	}
	


	public void updateUserFields() 
	{
		
		phPanel.clear();
		
		final FlexTable t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "80px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");
		t.getFlexCellFormatter().setWidth(0, 3, "100px");
		t.getFlexCellFormatter().setWidth(0, 4, "60px");
		t.getFlexCellFormatter().setWidth(0, 5, "50px");
		t.getFlexCellFormatter().setWidth(0, 6, "50px");
		t.getFlexCellFormatter().setWidth(0, 7, "50px");

		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Id");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Password");
		t.setText(0, 5, "Gruppe");
		t.setText(0, 6, "Edit");
		
		clientImpl.service.getUsers(new AsyncCallback<ArrayList<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<UserDTO> result) {
				// populate table and add delete anchor to each row
				for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
					t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getuserId());
					t.setText(rowIndex+1, 1, "" + result.get(rowIndex).getuserNavn());
					t.setText(rowIndex+1, 2, "" + result.get(rowIndex).getuserIni());
					t.setText(rowIndex+1, 3, "" + result.get(rowIndex).getuserCpr());
					t.setText(rowIndex+1, 4, "" + result.get(rowIndex).getuserPassword());
					t.setText(rowIndex+1, 5, "" + result.get(rowIndex).getuserGroup());
					
					Anchor edit = new Anchor("Redigere");
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
							navnTxt.setText(t.getText(eventRowIndex, 1));
							iniTxt.setText(t.getText(eventRowIndex, 2));
							cprTxt.setText(t.getText(eventRowIndex, 3));
							pwdTxt.setText(t.getText(eventRowIndex, 4));
							grpTxt.setText(t.getText(eventRowIndex, 5));
							

							// show text boxes for editing
							t.setWidget(eventRowIndex, 1, navnTxt);
							t.setWidget(eventRowIndex, 2, iniTxt);
							t.setWidget(eventRowIndex, 3, cprTxt);
							t.setWidget(eventRowIndex, 4, pwdTxt);
							t.setWidget(eventRowIndex, 5, grpTxt);

							// start editing here
							navnTxt.setFocus(true);

							// get edit anchor ref for cancel operation
							final Anchor edit =  (Anchor) event.getSource();

							// get textbox contents for cancel operation
							final String name = navnTxt.getText();
							final String ini = iniTxt.getText();
							final String cpr = cprTxt.getText();
							final String pwd = pwdTxt.getText();
							final String grp = grpTxt.getText();


							final Anchor ok = new Anchor("ok");
							ok.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									// remove inputboxes
									t.setText(eventRowIndex, 1, navnTxt.getText());
									t.setText(eventRowIndex, 2, iniTxt.getText());
									t.setText(eventRowIndex, 3, cprTxt.getText());
									t.setText(eventRowIndex, 4, pwdTxt.getText());
									t.setText(eventRowIndex, 5, grpTxt.getText());


									// here you will normally fetch the primary key of the row 
									// and use it for location the object to be edited

									// fill DTO with id and new values 
									final UserDTO dto= new UserDTO(
											Integer.parseInt(t.getText(eventRowIndex, 0)), navnTxt.getText(), iniTxt.getText(), cprTxt.getText(), pwdTxt.getText(), Integer.parseInt(grpTxt.getText())
										);

							
								
									// V.2
									clientImpl.service.updateUser(dto, new AsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
										
											
										}

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Server fejl!" + caught.getMessage());
										}

									});

									// restore edit link
									t.setWidget(eventRowIndex, 6, edit);
									t.clearCell(eventRowIndex, 7);

									previousCancel = null;

								}

							});

							Anchor cancel = new Anchor("Anullere");
							previousCancel = cancel;
							cancel.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									// restore original content of textboxes and rerun input validation
									navnTxt.setText(name);
									iniTxt.fireEvent(new KeyUpEvent() {}); // validation

									navnTxt.setText(ini);
									iniTxt.fireEvent(new KeyUpEvent() {});  // validation
									
									navnTxt.setText(cpr);
									cprTxt.fireEvent(new KeyUpEvent() {});  // validation
									
									navnTxt.setText(pwd);
									pwdTxt.fireEvent(new KeyUpEvent() {});  // validation
									
									navnTxt.setText(grp);
									grpTxt.fireEvent(new KeyUpEvent() {});  // validation


									t.setText(eventRowIndex, 1, name);
									t.setText(eventRowIndex, 2, ini);
									t.setText(eventRowIndex, 3, cpr);
									t.setText(eventRowIndex, 4, pwd);
									t.setText(eventRowIndex, 5, grp);

									// restore edit link
									t.setWidget(eventRowIndex, 6, edit);
									t.clearCell(eventRowIndex, 7);

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
									if (nameValid&&iniValid)
										t.setWidget(eventRowIndex, 6, ok);
									else
										t.setText(eventRowIndex, 6, "ok");				
								}

							});

							iniTxt.addKeyUpHandler(new KeyUpHandler(){

								@Override
								public void onKeyUp(KeyUpEvent event) {
									if (!FieldVerifier.isValidName(iniTxt.getText())) {
										iniTxt.setStyleName("gwt-TextBox-invalidEntry");
										iniValid = false;
									}
									else {
										iniTxt.removeStyleName("gwt-TextBox-invalidEntry");
										iniValid = true;
									}

									// enable/disable ok depending on form status 
									if (nameValid&&iniValid)
										t.setWidget(eventRowIndex, 6, ok);
									else
										t.setText(eventRowIndex, 6, "ok");
								}

							});

							// showing ok and cancel widgets
							t.setWidget(eventRowIndex, 6 , ok);
							t.setWidget(eventRowIndex, 7 , cancel);	
						}
					});
				}

			}

		});

		phPanel.add(t);

		// text boxes
		navnTxt = new TextBox();
		navnTxt.setWidth("50px");
		iniTxt = new TextBox();
		iniTxt.setWidth("50px");
		cprTxt = new TextBox();
		cprTxt.setWidth("50px");
		pwdTxt = new TextBox();
		pwdTxt.setWidth("50px");
		grpTxt = new TextBox();
		grpTxt.setWidth("10px");

	addUser.addClickHandler(new ClickHandler() {
	
		@Override
		public void onClick(ClickEvent event) {
			UserFields();
				}
	});
}
		
}
