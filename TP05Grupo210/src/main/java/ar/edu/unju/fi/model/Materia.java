package ar.edu.unju.fi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Table(name="materias")

public class Materia {
	
	@Id
	private String codigo;
	private String nombre;
	private String curso;
	private int cantHoras;
	private String modalidad;
	private boolean estado;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrera_codigo")
	private Carrera carrera;
	
	
}