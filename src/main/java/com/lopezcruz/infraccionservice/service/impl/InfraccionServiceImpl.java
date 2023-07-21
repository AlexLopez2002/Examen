package com.lopezcruz.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lopezcruz.infraccionservice.entity.Infraccion;
import com.lopezcruz.infraccionservice.exceptions.GeneralServiceException;
import com.lopezcruz.infraccionservice.exceptions.NoDataFoundException;
import com.lopezcruz.infraccionservice.exceptions.ValidateServiceException;
import com.lopezcruz.infraccionservice.repository.InfraccionRepository;
import com.lopezcruz.infraccionservice.service.InfraccionService;
import com.lopezcruz.infraccionservice.validator.InfraccionValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class InfraccionServiceImpl implements InfraccionService {
	
	@Autowired
	private InfraccionRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findByAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findByNombre(String dni, Pageable page) {
		try {
			return repository.findByNombreContaining(dni, page);
		}catch(ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Infraccion findById(int id) {
		try {
			Infraccion registro = repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe registro con ese ID")); 
			return registro;
		}catch(ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public Infraccion save(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			infraccion.setFalta(null);
			infraccion.setDescripcion(null);
			Infraccion registro = repository.save(infraccion);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Infraccion update(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			Infraccion registro = repository.findById(infraccion.getId()).orElseThrow(()->new NoDataFoundException("No existe registro con ese ID"));
			registro.setFalta(infraccion.getFalta());
			registro.setDescripcion(infraccion.getDescripcion());
			repository.save(registro);
			return registro;
		}catch(ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		try {
			Infraccion registro = repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe registro con ese ID"));
			repository.delete(registro);
		}catch(ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
}
