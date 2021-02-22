package umu.tds.apps.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;

public class Reciente extends JPanel {

	private JTable table;
	private JScrollPane scrollPane;
	private ControladorAppMusic controlador;
	private JFrame ventana;
	private LinkedList<Cancion> recientes;	// LinkedList??
	
	/**
	 * Create the panel.
	 */
	public Reciente(JFrame ventana) {
		this.ventana = ventana;
		controlador = ControladorAppMusic.getUnicaInstancia();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(400, 525));
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 25));
		panel_1.add(rigidArea);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Cancion c = recientes.get(table.getSelectedRow());
				Principal p = Principal.getInstancia();
				p.setCurrentSong(c);
				
			}
		});
		
		loadRecientes();

	}
	
	
	private void loadRecientes() {
		recientes = (LinkedList<Cancion>) controlador.getCancionesRecientes();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);  // Borra el contenido previo
		for (Cancion c : recientes) {
			Vector<String> v = new Vector<>(); 
			v.add(c.getTitulo());
			v.add(c.getInterprete());
			dtm.addRow(v);
			ventana.repaint();
			ventana.revalidate();
		}
	}

}
