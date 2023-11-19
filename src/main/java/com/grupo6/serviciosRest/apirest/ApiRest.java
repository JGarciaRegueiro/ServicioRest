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


/**
 * Esta clase representa una API REST para la gestión de libros.
 */

@RestController
@RequestMapping("/libros")
public class ApiRest {
	
	@Autowired
	private LibroDao ldao;
	
	/**
     * Endpoint para agregar un nuevo libro.
     *
     * @param libro: El libro que se va a agregar.
     * @return ResponseEntity con el libro agregado o un mensaje de conflicto si el título ya existe.
     */
	
	@PostMapping()
	public ResponseEntity<?> altaLibro(@RequestBody Libro libro){
		if(ldao.isAbleLibro(libro)) {
			ldao.add(libro);
			return new ResponseEntity<Libro>(libro,HttpStatus.CREATED);
		}else return new ResponseEntity<String>("El título ya existe",HttpStatus.CONFLICT);
	}
	
	/**
     * Endpoint para eliminar un libro por su ID.
     *
     * @param idLibro: El ID del libro que se va a eliminar.
     * @return ResponseEntity con el libro eliminado o un mensaje de no encontrado si el libro no existe.
     */
	@DeleteMapping("/{idLibro}")
	public ResponseEntity<?> bajaLibro(@PathVariable int idLibro){
		if (ldao.getLibro(idLibro) != null) {
			return new ResponseEntity<>(ldao.delete(idLibro),HttpStatus.OK);
		} else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
	}
	
	/**
     * Endpoint para actualizar un libro por su ID.
     *
     * @param idLibro: El ID del libro que se va a actualizar.
     * @param libro: La información actualizada del libro.
     * @return ResponseEntity con el libro actualizado, un mensaje de conflicto si el título ya existe,
     * o un mensaje de no encontrado si el libro no existe.
     */
	@PutMapping("/{idLibro}")
	public ResponseEntity<?> modificarLibro(@PathVariable int idLibro, @RequestBody Libro libro){
		if (ldao.getLibro(idLibro) != null) {
			if (ldao.isAbleLibro(libro)) {
				libro.setId(idLibro);
				return new ResponseEntity<>(ldao.update(libro),HttpStatus.OK);
			}else return new ResponseEntity<String>("El título ya existe",HttpStatus.CONFLICT);
		}else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
	}
	
	/**
     * Endpoint para obtener un libro por su ID.
     *
     * @param idLibro: El ID del libro que se va a obtener.
     * @return ResponseEntity con el libro obtenido o un mensaje de no encontrado si el libro no existe.
     */
	@GetMapping("/{idLibro}")
	public ResponseEntity<?> consultarLibro(@PathVariable int idLibro) {
		if (ldao.getLibro(idLibro) != null) {
			return new ResponseEntity<>(ldao.getLibro(idLibro),HttpStatus.OK);
		}else return new ResponseEntity<String>("El libro no existe",HttpStatus.NOT_FOUND);
	}
	
	/**
     * Endpoint para obtener la lista de todos los libros.
     *
     * @return Lista de libros.
     */
	@GetMapping()
	public List<Libro> consultarListado() {
		return ldao.getListado();
	}

}
