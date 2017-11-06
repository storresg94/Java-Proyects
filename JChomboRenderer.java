package net.juanxxiii.j23guiappframework.persistencia;

import java.awt.Component;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import net.juanxxiii.j23guiappframework.model.GestorModelos;

/**
 * Clase render de JComboBox.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class JChomboRenderer extends JLabel implements ListCellRenderer {

	GestorModelos g1 = new GestorModelos();
	private ArrayList<ImageIcon> iconos;

	/**
	 * Constructor.
	 * 
	 * @param items2
	 */
	public JChomboRenderer(ArrayList<ImageIcon> items2) {
		setOpaque(true);
		this.iconos = items2;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		int selectedIndex = ((Integer) value).intValue();

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		ImageIcon icon = this.iconos.get(selectedIndex);
		setIcon(icon);
		File f = new File(iconos.get(selectedIndex).toString());
		String fi = f.getName();//getName() devuelve la ruta y nombre de la imagen utilizada.
		try {
			setText(g1.getUnaMarca(Integer.parseInt(fi.substring(fi.lastIndexOf('/') + 1, fi.lastIndexOf('.')))));
			//Pongo como texto del icono la marca que corresponde al id que me devuelve el coger un substring del nombre de la imagen
			//empezando por la ultima "/" que encuentra y terminando en el "." por lo que coge unicamente el numero de la imagen.
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + e.getErrorCode());
			e.printStackTrace();
		}
		return this;
	}
}