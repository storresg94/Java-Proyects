/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.juanxxiii.j23guiappframework.model;

/**
 * Clase Modelo para almacenar los modelos de coches
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Modelo {
	private int id;
	private int id_marca;
	private String nombre;
	private float consumo;
	private float emisiones;
	private String c_energ;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param id_marca
	 * @param nombre
	 * @param consumo
	 * @param emisiones
	 * @param c_energ
	 */
	public Modelo(int id, int id_marca, String nombre, float consumo, float emisiones, String c_energ) {
		this.id = id;
		this.id_marca = id_marca;
		this.nombre = nombre;
		this.consumo = consumo;
		this.emisiones = emisiones;
		this.c_energ = c_energ;
	}

	/**
	 * Constructor
	 * 
	 * @param id_marca
	 * @param nombre
	 * @param consumo
	 * @param emisiones
	 * @param c_energ
	 */
	public Modelo(int id_marca, String nombre, float consumo, float emisiones, String c_energ) {
		this.id_marca = id_marca;
		this.nombre = nombre;
		this.consumo = consumo;
		this.emisiones = emisiones;
		this.c_energ = c_energ;
	}

	/**
	 * Getter de id
	 * 
	 * @return id modelo
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter de id marca
	 * 
	 * @return id marca
	 */
	public int getId_marca() {
		return id_marca;
	}

	/**
	 * Setter de id marca
	 * 
	 * @param id_marca
	 */
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}

	/**
	 * Getter de consumo
	 * 
	 * @return consumo
	 */
	public float getConsumo() {
		return consumo;
	}

	/**
	 * Setter de consumo
	 * 
	 * @param consumo
	 */
	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	/**
	 * Getter de emisiones
	 * 
	 * @return emisiones
	 */
	public float getEmisiones() {
		return emisiones;
	}

	/**
	 * Setter de emisiones
	 * 
	 * @param emisiones
	 */
	public void setEmisiones(float emisiones) {
		this.emisiones = emisiones;
	}

	/**
	 * Getter de c_energ
	 * 
	 * @return c_energ
	 */
	public String getC_energ() {
		return c_energ;
	}

	/**
	 * Setter de c_energ
	 * 
	 * @param c_energ
	 */
	public void setC_energ(String c_energ) {
		this.c_energ = c_energ;
	}

	/**
	 * Setter de id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter de nombre
	 * 
	 * @return nombre modelo
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

}
