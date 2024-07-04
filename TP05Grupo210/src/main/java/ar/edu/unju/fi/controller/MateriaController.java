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

import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
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
	AlumnoService alumnoService;
	
	@Autowired
	DocenteService docenteService;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	DocenteMapDTO docenteMapDTO;
	
	List<String> codigos = new ArrayList<>();
		
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateria);
		modelView.addObject("band", false);
		modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()));
		modelView.addObject("docentes", docenteMapDTO.convertirListaDocentesDTOEnListaDocentes(docenteService.mostrarDocentes()));
		
		return modelView;
	}
	
	
	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@Valid @ModelAttribute("nuevaMateria")  Materia materia, BindingResult result) {
		
		ModelAndView modelView = new ModelAndView();
		
		if(result.hasErrors() || codigos.contains(materia.getCodigo())) {
			modelView.setViewName("formMateria");
			modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()));
			modelView.addObject("docentes", docenteMapDTO.convertirListaDocentesDTOEnListaDocentes(docenteService.mostrarDocentes()));
		}
		else {
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
		return mostrarMaterias();		
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView formModificarMateria(@PathVariable(name="codigo") String codigo) {
		Materia materiaModificada =  materiaService.buscarMateria(codigo);
		
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", materiaModificada);
		modelView.addObject("band", true);
		modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()));
		modelView.addObject("docentes", docenteMapDTO.convertirListaDocentesDTOEnListaDocentes(docenteService.mostrarDocentes()));
		
		return modelView;
	}

	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materia, BindingResult result) {
		ModelAndView modelView = new ModelAndView();
		
		if(result.hasErrors()) {
			modelView.setViewName("formMateria");
			modelView.addObject("alumnos", alumnoMapDTO.convertirListaAlumnosDTOListaAlumnos(alumnoService.mostrarAlumnos()));
			modelView.addObject("docentes", docenteMapDTO.convertirListaDocentesDTOEnListaDocentes(docenteService.mostrarDocentes()));
		}
		else {
			materiaService.modificarMateria(materia);
			modelView = mostrarMaterias();
		}
		
		return modelView;
	}

	@GetMapping("/listaDeMaterias")
	public ModelAndView mostrarMaterias() {
				
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("ListadoMaterias", materiaService.mostrarMaterias());
		
		return modelView;
	}
	
}