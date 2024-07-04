package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="alumnos")

public class Alumno {
	@Id
	@NonNull
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate nacimiento;
	private String domicilio;
	private String LU;
	private boolean estado;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrera_codigo")
	private Carrera carrera;

}