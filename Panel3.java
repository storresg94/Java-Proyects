package net.juanxxiii.j23guiappframework.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;

import javax.swing.JTable;

import net.juanxxiii.j23guiappframework.model.GestorModelos;
import net.juanxxiii.j23guiappframework.model.Modelo;
import net.juanxxiii.j23guiappframework.model.ModelosTableModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Panel de consulta por consumo.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Panel3 extends JPanel {
	private JTable jtConsumo;
	private JSlider jsConsumo;
	private JLabel lbConsumo;

	/**
	 * Panel de consulta por consumo.
	 */
	public Panel3() {
		setLayout(null);
		setLayout(null);
		GestorModelos g1 = new GestorModelos();//Creo un objeto de GestorModelos para poder usar sus metodos.
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		setBackground(SystemColor.inactiveCaption);
		
		JScrollPane spTabla = new JScrollPane();
		spTabla.setBounds(10, 60, 716, 363);
		add(spTabla);
		
		lbConsumo = new JLabel("");
		lbConsumo.setBounds(500, 22, 169, 14);
		add(lbConsumo);
		
		jsConsumo = new JSlider();
		jsConsumo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int consumo = jsConsumo.getValue();
				lbConsumo.setText("Consumo menor que: " + consumo);
				try {
					jsConsumo.setMaximum((int)g1.consultaMaxConsumo());
					//Cambia el valor maximo del JSlider al consumo maximo que exista en la BBDD.
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(Panel3.this, "Clase no encontrada.");
					e1.printStackTrace();
				} catch (SQLException e2) {
					if(e2.getErrorCode()==0){
						JOptionPane.showMessageDialog(Panel3.this, "Error en la conexion.");
					}else if(e2.getErrorCode()==1045){
						JOptionPane.showMessageDialog(Panel3.this, "Error al iniciar sesion.");
					}else if (e2.getErrorCode()==1049){
						JOptionPane.showMessageDialog(Panel3.this, "Error al encontrar la Base de Datos.");
					}else {
						JOptionPane.showMessageDialog(Panel3.this, "Error en la Base de Datos.");
					}
					e2.printStackTrace();
				}
			}
		});
		jsConsumo.setValue(10);
		jsConsumo.setMajorTickSpacing(1);
		jsConsumo.setBounds(10, 11, 480, 38);
		add(jsConsumo);
		
		jtConsumo = new JTable();
		spTabla.setViewportView(jtConsumo);

		JButton jbBusquedaCons = new JButton("");
		jbBusquedaCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Modelo> modelos = new ArrayList<>();
				try {
					modelos = g1.consultarModeloCons(jsConsumo.getValue());
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(Panel3.this, "Clase no encontrada.");
					e1.printStackTrace();
				} catch (SQLException e2) {
					if(e2.getErrorCode()==0){
						JOptionPane.showMessageDialog(Panel3.this, "Error en la conexion.");
					}else if(e2.getErrorCode()==1045){
						JOptionPane.showMessageDialog(Panel3.this, "Error al iniciar sesion.");
					}else if (e2.getErrorCode()==1049){
						JOptionPane.showMessageDialog(Panel3.this, "Error al encontrar la Base de Datos.");
					}else {
						JOptionPane.showMessageDialog(Panel3.this, "Error en la Base de Datos.");
					}
					e2.printStackTrace();
				}
				jtConsumo.setModel(new ModelosTableModel(modelos));
				JOptionPane.showMessageDialog(Panel3.this, "Se cargaron "+modelos.size()+" registros.");
			}
		});
		jbBusquedaCons.setBounds(671, 0, 55, 55);
		jbBusquedaCons.setIcon(new ImageIcon(Panel3.class.getResource(Panel2.getRutaSearch())));
		add(jbBusquedaCons);
	}
}