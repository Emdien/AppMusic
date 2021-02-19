package umu.tds.apps.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.border.LineBorder;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.ListaReproduccion;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.DocumentException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import java.awt.Point;

public class Principal {
	
	// Referencia para que otros paneles puedan hacer llamadas a la ventana principal
	private static Principal instanciaPrincipal;

	private JFrame frame;
	private JPanel header_panel;
	private JLabel lblUsuario;
	private JButton btnMejorar;
	private JPanel body_panel;
	private JPanel sidebar_panel;
	private JPanel sidebar_buttons;
	private JButton btnExplorar;
	private JButton btnNuevaLista;
	private JButton btnReciente;
	private JButton btnMisListas;
	private JPanel sidebar_playlists;
	private JPanel action_panel;
	private JButton btnPrevious;
	private JButton btnPlay;
	private JButton btnNext;
	private JPanel content_panel;
	private JPanel current_content;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnMasReproducidas;
	private JButton btnImprimir;
	
	
	private ControladorAppMusic controlador;
	private LinkedList<ListaReproduccion> playlists;
	
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Principal() {
		this.controlador = ControladorAppMusic.getUnicaInstancia();
		initialize();
		this.frame.setVisible(true);
		instanciaPrincipal = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		header_panel = new JPanel();
		header_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) header_panel.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setAlignment(FlowLayout.TRAILING);
		header_panel.setMinimumSize(new Dimension(10, 50));
		header_panel.setMaximumSize(new Dimension(32767, 50));
		header_panel.setPreferredSize(new Dimension(10, 50));
		frame.getContentPane().add(header_panel);
		
