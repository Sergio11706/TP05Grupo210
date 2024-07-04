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
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Override
	public void guardarAlumno(AlumnoDTO alumnoDTO) {
		alumnoDTO.setEstadoDTO(true);
		alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAlumno(alumnoDTO));
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		return alumnoRepository.findAlumnoByEstado(true);
	}

	@Override
	public void eliminarAlumno(String dni) {

		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i = 0; i < alumnos.size(); i++) {
		      Alumno alumno = alumnos.get(i);
		      if (alumno.getDni().equals(dni)) {
		        alumno.setEstado(false);
		        alumnoRepository.save(alumno);
		        break;
		      }
		 }
	}

	@Override
	public AlumnoDTO buscarAlumno(String dni) {
		List<Alumno> alumnos = alumnoRepository.findAll();
		for(Alumno a : alumnos) {
			if(a.getDni().equals(dni)) return alumnoMapDTO.convertirAlumnoAlumnoDTO(a);
		}
		return null;
	}

	@Override
	public void modificarAlumno(AlumnoDTO alumnoDTO) {
		Alumno alumnoConvertido = alumnoMapDTO.convertirAlumnoDTOAlumno(alumnoDTO);
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno a = alumnos.get(i);
			if (a.getDni().equals(alumnoConvertido.getDni())) {
				alumnos.set(i, alumnoConvertido);
				alumnoConvertido.setEstado(true);
				alumnoRepository.save(alumnoConvertido);
				break;
			}
		}
	}
}