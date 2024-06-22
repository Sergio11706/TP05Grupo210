package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
public class AlumnoController {
	@Autowired
	Alumno alumno;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/listaDeAlumnos")
	public ModelAndView mostrarLista() {
	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());
	    
	    return modelView;
	}
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", alumno);
	
		return modelView;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView guardarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumno) {
		
		alumnoService.guardarAlumno(alumno);
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");

		modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());

		return modelView;
	}
	
	@GetMapping("/eliminarAlumno/{dni}")
	public ModelAndView EliminarAlumno(@PathVariable (name="dni") String dni) {
		
		alumnoService.eliminarAlumno(dni);
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");

		modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());

		return modelView;
	}
	
	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView formModificarAlumno(@PathVariable("dni") String dni) {
		Alumno alumnoModificado = alumnoService.buscarAlumno(dni);

		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", alumnoModificado);
		modelView.addObject("band", true);

		return modelView;
	}
	
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@ModelAttribute("alumnoModificado") Alumno alumno) {
		
		alumnoService.modificarAlumno(alumno);
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");

		modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());

		return modelView;
	}
	
	@GetMapping("/listaDeAlumnos")
	public ModelAndView mostrarAlumnos() {

		ModelAndView modelView = new ModelAndView("listaDeAlumnos");

		modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());

		return modelView;
	}
}