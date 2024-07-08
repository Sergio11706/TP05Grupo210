package ar.edu.unju.fi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;
import jakarta.validation.Valid;

@Controller
public class DocenteController {
	@Autowired
	Docente nuevoDocente;
	
	@Autowired
	DocenteService docenteService;
	
	List<String> legajos = new ArrayList<>();
	
	@GetMapping("/listaDeDocentes")
	public ModelAndView mostrarDocentes() {
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("ListadoDocentes", docenteService.mostrarDocentes());
	
		return modelView;
	}
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", nuevoDocente);
		modelView.addObject("band", false);
		
		return modelView;
	}
	
	
	@PostMapping("/guardarDocente")
	public ModelAndView guardarDocente(@Valid @ModelAttribute("nuevoDocente")  Docente docente, BindingResult result) {
		
		  ModelAndView modelView = new ModelAndView();

		  if (result.hasErrors() || legajos.contains(docente.getLegajo())) {
			  
			  if (legajos.contains(docente.getLegajo())) {
				result.rejectValue("legajo", "error.docente","El legajo ya existe. Por favor, proporcione otro.");
			}
			  modelView.setViewName("formDocente");
		  }
		  else {
			  //guardar docente
			  legajos.add(docente.getLegajo());
			  docenteService.guardarDocente(docente);
			  
			  //mostrar docente
			  modelView = mostrarDocentes();
		  }

		return modelView;
	}
	
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView BorrarDocente(@PathVariable(name="legajo") String legajo) {
		//borrar docente
		docenteService.borrarDocente(legajo);
		legajos.remove(legajo);
		
		//mostrar docente
		return mostrarDocentes();
	}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView formModificarDocente(@PathVariable("legajo") String legajo) {
		Docente docenteModificado =  docenteService.buscarDocente(legajo);
		
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", docenteModificado);
		modelView.addObject("band", true);

		return modelView;
	}
	
	@PostMapping("/modificarDocente")
	public ModelAndView modificarDocente(@Valid @ModelAttribute("nuevoDocente") Docente docente, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView();
		
		if (result.hasErrors()) {
			  modelView.setViewName("formDocente");
		}
		else {
			docenteService.modificarDocente(docente);
			modelView = mostrarDocentes();
		}
		
		return modelView;
	}
}
