package umu.tds.apps.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;

public class MisListas extends JPanel {
	private JTable table;
	private ControladorAppMusic controlador;
	private LinkedList<ListaReproduccion> playlists; 
	private ListaReproduccion playlist_actual;
	private JFrame ventana;
	
	
	public MisListas(JFrame ventana) {
		this.ventana = ventana;
		controlador = ControladorAppMusic.getUnicaInstancia();
		playlists = (LinkedList<ListaReproduccion>) controlador.getAllListasReproduccion();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(400, 525));
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setPreferredSize(new Dimension(20, 25));
		panel_1.add(rigidArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 500));
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		table.setPreferredSize(new Dimension(400, 477));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int indiceCancion = table.getSelectedRow();
				Principal p = Principal.getInstancia();
				p.setCurrentPlaylist(playlist_actual, indiceCancion);
				
			}
		});
		
		
	}
	
	public void update_selected_playlist(int value) {
		if (value == -1) return;
		playlist_actual = playlists.get(value);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Cancion c : playlist_actual.getCanciones()) {
			Vector<String> v = new Vector<>();
			v.add(c.getTitulo());
			v.add(c.getInterprete());
			dtm.addRow(v);
		}
		ventana.repaint();
		ventana.revalidate();
	}

}
