package pagrindinis.projektas;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import pagrindinis.projektas.User;
import pagrindinis.projektas.UserRepository;

@Controller
public class ProjektasController
{
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
	private PrekeRepository preke_repository;
	
	@RequestMapping(path="/pagrindinis") 
	public String home()
	{
		return "pagrindinis";
	}
	
	@RequestMapping("/")
	public String home_2()
	{
		return "pagrindinis";
	}
	
	@RequestMapping("/login")
	public String login(Model model)
	{		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model)
	{
		return "redirect:login";
	}
	
	@RequestMapping(path="/register")
	public String registration(Model model)
	{
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@RequestMapping(path="/process_register")
	public String processRegister(User user)
	{
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    user_repository.save(user);
	     
	    return "login";
	}
	
	@RequestMapping(path="/admin")
	public String admin()
	{
		return "admin";
	}
	
	@RequestMapping(path="/lentele", method={ RequestMethod.GET, RequestMethod.POST })
    public String miestai(
    		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
			, @RequestParam(name="pav", required=false, defaultValue="") String pav
			, @RequestParam(name="kaina", required=false, defaultValue="") String kaina	
			, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
			, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
			, Model model) {
		
		Preke preke = new Preke();
		if(prideti.equals("prideti"))
		{
			Optional <Preke> found = preke_repository.findById(id);
			
			if(found.isPresent())
			{
				preke = found.get();
				preke.setId(id);
			}
	
			preke.setPavadinimas(pav);
			preke.setKaina(Double.parseDouble(kaina));
			preke_repository.save(preke);
		}
		
    	model.addAttribute("prekes", preke_repository.findAll() );
    	//model.addAttribute("lst_menu", Menu.values() ); 
   
		return "lentele";	
	}
	
	@RequestMapping(path="/preke")	
	public @ResponseBody Preke prekesDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		Preke preke = new Preke();
		
		if (id > 0)
		{
			Optional <Preke> found = preke_repository.findById( id );
		
			if (found.isPresent())
			{
			   preke = found.get();
			   preke.setId ( id );
			}
		}
		return preke;
	}

	@RequestMapping(path="/salinti-preke")
	public String salintiTiekeja (
			@RequestParam Integer id_prekes,
			@RequestParam(name="", required=false, defaultValue="") String salinti
			)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Preke> found = preke_repository.findById( id_prekes );
			if (found.isPresent())
			{
				   Preke n = found.get();
				   preke_repository.deleteById(id_prekes);
			}
		}
		return "redirect:lentele";
	}
}