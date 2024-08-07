 package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService {
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	} 

	@Override
	public List<CarreraDTO> mostrarCarreras() {
		return carreraMapDTO.convertirListaCarrerasListaCarrerasDTO(carreraRepository.findByEstado(true));
	}


	@Override
	public void borrarCarrera(String codigo) {
	
		List<Carrera> carreras = carreraRepository.findAll();
		for (int i = 0; i < carreras.size(); i++) {
		      Carrera carrera = carreras.get(i);
		      if (carrera.getCodigo().equals(codigo)) {
		        carrera.setEstado(false);
		        carreraRepository.save(carrera);
		        break;
		      }
		 }
	}

	@Override
	public void modificarCarrera(Carrera carreraConvertida) {
		
		List<Carrera> carreras = carreraRepository.findAll();
		for (int i = 0; i < carreras.size(); i++) {
	        Carrera c = carreras.get(i);
	        if (c.getCodigo().equals(carreraConvertida.getCodigo())) {
	        	carreraConvertida.setEstado(true);
	            carreras.set(i, carreraConvertida);
	            carreraRepository.save(carreraConvertida);
	            break;
	        }
	    }
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		for (Carrera c: carreras) {
			if(c.getCodigo().equals(codigo))  {
				return c;
			}	
		}	
		return null;
 }
}