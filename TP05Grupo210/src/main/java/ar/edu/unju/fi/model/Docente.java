package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Component
@Entity
@Table(name="docente")
public class Docente {
	
	@Id
	@Size(min=3, message="Proporcione un legajo que contenga como minimo 3 caracteres")
	private String legajo;
	
	@Column(nullable = false)
	@Size(min=3,max=20, message="El nombre debe contener como minimo 3 caracteres y como maximo 20 caracteres")
	@Pattern(regexp="[a-zA-ZÑñ]*", message="El nombre debe estar conformado unicamente por caracteres alfabeticos")
	private String nombre;
	
	@Column(nullable = false)
	@Size(min=3,max=30, message="El apellido debe contener como minimo 3 caracteres y como maximo 20 caracteres")
	@Pattern(regexp="[a-zA-ZÑñ]*", message="El apellido debe estar conformado unicamente por caracteres alfabeticos")
	private String apellido;
	
	@Column(nullable = false)
	private String email;
	
    @Column(nullable = false)
    @Pattern(regexp = "\\d{3} \\d{3}-\\d{4}", message = "El número de teléfono debe seguir el formato 123 456-7890")
    private String telefono;
	
	@Column(nullable = false)
	private boolean estado;
		
}
