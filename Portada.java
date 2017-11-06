package net.juanxxiii.j23guiappframework.gui;

import javax.swing.JPanel;

import net.juanxxiii.j23guiappframework.persistencia.BBDDMotoresProperties;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Panel de la portada de la aplicacion.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Portada extends JPanel {
	private static final String RUTA_PORTADA="/imagenes/bmw.jpg";

	/**
	 * Panel de la portada de la aplicacion.
	 */
	public Portada() {
		setLayout(new BorderLayout(0, 0));
		BBDDMotoresProperties.datosConexionBBDD();
		//Establezco aqui los datos de la conexion ya que esta es la primera pantalla en ejecutarse.
		JLabel lblPortada = new JLabel("");
		lblPortada.setIcon(new ImageIcon(Portada.class.getResource(RUTA_PORTADA)));
		add(lblPortada, BorderLayout.CENTER);
	}
}
