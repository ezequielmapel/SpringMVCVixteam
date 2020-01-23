package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.PessoaDto;
import dto.SexoEnum;

@Controller
@Scope("request")
@RequestMapping("/helloWorld")
public class HelloWorldController {
	private HttpSession session;
	List<String> pessoas = new ArrayList<String>();
	@RequestMapping(value="/wel", method=RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String getForm(@RequestParam(value="nome") String nome,
			@RequestParam(value="sobrenome") String sobrenome,
			ModelMap model) {
		model.addAttribute("nomeCompleto", nome+""+sobrenome);
		return "form";
	}
	
	@RequestMapping(value="/pessoaEnum", method=RequestMethod.GET)
	public String converter(PessoaDto pessoa, ModelMap model) {
		System.out.println(pessoa.getNome());
		System.out.println(pessoa.getSexoEnum());
		return "pessoaEnum";
	}
	
	@RequestMapping(value="/pessoa", method=RequestMethod.GET)
	public String listPessoas(ModelMap model, HttpSession session) {

		
		model.addAttribute("pessoas", session.getAttribute("pessoas"));
		
		return "salvar";
	}
	
	@RequestMapping(value="/salvarPessoa", method=RequestMethod.POST)
	public String salvarPessoa(String nome, ModelMap model, HttpSession session){
		String pessoa = nome;
		pessoas = (List<String>) session.getAttribute("pessoas");
		pessoas.add(pessoa);
		for(String p: pessoas) {
			System.out.println(p);
		}
		session.setAttribute("pessoas", pessoas);
		return "salvar";
	}
}
