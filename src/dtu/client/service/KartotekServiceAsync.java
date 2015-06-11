package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.UserDTO;

public interface KartotekServiceAsync {

	void saveRaavare(RaavareDTO r, AsyncCallback<Void> callback);

	void updateRaavare(RaavareDTO r, AsyncCallback<Void> callback);

	void getRaavare(AsyncCallback<List<RaavareDTO>> callback);

	void deleteRaavare(int index, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);

	
	void saveUser(UserDTO u, AsyncCallback<Void> callback);

	void saveRecept(ReceptDTO newRecept, AsyncCallback<Void> asyncCallback);

	void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent, AsyncCallback<Void> asyncCallback);

	void saveProduktbatch(ProduktbatchDTO pb, AsyncCallback<Void> asyncCallback);

	
	
}
