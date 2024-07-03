package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CarreraDTO {
	
	private String codigoDTO;
	private String nombreDTO;
	private byte cantAniosDTO;
	private boolean estadoDTO;
	private List<Materia> materiaDTO;
	private List<Alumno> alumnoDTO;
}
