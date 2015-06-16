package dtu.client.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.RaavarebatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.UserDTO;


@RemoteServiceRelativePath("kartotekservice")
public interface KartotekService extends RemoteService {

	// note: announcing exception makes it possible to communicate 
	// user defined exceptions from the server side to the client side
	// otherwise only generic server exceptions will be send back
	// in the onFailure call back method
	
	public void saveRaavare(RaavareDTO r) throws Exception;
	public void updateRaavare(RaavareDTO r) throws Exception;
	public ArrayList<RaavareDTO> getRaavare() throws Exception;
	public void deleteRaavare(int id) throws Exception; 
	public int getSize() throws Exception;
	
	public void saveUser(UserDTO u) throws Exception;
	public UserDTO findUser(int u) throws Exception;
	public void updateUser(UserDTO r) throws Exception;
	
	public void saveRecept(ReceptDTO newRecept) throws Exception;
	
	public void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent) throws Exception;
	public ArrayList<String> findReceptkomponet(int id) throws Exception;
	
	public void saveProduktbatch(ProduktbatchDTO pb) throws Exception;
	public ArrayList<String> showProduktbatch() throws Exception;
	public void statusProduktbatch(int id, int status) throws Exception;
	
	public void saveRaavarebatch(RaavarebatchDTO rb) throws Exception;
	
	public String userName(int id) throws Exception;
	
	public int logUser(int id, String psw) throws Exception;
	
	public ArrayList<UserDTO> getUsers() throws Exception;
	

}
