package controller;


import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import businessRules.ClientBusiness;
import dto.ClientDto;

@Controller
@RequestMapping("/cliente")
public class ClientController {
	@Autowired
	private ClientBusiness clientBusiness;
	
	@RequestMapping("")
	public String mainRoute(ModelMap model) {
		model.addAttribute("clientes", this.clientBusiness.listar());
		return "indexClients";
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public String salvar(ClientDto client, ModelMap model){
		Map<String, Object> status = clientBusiness.salvar(client);
		if(status.get("type").equals("Já existe")) {
			model.addAttribute("error", status.get("type"));
			model.addAttribute("clienteEnviado", client);
		}
		
		model.addAttribute("clientes", status.get("data"));
		return "indexClients";
	}
	
	@RequestMapping(value="/deletar", method=RequestMethod.POST)
	public String deletar(ClientDto client, ModelMap model){
		Map<String, Object> status = clientBusiness.deletar(client);
		if(status.get("type").equals("Não existe tal cliente")) {
			model.addAttribute("error", status.get("type"));
			model.addAttribute("clienteEnviado", client);
		}
		
		model.addAttribute("clientes", status.get("data"));
		return "indexClients";
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public String alterar(String name, String date, Integer position, ModelMap model){
		
		
		Map<String, Object> status = clientBusiness.deletar(client);
		if(status.get("type").equals("Não existe tal cliente")) {
			model.addAttribute("error", status.get("type"));
			model.addAttribute("clienteEnviado", client);
		}
		
		model.addAttribute("clientes", status.get("data"));
		return "indexClients";
	}
}
