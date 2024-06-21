package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class MateriaController {
	
	@Autowired
	MateriaDTO nuevaMateriaDTO;
	
	@Autowired
	MateriaService materiaService;
	
	@GetMapping("/listaDeMaterias")
	public ModelAndView mostrarMaterias() {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("ListadoMaterias", materiaService.mostrarMaterias());
	
		return modelView;
	}
	
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateriaDTO);
		modelView.addObject("band", false);
		
		return modelView;
	}
	
	
	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@ModelAttribute("nuevaMateria")  MateriaDTO MateriaDTO) {
		materiaService.guardarMateria(MateriaDTO);
		return mostrarMaterias();
	}
	
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView BorrarMateria(@PathVariable(name="codigo") String codigo) {
		materiaService.borrarMateria(codigo);
		return mostrarMaterias();		
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView formModificarMateria(@PathVariable(name="codigo") String codigo) {
		MateriaDTO materiaModificada =  materiaService.buscarMateria(codigo);
		
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", materiaModificada);
		modelView.addObject("band", true);

		return modelView;
	}

	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") MateriaDTO materiaDTO) {
		materiaService.modificarMateria(materiaDTO);
		return mostrarMaterias();
	}

}