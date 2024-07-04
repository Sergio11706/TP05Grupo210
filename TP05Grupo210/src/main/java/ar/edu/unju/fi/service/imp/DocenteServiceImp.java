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
	public void guardarDocente(Docente docente) {
		docente.setEstado(true);
		docenteRepository.save(docente);
	}

	@Override
	public List<DocenteDTO> mostrarDocentes() {
		return docenteMapDTO.convertirListaDocentesEnListaDocentesDTO(docenteRepository.findDocenteByEstado(true));
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
	public void modificarDocente(Docente docente) {
		List<Docente> docentes = docenteRepository.findAll();
		for (int i = 0; i < docentes.size(); i++) {
	        Docente d = docentes.get(i);
	        if (d.getLegajo().equals(docente.getLegajo())) {
	        	docente.setEstado(true);
	            docentes.set(i, docente);
	            docenteRepository.save(docente);
	            break;
	        }
	    }
	}

	@Override
	public Docente buscarDocente(String legajo) {
		List<Docente> docentes = docenteRepository.findAll();
		for(Docente d : docentes) {
			if(d.getLegajo().equals(legajo)) return d;
		}
		return null;
	}
}