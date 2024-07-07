package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="carrera")
public class Carrera {
	@Id
	@Size(min=3, message="El codigo de carrera debe contener almenos 3 caracteres")
	private String codigo;
	
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-ZÑñ]*", message="Debe ingresar únicamente caracteres alfabeticos")
	private String nombre;
	
	@Min(value = 3, message = "La cantidad de años debe ser mayor o igual a 3 ")
    @Max(value = 6, message = "La cantidad de años debe ser como máximo 6")
	private byte cantAnios;
	
	private Boolean estado;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Materia> materias;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Alumno> alumnos;
	
}
