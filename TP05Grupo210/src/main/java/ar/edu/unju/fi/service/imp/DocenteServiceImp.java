package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService{
	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	DocenteMapDTO docenteMapDTO;
	
	@Override
	public void guardarDocente(DocenteDTO docenteDTO) {
		docenteDTO.setEstadoDTO(true);
		docenteRepository.save(docenteMapDTO.convertirDocenteDTOEnDocente(docenteDTO));
	}

	@Override
	public List<Docente> mostrarDocentes() {
		return docenteRepository.findDocenteByEstado(true);
	}

	@Override
	public void borrarDocente(String legajo) {
		List<Docente> docentes = docenteRepository.findAll();
		for(Docente i : docentes) {
			if(i.getLegajo().equals(legajo)) {
				i.setEstado(false);
				docenteRepository.save(i);
			}
		}
	}

	@Override
	public void modificarDocente(DocenteDTO docenteDTO) {
		Docente docenteConvertido = docenteMapDTO.convertirDocenteDTOEnDocente(docenteDTO);
		List<Docente> docentes = docenteRepository.findAll();
		for (int i = 0; i < docentes.size(); i++) {
	        Docente d = docentes.get(i);
	        if (d.getLegajo().equals(docenteConvertido.getLegajo())) {
	        	docenteConvertido.setEstado(true);
	            docentes.set(i, docenteConvertido);
	            docenteRepository.save(docenteConvertido);
	            break;
	        }
	    }
	}

	@Override
	public DocenteDTO buscarDocente(String legajo) {
		List<Docente> docentes = docenteRepository.findAll();
		for(Docente d : docentes) {
			if(d.getLegajo().equals(legajo)) return docenteMapDTO.convertirDocenteEnDocenteDTO(d);
		}
		return null;
	}
}
