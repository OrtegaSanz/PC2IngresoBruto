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
import org.springframework.web.bind.annotation.PathVariable;

import edu.ingreso.model.Ingreso;
import edu.ingreso.repository.IngresoRepository;

@Controller
public class IngresoController {
	
	@Autowired
	private IngresoRepository ingresoRepository;
	
	//Listar
	@GetMapping("/ingreso/lista")
		public String list(Map<String, Object> model) {
			List<Ingreso> ingreso =ingresoRepository.findAll();
			model.put("ingreso", ingreso);
			return "listIngreso";
	}
	
	
	@GetMapping("/ingreso/new")
	public String initCreationForm(Model model)
	{
		model.addAttribute("ingreso", new Ingreso());
		return "ingresoAddForm";
	}
	
	@PostMapping("/ingreso/new")
	public String submitForm(@Valid Ingreso ingreso, BindingResult bindingResult)
	{
		double UIT = 4050.0;
		if(bindingResult.hasFieldErrors()) {
			return "ingresoAddForm";
		}
		if(ingreso.getIngresoBruto() - (UIT*7) >= 0) {
			ingreso.setRentaNeta(ingreso.getIngresoBruto() - (UIT*7));
			ingreso.setImpuesto(ingreso.getRentaNeta()*0.08);
			if(ingreso.getRentaNeta()>=20250) {
				ingreso.setImpuesto(Math.ceil(((UIT*5)*0.08) + (ingreso.getRentaNeta()-(UIT*5))*0.14));
			}
			if(ingreso.getRentaNeta() >= 81000) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (ingreso.getRentaNeta() - UIT*20)*0.17));
			}
			if(ingreso.getRentaNeta() >= 141750) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (UIT*35 - UIT*20) * 0.17 + (ingreso.getRentaNeta() - UIT*35)*0.2));
			}
			if(ingreso.getRentaNeta() >= 182250) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (UIT*35 - UIT*20)*0.17 + (UIT*45-UIT*35)*0.2+ (ingreso.getRentaNeta() - UIT*45)*0.3));
			}
			ingreso.setRentaMensual(Math.round(ingreso.getImpuesto()/12));
		}else
		{
			ingreso.setRentaNeta(0);
			ingreso.setImpuesto(0);
		}
		ingresoRepository.save(ingreso);
		return "resultForm";
	}
	
	
	@GetMapping("/ingreso/{ingresoId}/edit")
	public String edit(@PathVariable("ingresoId") int ingresoId, 
			Model model){
		Ingreso ingreso =ingresoRepository.findById(ingresoId);
		model.addAttribute(ingreso);
		return "editIngreso";
	}
	
	@PostMapping("/ingreso/{ingresoId}/edit")
	public String update(
			@Valid Ingreso ingreso,
			BindingResult bindingResult,
			@PathVariable("ingresoId") int ingresoId){
		if(bindingResult.hasFieldErrors()) {
			return "redirect:/ingreso/{ingresoId}/edit";
		}
		double UIT = 4050.0;
		if(bindingResult.hasFieldErrors()) {
			return "ingresoAddForm";
		}
		if(ingreso.getIngresoBruto() - (UIT*7) >= 0) {
			ingreso.setRentaNeta(ingreso.getIngresoBruto() - (UIT*7));
			ingreso.setImpuesto(ingreso.getRentaNeta()*0.08);
			if(ingreso.getRentaNeta()>=20250) {
				ingreso.setImpuesto(Math.ceil(((UIT*5)*0.08) + (ingreso.getRentaNeta()-(UIT*5))*0.14));
			}
			if(ingreso.getRentaNeta() >= 81000) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (ingreso.getRentaNeta() - UIT*20)*0.17));
			}
			if(ingreso.getRentaNeta() >= 141750) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (UIT*35 - UIT*20) * 0.17 + (ingreso.getRentaNeta() - UIT*35)*0.2));
			}
			if(ingreso.getRentaNeta() >= 182250) {
				ingreso.setImpuesto(Math.ceil(UIT*5*0.08 + (UIT*20 - UIT*5)*0.14 + (UIT*35 - UIT*20)*0.17 + (UIT*45-UIT*35)*0.2+ (ingreso.getRentaNeta() - UIT*45)*0.3));
			}
			ingreso.setRentaMensual(Math.round(ingreso.getImpuesto()/12));
		}else
		{
			ingreso.setRentaNeta(0);
			ingreso.setImpuesto(0);
		}
		ingreso.setId(ingresoId);		
		ingresoRepository.save(ingreso);
		return "redirect:/ingreso/lista";
	}
		@GetMapping("/ingreso/{ingresoId}/delete")
		public String delete(@PathVariable("ingresoId") int ingresoId, 
				Model model){
			Ingreso ingreso =ingresoRepository.findById(ingresoId);
			ingresoRepository.delete(ingreso);
			return "redirect:/ingreso/lista";
		}
}
