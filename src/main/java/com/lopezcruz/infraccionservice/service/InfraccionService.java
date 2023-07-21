package com.lopezcruz.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lopezcruz.infraccionservice.entity.Infraccion;

public interface InfraccionService {
	public List<Infraccion> findByAll(Pageable page);
	public List<Infraccion> findByNombre(String dni, Pageable page);
	public Infraccion findById(int id);
	public Infraccion save(Infraccion infraccion);
	public Infraccion update(Infraccion infraccion);
	public void delete(int id);

}
