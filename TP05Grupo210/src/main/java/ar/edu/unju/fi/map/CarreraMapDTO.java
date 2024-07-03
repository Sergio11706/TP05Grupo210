package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	
	@Mapping(source="codigo", target="codigoDTO")
	@Mapping(source="nombre", target="nombreDTO")
	@Mapping(source="cantAnios", target="cantAniosDTO")
	@Mapping(source="estado", target="estadoDTO")
	@Mapping(source="materias", target="materiaDTO")
	@Mapping(source="alumnos", target="alumnoDTO")
	
	CarreraDTO convertirCarreraAcarreraDTO(Carrera c);
	
	@InheritInverseConfiguration
	
	Carrera convertirCarreraDTOaCarrera(CarreraDTO cDTO);

	List<CarreraDTO> convertirListaCarrerasListaCarrerasDTO (List<Carrera> listaC);
	List<Carrera> convertirListaCarrerasDTOListaCarreras(List<CarreraDTO> listacDTO);
}
