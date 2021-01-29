package umu.tds.apps.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Principal {

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
	
	
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
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
		flowLayout.setVgap(12);
		flowLayout.setAlignment(FlowLayout.TRAILING);
		header_panel.setMinimumSize(new Dimension(10, 50));
		header_panel.setMaximumSize(new Dimension(32767, 50));
		header_panel.setPreferredSize(new Dimension(10, 50));
		frame.getContentPane().add(header_panel);
		
		JButton btnLogout = new JButton("Cerrar sesi\u00F3n");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblUsuario = new JLabel("#Username");
		header_panel.add(lblUsuario);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea_1);
		
		btnMejorar = new JButton("Mejorar tu cuenta");
		btnMejorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		sidebar_buttons.setMinimumSize(new Dimension(225, 250));
		sidebar_buttons.setPreferredSize(new Dimension(225, 210));
		sidebar_buttons.setMaximumSize(new Dimension(225, 250));
		sidebar_panel.add(sidebar_buttons, BorderLayout.NORTH);
		sidebar_buttons.setLayout(new BoxLayout(sidebar_buttons, BoxLayout.Y_AXIS));
		
		btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("test");
				Explorar panel_exp = new Explorar();
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
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNuevaLista.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnNuevaLista);
		
		btnReciente = new JButton("Reciente");
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReciente.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnReciente);
		
		btnMisListas = new JButton("Mis listas");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane.setVisible(true);
				frame.repaint();
				frame.revalidate();
			}
		});
		btnMisListas.setMaximumSize(new Dimension(225, 50));
		sidebar_buttons.add(btnMisListas);
		
		sidebar_playlists = new JPanel();
		sidebar_panel.add(sidebar_playlists, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setPreferredSize(new Dimension(225, 340));
		sidebar_playlists.add(scrollPane);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Playlist 1", "Playlist 2", "Playlist 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		action_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) action_panel.getLayout();
		flowLayout_1.setVgap(20);
		action_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		action_panel.setPreferredSize(new Dimension(10, 65));
		body_panel.add(action_panel, BorderLayout.SOUTH);
		
		btnPrevious = new JButton("#Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPrevious);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_2);
		
		btnPlay = new JButton("#Play/Stop\r\n");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPlay);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_3);
		
		btnNext = new JButton("#Next\r\n");
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
	}

}
