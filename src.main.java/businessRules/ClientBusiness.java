package businessRules;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ClientDto;
import persistence.ClientDao;

@Service
public class ClientBusiness {
	@Autowired
	private ClientDao clientDao;
	
	public Map<String, Object> salvar(ClientDto client){
		return this.clientDao.salvar(client);
	}
	
	public List<ClientDto> listar(){
		return this.clientDao.listar();
	}
	
	public Map<String, Object> deletar(ClientDto client){
		return this.clientDao.deletar(client);
	}
}
