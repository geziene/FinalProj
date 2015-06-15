package dtu.client.ui;

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
import dtu.shared.UserDTO;

public class adminView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel phPanel;

	Button addUser = new Button("Create User");
	Button showUser = new Button("Show User");
	Button updateUser = new Button("Update User");
	
//	EditView bv = new EditView(clientImpl);
	
	
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
		
		updateUser.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				FindUser();
			}
		});
		
		phPanel.add(addUser);
		phPanel.add(showUser);
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
		
		Button save = new Button("Create");
		
		
		// total height of widget. Components are distributed evenly
		phPanel.setHeight("120px");	

		HorizontalPanel idPanel = new HorizontalPanel();
		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel iniPanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel passwordPanel = new HorizontalPanel();
		HorizontalPanel groupPanel = new HorizontalPanel();

		idLbl = new Label("User ID:");
		idLbl.setWidth("100px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		nameLbl = new Label("User Name:");
		nameLbl.setWidth("100px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);


		iniLbl = new Label("Initials:");
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

		passwordLbl = new Label("Password:");
		passwordLbl.setWidth("100px");
		passwordTxt = new TextBox();
		//levTxt.setWidth("5em");
		passwordTxt.setHeight("1em");
		passwordPanel.add(passwordLbl);
		passwordPanel.add(passwordTxt);
		
			
		groupLbl = new Label("Group:");
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
				Window.alert("User saved in database");
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
	
	public void FindUser()
	{
phPanel.clear();
		
		Label idLbl;
		
		final TextBox idTxt;
		
		Button Find = new Button("Find ID");
		
		final HorizontalPanel idPanel = new HorizontalPanel();
		
		idLbl = new Label("User ID:");
		idLbl.setWidth("100px");
		idTxt = new TextBox();
		idTxt.setHeight("1em");
		idPanel.add(idLbl);
		idPanel.add(idTxt);
		
		Find.addClickHandler(new ClickHandler()  {
			
			@Override
			public void onClick(ClickEvent event) {
				int user = Integer.parseInt(idTxt.getText());
				clientImpl.service.findUser(user, new AsyncCallback<UserDTO>() {
					
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! " + caught.getMessage());
					}

			@Override
			public void onSuccess(UserDTO result) {
		/*		FlexTable foundUser = null;
				foundUser.setText(0, 0, "User ID");
				foundUser.setText(0, 1, "Username");
				foundUser.setText(0, 2, "Initials");
				foundUser.setText(0, 3, "CPR");
				foundUser.setText(0, 4, "Password");
				foundUser.setText(0, 5, "Group");
				foundUser.setText(1, 0, String.valueOf(result.getuserId()));
				foundUser.setText(1, 1, result.getuserNavn());
				foundUser.setText(1, 2, result.getuserIni());
				foundUser.setText(1, 3, result.getuserCpr());
				foundUser.setText(1, 4, result.getuserPassword());
				foundUser.setText(1, 5, String.valueOf(result.getuserGroup()));
				foundUser.setBorderWidth(1);
				phPanel.add(foundUser); */
				UserUpdate(result);
					}
			});
			
		}
			
		});
		phPanel.add(idPanel);
		phPanel.add(Find);

	}
		public void UserUpdate(UserDTO result)
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
			
			Button save = new Button("Update");
			
			
			// total height of widget. Components are distributed evenly
			phPanel.setHeight("120px");	

			HorizontalPanel idPanel = new HorizontalPanel();
			HorizontalPanel namePanel = new HorizontalPanel();
			HorizontalPanel iniPanel = new HorizontalPanel();
			HorizontalPanel cprPanel = new HorizontalPanel();
			HorizontalPanel passwordPanel = new HorizontalPanel();
			HorizontalPanel groupPanel = new HorizontalPanel();

			idLbl = new Label("User ID:");
			idLbl.setWidth("100px");
			idTxt = new TextBox();
			idTxt.setHeight("1em");
			idPanel.add(idLbl);
			idPanel.add(idTxt);
			
			nameLbl = new Label("User Name:");
			nameLbl.setWidth("100px");
			nameTxt = new TextBox();
			nameTxt.setHeight("1em");
			namePanel.add(nameLbl);
			namePanel.add(nameTxt);


			iniLbl = new Label("Initials:");
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

			passwordLbl = new Label("Password:");
			passwordLbl.setWidth("100px");
			passwordTxt = new TextBox();
			//levTxt.setWidth("5em");
			passwordTxt.setHeight("1em");
			passwordPanel.add(passwordLbl);
			passwordPanel.add(passwordTxt);
			
				
			groupLbl = new Label("Group:");
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
					Window.alert("User Updated in database");
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

}
	
