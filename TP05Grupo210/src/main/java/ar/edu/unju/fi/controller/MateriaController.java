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

import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class MateriaController {
	
	@Autowired
	Materia nuevaMateria;
		
	@Autowired
	MateriaService materiaService;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;
	
	@Autowired
	DocenteService docenteService;
	
	@Autowired
	DocenteMapDTO docenteMapDTO;
	
	@Autowired
	CarreraService carreraService;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	List<String> codigos = new ArrayList<>();
		
	List<String> legajos = new ArrayList<>();
	
	Materia materiaSinModificar;
	
	
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateria);
		modelView.addObject("band", false);
		modelView.addObject("docentes", docentesFiltrados());
		modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
		
		return modelView;
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@Valid @ModelAttribute("nuevaMateria")  Materia materia, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView();
		
		if(result.hasErrors() || codigos.contains(materia.getCodigo())) {
			
			if (codigos.contains(materia.getCodigo())) {
	            result.rejectValue("codigo", "error.materia", "El código ya existe. Por favor, elija otro.");
	        }
			
			modelView.setViewName("formMateria");
			modelView.addObject("docentes", docentesFiltrados());
			modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
		}
		else {
			legajos.add(materia.getDocente().getLegajo());
			codigos.add(materia.getCodigo());
			materiaService.guardarMateria(materia);
			modelView = mostrarMaterias();
		}
		
		return modelView;
		
	}
	
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView BorrarMateria(@PathVariable(name="codigo") String codigo) {
		materiaService.borrarMateria(codigo);
		codigos.remove(codigo);
		legajos.remove(materiaService.buscarMateria(codigo).getDocente().getLegajo());
		return mostrarMaterias();		
	}
	
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView formModificarMateria(@PathVariable(name="codigo") String codigo) {
		Materia materiaModificada =  materiaService.buscarMateria(codigo);
		materiaSinModificar = materiaModificada;
		
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", materiaModificada);
		modelView.addObject("band", true);
		modelView.addObject("docentes", docentesFiltrados());
		modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
		
		return modelView;
	}

	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materia, BindingResult result) {
		ModelAndView modelView = new ModelAndView();
				
		if( result.hasErrors() ) {
			modelView.setViewName("formMateria");
			modelView.addObject("docentes", docentesFiltrados());
			modelView.addObject("carreras", carreraMapDTO.convertirListaCarrerasDTOListaCarreras(carreraService.mostrarCarreras()));
		}
		else {
			materiaService.modificarMateria(materia);
			if( ! materia.getDocente().getLegajo().equals(materiaSinModificar.getDocente().getLegajo()) ) {
				legajos.remove(materiaSinModificar.getDocente().getLegajo());
				legajos.add(materia.getDocente().getLegajo());
			}
			modelView = mostrarMaterias();
		}
		
		return modelView;
	}

	
	@GetMapping("/inscripcionMateria")
	public ModelAndView inscripcionMateria(){
		ModelAndView modelView = new ModelAndView("inscripcionMateria");
		modelView.addObject("nuevaMateria", nuevaMateria);
		modelView.addObject("materias", materiaMapDTO.convertirListaMateriasDTOListaMaterias(materiaService.mostrarMaterias()) );
		modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()) );
		
		return modelView;
	}
	
	@PostMapping("/guardarInscripcion")
	public ModelAndView guardarInscripcion(@ModelAttribute("nuevaMateria") Materia materia) {
		Materia materiaModificada = materiaService.buscarMateria(materia.getCodigo());
		materiaModificada.setAlumnos(materia.getAlumnos());
		materiaService.modificarMateria(materiaModificada);
		
		return mostrarMaterias();
	}
	
	@GetMapping("/listaDeMaterias")
	public ModelAndView mostrarMaterias() {
				
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("ListadoMaterias", materiaService.mostrarMaterias());
		
		return modelView;
	}
	
	public List<Docente> docentesFiltrados(){
		List<Docente> docentesFiltrados = docenteMapDTO.convertirListaDocentesDTOEnListaDocentes(docenteService.mostrarDocentes()).stream() 
			.filter(docente -> !legajos.contains(docente.getLegajo()))
			.collect(Collectors.toList());
		
		return docentesFiltrados;
	}
	
}