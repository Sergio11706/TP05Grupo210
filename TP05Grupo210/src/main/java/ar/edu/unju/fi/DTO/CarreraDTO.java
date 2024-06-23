package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CarreraDTO {
	private String codigoDTO;
	private String nombreDTO;
	private byte cantAniosDTO; 
	private Boolean estadoDTO;
}
