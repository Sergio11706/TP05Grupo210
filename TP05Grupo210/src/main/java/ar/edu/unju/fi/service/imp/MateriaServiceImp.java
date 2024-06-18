package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService {

	@Autowired
	MateriaRepository materiaRepository;
	
	@Override
	public void guardarMateria(Materia materia) {
		materia.setEstado(true);
		materiaRepository.save(materia);
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
	public void modificarMateria(Materia materia) {
		List<Materia> materias = materiaRepository.findAll();
		for (int i = 0; i < materias.size(); i++) {
	        Materia m = materias.get(i);
	        if (m.getCodigo().equals(materia.getCodigo())) {
	            materias.set(i, materia);
	            materia.setEstado(true);
	            materiaRepository.save(materia);
	            break;
	        }
	    }
	}

	@Override
	public Materia buscarMateria(String codigo) {
		List<Materia> materias = materiaRepository.findAll();
		for(Materia i : materias) {
			if(i.getCodigo().equals(codigo)) return i;
		}
		return null;
	}

}
