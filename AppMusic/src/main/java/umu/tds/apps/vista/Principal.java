package umu.tds.apps.vista;



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
import java.io.File;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.Box;

import umu.tds.apps.controlador.ControladorAppMusic;
import umu.tds.apps.modelo.Cancion;
import umu.tds.apps.modelo.ListaReproduccion;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.DocumentException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import pulsador.Luz;
import pulsador.IEncendidoListener;
import java.util.EventObject;

public class Principal implements ActionListener{
	
	
	private static final int PAUSED = 0;
	private static final int PLAYING = 1;
	private int estadoReproductor = PAUSED;
	private static final String IMG_SRC= "/umu/tds/apps/images/";
	
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
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblCancionSeleccionada;
	private JLabel lblSelectedSong;
	private JLabel lblSelectedArtist;
	private JButton btnLogout;
	
	
	private ControladorAppMusic controlador;
	private LinkedList<ListaReproduccion> playlists;
	private Cancion cancionActual;
	private ListaReproduccion playlistActual;
	private int playlistIndex;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private Component rigidArea_4;
	private Component rigidArea_5;
	private Component rigidArea_6;
	private JLabel lblNewLabel_8;
	private Component rigidArea_7;
	private JLabel lblNewLabel_9;
	private Component rigidArea_8;
	private JLabel lblNewLabel_10;
	private Luz luz;
	private Component rigidArea_9;
	


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
		header_panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) header_panel.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setAlignment(FlowLayout.TRAILING);
		header_panel.setMinimumSize(new Dimension(10, 50));
		header_panel.setMaximumSize(new Dimension(32767, 50));
		header_panel.setPreferredSize(new Dimension(10, 50));
		frame.getContentPane().add(header_panel);
		
		btnLogout = new JButton("Cerrar sesi\u00F3n");
		btnLogout.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Logout.png")));
		btnLogout.addActionListener(this);
		
		luz = new Luz();
		luz.setEncendido(false);
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String songFilePath;
						songFilePath = file.getAbsolutePath();
						controlador.setFicheroCanciones(songFilePath);
						controlador.cargarNuevasCanciones();
				}
			}
		});
		luz.setColor(Color.YELLOW);
		
		header_panel.add(luz);
		
		rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_9.setPreferredSize(new Dimension(575, 20));
		header_panel.add(rigidArea_9);
		
		lblUsuario = new JLabel();
		lblUsuario.setText(controlador.getUsuarioActual().getNombre());
		header_panel.add(lblUsuario);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea_1);
		
		btnMejorar = new JButton("Mejorar tu cuenta");
		btnMejorar.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Premium.png")));
		btnMejorar.addActionListener(this);
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
		sidebar_panel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		sidebar_panel.setPreferredSize(new Dimension(225, 10));
		body_panel.add(sidebar_panel, BorderLayout.WEST);
		sidebar_panel.setLayout(new BorderLayout(0, 0));
		
		sidebar_buttons = new JPanel();
		sidebar_buttons.setMinimumSize(new Dimension(225, 300));
		sidebar_buttons.setPreferredSize(new Dimension(225, 300));
		sidebar_buttons.setMaximumSize(new Dimension(225, 300));
		sidebar_panel.add(sidebar_buttons, BorderLayout.NORTH);
		sidebar_buttons.setLayout(new BoxLayout(sidebar_buttons, BoxLayout.Y_AXIS));
		
		btnExplorar = new JButton("Explorar Canciones");
		btnExplorar.setHorizontalAlignment(SwingConstants.LEFT);
		btnExplorar.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Search.png")));
		btnExplorar.setPreferredSize(new Dimension(225, 50));
		btnExplorar.setMinimumSize(new Dimension(225, 50));
		btnExplorar.addActionListener(this);	
		btnExplorar.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnExplorar);
		
		btnNuevaLista = new JButton("Nueva Playlist");
		btnNuevaLista.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevaLista.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "NewList.png")));
		btnNuevaLista.setPreferredSize(new Dimension(225, 50));
		btnNuevaLista.setMinimumSize(new Dimension(225, 50));
		btnNuevaLista.addActionListener(this);
		btnNuevaLista.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnNuevaLista);
		
		btnReciente = new JButton("Canciones Recientes");
		btnReciente.setHorizontalAlignment(SwingConstants.LEFT);
		btnReciente.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Recent.png")));
		btnReciente.setPreferredSize(new Dimension(225, 50));
		btnReciente.setMinimumSize(new Dimension(225, 50));
		btnReciente.addActionListener(this);
		btnReciente.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnReciente);
		
		btnMisListas = new JButton("Mis Playlists");
		btnMisListas.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisListas.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "MyLists.png")));
		btnMisListas.setPreferredSize(new Dimension(225, 50));
		btnMisListas.setMinimumSize(new Dimension(225, 50));
		btnMisListas.addActionListener(this);
		btnMisListas.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnMisListas);
		
		btnMasReproducidas = new JButton("Canciones mas Populares");
		btnMasReproducidas.setHorizontalAlignment(SwingConstants.LEFT);
		btnMasReproducidas.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Popular.png")));
		btnMasReproducidas.addActionListener(this);
		btnMasReproducidas.setEnabled(false);
		btnMasReproducidas.setMinimumSize(new Dimension(225, 50));
		btnMasReproducidas.setMaximumSize(new Dimension(225, 50));
		btnMasReproducidas.setPreferredSize(new Dimension(225, 50));
		sidebar_buttons.add(btnMasReproducidas);
		
		btnImprimir = new JButton("Imprimir Playlists");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		btnImprimir.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "Print.png")));
		btnImprimir.addActionListener(this);
		btnImprimir.setEnabled(false);
		btnImprimir.setPreferredSize(new Dimension(225, 50));
		btnImprimir.setMinimumSize(new Dimension(225, 50));
		btnImprimir.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnImprimir);
		
		sidebar_playlists = new JPanel();
		sidebar_panel.add(sidebar_playlists, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setPreferredSize(new Dimension(225, 250));
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
		action_panel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		action_panel.setPreferredSize(new Dimension(10, 65));
		body_panel.add(action_panel, BorderLayout.SOUTH);
		action_panel.setLayout(new BoxLayout(action_panel, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		panel.setMaximumSize(new Dimension(225, 65));
		panel.setPreferredSize(new Dimension(225, 65));
		action_panel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		lblCancionSeleccionada = new JLabel("Cancion seleccionada:");
		lblCancionSeleccionada.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblCancionSeleccionada);
		
		lblSelectedSong = new JLabel("");
		lblSelectedSong.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblSelectedSong);
		lblSelectedSong.setVisible(false);
		
		lblSelectedArtist = new JLabel("");
		lblSelectedArtist.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblSelectedArtist);
		lblSelectedArtist.setVisible(false);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(13);
		panel_1.setPreferredSize(new Dimension(50, 10));
		action_panel.add(panel_1);
		
		btnPrevious = new JButton("");
		panel_1.add(btnPrevious);
		btnPrevious.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PreviousButton.png")));
		btnPrevious.addActionListener(this);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_2);
		
		btnPlay = new JButton("");
		panel_1.add(btnPlay);
		btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PlayButton.png")));
		btnPlay.addActionListener(this);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_3);
		
		btnNext = new JButton("");
		panel_1.add(btnNext);
		btnNext.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "NextButton.png")));
		btnNext.addActionListener(this);
		
		content_panel = new JPanel();
		content_panel.setAutoscrolls(true);
		content_panel.setMaximumSize(new Dimension(975, 32767));
		body_panel.add(content_panel, BorderLayout.CENTER);
		content_panel.setLayout(new BoxLayout(content_panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("\u00A1Bienvenido a AppMusic!");
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 46));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel);
		
		current_content = content_panel;
		
		rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		content_panel.add(rigidArea_4);
		
		lblNewLabel_1 = new JLabel("En AppMusic, usted...:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_1);
		
		rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		content_panel.add(rigidArea_5);
		
		lblNewLabel_2 = new JLabel("- Puede buscar canciones en el cat\u00E1logo de canciones de AppMusic pulsando Explorar Canciones");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("- Crear nuevas listas de reproduccion para escuchar en cualquier momento pulsando Nueva Playlist");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("- Escuchar las canciones que a reproducido recientemente pulsando Canciones Recientes");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("- Ver las listas de reproduccion que ha creado pulsando Mis Playlists");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_5);
		
		rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_6.setMaximumSize(new Dimension(20, 40));
		rigidArea_6.setPreferredSize(new Dimension(20, 40));
		content_panel.add(rigidArea_6);
		
		lblNewLabel_8 = new JLabel("Adem\u00E1s, puede mejorar su cuenta a una cuenta Premium y tener acceso a funcionalidades tales como:");
		lblNewLabel_8.setForeground(new Color(30, 144, 255));
		lblNewLabel_8.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_8.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_8);
		
		rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		content_panel.add(rigidArea_7);
		
		lblNewLabel_6 = new JLabel("- Ver las canciones m\u00E1s  escuchadas pulsando Canciones mas Populares");
		lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("- Imprimir sus listas de reproduccion para exportarlas pulsando Imprimir Playlists");
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_7);
		
		lblNewLabel_9 = new JLabel("\u00A1Y mucho m\u00E1s en el futuro!");
		lblNewLabel_9.setForeground(new Color(0, 128, 0));
		lblNewLabel_9.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_9.setAlignmentX(Component.CENTER_ALIGNMENT);
		content_panel.add(lblNewLabel_9);
		
		rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		rigidArea_8.setMaximumSize(new Dimension(20, 100));
		content_panel.add(rigidArea_8);
		
		lblNewLabel_10 = new JLabel("Desarrollado por Gonzalo Nicol\u00E1s Mart\u00EDnez para la asignatura de TDS 2020-2021");
		lblNewLabel_10.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_10.setIcon(new ImageIcon(Principal.class.getResource("/umu/tds/apps/images/logo-um.jpg")));
		content_panel.add(lblNewLabel_10);
		enablePremiumFeatures();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogout) {
			controlador.logout();
			Login windowL = new Login();
			frame.dispose();
		}
		else if (e.getSource() == btnMejorar) {
			scrollPane.setVisible(false);
			MejorarCuenta panel_mc = new MejorarCuenta(frame);
			body_panel.remove(current_content);
			body_panel.add(panel_mc, BorderLayout.CENTER);
			current_content = panel_mc;
			frame.repaint();
			frame.revalidate();
		} 
		else if (e.getSource() == btnExplorar) {
			scrollPane.setVisible(false);
			Explorar panel_exp = new Explorar(frame);
			body_panel.remove(current_content);
			body_panel.add(panel_exp, BorderLayout.CENTER);
			current_content = panel_exp;
			frame.repaint();
			frame.revalidate();
		} 
		else if (e.getSource() == btnNuevaLista) {
			scrollPane.setVisible(false);
			NuevaLista panel_nl = new NuevaLista(frame);
			body_panel.remove(current_content);
			body_panel.add(panel_nl, BorderLayout.CENTER);
			current_content = panel_nl;
			frame.repaint();
			frame.revalidate();
		}
		else if (e.getSource() == btnReciente) {
			scrollPane.setVisible(false);
			Reciente panel_rec = new Reciente(frame);
			body_panel.remove(current_content);
			body_panel.add(panel_rec, BorderLayout.CENTER);
			current_content = panel_rec;
			frame.repaint();
			frame.revalidate();
		}
		else if (e.getSource() == btnMisListas) {
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
		else if (e.getSource() == btnMasReproducidas) {
			scrollPane.setVisible(false);
			MasReproducidas panel_mr = new MasReproducidas(frame);
			body_panel.remove(current_content);
			body_panel.add(panel_mr, BorderLayout.CENTER);
			current_content = panel_mr;
			frame.repaint();
			frame.revalidate();
		}
		else if (e.getSource() == btnImprimir) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Selecciona carpeta de destino");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			
			if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				try {
					controlador.printPDF(path);
				} catch (DocumentException ev) {
					ev.printStackTrace();
				}
			}
		}
		else if (e.getSource() == btnPrevious) {
			if (playlistActual != null) {
				if (playlistIndex == 0) {
					playlistIndex = playlistActual.getCanciones().size()-1;
					cancionActual = playlistActual.getCanciones().get(playlistIndex);
					controlador.reproducirCancion(cancionActual);
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PauseButton.png")));
					estadoReproductor = PLAYING;
					lblSelectedSong.setText(cancionActual.getTitulo());
					lblSelectedArtist.setText(cancionActual.getInterprete());
					frame.repaint();
					frame.revalidate();
				} else {
					playlistIndex--;
					cancionActual = playlistActual.getCanciones().get(playlistIndex);
					controlador.reproducirCancion(cancionActual);
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PauseButton.png")));
					estadoReproductor = PLAYING;
					lblSelectedSong.setText(cancionActual.getTitulo());
					lblSelectedArtist.setText(cancionActual.getInterprete());
					frame.repaint();
					frame.revalidate();
				}
			}
		}
		else if (e.getSource() == btnPlay) {
			if (cancionActual != null) {
				if (estadoReproductor == PAUSED) {
					controlador.reproducirCancion(cancionActual);
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PauseButton.png")));
					estadoReproductor = PLAYING;
				} else if (estadoReproductor == PLAYING) {
					controlador.pausarCancion();
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PlayButton.png")));
					estadoReproductor = PAUSED;
				}
				
				
			}
		}
		else if (e.getSource() == btnNext)  {
			if (playlistActual != null) {
				if (playlistIndex == playlistActual.getCanciones().size()-1) {
					playlistIndex = 0;
					cancionActual = playlistActual.getCanciones().get(playlistIndex);
					controlador.reproducirCancion(cancionActual);
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PauseButton.png")));
					estadoReproductor = PLAYING;
					lblSelectedSong.setText(cancionActual.getTitulo());
					lblSelectedArtist.setText(cancionActual.getInterprete());
					frame.repaint();
					frame.revalidate();
				} else {
					playlistIndex++;
					cancionActual = playlistActual.getCanciones().get(playlistIndex);
					controlador.reproducirCancion(cancionActual);
					btnPlay.setIcon(new ImageIcon(Principal.class.getResource(IMG_SRC + "PauseButton.png")));
					estadoReproductor = PLAYING;
					lblSelectedSong.setText(cancionActual.getTitulo());
					lblSelectedArtist.setText(cancionActual.getInterprete());
					frame.repaint();
					frame.revalidate();
				}
			}
		}
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
	
	public void setCurrentSong(Cancion c) {
		lblSelectedArtist.setVisible(true);
		lblSelectedSong.setVisible(true);
		playlistActual = null;
		playlistIndex = 0;
		
		if (cancionActual == null || cancionActual != c) {
			cancionActual = c;
			lblSelectedSong.setText(c.getTitulo());
			lblSelectedArtist.setText(c.getInterprete());
			frame.repaint();
			frame.revalidate();
		} 
	}
	
	public void setCurrentPlaylist(ListaReproduccion l, int indice) {
		
		lblSelectedArtist.setVisible(true);
		lblSelectedSong.setVisible(true);
		
		if (playlistActual == null || playlistActual != l) {
			playlistActual = l;
			playlistIndex = indice;
			cancionActual = playlistActual.getCanciones().get(playlistIndex);
			lblSelectedSong.setText(cancionActual.getTitulo());
			lblSelectedArtist.setText(cancionActual.getInterprete());
			frame.repaint();
			frame.revalidate();
		} else {
			playlistIndex = indice;
			cancionActual = playlistActual.getCanciones().get(playlistIndex);
			lblSelectedSong.setText(cancionActual.getTitulo());
			lblSelectedArtist.setText(cancionActual.getInterprete());
			frame.repaint();
			frame.revalidate();
		}
		
		
	}
	
	public static Principal getInstancia() {
		return instanciaPrincipal;
	}
	
	
}
