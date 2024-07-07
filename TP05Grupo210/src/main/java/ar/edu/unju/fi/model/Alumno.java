package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="alumno")
public class Alumno {
	@Id
	@Pattern(regexp ="[0-9]{8}", message="Debe ingresar solo 8 digitos")
	private String dni;
	
	@Size(min=2, message="Debe ingresar como minimo 2 caracteres")
	@Pattern(regexp= "[a-z A-Z ÁÉÍÓÚ áéíóú Ññ]*", message="Debe ingresar unicamente letras")
	private String nombre;
	
	@Size(min=2, message="Debe ingresar como minimo 2 caracteres")
	@Pattern(regexp= "[a-z A-Z ÁÉÍÓÚ áéíóú Ññ]*", message="Debe ingresar unicamente letras")
	private String apellido;
	
	@NotBlank(message="Debe ingresar su correo")
	@Email(message="Ingrese un correo valido")
	private String email;
	
	@Pattern(regexp="^(388\\d{5,}|154\\d{5,})$", message="Su número de teléfono debe empezar con 388 o 154 y deben ser 8 digitos")
	private String telefono;
	
	@NotNull(message="Debe ingresar la fecha")
	@Past(message="La fecha tiene que ser anterior a la actual")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate nacimiento;
	
	
	@Pattern(regexp= "[a-z A-Z ÁÉÍÓÚ áéíóú Ññ \s 0-9]*", message="Debe ingresar solo letras o numeros")
	@Size(min=5, message="Debe ingresar como minimo 5 caracteres")
	private String domicilio;
	
	@Size(min=5, max=10, message="Debe ingresar como minimo 5 digitos")
	@Pattern(regexp="[0-9]*", message="Debe ingresar solo digitos")
	private String LU;
	
	private boolean estado;

	@ManyToMany(mappedBy= "alumnos")
	private List<Materia> materias;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "carrara_codigo")
	private Carrera carrera;
}