package com.grupo6.serviciosRest.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo6.serviciosRest.entities.Libro;

@Component
public class LibroDao {
	
	public List<Libro> listaLibros = new ArrayList<>();
	public int contador;

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
	
	public List<Libro> getListado(){
		return listaLibros;
	}
	
	public synchronized void add(Libro libro) {		
		libro.setId(contador++);
		if(isAbleLibro(libro)) {				
			listaLibros.add(libro);				
		}else contador--;
	}
	
	public Libro delete(int idLibro) {
		try {
			return listaLibros.remove(listaLibros.indexOf(getLibro(idLibro)));
		}catch (IndexOutOfBoundsException iobe){
			return null;
		}
	}
	
	public Libro update(Libro libro) {
		try {			
			listaLibros.set(listaLibros.indexOf(getLibro(libro.getId())), libro);
			return getLibro(libro.getId());
		}catch(IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	
	private boolean isAbleLibro(Libro libro) {
		if(libro.getTitulo() == "")
			return false;
		
		for(int i = 0; i < listaLibros.size(); i++) {			
			if(listaLibros.get(i).getTitulo().toLowerCase().equals(libro.getTitulo().toLowerCase()))				
				return false;	
		}
		
		return true;
	}
}
