package umu.tds.apps.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;

public class MasReproducidas extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JTable table;
	private JScrollPane scrollPane;
	private ControladorAppMusic controlador;
	private JFrame ventana;
	private ArrayList<Cancion> populares;
	
	public MasReproducidas(JFrame ventana) {
		controlador = ControladorAppMusic.getUnicaInstancia();
		this.ventana = ventana;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(500, 525));
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 25));
		panel_1.add(rigidArea);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 500));
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete", "Reproducciones"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setMinWidth(200);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Cancion c = populares.get(table.getSelectedRow());
				Principal p = Principal.getInstancia();
				p.setCurrentSong(c);
				
			}
		});
		
		loadPopulares();
	}
	
	
	private void loadPopulares() {
		populares = (ArrayList<Cancion>) controlador.getCancionesMasReproducidas();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);  // Borra el contenido previo
		for (Cancion c : populares) {
			Vector<String> v = new Vector<>(); 
			v.add(c.getTitulo());
			v.add(c.getInterprete());
			v.add(c.getNumReproducciones().toString());
			dtm.addRow(v);
			ventana.repaint();
			ventana.revalidate();
		}
	}

}
