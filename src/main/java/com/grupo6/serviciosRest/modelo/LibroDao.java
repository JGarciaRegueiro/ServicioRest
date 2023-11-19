package com.grupo6.serviciosRest.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo6.serviciosRest.entities.Libro;

/**
 * Esta clase representa el acceso a datos para la gestión de libros.
 */
@Component
public class LibroDao {
	
	// Lista que almacena los libros
	public List<Libro> listaLibros = new ArrayList<>();
	
	// Contador para asignar identificadores únicos a los libros
	public int contador;
	
	/**
	 * Constructor de la clase que inicializa la lista de libros con algunos libros de ejemplo.
	 */
	public LibroDao() {
	    Libro libro1 = new Libro(contador++, "Juego de tronos", "Invierno Editorial", 8.5);
	    Libro libro2 = new Libro(contador++, "La sombra del viento", "Misterio Literario", 9.0);
	    Libro libro3 = new Libro(contador++, "Cien años de soledad", "Realismo mágico ediciones", 9.7);
	    Libro libro4 = new Libro(contador++, "El código da vinci", "Enigma editorial", 8.2);
	    Libro libro5 = new Libro(contador++, "Moby Dick", "Aventuras en el mar", 7.8);	    
	    
	    listaLibros.add(libro1);
	    listaLibros.add(libro2);
	    listaLibros.add(libro3);
	    listaLibros.add(libro4);
	    listaLibros.add(libro5);	  
	}
	
	/**
	 * Obtiene un libro por su ID.
	 *
	 * @param idLibro: El ID del libro a buscar.
	 * @return El libro correspondiente al ID o null si no se encuentra.
	 */
	
	public Libro getLibro(int idLibro) {
		try {
			Libro libroOriginal = null;
			for (Libro libro : listaLibros) {
				if (libro.getId()==idLibro) {
					libroOriginal=libro;
				}
			}
			return libroOriginal;
			
		}catch(IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	/**
	 * Obtiene la lista de todos los libros.
	 *
	 * @return Lista de libros.
	 */
	public List<Libro> getListado(){
		return listaLibros;
	}
	
	/**
	 * Agrega un nuevo libro a la lista.
	 *
	 * @param libro: El libro a agregar.
	 */
	public void add(Libro libro) {		
		libro.setId(contador++);
		listaLibros.add(libro);				
	}
	/**
	 * Elimina un libro por su ID.
	 *
	 * @param idLibro: El ID del libro a eliminar.
	 * @return El libro eliminado o null si no se encuentra.
	 */
	public Libro delete(int idLibro) {
		try {
			return listaLibros.remove(listaLibros.indexOf(getLibro(idLibro)));
		}catch (IndexOutOfBoundsException iobe){
			return null;
		}
	}
	/**
	 * Actualiza la información de un libro.
	 *
	 * @param libro: El libro con la información actualizada.
	 * @return El libro actualizado o null si no se encuentra.
	 */
	public Libro update(Libro libro) {
		try {			
			listaLibros.set(listaLibros.indexOf(getLibro(libro.getId())), libro);
			return getLibro(libro.getId());
		}catch(IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	/**
	 * Verifica si es posible agregar un libro, verificando que el titulo no exista.
	 *
	 * @param libro: El libro a verificar.
	 * @return true si es posible agregar el libro, false si ya existe un libro con el mismo título o si el título es vacío.
	 */
	public boolean isAbleLibro(Libro libro) {
		if(libro.getTitulo() == "")
			return false;
		
		for(int i = 0; i < listaLibros.size(); i++) {			
			if(listaLibros.get(i).getTitulo().toLowerCase().equals(libro.getTitulo().toLowerCase()))				
				return false;	
		}
		
		return true;
	}
}
