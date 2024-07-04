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

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {
	
	@Autowired
	Carrera nuevaCarrera;
	
	@Autowired
	CarreraService carreraService;
	
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	AlumnoService alumnoService;
	
	List<String> codigos = new ArrayList<>();
	
	@GetMapping("/formularioCarrera")
	
	public ModelAndView getFormCarrera() {
		
		ModelAndView modelView = new ModelAndView("formCarrera");
		
		modelView.addObject("nuevaCarrera", nuevaCarrera);
		
		modelView.addObject("materias", materiaService.mostrarMaterias());	
		
		modelView.addObject("alumnos", alumnoService.mostrarAlumnos());	
		
		modelView.addObject("band", false);
		
		return modelView;
	}
	
	
	@PostMapping("/guardarCarrera")
	
	public ModelAndView guardarCarrera(@Valid @ModelAttribute("nuevaCarrera")  Carrera carreraAguardar, BindingResult result) {
		
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		if(result.hasErrors() || codigos.contains(carreraAguardar.getCodigo()) ) {
			
			modelView.setViewName("formCarrera");
			
			modelView.addObject("materias", materiaService.mostrarMaterias());
			
			modelView.addObject("alumnos", alumnoService.mostrarAlumnos());	
		}
		else {
			codigos.add(carreraAguardar.getCodigo());
			
			carreraService.guardarCarrera(carreraAguardar);	
			
			modelView = mostrarCarreras();
		}
		
		return modelView;
	}
	
	 @GetMapping("/borrarCarrera/{codigo}")
	
	public ModelAndView BorrarCarrera(@PathVariable(name="codigo") String codigo) {
		
		carreraService.borrarCarrera(codigo);
		
		codigos.remove(codigo);
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		modelView.addObject("ListadoCarreras", carreraService.mostrarCarreras());	
		
		return modelView;		
		}
	
	 
	 
	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView formModificarCarrera(@PathVariable("codigo") String codigo) {
		
		Carrera carreraModificada = carreraService.buscarCarrera(codigo);

		ModelAndView modelView = new ModelAndView("formCarrera");
		
		modelView.addObject("nuevaCarrera", carreraModificada);
		modelView.addObject("materias", materiaService.mostrarMaterias());	
		modelView.addObject("alumnos", alumnoService.mostrarAlumnos());	
		modelView.addObject("band", true);
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView modificarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carrera, BindingResult result) {
	
		ModelAndView modelView = new ModelAndView();
		
		if(result.hasErrors()) {
	
			modelView.setViewName("formCarrera");
			modelView.addObject("materias", materiaService.mostrarMaterias());	
			modelView.addObject("alumnos", alumnoService.mostrarAlumnos());	
		}
		else {
			carreraService.modificarCarrera(carrera);
			
			modelView = mostrarCarreras();
		}
		
		return modelView;
	}
	
	@GetMapping("/listaDeCarreras")
	public ModelAndView mostrarCarreras() {
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		modelView.addObject("ListadoCarreras", carreraService.mostrarCarreras());

		return modelView;
}

	
	@GetMapping("/consultarAlumnosCarrera/{codigo}")
	public ModelAndView consultarAlumnosCarrera(@PathVariable(name="codigo") String codigo) {
	    Carrera carrera = carreraService.buscarCarrera(codigo);
	    List<Alumno> alumnos = carrera.getAlumnos();

	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnos);

	    return modelView;
	}
}
