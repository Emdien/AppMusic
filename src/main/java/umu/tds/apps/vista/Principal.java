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

public class Principal {

	private JFrame frame;

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
		
		JPanel header_panel = new JPanel();
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
		
		JLabel lblUsuario = new JLabel("#Username");
		header_panel.add(lblUsuario);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea_1);
		
		JButton btnMejorar = new JButton("Mejorar tu cuenta");
		btnMejorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		header_panel.add(btnMejorar);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		header_panel.add(rigidArea);
		header_panel.add(btnLogout);
		
		JPanel body_panel = new JPanel();
		frame.getContentPane().add(body_panel);
		body_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel sidebar_panel = new JPanel();
		sidebar_panel.setMinimumSize(new Dimension(225, 10));
		sidebar_panel.setMaximumSize(new Dimension(225, 32767));
		sidebar_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		sidebar_panel.setPreferredSize(new Dimension(225, 10));
		body_panel.add(sidebar_panel, BorderLayout.WEST);
		sidebar_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(225, 250));
		panel.setPreferredSize(new Dimension(225, 210));
		panel.setMaximumSize(new Dimension(225, 250));
		sidebar_panel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExplorar.setMaximumSize(new Dimension(225, 50));
		panel.add(btnExplorar);
		
		JButton btnNuevaLista = new JButton("Nueva lista");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNuevaLista.setMaximumSize(new Dimension(225, 50));
		panel.add(btnNuevaLista);
		
		JButton btnReciente = new JButton("Reciente");
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReciente.setMaximumSize(new Dimension(225, 50));
		panel.add(btnReciente);
		
		JButton btnMisListas = new JButton("Mis listas");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMisListas.setMaximumSize(new Dimension(225, 50));
		panel.add(btnMisListas);
		
		JPanel panel_1 = new JPanel();
		sidebar_panel.add(panel_1, BorderLayout.CENTER);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setPreferredSize(new Dimension(225, 350));
		panel_1.add(list);
		
		JPanel action_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) action_panel.getLayout();
		flowLayout_1.setVgap(20);
		action_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		action_panel.setPreferredSize(new Dimension(10, 65));
		body_panel.add(action_panel, BorderLayout.SOUTH);
		
		JButton btnPrevious = new JButton("#Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPrevious);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_2);
		
		JButton btnPlay = new JButton("#Play/Stop\r\n");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnPlay);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		action_panel.add(rigidArea_3);
		
		JButton btnNext = new JButton("#Next\r\n");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		action_panel.add(btnNext);
		
		JPanel content_panel = new JPanel();
		body_panel.add(content_panel, BorderLayout.CENTER);
	}

}
