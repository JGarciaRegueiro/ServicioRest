package com.grupo6.serviciosRest.apirest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo6.serviciosRest.entities.Libro;
import com.grupo6.serviciosRest.modelo.LibroDao;

@RestController
@RequestMapping("/libros")
public class ApiRest {
	
	@Autowired
	private LibroDao ldao;
	
	@PostMapping()
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro libro){
		ldao.add(libro);
		return new ResponseEntity<Libro>(libro,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{idLibro}")
	public ResponseEntity<?> bajaLibro(@PathVariable int idLibro){
		if (ldao.getLibro(idLibro) != null) {
			return new ResponseEntity<>(ldao.delete(idLibro),HttpStatus.OK);
		} else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{idLibro}")
	public ResponseEntity<?> modificarLibro(@PathVariable int idLibro, @RequestBody Libro libro){
		if (ldao.getLibro(idLibro) != null) {
			libro.setId(idLibro);
			return new ResponseEntity<>(ldao.update(libro),HttpStatus.OK);
		}else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{idLibro}")
	public ResponseEntity<?> consultarLibro(@PathVariable int idLibro) {
		if (ldao.getLibro(idLibro) != null) {
			return new ResponseEntity<>(ldao.getLibro(idLibro),HttpStatus.OK);
		}else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping()
	public List<Libro> consultarListado() {
		return ldao.getListado();
	}

}
