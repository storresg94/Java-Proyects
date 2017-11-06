package net.juanxxiii.j23guiappframework.model;

import java.sql.SQLException;
import java.util.ArrayList;

import net.juanxxiii.j23guiappframework.persistencia.BBDDMotoresProperties;
import net.juanxxiii.j23guiappframework.persistencia.GestorBBDDModelos;

/**
 * Clase de conexiones a los metodos de la BBDD.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class GestorModelos {
	GestorBBDDModelos gp = new GestorBBDDModelos(BBDDMotoresProperties.getUsuario(),BBDDMotoresProperties.getPassword(), BBDDMotoresProperties.getIp(), BBDDMotoresProperties.getBbdd());

	/**
	 * Metodo que agrega modelos a la BBDD.
	 * 
	 * @param id_marca
	 * @param modelo
	 * @param consumo
	 * @param emisiones
	 * @param c_energ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addModelo(int id_marca, String modelo, float consumo, float emisiones, String c_energ) throws ClassNotFoundException, SQLException{
		gp.establecerConexion();
		gp.insertarModelo(id_marca,modelo,consumo,emisiones,c_energ);
		gp.cerrarConexion();
	}
	
	/**
	 * Metodo que borra modelos de la BBDD.
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void borrarModelo(int id) throws ClassNotFoundException, SQLException{
		gp.establecerConexion();
		gp.borrarModelo(id);
		gp.cerrarConexion();
	}
	
	/**
	 * Metodo que consulta la id a borrar de la BBDD.
	 * 
	 * @param modelo
	 * @param consumo
	 * @param emisiones
	 * @return id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int consultaIDBorrar(String modelo, Float consumo, Float emisiones) throws SQLException, ClassNotFoundException{
		gp.establecerConexion();
		int retorno = gp.consultaIDBorrar(modelo, consumo, emisiones);
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta un modelo de la BBDD.
	 * 
	 * @param id_marca
	 * @param modelo
	 * @param consumo
	 * @param emisiones
	 * @param c_energ
	 * @return Objeto de Modelo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Modelo consultaModelo(int id_marca,String modelo,Float consumo,Float emisiones,String c_energ) throws ClassNotFoundException, SQLException{
		gp.establecerConexion();
		Modelo retorno = gp.consultaModelo(id_marca, modelo, consumo, emisiones, c_energ);
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta los modelos de una marca de la BBDD.
	 * 
	 * @param id_marca
	 * @return ArrayList de Modelos de una marca
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Modelo> consultarModeloMarca(int id_marca) throws ClassNotFoundException, SQLException{
		ArrayList<Modelo> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.consultarModeloMarca(id_marca);
		gp.cerrarConexion();
		
		return modelos;
	}
	
	/**
	 * Metodo que consulta los modelos de la BBDD.
	 * 
	 * @return ArrayList de Modelos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Modelo> getModelo() throws SQLException, ClassNotFoundException{
		ArrayList<Modelo> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.getModelo();
		gp.cerrarConexion();
		
		return modelos;
	}
	
	/**
	 * Metodo que consulta un modelo de un nombre de marca de la BBDD.
	 * 
	 * @param texto
	 * @return ArrayList de Modelos de un nombre de marca
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Modelo> getModelo(String texto) throws SQLException, ClassNotFoundException{
		ArrayList<Modelo> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.getModelo(texto);
		gp.cerrarConexion();
		
		return modelos;
	}
	
	/**
	 * Metodo que consulta una marca de una id de marca de la BBDD.
	 * 
	 * @param id_marca
	 * @return Nombre de la marca
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String getUnaMarca(int id_marca) throws SQLException, ClassNotFoundException{
		gp.establecerConexion();
		String retorno = gp.getUnaMarca(id_marca);
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta las marcas de la BBDD.
	 * 
	 * @return ArrayList de nombres de Marcas
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Marca> getMarca() throws SQLException, ClassNotFoundException{
		ArrayList<Marca> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.getMarca();
		gp.cerrarConexion();
		
		return modelos;
	}
	
	/**
	 * Metodo que consulta el id de las marcas de la BBDD.
	 * 
	 * @return ArrayList de id de marcas
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Integer> getIDMarca() throws SQLException, ClassNotFoundException{
		ArrayList<Integer> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.getIDMarca();
		gp.cerrarConexion();
		
		return modelos;
	}
	
	/**
	 * Metodo que consulta las c_energ de la BBDD.
	 * 
	 * @return ArrayList de c_energ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<String> getC_Energ() throws SQLException, ClassNotFoundException{
		ArrayList<String> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.getC_Energ();
		gp.cerrarConexion();
		
		return modelos;
	}

	/**
	 * Metodo que consulta la id de una marca de la BBDD.
	 * 
	 * @param marca
	 * @return id marca
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int consultaIDMarca(String marca) throws SQLException, ClassNotFoundException{
		gp.establecerConexion();
		int retorno = gp.consultaIDMarca(marca);
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta cuantas filas de marcas hay de la BBDD.
	 * 
	 * @return numero de filas
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int consultaCOUNTIDMarca() throws SQLException, ClassNotFoundException{
		gp.establecerConexion();
		int retorno = gp.consultaCOUNTIDMarca();
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta el consumo maximo de la BBDD.
	 * 
	 * @return consumo maximo
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public float consultaMaxConsumo() throws SQLException, ClassNotFoundException{
		gp.establecerConexion();
		float retorno = gp.consultaMaxConsumo();
		gp.cerrarConexion();
		return retorno;
	}
	
	/**
	 * Metodo que consulta los modelos con un consumo de la BBDD.
	 * 
	 * @param consumo
	 * @return ArrayList de modelos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Modelo> consultarModeloCons(int consumo) throws SQLException, ClassNotFoundException{
		ArrayList<Modelo> modelos = new ArrayList();
		gp.establecerConexion();
		modelos = gp.consultarModeloCons(consumo);
		gp.cerrarConexion();
		
		return modelos;
	}

	/**
	 * Metodo que consulta un icono de c_energ de la BBDD.
	 * 
	 * @param c_energ
	 * @return icono
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getIcono(String c_energ) throws ClassNotFoundException, SQLException {
		gp.establecerConexion();
		String retorno = gp.getIcono(c_energ);
		gp.cerrarConexion();
		return retorno;
	}
}
