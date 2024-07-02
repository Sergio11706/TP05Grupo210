package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class MateriaDTO {
	
	private String codigoDTO;
	private String nombreDTO;
	private String cursoDTO;
	private int cantHorasDTO;
	private String modalidadDTO;
	private boolean estadoDTO;
	private List<Alumno> alumnosDTO;
	private Docente docenteDTO;
	private Carrera carreraDTO;
}
