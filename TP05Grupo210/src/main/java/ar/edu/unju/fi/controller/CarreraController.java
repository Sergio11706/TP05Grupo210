package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class CarreraController {
	
	@Autowired
	CarreraDTO nuevaCarreraDTO;
	
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioCarrera")
	
	public ModelAndView getFormCarrera() {
		
		ModelAndView modelView = new ModelAndView("formCarrera");
		
		modelView.addObject("nuevaCarrera", nuevaCarreraDTO);
		
		modelView.addObject("band", false);
		
		return modelView;
	}
	
	
	@PostMapping("/guardarCarrera")
	
	public ModelAndView guardarCarrera(@ModelAttribute("nuevaCarrera")  CarreraDTO carreraDTO) {
		
		carreraService.guardarCarrera(carreraDTO);
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		modelView.addObject("ListadoCarreras", carreraService.mostrarCarreras());
		
		return modelView;
	}
	
	@GetMapping("/borrarCarrera/{codigo}")
	
	public ModelAndView BorrarCarrera(@PathVariable(name="codigo") String codigo) {
		
		carreraService.borrarCarrera(codigo);
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		modelView.addObject("ListadoCarreras", carreraService.mostrarCarreras());	
		
		return modelView;		
		}
	

	@GetMapping("/modificarCarrera/{codigo}")
	
	public ModelAndView formModificarCarrera(@PathVariable("codigo") String codigo) {
		
		CarreraDTO carreraModificada = carreraService.buscarCarrera(codigo);

		ModelAndView modelView = new ModelAndView("formCarrera");
		
		modelView.addObject("nuevaCarrera", carreraModificada);
		
		modelView.addObject("band", true);
		
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView modificarCarrera(@ModelAttribute("carreraModificada") CarreraDTO carreraDTO) {

		carreraService.modificarCarrera(carreraDTO);
		
		return mostrarCarreras();
	}
	
	@GetMapping("/listaDeCarreras")
	public ModelAndView mostrarCarreras() {
		
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		
		modelView.addObject("ListadoCarreras", carreraService.mostrarCarreras());

		return modelView;
	}
}
