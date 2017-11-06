package net.juanxxiii.j23guiappframework.model;

/**
 * Clase Marca para almacenar las marcas de la BBDD.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Marca {
	private String nombre;
	private int id;
	
	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param id
	 */
	public Marca(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}

	/**
	 * Constructor vacio
	 * 
	 */
	public Marca() {
		
	}

	/**
	 * Getter de nombre
	 * 
	 * @return nombre marca
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de id
	 * 
	 * @return id marca
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter de id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
