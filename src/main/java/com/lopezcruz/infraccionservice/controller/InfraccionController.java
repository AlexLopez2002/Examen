package com.lopezcruz.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lopezcruz.infraccionservice.converter.InfraccionConverter;
import com.lopezcruz.infraccionservice.dto.InfraccionDTO;
import com.lopezcruz.infraccionservice.entity.Infraccion;
import com.lopezcruz.infraccionservice.service.InfraccionService;

@RestController
@RequestMapping("/v1/infracciones")

public class InfraccionController {
	@Autowired
	private InfraccionService service;
	
	@Autowired
	private InfraccionConverter converter;
	
	@GetMapping()
	 public ResponseEntity<List<InfraccionDTO>>findAll(
			 @RequestParam(value="dni",required=false,defaultValue="") String dni,
			 @RequestParam(value="offset",required=false,defaultValue="0") int pageNumber,
			 @RequestParam(value="limit",required=false,defaultValue="5") int pageSize
			 )
	{
		Pageable page = PageRequest.of(pageNumber, pageSize);
		 List<Infraccion> infracciones;
		 if(dni==null) {
			 infracciones = service.findByAll(page);
		 }else {
			 infracciones = service.findByNombre(dni, page);
		 }
		 
		 if(infracciones.isEmpty()) {
			 return ResponseEntity.noContent().build();
		 }
		 List<InfraccionDTO> infraccionesDTO = converter.fromEntity(infracciones);
		 return ResponseEntity.ok(infraccionesDTO);
	}
	
	@GetMapping(value="/{id}")
	 public ResponseEntity<InfraccionDTO> findById(@PathVariable("id") int id){
		 Infraccion infraccion = service.findById(id);
		 if(infraccion==null) {
			 return ResponseEntity.notFound().build();
		 }
		 InfraccionDTO articuloDTO= converter.fromEntity(infraccion);
		 return ResponseEntity.ok(articuloDTO);
	 }
	
	 @PostMapping
	 public ResponseEntity<InfraccionDTO> create(@RequestBody InfraccionDTO infraccionDTO){
		 Infraccion registro = service.save(converter.fromDTO(infraccionDTO));
		 InfraccionDTO registroDTO = converter.fromEntity(registro);
		 return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
	 }
	 
	 @PutMapping(value="/{id}")
	 public ResponseEntity<InfraccionDTO> update(@PathVariable("id") int id, @RequestBody InfraccionDTO infraccionDTO){
		 Infraccion registro = service.update(converter.fromDTO(infraccionDTO));
		 if(registro==null) {
			 return ResponseEntity.notFound().build();
		 }
		 InfraccionDTO registroDTO = converter.fromEntity(registro);
		 return ResponseEntity.ok(registroDTO);
	 }
	 
	 @DeleteMapping(value="/{id}")
	 public ResponseEntity<InfraccionDTO> delete(@PathVariable("id") int id){
		 service.delete(id);
		 return ResponseEntity.ok(null);
	 }
	
}
