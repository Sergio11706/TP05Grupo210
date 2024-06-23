package ar.edu.unju.fi.DTO;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

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
}