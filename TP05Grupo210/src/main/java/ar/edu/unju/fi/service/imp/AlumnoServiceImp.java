package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	AlumnoRepository alumnoRepository;
	
	
	@Override
	public void guardarAlumno(Alumno alumno) {
		alumno.setEstado(true);
		alumnoRepository.save(alumno);
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnos() {
		return alumnoMapDTO.convertirListaAlumnosListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
	}

	@Override
	public void eliminarAlumno(String dni) {

		List<Alumno> alumnos = alumnoRepository.findAll();
		for(Alumno i : alumnos) {
			if(i.getDni().equals(dni)) {
				i.setEstado(false);
				alumnoRepository.save(i);
			}
		}
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno a = alumnos.get(i);
			if (a.getDni().equals(alumno.getDni())) {
				//alumnos.set(i, alumno);
				alumno.setEstado(true);
				alumnoRepository.save(alumno);
				break;
			}
		}
	}
	
	@Override
	public Alumno buscarAlumno(String dni) {
		List<Alumno> alumnos = alumnoRepository.findAll();
		for(Alumno a : alumnos) {
			if(a.getDni().equals(dni)) return a;
		}
		return null;
	}
	
	/*
	@Override
	public List<AlumnoDTO> filtrarPorMateria(String codigoMateria) {
	    List<Alumno> alumnos = alumnoRepository.findByMaterias_CodigoAndEstado(codigoMateria, true);
	    return alumnoMapDTO.convertirListaAlumnosListaAlumnosDTO(alumnos);
	}
	*/
}