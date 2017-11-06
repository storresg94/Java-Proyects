package net.juanxxiii.j23guiappframework.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexion a la BBDD
 * 
 * @author Sergio Torres Garcia
 *
 */
public class GestorBBDD {

    private String usuario;
    private String password;
    private String ip;
    private String puerto;
    private String nombreBBDD;
    protected static Connection conexion;

    /**
     * Constructor que se utiliza cuando el puerto es el predeterminado, que es
     * el "3306".
     *
     * @param usuario
     * @param password
     * @param ip
     * @param nombreBBDD
     */
    public GestorBBDD(String usuario, String password, String ip, String nombreBBDD) {
        this.usuario = usuario;
        this.password = password;
        this.ip = ip;
        this.puerto = "3306";
        this.nombreBBDD = nombreBBDD;
    }

    /**
     * Constructor.
     *
     * @param usuario
     * @param password
     * @param ip
     * @param puerto
     * @param nombreBBDD
     */
    public GestorBBDD(String usuario, String password, String ip, String puerto, String nombreBBDD) {
        this.usuario = usuario;
        this.password = password;
        this.ip = ip;
        this.puerto = puerto;
        this.nombreBBDD = nombreBBDD;
    }

    /**
     * Metodo que establece la conexion con la BBDD.
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public void establecerConexion() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        String servidor_bbdd = "jdbc:mysql://" + ip + "/" + nombreBBDD;
        /*Hay que establecer el import de java.sql.Connection*/
        conexion = DriverManager.getConnection(servidor_bbdd, usuario, password);
    }

	/**
	 * Metodo que cierra la conexion con la BBDD.
	 * 
	 * @throws SQLException
	 */
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
