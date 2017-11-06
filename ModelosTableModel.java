package net.juanxxiii.j23guiappframework.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Extension de TableModel para darle el modelo que queremos
 * 
 * @author Sergio Torres Garcia
 *
 */
public class ModelosTableModel implements TableModel {
	private ArrayList<Modelo> modelos;

	/**
	 * Constructor
	 * 
	 * @param modelos
	 */
	public ModelosTableModel(ArrayList<Modelo> modelos) {
		this.modelos = modelos;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0){
			return String.class;
		} else if(columnIndex==1){
			return String.class;
		} else if(columnIndex==2){
			return Float.class;
		} else if(columnIndex==3){
			return Float.class;
		} else {
			return ImageIcon.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "MARCA";
		} else if (columnIndex == 1) {
			return "MODELO";
		} else if (columnIndex == 2) {
			return "CONSUMO - l/100km";
		} else if (columnIndex==3){
			return "EMISIONES - gCO2/km";
		} else{
			return "C_ENERGETICA";
		}
	}

	@Override
	public int getRowCount() {
		return modelos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Modelo model = modelos.get(rowIndex);
		String marca = null;
		GestorModelos g1 = new GestorModelos();

		if (columnIndex == 0) {
			try {
				marca = g1.getUnaMarca(model.getId_marca());
			} catch (SQLException e2) {
				if(e2.getErrorCode()==0){
					JOptionPane.showMessageDialog(null, "Error en la conexion.");
				}else if(e2.getErrorCode()==1045){
					JOptionPane.showMessageDialog(null, "Error al iniciar sesion.");
				}else if (e2.getErrorCode()==1049){
					JOptionPane.showMessageDialog(null, "Error al encontrar la Base de Datos.");
				}else {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos.");
				}
				e2.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
				e.printStackTrace();
			}
			return marca;
		} else if (columnIndex == 1) {
			return model.getNombre();
		} else if (columnIndex == 2) {
			return model.getConsumo();
		} else if (columnIndex==3){
			return model.getEmisiones();
		} else{
			String icono = null;
			try {
				icono = g1.getIcono(model.getC_energ());
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
				e.printStackTrace();
			} catch (SQLException e2) {
				if(e2.getErrorCode()==0){
					JOptionPane.showMessageDialog(null, "Error en la conexion.");
				}else if(e2.getErrorCode()==1045){
					JOptionPane.showMessageDialog(null, "Error al iniciar sesion.");
				}else if (e2.getErrorCode()==1049){
					JOptionPane.showMessageDialog(null, "Error al encontrar la Base de Datos.");
				}else {
					JOptionPane.showMessageDialog(null, "Error en la Base de Datos.");
				}
			}
			return new ImageIcon(ModelosTableModel.class.getResource("/iconosclasificacionenergetica/"+icono));
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

}
