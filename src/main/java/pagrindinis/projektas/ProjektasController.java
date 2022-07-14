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
	private UgdymoIstaigaRepository ugdymo_istaiga_repository;
	
	@Autowired
	private PrasymasRepository prasymo_repository;
	
	//@Autowired
	//private PrekeRepository preke_repository;
	
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
	
/*	@RequestMapping(path="/lentele", method={ RequestMethod.GET, RequestMethod.POST })
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
*/	//-------------------------------------------------------------------------------------------
	@RequestMapping(path="/admin_prideti", method={ RequestMethod.GET, RequestMethod.POST })
	public String admin_prideti(
		@RequestParam(name="id", required=false, defaultValue="0") Integer id 
		, @RequestParam(name="pav", required=false, defaultValue="") String pav
		, @RequestParam(name="kodas", required=false, defaultValue="") String kodas
		, @RequestParam(name="adresas", required=false, defaultValue="") String adresas
		, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
		, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
		, Model model)
	{
		UgdymoIstaiga ugdymo_istaiga = new UgdymoIstaiga();
		if(prideti.equals("prideti"))
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById(id);
			
			if(found.isPresent())
			{
				ugdymo_istaiga = found.get();
				ugdymo_istaiga.setId(id);
			}
	
			ugdymo_istaiga.setPavadinimas(pav);
			ugdymo_istaiga.setKodas(kodas);
			ugdymo_istaiga.setAdresas(adresas);
			
			ugdymo_istaiga_repository.save(ugdymo_istaiga);
		}
		model.addAttribute("istaigos", ugdymo_istaiga_repository.findAll());
			
		return "admin_prideti";
	}
	
	@RequestMapping(path="/ugdymo_istaiga_redaguoti")	
	public @ResponseBody UgdymoIstaiga ugdymoIstaigosDuom(@RequestParam(name="id", required=true, defaultValue="0") Integer id ) throws IOException {
		
		UgdymoIstaiga ugdymo_istaiga = new UgdymoIstaiga();
		
		if (id > 0)
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById( id );
		
			if (found.isPresent())
			{
				ugdymo_istaiga = found.get();
				ugdymo_istaiga.setId ( id );
			}
		}
		return ugdymo_istaiga;
	}
	
	@RequestMapping(path="/salinti_istaiga")
	public String salintiIstaiga (
		@RequestParam Integer id_istaigos,
		@RequestParam(name="", required=false, defaultValue="") String salinti)
	{
		if(salinti.equals("salinti"))
		{
			Optional <UgdymoIstaiga> found = ugdymo_istaiga_repository.findById( id_istaigos );
			if (found.isPresent())
			{
				UgdymoIstaiga n = found.get();
				ugdymo_istaiga_repository.deleteById(id_istaigos);
			}
		}
		return "redirect:admin_prideti";
	}
	
	@RequestMapping(path="/admin_patvirtinti", method={ RequestMethod.GET, RequestMethod.POST })
	public String admin_patvirtinti(
			@RequestParam(name="id_prasymo", required=false, defaultValue="0") Integer id_prasymo,
			Model model)
	{
		Optional<Prasymas> found = prasymo_repository.findById(id_prasymo);
		
		if(found.isPresent())
		{
			Prasymas p = found.get();
			p.setPatvirtintas(1);
			prasymo_repository.save(p);
		}
		
		model.addAttribute("prasymai", prasymo_repository.findAll());
		
		return "admin_patvirtinti";
	}
	
	@RequestMapping(path="/prasymai", method={ RequestMethod.GET, RequestMethod.POST })
	public String prasymai(
			@RequestParam(name="id", required=false, defaultValue="0") Integer id 
			, @RequestParam(name="asmenskodas", required=false, defaultValue="") String asmenskodas
			, @RequestParam(name="vardas", required=false, defaultValue="") String vardas
			, @RequestParam(name="pavarde", required=false, defaultValue="") String pavarde
			, @RequestParam(name="klase", required=false, defaultValue="") String klase
			, @RequestParam(name="pavadinimas", required=false, defaultValue="") String pavadinimas
			, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
			, @RequestParam(name="prideti_name", required=false, defaultValue="neprideti") String prideti
			, Model model)
	{
		Prasymas prasymas = new Prasymas();
		if(prideti.equals("prideti"))
		{
			Optional <Prasymas> found = prasymo_repository.findById(id);
			
			if(found.isPresent())
			{
				prasymas = found.get();
				prasymas.setId(id);
			}
	
			prasymas.setAsmenskodas(asmenskodas);
			prasymas.setVardas(vardas);
			prasymas.setPavarde(pavarde);
			prasymas.setKlase(klase);
			prasymas.setPavadinimas(pavadinimas);
			prasymas.setPatvirtintas(0);
			
			prasymo_repository.save(prasymas);
		}
		model.addAttribute("prasymai", prasymo_repository.findAll());
		//Prasymuose saugoti user_id ir kaip rodyti atrinktus pagal tai?
		return "prasymai";
	}
	
	@RequestMapping(path="/salinti_prasyma")
	public String salintiPrasyma (
		@RequestParam Integer id_prasymo,
		@RequestParam(name="", required=false, defaultValue="") String salinti)
	{
		if(salinti.equals("salinti"))
		{
			Optional <Prasymas> found = prasymo_repository.findById( id_prasymo );
			if (found.isPresent())
			{
				Prasymas n = found.get();
				prasymo_repository.deleteById(id_prasymo);
			}
		}
		return "redirect:prasymai";
	}
}