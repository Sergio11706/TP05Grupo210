package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.service.DocenteService;


public class DocenteController {
	@Autowired
	DocenteDTO nuevoDocenteDTO;
	
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/listaDeDocentes")
	public ModelAndView mostrarDocentes() {
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("ListadoDocentes", docenteService.mostrarDocentes());
	
		return modelView;
	}
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", nuevoDocenteDTO);
		modelView.addObject("band", false);
		
		return modelView;
	}
	
	
	@PostMapping("/guardarDocente")
	public ModelAndView guardarDocente(@ModelAttribute("nuevoDocente")  DocenteDTO docenteDTO) {
		docenteService.guardarDocente(docenteDTO);
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("ListadoDocentes", docenteService.mostrarDocentes());
		return modelView;
	}
	
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView BorrarDocente(@PathVariable(name="legajo") String legajo) {
		docenteService.borrarDocente(legajo);
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("ListadoDocentes", docenteService.mostrarDocentes());
		return modelView;
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView formModificarDocente(@PathVariable(name="legajo") String legajo) {
		DocenteDTO docenteModificado =  docenteService.buscarDocente(legajo);
		
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", docenteModificado);
		modelView.addObject("band", true);

		return modelView;
	}
	
	@PostMapping("/modificarDocente")
	public ModelAndView modificarDocente(@ModelAttribute("docenteModificado") DocenteDTO docenteDTO) {
		docenteService.modificarDocente(docenteDTO);
		return mostrarDocentes();
	}
}
