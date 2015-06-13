package dtu.server.dal;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.RaavarebatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.UserDTO;

public class GeneralDAO extends RemoteServiceServlet implements KartotekService {
	// primary key, autoincrement - not safe!
	private static int id = 0;

	private List<RaavareDTO> pList;
	private List<UserDTO> uList;
	private List<ReceptDTO> rList;
	private List<ReceptkomponentDTO> kList;
	private List<ProduktbatchDTO> pbList;
	private List<RaavarebatchDTO> rbList;

	public GeneralDAO() throws Exception {
		pList = new ArrayList<RaavareDTO>();
		uList = new ArrayList<UserDTO>();
		rList = new ArrayList<ReceptDTO>();
		kList = new ArrayList<ReceptkomponentDTO>();
		pbList = new ArrayList<ProduktbatchDTO>();
		rbList = new ArrayList<RaavarebatchDTO>();
	}

	@Override
	public void saveRaavare(RaavareDTO r) throws Exception {
		// add primary key
		r.setRaavareId(id++);
		pList.add(r);
	}

	@Override
	public void updateRaavare(RaavareDTO r) throws Exception {
		// find object with id and update it
		for (int i=0; i<pList.size();i++)
			if (pList.get(i).getRaavareId() == r.getRaavareId())	
				pList.set(i, r);
	}
	
	@Override
	public List<RaavareDTO> getRaavare() throws Exception {
		return pList;
	}

	@Override
	public int getSize() throws Exception {
		return pList.size();
	}

	@Override
	public void deleteRaavare(int id) throws Exception {

		// find object with id and remove it
		for (int i=0; i<pList.size();i++)
			if (pList.get(i).getRaavareId() == id)	
				pList.remove(i);
	}
	
	public void saveUser(UserDTO u) throws Exception {
		u.setuserId(id++);
		uList.add(u);	
	}
	
	public UserDTO findUser(int u) throws Exception{
		UserDTO dto = null;
		return dto;
	}

	@Override
	public void saveRecept(ReceptDTO newRecept) throws Exception {
		newRecept.setreceptId(id++);
		rList.add(newRecept);
		
	}

	@Override
	public void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent) throws Exception {
		newReceptkomponent.setreceptkomponentId(id++);
		kList.add(newReceptkomponent);
	}
	
	@Override
	public void saveProduktbatch(ProduktbatchDTO pb) throws Exception {
		pb.setproduktbatchId(id++);
		pbList.add(pb);
	}

	@Override
	public void saveRaavarebatch(RaavarebatchDTO rb) throws Exception {
		rb.setRaavarebatchId(id++);
		rbList.add(rb);
	}

	@Override
	public ArrayList<String> showProduktbatch() throws Exception {
		ArrayList list = new ArrayList();
		return list;
		
	}
}
