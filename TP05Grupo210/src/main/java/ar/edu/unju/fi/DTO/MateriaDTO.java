package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

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
	
}
