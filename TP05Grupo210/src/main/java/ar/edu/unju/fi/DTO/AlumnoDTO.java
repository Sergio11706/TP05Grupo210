package ar.edu.unju.fi.DTO;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class AlumnoDTO {
	private String dniDTO;
	private String nombreDTO;
	private String apellidoDTO;
	private String emailDTO;
	private String telefonoDTO;
	private LocalDate nacimientoDTO;
	private String domicilioDTO;
	private String LUDTO;
	private boolean estadoDTO;
	private List<Materia> materiasDTO;
	private Carrera carreraDTO;
}