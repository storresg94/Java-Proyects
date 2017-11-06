package net.juanxxiii.j23guiappframework.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Clase Frame principal de la aplicacion.
 * 
 * @author Sergio Torres Garcia
 *
 */
public class GestMotor extends JFrame {
	private JPanel contentPane;

	/**
	 * Ejecuta la aplicacion.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestMotor frame = new GestMotor();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Frame de la aplicacion
	 */
	public GestMotor() {
		getContentPane().setLayout(new CardLayout(0, 0));
		setTitle("Sergio Torres Garcia - Juan XXIII");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setResizable(false); //--> Esto impide que se puedan redimensionar las pantallas.
		
		//CAMBIO DE LOOK&FEEL A WINDOWS
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnExit.add(mntmExit);
		
		JMenu mnPanel = new JMenu("Panel");
		menuBar.add(mnPanel);
		
		JMenuItem mntmPanel_1 = new JMenuItem("Agregar Modelos");
		mntmPanel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, "Pantalla1");
			}
		});
		mnPanel.add(mntmPanel_1);
		
		JMenuItem mntmPanel = new JMenuItem("Consultar por Marca/Borrar Modelos");
		mntmPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, "Pantalla2");
			}
		});
		mnPanel.add(mntmPanel);
		
		JMenuItem mntmPanel_2 = new JMenuItem("Consultar por Consumo");
		mntmPanel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)contentPane.getLayout();
				cl.show(contentPane, "Pantalla3");
			}
		});
		mnPanel.add(mntmPanel_2);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(GestMotor.this,
					    "Practica Programacion 1ºDAM - By Sergio Torres.",
					    "Juan XXIII - 2017",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		contentPane.add(new Portada(),"Portada");
		contentPane.add(new Panel1(),"Pantalla1");
		contentPane.add(new Panel2(),"Pantalla2");
		contentPane.add(new Panel3(),"Pantalla3");
	}

}
