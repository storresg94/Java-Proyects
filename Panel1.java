package net.juanxxiii.j23guiappframework.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import net.juanxxiii.j23guiappframework.model.GestorModelos;
import net.juanxxiii.j23guiappframework.model.Marca;
import net.juanxxiii.j23guiappframework.model.Modelo;
import net.juanxxiii.j23guiappframework.model.ModelosTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import net.juanxxiii.j23guiappframework.persistencia.JChomboBox;
import net.juanxxiii.j23guiappframework.persistencia.JChomboRenderer;

import java.awt.Font;
import javax.swing.JTable;

/**
 * Panel de agregar modelos.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Panel1 extends JPanel {
	private JTextField jtfModelo;
	private JTextField jtfConsumo;
	private JTextField jtfEmisiones;
	private JTable jtRegistro;

	/**
	 * Panel de agregar modelos.
	 */
	public Panel1() {
		setBackground(SystemColor.info);
		setLayout(new BorderLayout(0, 0));
		GestorModelos g1 = new GestorModelos();//Creo un objeto de GestorModelos para poder usar sus metodos.
		ArrayList<Marca> items = new ArrayList<>();
		try {
			items = g1.getMarca();
		} catch (ClassNotFoundException e3) {
			JOptionPane.showMessageDialog(Panel1.this, "Clase no encontrada.");
			e3.printStackTrace();
		} catch (SQLException e2) {
			if(e2.getErrorCode()==0){
				JOptionPane.showMessageDialog(Panel1.this, "Error en la conexion.");
				System.exit(0);//Cierro la aplicacion si la conexion falla.
			}else if(e2.getErrorCode()==1045){
				JOptionPane.showMessageDialog(Panel1.this, "Error al iniciar sesion.");
			}else if (e2.getErrorCode()==1049){
				JOptionPane.showMessageDialog(Panel1.this, "Error al encontrar la Base de Datos.");
			}else {
				JOptionPane.showMessageDialog(Panel1.this, "Error en la Base de Datos.");
			}
			e2.printStackTrace();
		}
		final ArrayList<Marca> mark = items;//Creo un auxiliar final del ArrayList<Marca> para usarlo en otro scope.

		JPanel panel = new JPanel();
		panel.setBackground(new Color(116, 203, 192));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		jtfModelo = new JTextField();
		jtfModelo.setBounds(85, 85, 86, 27);
		panel.add(jtfModelo);
		jtfModelo.setColumns(10);

		JChomboBox cbId_marca;
		// Creo un ArrayList de ImageIcon para poder introducir por consulta los iconos de cada marca.
		
		ArrayList<ImageIcon> iconos = new ArrayList<>();
			try {
				for (Integer id : g1.getIDMarca()) {
				iconos.add(new ImageIcon(getClass().getResource("/iconosmarcas/" + id + ".jpg")));
				}
			} catch (ClassNotFoundException e3) {
				JOptionPane.showMessageDialog(Panel1.this, "Clase no encontrada.");
				e3.printStackTrace();
			} catch (SQLException e2) {
				if(e2.getErrorCode()==0){
					JOptionPane.showMessageDialog(Panel1.this, "Error en la conexion.");
					System.exit(0);//Cierro la aplicacion si la conexion falla.
				}else if(e2.getErrorCode()==1045){
					JOptionPane.showMessageDialog(Panel1.this, "Error al iniciar sesion.");
				}else if (e2.getErrorCode()==1049){
					JOptionPane.showMessageDialog(Panel1.this, "Error al encontrar la Base de Datos.");
				}else {
					JOptionPane.showMessageDialog(Panel1.this, "Error en la Base de Datos.");
				}
				e2.printStackTrace();
			};
			
		cbId_marca = new JChomboBox(items.size());
		cbId_marca.setBounds(64, 10, 200, 55);
		JChomboRenderer render = new JChomboRenderer(iconos);
		cbId_marca.setRenderer(render);
		panel.add(cbId_marca);

		JComboBox cbC_energ = new JComboBox();
		cbC_energ.setBounds(85, 243, 86, 20);
		panel.add(cbC_energ);
		try {
			for (String capacidad : g1.getC_Energ()) {
				cbC_energ.addItem(capacidad);
			}
		} catch (SQLException e2) {
			if(e2.getErrorCode()==0){
				JOptionPane.showMessageDialog(Panel1.this, "Error en la conexion.");
			}else if(e2.getErrorCode()==1045){
				JOptionPane.showMessageDialog(Panel1.this, "Error al iniciar sesion.");
			}else if (e2.getErrorCode()==1049){
				JOptionPane.showMessageDialog(Panel1.this, "Error al encontrar la Base de Datos.");
			}else {
				JOptionPane.showMessageDialog(Panel1.this, "Error en la Base de Datos.");
			}
			e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(Panel1.this, "Clase no encontrada.");
			e1.printStackTrace();
		}

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(8, 88, 67, 14);
		panel.add(lblModelo);

		JLabel lblIdmarca = new JLabel("ID_Marca");
		lblIdmarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIdmarca.setBounds(10, 32, 46, 14);
		panel.add(lblIdmarca);

		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setBounds(8, 136, 67, 14);
		panel.add(lblConsumo);

		JLabel lblEmisiones = new JLabel("Emisiones");
		lblEmisiones.setBounds(8, 191, 67, 14);
		panel.add(lblEmisiones);

		JLabel lblCenerg = new JLabel("C_Energ");
		lblCenerg.setBounds(8, 246, 67, 14);
		panel.add(lblCenerg);

		JButton btnGuardar = new JButton("Agregar Modelo");
		btnGuardar.setBounds(153, 389, 128, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Marca marc = mark.get(cbId_marca.getSelectedIndex());
				String marca = marc.getNombre();

				int id_marca = 0;
				try {
					ArrayList<Modelo> modelos = new ArrayList<>();
					id_marca = g1.consultaIDMarca(marca);

					if ((jtfConsumo.getText()).equals("")) {
						JOptionPane.showMessageDialog(Panel1.this, "El consumo no puede estar vacio");
						jtfConsumo.requestFocus();//Pone en foco este JTextField si se cumple el if
						return;
					} else if ((jtfEmisiones.getText()).equals("")) {
						JOptionPane.showMessageDialog(Panel1.this, "Las emisiones no pueden estar vacias");
						jtfEmisiones.requestFocus();//Pone en foco este JTextField si se cumple el if
					}
					String modelo = jtfModelo.getText();
					float consumo = Float.parseFloat(jtfConsumo.getText());
					float emisiones = Float.parseFloat(jtfEmisiones.getText());
					String c_energ = (cbC_energ.getSelectedItem()).toString();

					g1.addModelo(id_marca, modelo, consumo, emisiones, c_energ);
					JOptionPane.showMessageDialog(Panel1.this, "El modelo fue agregado correctamente.");

					modelos.add(g1.consultaModelo(id_marca, modelo, consumo, emisiones, c_energ));

					jtRegistro.setModel(new ModelosTableModel(modelos));
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(Panel1.this, "Clase no encontrada.");
					e1.printStackTrace();
				} catch (SQLException e2) {
					if(e2.getErrorCode()==0){
						JOptionPane.showMessageDialog(Panel1.this, "Error en la conexion.");
					}else if(e2.getErrorCode()==1045){
						JOptionPane.showMessageDialog(Panel1.this, "Error al iniciar sesion.");
					}else if (e2.getErrorCode()==1049){
						JOptionPane.showMessageDialog(Panel1.this, "Error al encontrar la Base de Datos.");
					}else {
						JOptionPane.showMessageDialog(Panel1.this, "Error en la Base de Datos.");
					}
					e2.printStackTrace();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(Panel1.this, "No se han rellenado los campos correctamente o estan vacios.");
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnGuardar);

		JLabel lblPag = new JLabel("INSERCION");
		lblPag.setForeground(Color.BLUE);
		lblPag.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPag.setBounds(388, 32, 181, 31);
		panel.add(lblPag);

		JLabel lblBorradoDe = new JLabel("DE MODELOS");
		lblBorradoDe.setForeground(Color.BLUE);
		lblBorradoDe.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblBorradoDe.setBounds(388, 69, 168, 63);
		panel.add(lblBorradoDe);

		jtfConsumo = new JTextField();
		jtfConsumo.setBounds(85, 133, 86, 27);
		panel.add(jtfConsumo);
		jtfConsumo.setColumns(10);

		jtfEmisiones = new JTextField();
		jtfEmisiones.setBounds(85, 188, 86, 27);
		panel.add(jtfEmisiones);
		jtfEmisiones.setColumns(10);

		JLabel lbDecim = new JLabel("Introduzca decimales con puntos. Ej(21.4)");
		lbDecim.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbDecim.setBounds(38, 164, 237, 14);
		panel.add(lbDecim);

		JLabel lbTabla = new JLabel("Se agrego el siguiente registro:");
		lbTabla.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbTabla.setBounds(384, 202, 201, 14);
		panel.add(lbTabla);
		
		JScrollPane spTabla = new JScrollPane();
		spTabla.setBounds(184, 227, 590, 79);
		panel.add(spTabla);
		
		jtRegistro = new JTable();
		spTabla.setViewportView(jtRegistro);
		
		JLabel lblLkm = new JLabel("l/100km");
		lblLkm.setBounds(181, 136, 46, 14);
		panel.add(lblLkm);
		
		JLabel lblGco2 = new JLabel("gCO2/km");
		lblGco2.setBounds(181, 191, 46, 14);
		panel.add(lblGco2);
	}
}