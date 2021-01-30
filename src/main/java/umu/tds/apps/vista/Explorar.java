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

public class Explorar extends JPanel {
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTextField txtEstilo;
	private JTable table;
	private ControladorAppMusic controlador;
	private ArrayList<Cancion> resultadoBusqueda = new ArrayList<>();

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
		
		txtEstilo = new JTextField();
		panel.add(txtEstilo);
		txtEstilo.setColumns(10);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel.add(rigidArea_2);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);  // Borra el contenido previo
				resultadoBusqueda = new ArrayList<>();
				resultadoBusqueda = (ArrayList<Cancion>) controlador.buscarCanciones(txtTitulo.getText(), txtInterprete.getText(), txtEstilo.getText());	// Arreglar para que no haga casting?
				for (Cancion c : resultadoBusqueda) {
					System.out.println(c.getTitulo());
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 500));
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		table.setPreferredSize(new Dimension(400, 477));
		scrollPane.setViewportView(table);

	}
	


}
