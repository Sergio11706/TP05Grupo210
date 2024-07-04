package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	@Autowired
	Alumno nuevoAlumno;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/listaDeAlumnos")
	public ModelAndView mostrarAlumnos() {
	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());
	    
	    return modelView;
	}
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", nuevoAlumno);
		modelView.addObject("band", false);
		return modelView;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumno, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView(); 
		
		if (result.hasErrors()) {
			modelView.setViewName("formAlumno");
		}
		else {
			alumnoService.guardarAlumno(alumno);
			modelView = mostrarAlumnos();
		}
		
		return modelView;	
	}
	
	@GetMapping("/eliminarAlumno/{dni}")
	public ModelAndView EliminarAlumno(@PathVariable (name="dni") String dni) {
		
		alumnoService.eliminarAlumno(dni);
		return mostrarAlumnos();
	}
	
	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView formModificarAlumno(@PathVariable(name="dni") String dni) {
		Alumno alumnoModificado = alumnoService.buscarAlumno(dni);

		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno",alumnoModificado);
		modelView.addObject("band", true);

		return modelView;
	}
	
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("alumnoModificado") Alumno alumno, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView();
		
		if (result.hasErrors()) {
			modelView.setViewName("formAlumno");
		}
		else {
			alumnoService.modificarAlumno(alumno);
			
			modelView = mostrarAlumnos();
		}
		
		return modelView;
	}
	
	/*
	@GetMapping("/filtrarAlumnos")
	public ModelAndView filtrarAlumnos(@RequestParam("materia") String codigoMateria) {
	    List<Alumno> alumnosFiltrados = alumnoService.filtrarPorMateria(codigoMateria);

	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnosFiltrados);

	    return modelView;
	}
	
	@GetMapping("/consultarAlumno")
	public ModelAndView consultarAlumno(@RequestParam("carrera") String codigoCarrera) {
	    List<Alumno> alumnosConsultados = alumnoService.consultarPorCarrera(codigoCarrera);

	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnosConsultados);

	    return modelView;
	}
	*/
}