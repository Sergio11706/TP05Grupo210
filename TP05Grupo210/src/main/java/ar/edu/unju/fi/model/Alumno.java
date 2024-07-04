package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="alumno")
public class Alumno {
	@Id
	@Pattern(regexp ="[0-9]{8}", message="Debe ingresar los 8 digitos su DNI")
	private String dni;
	
	@Size(min=2, message="El nombre debe contener como minimo 2 caracteres")
	@Pattern(regexp= "[a-z A-Z Ññ]*", message="Debe ingresar únicamente letras")
	private String nombre;
	
	@Size(min=2, message="El apellido debe contener como minimo 2 caracteres")
	@Pattern(regexp= "[a-z A-Z Ññ]*", message="Debe ingresar unicamente letras")
	private String apellido;
	
	private String email;
	
	@Pattern(regexp="[0-9]*", message="Debe ingresar su numero telefonico (Ej: 3885909121)")
	@Size(min=8, message="Debe ingresar como minimo 8 digitos")
	private String telefono;
	
	private LocalDate nacimiento;
	
	
	@Pattern(regexp= "[a-z A-Z Ññ \s 0-9]*", message="Debe ingresar letras o numeros")
	@Size(min=5, message="Debe ingresar como minimo 5 caracteres")
	private String domicilio;
	
	@Size(min=5, message="Debe ingresar como minimo 5 digitos")
	@Pattern(regexp="[0-9]*", message="Debe ingresar solo digitos")
	private String LU;
	
	private boolean estado;

	@ManyToMany(mappedBy= "alumnos")
	private List<Materia> materias;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "carrara_codigo")
	private Carrera carrera;
}