package edu.ingreso.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.ingreso.model.Ingreso;
import edu.ingreso.repository.IngresoRepository;

@Controller
public class IngresoController {
	
	@Autowired
	private IngresoRepository ingresoRepository;
	
	@GetMapping("/listaIngresos")
		public String list(Map<String, Object> model) {
			List<Ingreso> ingreso =ingresoRepository.findAll();
			model.put("ingreso", ingreso);
			return "listIngreso";
	}
	
	@GetMapping("/addIngreso")
	public String initCreationForm(Model model)
	{
		model.addAttribute("ingreso", new Ingreso());
		return "ingresoAddForm";
	}
	
	@PostMapping("/addIngreso")
	public String submitForm(@Valid Ingreso ingreso, BindingResult bindingResult)
	{
		double UIT = 4050.0;
		if(bindingResult.hasFieldErrors()) {
			return "ingresoAddForm";
		}
		if(ingreso.getIngresoBruto() - (UIT*7) >= 0) {
			ingreso.setRentaNeta(ingreso.getIngresoBruto() - (UIT*7));
			ingreso.setImpuesto(ingreso.getRentaNeta()*0.08);
		}else
		{
			ingreso.setRentaNeta(0);
			ingreso.setImpuesto(0);
		}
		ingresoRepository.save(ingreso);
		return "resultForm";
	}
}
