package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dto.ClientDto;

@Repository
public class ClientDao {
	List<ClientDto> listClients = new ArrayList<ClientDto>();
	
	
	public Map<String, Object> salvar(ClientDto client){
		HashMap<String, Object> status = new HashMap<String, Object>();
		
		if(checkIfExists(client.getName(), this.listClients)) {
			status.put("type", "Já existe");
			status.put("data", this.listClients);
			return status;
		}
		
		this.listClients.add(client);
		status.put("type", "success");
		status.put("data", this.listClients);
		return status;
	}
	
	public List<ClientDto> listar(){
		return this.listClients;
	}
	
	public Map<String, Object> deletar(ClientDto client){
		Map<String, Object> status = new HashMap<String, Object>();
		
		if(!checkIfExists(client.getName(), this.listClients)) {
			status.put("type", "Não existe tal cliente");
		}else {
			status.put("type", "Success");
			List<ClientDto> clone = new ArrayList<ClientDto>(this.listClients);
			for(ClientDto c:clone) {
				if(client.getName().equals(c.getName())) {
					this.listClients.remove(c);
				}
			}
		}
		
		status.put("data", this.listClients);
		return status;
	}
	
	private Boolean checkIfExists(String name, List<ClientDto> listClients) {
		for(ClientDto client:listClients) {
			if(client.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
