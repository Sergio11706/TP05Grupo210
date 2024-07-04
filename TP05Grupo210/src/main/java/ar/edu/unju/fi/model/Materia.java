package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="materias")
public class Materia {
	
	@Id
	@Pattern(regexp="^[a-z]{3}[0-9]{3}$", message="El formato debe ser de 3 letras minúsculas seguidas de 3 números")
	private String codigo;
	
	private String nombre;
	private String curso;
	
	@Min(value=2, message="La cantidad mínima de horas debe ser mayor o igual a 2")
	private Integer cantHoras;
	
	private String modalidad;
	
	private Boolean estado;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "materia_alumno",
			joinColumns = @JoinColumn(name="codigoMateria"),
			inverseJoinColumns = @JoinColumn(name="dniAlumno")
	)
	private List<Alumno> alumnos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="legajo_docente")
	private Docente docente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_carrera")
	private Carrera carrera;
}