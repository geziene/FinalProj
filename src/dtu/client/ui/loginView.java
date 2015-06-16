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
import com.google.gwt.user.client.ui.Widget;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.UserDTO;


public class loginView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel loginPanel;
	FlexTable ft;
	Label usr;
	final TextBox usrTxt;
	Label pwd;
	final TextBox pwdTxt;

	Button login = new Button("Login");	
	
	public loginView(final KartotekServiceClientImpl clientImpl)
	{
		this.clientImpl = clientImpl;

		loginPanel = new VerticalPanel();
		initWidget(this.loginPanel);
		
		HorizontalPanel userPanel = new HorizontalPanel();
		HorizontalPanel pwdPanel = new HorizontalPanel();

		usr = new Label("Username:");
		usr.setWidth("60px");
		usrTxt = new TextBox();
		usrTxt.setHeight("1em");
		userPanel.add(usr);
		userPanel.add(usrTxt);
		
		pwd = new Label("Password:");
		pwd.setWidth("60px");
		pwdTxt = new TextBox();
		pwdTxt.setHeight("1em");
		pwdPanel.add(pwd);
		pwdPanel.add(pwdTxt);
		
		login.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int userid = Integer.parseInt(usrTxt.getText());
				String userpsw = (pwdTxt.getText());
				clientImpl.service.logUser(userid, userpsw, new AsyncCallback<Integer>() {
					
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! " + caught.getMessage());
					}

			@Override
			public void onSuccess(Integer result) {
	
				check(result);
					}
			});
			
			}		
		});
		
		loginPanel.add(userPanel);
		loginPanel.add(pwdPanel);
		loginPanel.add(login);

	}

public void check(int result)
{
	Widget w = null;
	if (result == 1){
		w = new adminView(clientImpl);
	} else if(result == 2){
		w = new PharmacistView(clientImpl);
	} else if(result == 3){
		w = new VaerkfView(clientImpl);
	} else if(result == 4){
		w = new OperatorView(clientImpl);
	}else Window.alert("Bruger i gruppe: " + result + " findes ikke");
	
	if(result != 5) 
		loginPanel.clear();
	loginPanel.add(w);
	
}


}
