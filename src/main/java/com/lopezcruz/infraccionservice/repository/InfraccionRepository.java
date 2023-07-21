package com.lopezcruz.infraccionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.lopezcruz.infraccionservice.entity.Infraccion;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion,Integer>{
	List<Infraccion> findByNombreContaining(String dni, Pageable page);
	Infraccion findByNombreContaining(String dni);

}