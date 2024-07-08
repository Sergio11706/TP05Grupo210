package ar.edu.unju.fi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.service.CarreraService;

import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	@Autowired
	Alumno nuevoAlumno;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;
	
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	CarreraService carreraService;
	
	List<String> dnis = new ArrayList<>();
	
	@GetMapping("/listaDeAlumnos")
	public ModelAndView mostrarAlumnos() {
	    ModelAndView modelView = new ModelAndView("listaDeAlumnos");
	    modelView.addObject("ListadoAlumnos", alumnoService.mostrarAlumnos());
	    modelView.addObject("materias", materiaMapDTO.convertirListaMateriasDTOListaMaterias(materiaService.mostrarMaterias()));
	    modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
	    modelView.addObject("filtrarMateria", new Materia());
	    modelView.addObject("filtrarCarrera", new Carrera());
	    
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
		
		if (result.hasErrors() || dnis.contains(alumno.getDni())) {
			if (dnis.contains(alumno.getDni())) {
	            result.rejectValue("dni", "error.alumno", "El dni ya existe. Por favor, elija otro");
	        }

			modelView.setViewName("formAlumno");
		}
		else {
			dnis.add(alumno.getDni());
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
	
	@GetMapping("/inscripcionMateria")
	public ModelAndView inscripcionMateria() {
		ModelAndView modelView = new ModelAndView("inscripcionMateria");
		modelView.addObject("nuevoAlumno", nuevoAlumno);
		modelView.addObject("materias", materiaMapDTO.convertirListaMateriasDTOListaMaterias(materiaService.mostrarMaterias()));
		modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()));
		modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
		
		return modelView;
	}
	
	@PostMapping("/guardarInscripcion")
	public ModelAndView guardarInscripcion(@ModelAttribute ("nuevoAlumno") Alumno alumno) {
		Alumno alumnoModificado = alumnoService.buscarAlumno(alumno.getDni());
		alumnoModificado.setCarrera(alumno.getCarrera());
		alumnoModificado.setMaterias(alumno.getMaterias());
		alumnoService.modificarAlumno(alumnoModificado);
		
		return mostrarAlumnos();
	}
	
	@PostMapping("/filtrar")
	public ModelAndView filtrar(@ModelAttribute("filtrarCarrera") Carrera filtroCarrera, @ModelAttribute("filtrarMateria") Carrera filtroMateria) {
		if(filtroMateria.getCodigo()==null && filtroCarrera.getCodigo()==null) {
			return mostrarAlumnos();
		}
		else {
			List<Alumno> alumnosFiltrados = alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()).stream()
					.filter(alumno -> alumno.getMaterias().stream().anyMatch( materia -> materia.getCodigo().equals(filtroMateria.getCodigo()) ) 
							|| alumno.getCarrera().getCodigo().equals(filtroCarrera.getCodigo()))
					.collect(Collectors.toList());
			
			ModelAndView modelView = new ModelAndView("listaDeAlumnos");
			modelView.addObject("ListadoAlumnos", alumnoMapDTO.convertirListaAlumnosListaAlumnosDTO(alumnosFiltrados));
			modelView.addObject("materias", materiaMapDTO.convertirListaMateriasDTOListaMaterias(materiaService.mostrarMaterias()));
			modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
			modelView.addObject("filtrarMateria", new Materia());
			modelView.addObject("filtrarCarrera", new Carrera());
			
			return modelView;
		}
	}
}