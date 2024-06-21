package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {

	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;
	
	@Override
	public void guardarMateria(MateriaDTO materiaDTO) {
		materiaDTO.setEstadoDTO(true);
		materiaRepository.save(materiaMapDTO.convertirMateriaDTOMateria(materiaDTO));
	}

	@Override
	public List<Materia> mostrarMaterias() {
		return materiaRepository.findMateriaByEstado(true);
	}

	@Override
	public void borrarMateria(String codigo) {
		List<Materia> materias = materiaRepository.findAll();
		for(Materia i : materias) {
			if(i.getCodigo().equals(codigo)) {
				i.setEstado(false);
				materiaRepository.save(i);
			}
		}
	}

	@Override
	public void modificarMateria(MateriaDTO materiaDTO) {
		Materia materiaConvertida = materiaMapDTO.convertirMateriaDTOMateria(materiaDTO);
		List<Materia> materias = materiaRepository.findAll();
		for (int i = 0; i < materias.size(); i++) {
	        Materia m = materias.get(i);
	        if (m.getCodigo().equals(materiaConvertida.getCodigo())) {
	        	materiaConvertida.setEstado(true);
	            materias.set(i, materiaConvertida);
	            materiaRepository.save(materiaConvertida);
	            break;
	        }
	    }
	}

	@Override
	public MateriaDTO buscarMateria(String codigo) {
		List<Materia> materias = materiaRepository.findAll();
		for(Materia i : materias) {
			if(i.getCodigo().equals(codigo)) return materiaMapDTO.convertirMateriaMateriaDTO(i);
		}
		return null;
	}

}
