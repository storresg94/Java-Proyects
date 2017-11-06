package net.juanxxiii.j23guiappframework.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.juanxxiii.j23guiappframework.model.Marca;
import net.juanxxiii.j23guiappframework.model.Modelo;

/**
 * Clase que realiza las operaciones con la BBDD.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class GestorBBDDModelos extends GestorBBDD{
	
	/**
	 * Constructor.
	 * 
	 * @param usuario
	 * @param password
	 * @param ip
	 * @param nombreBBDD
	 */
    public GestorBBDDModelos(String usuario, String password, String ip, String nombreBBDD) {
        super(usuario, password, ip, nombreBBDD);
    }
    
    /**
     * Metodo que agrega modelos a la BBDD.
     * 
     * @param id_marca
     * @param modelo
     * @param consumo
     * @param emisiones
     * @param c_energ
     * @throws SQLException
     */
    public void insertarModelo(int id_marca, String modelo, float consumo, float emisiones, String c_energ) throws SQLException {
        String sql = "INSERT INTO modelos (ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA) VALUES ('" + id_marca + "','" + modelo + "','" + consumo + "','" + emisiones + "','" + c_energ + "')";
        Statement st = conexion.createStatement();
        st.executeUpdate(sql);
        st.close();
    }
    
    /**
     * Metodo que consulta los modelos de la BBDD.
     * 
     * @return ArrayList de Modelos
     * @throws SQLException
     */
    public ArrayList<Modelo> getModelo() throws SQLException{
        ArrayList<Modelo> modelos = new ArrayList();
        String sql = "SELECT ID,ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA FROM modelos";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Modelo p;
        while(rs.next() !=false){
            p = new Modelo(rs.getInt("ID"),rs.getInt("ID_MARCA"),rs.getString("MODELO"),rs.getFloat("CONSUMO"),rs.getFloat("EMISIONES"),rs.getString("C_ENERGETICA"));
            modelos.add(p);
        }
         for (Modelo modelo : modelos) {
             System.out.println("ID: "+modelo.getId()+" NOMBRE: "+modelo.getNombre());
        }
        return modelos;
    }
    
    /**
     * Metodo que consulta un modelo de un nombre de marca de la BBDD.
     * 
     * @param texto
     * @return ArrayList de Modelos de un nombre de marca
     * @throws SQLException
     */
    public ArrayList<Modelo> getModelo(String texto) throws SQLException{
        ArrayList<Modelo> modelos = new ArrayList();
        String sql = "SELECT ID,ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA FROM modelos WHERE NOMBRE like ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, "%" + texto + "%");
        ResultSet rs = ps.executeQuery();
        Modelo p;
        while(rs.next() !=false){
        	p = new Modelo(rs.getInt("ID"),rs.getInt("ID_MARCA"),rs.getString("MODELO"),rs.getFloat("CONSUMO"),rs.getFloat("EMISIONES"),rs.getString("C_ENERGETICA"));
            modelos.add(p);
        }
         for (Modelo modelo : modelos) {
             System.out.println("ID: "+modelo.getId()+" NOMBRE: "+modelo.getNombre());
        }
        return modelos;
    }
    
    /**
     * Metodo que consulta una marca de una id de marca de la BBDD.
     * 
     * @param id_marca
     * @return Nombre de la marca
     * @throws SQLException
     */
    public String getUnaMarca(int id_marca) throws SQLException{
        String sql = "SELECT MARCA FROM marcas WHERE ID=" + id_marca + ";";
        String retorno = null;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next() !=false){
        	retorno = rs.getString("MARCA");
        }
        return retorno;
    }
    
    /**
     * Metodo que consulta los modelos de una marca de la BBDD.
     * 
     * @param id_marca
     * @return ArrayList de Modelos de una marca
     * @throws SQLException
     */
    public ArrayList<Modelo> consultarModeloMarca(int id_marca) throws SQLException{
    	ArrayList<Modelo> modelos = new ArrayList();
        String sql = "SELECT ID,ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA FROM modelos WHERE ID_MARCA=" + id_marca + ";";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Modelo p = null;
        while(rs.next() !=false){
        	p = new Modelo(rs.getInt("ID"),rs.getInt("ID_MARCA"),rs.getString("MODELO"),rs.getFloat("CONSUMO"),rs.getFloat("EMISIONES"),rs.getString("C_ENERGETICA"));
            modelos.add(p);
        }
        return modelos;
    }
    
    /**
     * Metodo que consulta los modelos con un consumo de la BBDD.
     * 
     * @param consumo
     * @return ArrayList de modelos
     * @throws SQLException
     */
    public ArrayList<Modelo> consultarModeloCons(int consumo) throws SQLException{
    	ArrayList<Modelo> modelos = new ArrayList();
        String sql = "SELECT ID,ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA FROM modelos WHERE CONSUMO<" + consumo + " ORDER BY CONSUMO;";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Modelo p = null;
        while(rs.next() !=false){
        	p = new Modelo(rs.getInt("ID"),rs.getInt("ID_MARCA"),rs.getString("MODELO"),rs.getFloat("CONSUMO"),rs.getFloat("EMISIONES"),rs.getString("C_ENERGETICA"));
            modelos.add(p);
        }
        return modelos;
    }
    
    /**
     * Metodo que consulta las marcas de la BBDD.
     * 
     * @return ArrayList de nombres de Marcas
     * @throws SQLException
     */
    public ArrayList<Marca> getMarca() throws SQLException{
        String sql = "SELECT ID, MARCA FROM marcas";
        ArrayList<Marca> retorno = new ArrayList<>();
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next() !=false){
        	retorno.add(new Marca(rs.getString("MARCA"), rs.getInt("ID")));
        }
        return retorno;
    }
    
    /**
     * Metodo que consulta el id de las marcas de la BBDD.
     * 
     * @return ArrayList de id de marcas
     * @throws SQLException
     */
    public ArrayList<Integer> getIDMarca() throws SQLException{
        String sql = "SELECT ID FROM marcas";
        ArrayList<Integer> retorno = new ArrayList<>();
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        System.out.println();
        while(rs.next() !=false){
        	retorno.add(rs.getInt("ID"));
        }
        return retorno;
    }
    
    /**
     * Metodo que consulta las c_energ de la BBDD.
     * 
     * @return ArrayList de c_energ
     * @throws SQLException
     */
    public ArrayList<String> getC_Energ() throws SQLException{
        String sql = "SELECT C_ENERGETICA FROM eficiencias";
        ArrayList<String> retorno = new ArrayList<>();
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        System.out.println();
        while(rs.next() !=false){
        	retorno.add(rs.getString("C_ENERGETICA"));
        }
        return retorno;
    }
    
    /**
     * Metodo que consulta la id de una marca de la BBDD.
     * 
     * @param marca
     * @return id marca
     * @throws SQLException
     */
    public int consultaIDMarca(String marca) throws SQLException{
    	int retorno=0;
    	String sql = "SELECT ID FROM marcas WHERE MARCA='"+ marca +"'";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next() !=false){
        	retorno = rs.getInt("ID");
        }
        return retorno;
    }
    
    /**
     * Metodo que consulta cuantas filas de marcas hay de la BBDD.
     * 
     * @return numero de filas
     * @throws SQLException
     */
    public int consultaCOUNTIDMarca() throws SQLException{
    	int retorno=0;
    	String sql = "SELECT COUNT(*) as filas FROM marcas";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        System.out.println();
        while(rs.next() !=false){
        	retorno = Integer.parseInt(rs.getString("filas"));
        }
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
     * @throws SQLException
     */
    public Modelo consultaModelo(int id_marca,String modelo,Float consumo,Float emisiones,String c_energ) throws SQLException{
    	String sql = "SELECT ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA FROM modelos WHERE ID_MARCA="+ id_marca +" and MODELO='"+ modelo +"' and CONSUMO="+ consumo +" and EMISIONES="+ emisiones +" and C_ENERGETICA='"+ c_energ +"';";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        Modelo p = null;
        while(rs.next() !=false){
            p = new Modelo(rs.getInt("ID_MARCA"),rs.getString("MODELO"),rs.getFloat("CONSUMO"),rs.getFloat("EMISIONES"),rs.getString("C_ENERGETICA"));
        }
        return p;
    }
    
    /**
     * Metodo que consulta la id a borrar de la BBDD.
     * 
     * @param modelo
     * @param consumo
     * @param emisiones
     * @return id
     * @throws SQLException
     */
    public int consultaIDBorrar(String modelo, Float consumo, Float emisiones) throws SQLException{
    	int retorno=0;
    	String sql = "SELECT ID FROM modelos WHERE MODELO='"+ modelo +"' and CONSUMO="+ consumo +" and EMISIONES="+ emisiones +";";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next() !=false){
        	retorno = rs.getInt("ID");
        }
        return retorno;
    }
    
    /**
     * Metodo que borra modelos de la BBDD.
     * 
     * @param id
     * @throws SQLException
     */
    public void borrarModelo(int id) throws SQLException {
        String sql = "DELETE FROM modelos WHERE ID="+ id +";";
        Statement st = conexion.createStatement();
        st.executeUpdate(sql);
        st.close();
    }
    
    /**
     * Metodo que consulta el consumo maximo de la BBDD.
     * 
     * @return consumo maximo
     * @throws SQLException
     */
    public float consultaMaxConsumo() throws SQLException{
    	float retorno=0;
    	String sql = "SELECT MAX(CONSUMO) as consumo FROM modelos";
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        System.out.println();
        while(rs.next() !=false){
        	retorno = Float.parseFloat(rs.getString("consumo"));
        }
        return retorno+1;
    }

    /**
     * Metodo que consulta un icono de c_energ de la BBDD.
     * 
     * @param c_energ
     * @return icono
     * @throws SQLException
     */
	public String getIcono(String c_energ) throws SQLException {
		String sql = "SELECT ICONO FROM eficiencias WHERE C_ENERGETICA='" + c_energ + "';";
        String retorno = null;
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next() !=false){
        	retorno = rs.getString("ICONO");
        }
        return retorno;
	}
}
