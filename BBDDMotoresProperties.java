package net.juanxxiii.j23guiappframework.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Clase Properties que carga los datos necesarios para la conexion a la BBDD.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class BBDDMotoresProperties {
	private static String usuario;
	private static String password;
	private static String ip;
	private static String bbdd;
	
	/**
	 * Metodo que crea la conexion a la BBDD.
	 * Si el archivo config no existe lo crea y si existe simplemente realiza la conexion.
	 */
	public static void datosConexionBBDD(){
		Path p1 = Paths.get("config.dat");
		boolean existe = Files.exists (p1);{
		if (!existe){
	            try {
					crearProp();
					cargarProp();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    } else if (existe){
	            try {
					cargarProp();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }
	}

	/**
	 * Getter de usuario
	 * 
	 * @return usuario de la BBDD
	 */
	public static String getUsuario() {
		return usuario;
	}

	/**
	 * Getter del password
	 * 
	 * @return password del ususario de la BBDD
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * Getter de la IP de la BBDD
	 * 
	 * @return ip
	 */
	public static String getIp() {
		return ip;
	}

	/**
	 * Getter de la BBDD a la que se conecta
	 * 
	 * @return bbdd
	 */
	public static String getBbdd() {
		return bbdd;
	}

	/**
	 * Metodo que crea el archivo Properties.
	 * 
	 * @throws IOException
	 */
	public static void crearProp() throws IOException{
		Properties p = new Properties();
		FileOutputStream fos = new FileOutputStream("config.dat");
		p.setProperty("usuario", "dam2017");
		p.setProperty("password", "dam2017");
		p.setProperty("ip", "localhost");
		p.setProperty("bbdd", "bbdd_gestmotor");
		p.store(fos, "Fichero de configuracion de usuario de la BBDD");
		fos.close();
	}
	
	/**
	 * Metodo que carga el archivo Properties y asigna sus valores a los atributos de la clase.
	 * 
	 * @throws IOException
	 */
	public static void cargarProp() throws IOException{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("config.dat");
		p.load(fis);
		usuario = p.getProperty("usuario");
		password = p.getProperty("password");
		ip = p.getProperty("ip");
		bbdd = p.getProperty("bbdd");
		fis.close();
	}
}
