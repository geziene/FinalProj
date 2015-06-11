package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.RaavareDTO;
import dtu.shared.UserDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	
	public void saveRaavare(RaavareDTO r) throws Exception;
	public void updateRaavare(RaavareDTO r) throws Exception;
	public List<RaavareDTO> getRaavare() throws Exception;
	public void deleteRaavare(int id) throws Exception; 
	public int getSize() throws Exception;
	
	public void saveUser(UserDTO u) throws Exception;
}
