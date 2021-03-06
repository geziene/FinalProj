package dtu.client.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.RaavarebatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.UserDTO;

public interface KartotekServiceAsync {

	void saveRaavare(RaavareDTO r, AsyncCallback<Void> callback);
	void updateRaavare(RaavareDTO r, AsyncCallback<Void> callback);
	void getRaavare(AsyncCallback<ArrayList<RaavareDTO>> callback);
	void deleteRaavare(int index, AsyncCallback<Void> callback);
	void getSize(AsyncCallback<Integer> callback);
	
	void saveRecept(ReceptDTO newRecept, AsyncCallback<Void> asyncCallback);
	void showRecept(AsyncCallback<ArrayList<ReceptDTO>> asyncCallback);
	void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent, AsyncCallback<Void> asyncCallback);
	void findReceptkomponet(int id, AsyncCallback<ArrayList<String>> asyncCallback);
	void updateReceptkomponent(ReceptkomponentDTO newreceptkomponent, AsyncCallback<Void> callback);
	void getReceptkomponent(AsyncCallback<ArrayList<ReceptkomponentDTO>> callback);
	
		
	void saveUser(UserDTO u, AsyncCallback<Void> callback);
	void findUser(int u, AsyncCallback<UserDTO> callback);
	void updateUser(UserDTO r,AsyncCallback<Void> callback);
	void getUsers(AsyncCallback<ArrayList<UserDTO>> asyncCallback);
	void logUser(int id, String psw, AsyncCallback<Integer> asyncCallback);
	void userName(int id, AsyncCallback<String> asyncCallback);


	void saveProduktbatch(ProduktbatchDTO pb, AsyncCallback<Void> asyncCallback);
	void showProduktbatch(AsyncCallback<ArrayList<String>> callback);
	void statusProduktbatch(int id, int status,AsyncCallback<Void> asyncCallback);
	void getProduktbatches(AsyncCallback<ArrayList<ProduktbatchDTO>> asyncCallback);
	void updateProduktbatches(ProduktbatchDTO p, AsyncCallback<Void> callback);

	void saveRaavarebatch(RaavarebatchDTO rb, AsyncCallback<Void> asyncCallback);


	
	


	
	
}
