package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class DocenteDTO {
	private String legajoDTO;
	private String nombreDTO;
	private String apellidoDTO;
	private String emailDTO;
	private String telefonoDTO;
	private boolean estadoDTO;
	private List<Materia>materiaDTO;
}
