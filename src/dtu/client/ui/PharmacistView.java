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
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;

public class PharmacistView extends Composite {
	KartotekServiceClientImpl clientImpl;
	VerticalPanel phPanel;

	Button addRaavare = new Button("Create Raavare");
	Button showRaavare = new Button("Show Raavare");
	Button updateRaavare = new Button("Update Raavare");
	
	Button addRecept = new Button("Create Recept");
	Button showRecept = new Button("Show Recept");
	Button addReceptkomponent = new Button("Create receptkomponet");
	Button updateReceptkomponent = new Button("Update receptkomponet");
	//BrowseView bv = new BrowseView(clientImpl);
	
	public PharmacistView(final KartotekServiceClientImpl clientImpl) // pramtriz cnsrctr
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

		addRecept.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ReceptFields();
					}

			private void ReceptFields() {
				phPanel.clear();
				Label receptidLbl;
				final TextBox receptidTxt;
				Label receptnameLbl;
				final TextBox receptnameTxt;
				
				Button save = new Button("Create");

				// total height of widget. Components are distributed evenly
				phPanel.setHeight("120px");	

				HorizontalPanel receptnamePanel = new HorizontalPanel();
				HorizontalPanel receptidPanel = new HorizontalPanel();
				
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

				save.addClickHandler(new ClickHandler()  {

					@Override
					public void onClick(ClickEvent event)   {
					 ReceptDTO newRecept = new ReceptDTO( Integer.parseInt(receptidTxt.getText()), receptnameTxt.getText());	 
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
				} );
				
				phPanel.add(receptidPanel);
				phPanel.add(receptnamePanel);
				phPanel.add(save);

			}

		});
		
			addReceptkomponent.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ReceptkomponentFields();
					}

			private void ReceptkomponentFields() {
				phPanel.clear();
				Label receptidLbl;
				final TextBox receptidTxt;
				Label nettoLbl;
				final TextBox nettoTxt;
				Label toleranceLbl;
				final TextBox toleranceTxt;
				Label madebyLbl;
				final TextBox madebyTxt;
				Label raavareidLbl;
				final TextBox raavareidTxt;
		
				Button savereceptkomponent = new Button("Create");
				
				// total height of widget. Components are distributed evenly
				phPanel.setHeight("120px");	

				HorizontalPanel receptnamePanel = new HorizontalPanel();
				HorizontalPanel receptidPanel = new HorizontalPanel();
				HorizontalPanel raavareidPanel = new HorizontalPanel();
				HorizontalPanel nettoPanel = new HorizontalPanel();
				HorizontalPanel tolerancePanel = new HorizontalPanel();
				HorizontalPanel madebyPanel = new HorizontalPanel();
				
				receptidLbl = new Label("Reccept ID:");
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
				
				madebyLbl = new Label("Made by:");
				madebyLbl.setWidth("100px");
				madebyTxt = new TextBox();
				madebyTxt.setHeight("1em");
				madebyPanel.add(madebyLbl);
				madebyPanel.add(madebyTxt);
				
				savereceptkomponent.addClickHandler(new ClickHandler()  {

					@Override
					public void onClick(ClickEvent event) {
					
					ReceptkomponentDTO newReceptkomponent = new ReceptkomponentDTO(Integer.parseInt(receptidTxt.getText()),
							 Integer.parseInt(raavareidTxt.getText()), Double.parseDouble(nettoTxt.getText()), 
							 Double.parseDouble(toleranceTxt.getText()),Integer.parseInt(madebyTxt.getText()));
					clientImpl.service.saveReceptkomponent(newReceptkomponent, new AsyncCallback<Void>() {

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
				} );
				
				phPanel.add(receptidPanel);
				phPanel.add(receptnamePanel);
				phPanel.add(raavareidPanel);
				phPanel.add(nettoPanel);
				phPanel.add(tolerancePanel);
				phPanel.add(madebyPanel);
				phPanel.add(savereceptkomponent);

			}

		});
		phPanel.add(addRaavare);
		phPanel.add(showRaavare);
		phPanel.add(updateRaavare);
		
		phPanel.add(addRecept);
		phPanel.add(showRecept);
		phPanel.add(addReceptkomponent);
		phPanel.add(updateReceptkomponent);

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
		} );
		
		phPanel.add(idPanel);
		phPanel.add(namePanel);
		phPanel.add(levPanel);
		
		phPanel.add(save);

	}
	
}
