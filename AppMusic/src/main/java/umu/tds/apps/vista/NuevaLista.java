package umu.tds.apps.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.Color;

public class NuevaLista extends JPanel {

	/**
	 * Create the panel.
	 */
	private ListaReproduccion playlist;
	private ControladorAppMusic controlador;
	private ArrayList<Cancion> resultadoBusqueda = new ArrayList<>();
	private ArrayList<Cancion> cancionesPlaylist = new ArrayList<>();
	
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable tableCanciones;
	private JTable tablePlaylist;
	private JTextField txtNombre;
	private JButton btnCrear;
	private JButton btnEliminar;
	private JPanel panelBusqueda;
	private JButton btnBuscar;
	private JPanel panelContenido;
	private JPanel panelCanciones;
	private JScrollPane scrollPaneCanciones;
	private JPanel panelPlaylist;
	private JScrollPane scrollPanePlaylist;
	private JPanel panelCommand;
	private JComboBox comboBox;

	
	public NuevaLista(JFrame ventana) {
		
		controlador = ControladorAppMusic.getUnicaInstancia();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelCreate = new JPanel();
		panelCreate.setPreferredSize(new Dimension(975, 40));
		panelCreate.setMinimumSize(new Dimension(975, 40));
		panelCreate.setMaximumSize(new Dimension(975, 40));
		add(panelCreate);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre de playlist");
		panelCreate.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		panelCreate.add(txtNombre);
		txtNombre.setColumns(15);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panelCreate.add(rigidArea_5);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtNombre.getText();
				if(nombre.equals("")) {
					JOptionPane.showMessageDialog(ventana,
							"Nombre de playlist no valido",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				panelBusqueda.setVisible(true);
				panelContenido.setVisible(true);
				panelCommand.setVisible(true);
				
				
				
				playlist =  controlador.crearNuevaLista(nombre);
				cancionesPlaylist = playlist.getCanciones();
				
				// Comprobar si la playlist es nueva. Si no es nueva, sacar un JDialog indicando que se va a realizar modificación. Ademas, se activa el boton de Eliminar
				// En ambos casos, guardo la playlist Y la lista de sus canciones para que puedan ser accesibles.
				
				if (!cancionesPlaylist.isEmpty()) {
					// JDialog indicando que se va a modificar la playlist
					// Mover esto a una funcion? Codigo repetido.
					
					JOptionPane.showMessageDialog(ventana,
							"Playlist ya creada. Se va a proceder a modificarla",
							"Aviso", JOptionPane.WARNING_MESSAGE);
					
					DefaultTableModel dtm = (DefaultTableModel) tablePlaylist.getModel();
					dtm.setRowCount(0);
					for (Cancion c : cancionesPlaylist) {
						Vector<String> v = new Vector<>();
						v.add(c.getTitulo());
						v.add(c.getInterprete());
						dtm.addRow(v);
					}
					btnEliminar.setVisible(true);
					ventana.repaint();
					ventana.revalidate();
				}
							
				
			}
		});
		panelCreate.add(btnCrear);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panelCreate.add(rigidArea_6);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVisible(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				controlador.removeListaReproduccion(playlist);
				resultadoBusqueda = new ArrayList<>();
				cancionesPlaylist = new ArrayList<>();
				cleanFields();
				
				
				
			}
		});
		panelCreate.add(btnEliminar);
		
		panelBusqueda = new JPanel();
		panelBusqueda.setVisible(false);
		panelBusqueda.setPreferredSize(new Dimension(10, 40));
		panelBusqueda.setMinimumSize(new Dimension(10, 40));
		panelBusqueda.setMaximumSize(new Dimension(975, 40));
		add(panelBusqueda);
		
		JLabel lblNewLabel = new JLabel("Interprete");
		panelBusqueda.add(lblNewLabel);
		
		txtInterprete = new JTextField();
		panelBusqueda.add(txtInterprete);
		txtInterprete.setColumns(15);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panelBusqueda.add(rigidArea);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo");
		panelBusqueda.add(lblNewLabel_1);
		
		txtTitulo = new JTextField();
		panelBusqueda.add(txtTitulo);
		txtTitulo.setColumns(15);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panelBusqueda.add(rigidArea_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estilo");
		panelBusqueda.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setPreferredSize(new Dimension(120, 25));
		comboBox.setMinimumSize(new Dimension(120, 25));
		comboBox.addItem("");
		for (String estilo : controlador.getEstilos()) {
			comboBox.addItem(estilo);
		}
		panelBusqueda.add(comboBox);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panelBusqueda.add(rigidArea_2);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm = (DefaultTableModel) tableCanciones.getModel();
				dtm.setRowCount(0);
				resultadoBusqueda = new ArrayList<>();
				resultadoBusqueda = (ArrayList<Cancion>) controlador.buscarCanciones(txtTitulo.getText(), txtInterprete.getText(), comboBox.getSelectedItem().toString());				
				
				Iterator<Cancion> it = resultadoBusqueda.iterator();
				
				while (it.hasNext()) {
					Cancion c = it.next();
					if (cancionesPlaylist.contains(c)) {
						it.remove();
						
					} else {
						Vector<String> v = new Vector<>();
						v.add(c.getTitulo());
						v.add(c.getInterprete());
						dtm.addRow(v);
					}
				}
				ventana.repaint();
				ventana.revalidate();
				
				
			}
		});
		btnBuscar.setPreferredSize(new Dimension(100, 26));
		btnBuscar.setMinimumSize(new Dimension(100, 26));
		btnBuscar.setMaximumSize(new Dimension(100, 26));
		panelBusqueda.add(btnBuscar);
		
		panelContenido = new JPanel();
		panelContenido.setVisible(false);
		panelContenido.setMaximumSize(new Dimension(975, 470));
		add(panelContenido);
		
		panelCanciones = new JPanel();
		panelCanciones.setBorder(new TitledBorder(null, "Canciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContenido.add(panelCanciones);
		
		scrollPaneCanciones = new JScrollPane();
		scrollPaneCanciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCanciones.add(scrollPaneCanciones);
		scrollPaneCanciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCanciones.setPreferredSize(new Dimension(400, 390));
		
		tableCanciones = new JTable();
		tableCanciones.setFillsViewportHeight(true);
		tableCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCanciones.setDefaultEditor(Object.class, null);
		tableCanciones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		scrollPaneCanciones.setViewportView(tableCanciones);
		
		JPanel panel_4 = new JPanel();
		panelContenido.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JButton btnAdd = new JButton("> >");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tableCanciones.getSelectedRow();
				
				if (row != -1) {
					Cancion c = resultadoBusqueda.get(row);
					cancionesPlaylist.add(c);
					DefaultTableModel dtm = (DefaultTableModel) tablePlaylist.getModel();
					Vector<String> v = new Vector<>();
					v.add(c.getTitulo());
					v.add(c.getInterprete());
					dtm.addRow(v);
					
					resultadoBusqueda.remove(row);
					DefaultTableModel dtmc = (DefaultTableModel) tableCanciones.getModel();
					dtmc.removeRow(row);
					
					ventana.repaint();
					ventana.revalidate();
				}
				
				
				
				
			}
		});
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnAdd);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_4.add(rigidArea_4);
		
		JButton btnRemove = new JButton("< <");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tablePlaylist.getSelectedRow();
				
				if (row != -1) {
					
					// Extraigo la cancion seleccionada. La añado a la tabla opuesta y a su correspondiente lista
					// Elimino la entrada de la tabla, asi como la entrada en la lista
					// Recargo la ventana
					
					
					Cancion c = cancionesPlaylist.get(row);
					resultadoBusqueda.add(c);
					DefaultTableModel dtm = (DefaultTableModel) tableCanciones.getModel();
					Vector<String> v = new Vector<>();
					v.add(c.getTitulo());
					v.add(c.getInterprete());
					dtm.addRow(v);
					
					cancionesPlaylist.remove(row);
					DefaultTableModel dtmp = (DefaultTableModel) tablePlaylist.getModel();
					dtmp.removeRow(row);
					
					ventana.repaint();
					ventana.revalidate();
					
				}
				
				
				
			}
		});
		btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(btnRemove);
		
		panelPlaylist = new JPanel();
		panelPlaylist.setBorder(new TitledBorder(null, "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelContenido.add(panelPlaylist);
		
		scrollPanePlaylist = new JScrollPane();
		scrollPanePlaylist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelPlaylist.add(scrollPanePlaylist);
		scrollPanePlaylist.setPreferredSize(new Dimension(400, 390));
		
		tablePlaylist = new JTable();
		tablePlaylist.setFillsViewportHeight(true);
		tablePlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePlaylist.setDefaultEditor(Object.class, null);
		tablePlaylist.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titulo", "Interprete"
			}
		));
		tablePlaylist.getColumnModel().getColumn(0).setPreferredWidth(76);
		scrollPanePlaylist.setViewportView(tablePlaylist);
		
		panelCommand = new JPanel();
		panelCommand.setVisible(false);
		panelCommand.setPreferredSize(new Dimension(10, 40));
		panelCommand.setMinimumSize(new Dimension(10, 40));
		add(panelCommand);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.guardarLista(playlist, cancionesPlaylist);
				cleanFields();
				
				
			}
		});
		panelCommand.add(btnAceptar);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panelCommand.add(rigidArea_3);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cleanFields();
				
				
			}
		});
		panelCommand.add(btnCancelar);
	}
	
	
	private void cleanFields() {
		DefaultTableModel dtm = (DefaultTableModel) tablePlaylist.getModel();
		dtm.setRowCount(0);
		
		dtm = (DefaultTableModel) tableCanciones.getModel();
		dtm.setRowCount(0);
		
		txtInterprete.setText("");
		txtTitulo.setText("");
		txtNombre.setText("");
		panelBusqueda.setVisible(false);
		panelContenido.setVisible(false);
		panelCommand.setVisible(false);
		btnEliminar.setVisible(false);
		
	}

}
