package ar.edu.unju.fi.map;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {
	@Mapping(source="dni", target="dniDTO")
	@Mapping(source="nombre", target="nombreDTO")
	@Mapping(source="apellido", target="apellidoDTO")
	@Mapping(source="email", target="emailDTO")
	@Mapping(source="telefono", target="telefonoDTO")
	@Mapping(source="nacimiento", target="nacimientoDTO")
	@Mapping(source="domicilio", target="domicilioDTO")
	@Mapping(source="LU", target="LUDTO")
	@Mapping(source="estado", target="estadoDTO")
	@Mapping(source="materias", target="materiasDTO")
	@Mapping(source="carrera", target="carreraDTO")
	
	AlumnoDTO convertirAlumnoAlumnoDTO(Alumno a);

	@InheritInverseConfiguration
	
	Alumno convertirAlumnoDTOAlumno(AlumnoDTO aDTO);

	List<AlumnoDTO> convertirListaAlumnosListaAlumnosDTO (List<Alumno> listaA);
	List<Alumno> convertirListaAlumnosDTOListaAlumnos (List<AlumnoDTO> listaADTO);
}