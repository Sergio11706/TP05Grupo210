package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {

	@Mapping(source="codigo", target="codigoDTO")
	@Mapping(source="nombre", target="nombreDTO")
	@Mapping(source="curso", target="cursoDTO")
	@Mapping(source="cantHoras", target="cantHorasDTO")
	@Mapping(source="modalidad", target="modalidadDTO")
	@Mapping(source="estado", target="estadoDTO")
	MateriaDTO convertirMateriaMateriaDTO(Materia m);
	
	@InheritInverseConfiguration
	Materia convertirMateriaDTOMateria(MateriaDTO mDTO);
	
	List<MateriaDTO> convertirListaMateriasListaMateriasDTO (List<Materia> listaM);
	
	List<Materia> convertirListaMateriasDTOListaMaterias (List<MateriaDTO> listaMDTO);
}
