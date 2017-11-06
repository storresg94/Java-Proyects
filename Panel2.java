package net.juanxxiii.j23guiappframework.gui;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.CardLayout;

import javax.swing.JTable;

import net.juanxxiii.j23guiappframework.model.GestorModelos;
import net.juanxxiii.j23guiappframework.model.Marca;
import net.juanxxiii.j23guiappframework.model.Modelo;
import net.juanxxiii.j23guiappframework.model.ModelosTableModel;
import net.juanxxiii.j23guiappframework.persistencia.JChomboBox;
import net.juanxxiii.j23guiappframework.persistencia.JChomboRenderer;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

/**
 * Panel de consulta por marca y de borrado de modelos.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class Panel2 extends JPanel {
	private static final String RUTA_SEARCH = "/imagenes/Search.jpg";
	private JTable jtMarcas;

	/**
	 * Getter de RutaSearch.
	 * 
	 * @return Ruta
	 */
	public static String getRutaSearch() {
		return RUTA_SEARCH;
	}

	/**
	 * Panel de consulta por marca y de borrado de modelos.
	 */
	public Panel2() {
		setLayout(null);
		setLayout(null);
		GestorModelos g1 = new GestorModelos();// Creo un objeto de
												// GestorModelos para poder usar
												// sus metodos.

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		setBackground(SystemColor.inactiveCaption);

		JChomboBox jcbMarcas;
		// Creo un ArrayList de ImageIcon para poder introducir por consulta los
		// iconos de cada marca.
		ArrayList<ImageIcon> items = new ArrayList<>();
		try {
			for (Integer id : g1.getIDMarca()) {
				items.add(new ImageIcon(getClass().getResource("/iconosmarcas/" + id + ".jpg")));
			}
		} catch (SQLException e2) {
			if (e2.getErrorCode() == 0) {
				JOptionPane.showMessageDialog(Panel2.this, "Error en la conexion.");
			} else if (e2.getErrorCode() == 1045) {
				JOptionPane.showMessageDialog(Panel2.this, "Error al iniciar sesion.");
			} else if (e2.getErrorCode() == 1049) {
				JOptionPane.showMessageDialog(Panel2.this, "Error al encontrar la Base de Datos.");
			} else {
				JOptionPane.showMessageDialog(Panel2.this, "Error en la Base de Datos.");
			}
			e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(Panel2.this, "Clase no encontrada.");
			e1.printStackTrace();
		}

		jcbMarcas = new JChomboBox(items.size());
		jcbMarcas.setBounds(23, 0, 180, 55);
		JChomboRenderer render = new JChomboRenderer(items);
		jcbMarcas.setRenderer(render);
		this.add(jcbMarcas);

		JScrollPane spTabla = new JScrollPane();
		spTabla.setBounds(10, 60, 716, 363);
		add(spTabla);

		jtMarcas = new JTable();
		jtMarcas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spTabla.setViewportView(jtMarcas);

		JButton jbBusquedaMarca = new JButton("");
		jbBusquedaMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Modelo> modelos = new ArrayList<>();
				ArrayList<Marca> mark = new ArrayList<>();
				// Guardo en modelos los nombres de las marcas que estan
				// contenidas en el text del render.

				try {
					mark = g1.getMarca();
					Marca marc = mark.get(jcbMarcas.getSelectedIndex());
					String marca = marc.getNombre();
					modelos = (g1.consultarModeloMarca(g1.consultaIDMarca(marca)));
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(Panel2.this, "Clase no encontrada.");
					e1.printStackTrace();
				} catch (SQLException e2) {
					if (e2.getErrorCode() == 0) {
						JOptionPane.showMessageDialog(Panel2.this, "Error en la conexion.");
					} else if (e2.getErrorCode() == 1045) {
						JOptionPane.showMessageDialog(Panel2.this, "Error al iniciar sesion.");
					} else if (e2.getErrorCode() == 1049) {
						JOptionPane.showMessageDialog(Panel2.this, "Error al encontrar la Base de Datos.");
					} else {
						JOptionPane.showMessageDialog(Panel2.this, "Error en la Base de Datos.");
					}
					e2.printStackTrace();
				}
				jtMarcas.setModel(new ModelosTableModel(modelos));
				JOptionPane.showMessageDialog(Panel2.this, "Se cargaron " + modelos.size() + " registros.");
			}
		});
		jbBusquedaMarca.setBounds(658, 0, 55, 55);
		jbBusquedaMarca.setIcon(new ImageIcon(RUTA_SEARCH));
		add(jbBusquedaMarca);

		JButton jbBorrar = new JButton("BORRAR");
		jbBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Modelo> modelos = new ArrayList<>();
				// Guardo en modelos los nombres de las marcas que estan
				// contenidas en el text del render.
				String modelo = (jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 1)).toString();
				Float consumo = (Float) (jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 2));
				Float emisiones = (Float) (jtMarcas.getValueAt(jtMarcas.getSelectedRow(), 3));
				ArrayList<Marca> mark = new ArrayList<>();
				int id = 0;

				try {
					id = g1.consultaIDBorrar(modelo, consumo, emisiones);
					g1.borrarModelo(id);
					mark = g1.getMarca();
					Marca marc = mark.get(jcbMarcas.getSelectedIndex());
					String marca = marc.getNombre();
					modelos = (g1.consultarModeloMarca(g1.consultaIDMarca(marca)));
					JOptionPane.showMessageDialog(Panel2.this, "El modelo fue borrado correctamente.");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(Panel2.this, "Clase no encontrada.");
					e.printStackTrace();
				} catch (SQLException e2) {
					if (e2.getErrorCode() == 0) {
						JOptionPane.showMessageDialog(Panel2.this, "Error en la conexion.");
					} else if (e2.getErrorCode() == 1045) {
						JOptionPane.showMessageDialog(Panel2.this, "Error al iniciar sesion.");
					} else if (e2.getErrorCode() == 1049) {
						JOptionPane.showMessageDialog(Panel2.this, "Error al encontrar la Base de Datos.");
					} else {
						JOptionPane.showMessageDialog(Panel2.this, "Error en la Base de Datos.");
					}
					e2.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(Panel2.this, "No has seleccionado ningun registro para borrar.");
					e.printStackTrace();
				}
				jtMarcas.setModel(new ModelosTableModel(modelos));
			}
		});
		jbBorrar.setBounds(559, 11, 89, 23);
		add(jbBorrar);
	}
}