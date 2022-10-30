package com.app.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.app.web.models.dao.IMovimientoDao;
import com.app.web.models.entity.Movimiento;

@Controller
@SessionAttributes("movimiento")
public class movimientoController {

	@Autowired
	private IMovimientoDao movimientoDao;

	@RequestMapping(value = "/listarmovimento", method = RequestMethod.GET)
	public String listarmovimiento (Model model) {
		model.addAttribute("titulo", "Listado de movimientos");
		model.addAttribute("movimiento", movimientoDao.findALL());
		return "listarmovimento";
	}
	
	

	@RequestMapping(value="/form_movimiento")
	public String crearMovimiento(Map<String, Object> model) {
		
		Movimiento movimiento =new Movimiento();
		model.put("movimiento", movimiento);
		model.put("titulo","Formulario de movimiento");
		return "form_movimiento";
	}
	
		
		@RequestMapping(value="/form_movimiento", method=RequestMethod.POST)
		public String guardarMovimiento (Movimiento movimiento,BindingResult result, Model model, SessionStatus status) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Movimiento");
				return "form_movimiento";
			
			}
			movimientoDao.save(movimiento);
			status.setComplete();
			return "redirect:listarmovimento";
		}
		
		
		@RequestMapping(value="/form_movimiento/{id}")
		public String editarMovimiento(@PathVariable(value="id") Long id ,Map<String, Object> model) {
			
			Movimiento movimiento = null;
			
			if(id>0) {
				movimiento = movimientoDao.findOne(id);
			}else {
				return "redirect:/listarmovimento";
			}
			model.put("movimiento", movimiento);
			model.put("titulo", "Editar movimiento");
			return "form_movimiento";
		}
		
		
	
}
