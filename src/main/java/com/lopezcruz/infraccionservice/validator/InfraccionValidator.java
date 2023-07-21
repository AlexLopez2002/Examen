package com.lopezcruz.infraccionservice.validator;

import com.lopezcruz.infraccionservice.entity.Infraccion;
import com.lopezcruz.infraccionservice.exceptions.ValidateServiceException;

public class InfraccionValidator {
		public static void save(Infraccion infraccion) {
		if(infraccion.getDni() ==null || infraccion.getDni().isEmpty()) {
			throw new ValidateServiceException("El DNI es requerido");
		}
		
		//mayor a 100
		if(infraccion.getDni().length()>8) {
			throw new ValidateServiceException("El nombre es muy largo");
		}
		
		if(infraccion.getInfraccion()==null) {
			throw new ValidateServiceException("El precio es requerido");
		}
		
		if(infraccion.getInfraccion().length() > 200) {
			throw new ValidateServiceException("El precio es incorrecto");
		}
	}
}
