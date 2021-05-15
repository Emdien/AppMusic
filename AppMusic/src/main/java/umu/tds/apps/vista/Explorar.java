package umu.tds.apps.vista;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import java.awt.Color;

public class Explorar extends JPanel {
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private ControladorAppMusic controlador;
	private ArrayList<Cancion> resultadoBusqueda = new ArrayList<>();
	private JScrollPane scrollPane;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public Explorar(JFrame ventana) {
		controlador = ControladorAppMusic.getUnicaInstancia();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		panel.setMinimumSize(new Dimension(10, 30));
		panel.setMaximumSize(new Dimension(975, 40));
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Interprete");
		panel.add(lblNewLabel);
		
		txtInterprete = new JTextField();
		panel.add(txtInterprete);
		txtInterprete.setColumns(15);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo");
		panel.add(lblNewLabel_1);
		
		txtTitulo = new JTextField();
		panel.add(txtTitulo);
		txtTitulo.setColumns(15);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estilo");
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setPreferredSize(new Dimension(120, 25));
		comboBox.setMinimumSize(new Dimension(120, 25));
		comboBox.addItem("");
		for (String estilo : controlador.getEstilos()) {
			comboBox.addItem(estilo);
		}
		panel.add(comboBox);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_2);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);  // Borra el contenido previo
				resultadoBusqueda = new ArrayList<>();
				resultadoBusqueda = (ArrayList<Cancion>) controlador.buscarCanciones(txtTitulo.getText(), txtInterprete.getText(), comboBox.getSelectedItem().toString());	// Arreglar para que no haga casting?
				for (Cancion c : resultadoBusqueda) {
					//System.out.println(c.getTitulo());
					Vector<String> v = new Vector<>(); 
					v.add(c.getTitulo());
					v.add(c.getInterprete());
					dtm.addRow(v);
					ventana.repaint();
					ventana.revalidate();
				}
				
			}
		});
		btnNewButton.setPreferredSize(new Dimension(100, 26));
		btnNewButton.setMinimumSize(new Dimension(100, 26));
		btnNewButton.setMaximumSize(new Dimension(100, 26));
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(975, 550));
		add(panel_1);
		
		scrollPane = new JScrollPane();
		//scrollPane.setMaximumSize(new Dimension(400, 500));
		//scrollPane.setMinimumSize(new Dimension(400, 500));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setPreferredSize(new Dimension(400, 500));
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
		//table.setPreferredSize(new Dimension(400, 477));
		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Cancion c = resultadoBusqueda.get(table.getSelectedRow());
				Principal p = Principal.getInstancia();
				p.setCurrentSong(c);
				
			}
		});

	}
	


}
