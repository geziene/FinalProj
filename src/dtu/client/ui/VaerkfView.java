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
import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavarebatchDTO;
	
	public class VaerkfView extends Composite{
		KartotekServiceClientImpl clientImpl;
		VerticalPanel phPanel;
		
		Button addRaavarebatches = new Button("Create Raavarebatches");
        Button showRaavarebatches = new Button("Show Raavarebatches");
        
		Button addProduktbatches = new Button("Create Produktbatches");
		Button showProduktbatches = new Button("Show Produktbatches");
		Button updateProduktbatches = new Button("Update Produktbatches");
		
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
			
			showProduktbatches.addClickHandler(new ClickHandler(){
				
				@Override
				public void onClick(ClickEvent event){
				showProduktbatchesFields();
				}
			});
			
			addRaavarebatches.addClickHandler(new ClickHandler() {
				 
                @Override
                public void onClick(ClickEvent event) {
                    RaavarebatchesFields();
                }
            });
             
            phPanel.add(addRaavarebatches);
             
            phPanel.add(showRaavarebatches);
            //phPanel.add(updateRaavare);
			
			phPanel.add(addProduktbatches);
			
			phPanel.add(showProduktbatches);
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
	                    	 RaavarebatchDTO rb = new RaavarebatchDTO(Integer.parseInt(raavarebatch_idTxt.getText()), Integer.parseInt(raavare_idTxt.getText()), Double.parseDouble(maengdeTxt.getText()));
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
		 
		 
		 public void showProduktbatchesFields(){
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
					// TODO Auto-generated method stub
					
				}
			});
			 pbTable.setBorderWidth(1);
			 phPanel.add(pbTable);
		 }

		public void ProduktbatchesFields()
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

		}
		
	}
