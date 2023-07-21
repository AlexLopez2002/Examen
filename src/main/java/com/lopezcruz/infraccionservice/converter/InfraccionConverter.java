package com.lopezcruz.infraccionservice.converter;

import org.springframework.stereotype.Component;

import com.lopezcruz.infraccionservice.dto.InfraccionDTO;
import com.lopezcruz.infraccionservice.entity.Infraccion;

@Component
public class InfraccionConverter extends AbstractConverter<Infraccion,InfraccionDTO>{
	@Override
	public InfraccionDTO fromEntity(Infraccion entity) {
		if(entity==null) return null;
		return InfraccionDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.falta(entity.getFalta())
				.infraccion(entity.getInfraccion())
				.descripcion(entity.getDescripcion())
				.build();
				
	}
	
	@Override
	public Infraccion fromDTO(InfraccionDTO dto) {
		if(dto==null) return null;
		return Infraccion.builder()
				.id(dto.getId())
				.dni(dto.getDni())
				.falta(dto.getFalta())
				.infraccion(dto.getInfraccion())
				.descripcion(dto.getDescripcion())
				.build();
	}
}
	
