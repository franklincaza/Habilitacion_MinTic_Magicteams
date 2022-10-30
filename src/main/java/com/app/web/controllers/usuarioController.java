package com.app.web.controllers;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.app.web.models.dao.IusuarioDao;
import com.app.web.models.entity.Usuario;




@Controller
@SessionAttributes("usuario")
public class usuarioController {
	

	@Autowired
	private IusuarioDao usuarioDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de usuario");
		model.addAttribute("usuario", usuarioDao.findALL());
		return "listar";
	}
		
	
	
	
	
	
	@GetMapping({ "/index","/","/","/home"})
	public String index (Model model) {
		return  "index";
	}
	/*
	@PostMapping ("/usuario")
	public String procesar(Model model,
			@RequestParam (name="user")String user,
			@RequestParam (name="pass")String pass)
			 {
		
		
		model.addAttribute("user",user);
		model.addAttribute("pass",pass);
		
		
		return "usuario";
			 }
	
	@GetMapping("/movimientos")
	public String movimientos (Model model) {
		return  "movimientos";
	}
	
	@GetMapping("/empresas")
	public String empresas (Model model) {
		return  "empresas";
	}
	
	@GetMapping("/empleados")
	public String empleados (Model model) {
		return  "empleados";
	}

	@GetMapping("/usuario")
	public String usuario (Model model) {
		model.addAttribute("titulo","Registros ingresado");
		return  "usuario";
	}
	
	@PostMapping ("/movimientos")
	public String procesar(Model model,
			@RequestParam (name="Monto")String Monto,
			@RequestParam String Concepto,
			@RequestParam String Fecha) {
		
		model.addAttribute("titulo","Registros ingresado");
		model.addAttribute("usuario",Monto);
		model.addAttribute("ingreso",Concepto);
		model.addAttribute("egreso",Fecha);
		
		return "movimientos";
		
	}*/
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		
		Usuario usuario =new Usuario();
		model.put("usuario", usuario);
		model.put("titulo","Formulario de usuario");
		return "form";
	}
	
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar (Usuario usuario,BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			return "form";
		
		}
		usuarioDao.save(usuario);
		status.setComplete();
		return "redirect:listar";
	}
	
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id ,Map<String, Object> model) {
		
		Usuario usuario = null;
		
		if(id>0) {
			usuario = usuarioDao.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Editar usuario");
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar (@PathVariable(value="id") Long id){
		if(id > 0) {
			usuarioDao.delete(id);
		}
		return "redirect:/listar";
	}

	
	
	
}
	

	
		
	
	
		
	