		JButton btnLogout = new JButton("Cerrar sesi\u00F3n");
		btnLogout.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Logout.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.logout();
				Login windowL = new Login();
				frame.dispose();
			}
			
			
		});
		
		lblUsuario = new JLabel();
		lblUsuario.setText(controlador.getUsuarioActual().getNombre());
		header_panel.add(lblUsuario);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea_1);
		
		btnMejorar = new JButton("Mejorar tu cuenta");
		btnMejorar.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Premium.png")));
		btnMejorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(false);
				MejorarCuenta panel_mc = new MejorarCuenta(frame);
				body_panel.remove(current_content);
				body_panel.add(panel_mc, BorderLayout.CENTER);
				current_content = panel_mc;
				frame.repaint();
				frame.revalidate();
				//System.out.println("test");
			}
		});
		header_panel.add(btnMejorar);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea);
		header_panel.add(btnLogout);
		
		body_panel = new JPanel();
		frame.getContentPane().add(body_panel);
		body_panel.setLayout(new BorderLayout(0, 0));
		
		sidebar_panel = new JPanel();
		sidebar_panel.setMinimumSize(new Dimension(225, 10));
		sidebar_panel.setMaximumSize(new Dimension(225, 32767));
		sidebar_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		sidebar_panel.setPreferredSize(new Dimension(225, 10));
		body_panel.add(sidebar_panel, BorderLayout.WEST);
		sidebar_panel.setLayout(new BorderLayout(0, 0));
		
		sidebar_buttons = new JPanel();
		sidebar_buttons.setMinimumSize(new Dimension(225, 300));
		sidebar_buttons.setPreferredSize(new Dimension(225, 300));
		sidebar_buttons.setMaximumSize(new Dimension(225, 300));
		sidebar_panel.add(sidebar_buttons, BorderLayout.NORTH);
		sidebar_buttons.setLayout(new BoxLayout(sidebar_buttons, BoxLayout.Y_AXIS));
		
		btnExplorar = new JButton("Explorar");
		btnExplorar.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Search.png")));
		btnExplorar.setPreferredSize(new Dimension(225, 50));
		btnExplorar.setMinimumSize(new Dimension(225, 50));
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(false);
				Explorar panel_exp = new Explorar(frame);
				body_panel.remove(current_content);
				body_panel.add(panel_exp, BorderLayout.CENTER);
				current_content = panel_exp;
				frame.repaint();
				frame.revalidate();	
			}
		});
		btnExplorar.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnExplorar);
		
		btnNuevaLista = new JButton("Nueva lista");
		btnNuevaLista.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/NewList.png")));
		btnNuevaLista.setPreferredSize(new Dimension(225, 50));
		btnNuevaLista.setMinimumSize(new Dimension(225, 50));
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(false);
				NuevaLista panel_nl = new NuevaLista(frame);
				body_panel.remove(current_content);
				body_panel.add(panel_nl, BorderLayout.CENTER);
				current_content = panel_nl;
				frame.repaint();
				frame.revalidate();
			}
		});
		btnNuevaLista.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnNuevaLista);
		
		btnReciente = new JButton("Reciente");
		btnReciente.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Recent.png")));
		btnReciente.setPreferredSize(new Dimension(225, 50));
		btnReciente.setMinimumSize(new Dimension(225, 50));
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(false);
				Reciente panel_rec = new Reciente();
				body_panel.remove(current_content);
				body_panel.add(panel_rec, BorderLayout.CENTER);
				current_content = panel_rec;
				frame.repaint();
				frame.revalidate();
			}
		});
		btnReciente.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnReciente);
		
		btnMisListas = new JButton("Mis listas");
		btnMisListas.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/MyLists.png")));
		btnMisListas.setPreferredSize(new Dimension(225, 50));
		btnMisListas.setMinimumSize(new Dimension(225, 50));
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (update_playlist_list()) {				// Existen listas
					list.setSelectedIndex(0);
					MisListas panel_ml = new MisListas(frame);
					panel_ml.update_selected_playlist(list.getSelectedIndex());
					body_panel.remove(current_content);
					body_panel.add(panel_ml, BorderLayout.CENTER);
					current_content = panel_ml;
					frame.repaint();
					frame.revalidate();
					
				} else {
					
					JOptionPane.showMessageDialog(frame,
							"No existe ninguna playlist actualmente",
							"Error", JOptionPane.ERROR_MESSAGE);
					
					
					
				}
				
				
			}
		});
		btnMisListas.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnMisListas);
		
		btnMasReproducidas = new JButton("Canciones mas reproducidas");
		btnMasReproducidas.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Popular.png")));
		btnMasReproducidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(false);
				MasReproducidas panel_mr = new MasReproducidas();
				body_panel.remove(current_content);
				body_panel.add(panel_mr, BorderLayout.CENTER);
				current_content = panel_mr;
				frame.repaint();
				frame.revalidate();
				
			}
		});
		btnMasReproducidas.setEnabled(false);
		btnMasReproducidas.setMinimumSize(new Dimension(225, 50));
		btnMasReproducidas.setMaximumSize(new Dimension(225, 50));
		btnMasReproducidas.setPreferredSize(new Dimension(225, 50));
		sidebar_buttons.add(btnMasReproducidas);
		
		btnImprimir = new JButton("Imprimir Playlists");
		btnImprimir.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/Print.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Selecciona carpeta de destino");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					System.out.println(path);
					try {
						controlador.printPDF(path);
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setPreferredSize(new Dimension(225, 50));
		btnImprimir.setMinimumSize(new Dimension(225, 50));
		btnImprimir.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnImprimir);
		
		sidebar_playlists = new JPanel();
		sidebar_panel.add(sidebar_playlists, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setPreferredSize(new Dimension(225, 240));
		sidebar_playlists.add(scrollPane);
		
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				MisListas panel_ml = new MisListas(frame);
				panel_ml.update_selected_playlist(list.getSelectedIndex());
				body_panel.remove(current_content);
				body_panel.add(panel_ml, BorderLayout.CENTER);
				current_content = panel_ml;
				frame.repaint();
				frame.revalidate();
			}
		});
		DefaultListModel<String> model = new DefaultListModel<String>();
		list.setModel(model);
		scrollPane.setViewportView(list);
		
		action_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) action_panel.getLayout();
		flowLayout_1.setVgap(13);
		action_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		action_panel.setPreferredSize(new Dimension(10, 65));
		body_panel.add(action_panel, BorderLayout.SOUTH);
		
		btnPrevious = new JButton("");
		btnPrevious.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/PreviousButton.png")));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPrevious);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_2);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/PlayButton.png")));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPlay);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_3);
		
		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/NextButton.png")));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnNext);
		
		content_panel = new JPanel();
		content_panel.setMaximumSize(new Dimension(975, 32767));
		body_panel.add(content_panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("975 x 585");
		content_panel.add(lblNewLabel);
		
		current_content = content_panel;
		enablePremiumFeatures();
	}
	
	
	public boolean update_playlist_list() {
		playlists = (LinkedList<ListaReproduccion>) controlador.getAllListasReproduccion();
		if (playlists != null) {
			DefaultListModel<String> dlm = new DefaultListModel<String>();
			
			for (ListaReproduccion lr : playlists) {
				String nombrePlaylist = lr.getNombre();
				dlm.addElement(nombrePlaylist);
			}
			list.setModel(dlm);
			scrollPane.setVisible(true);
			return true;
		} else return false;
	}
	
	public void enablePremiumFeatures() {
		if (controlador.getUsuarioActual().isPremium()) {
			btnImprimir.setEnabled(true);
			btnMasReproducidas.setEnabled(true);
		}
	}
	
	public static Principal getInstancia() {
		return instanciaPrincipal;
	}
	
	
}
