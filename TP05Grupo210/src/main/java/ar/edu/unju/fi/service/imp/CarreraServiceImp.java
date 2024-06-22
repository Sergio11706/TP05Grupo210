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
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Override
	public void guardarCarrera(CarreraDTO carreraDTO) {
		carreraDTO.setEstadoDTO(true);
		carreraRepository.save(carreraMapDTO.convertirCarreraDTOaCarrera(carreraDTO));
	} 

	@Override
	public List<Carrera> mostrarCarreras() {
		
		return carreraRepository.findByEstado(true);
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
	public void modificarCarrera(CarreraDTO carreraDTO) {
		Carrera carreraConvertida = carreraMapDTO.convertirCarreraDTOaCarrera(carreraDTO);
		List<Carrera> carreras = carreraRepository.findAll();
		for (int i = 0; i < carreras.size(); i++) {
	        Carrera c = carreras.get(i);
	        if (c.getCodigo().equals(carreraConvertida.getCodigo())) {
	            carreras.set(i, carreraConvertida);
	            carreraConvertida.setEstado(true);
	            carreraRepository.save(carreraConvertida);
	            break;
	        }
	    }
	}

	@Override
	public CarreraDTO buscarCarrera(String codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		for (Carrera c: carreras) {
			if(c.getCodigo().equals(codigo))  {
				return carreraMapDTO.convertirCarreraAcarreraDTO(c);
			}	
		}	
		return null;
 }
}