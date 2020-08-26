package tallerHilos;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame{

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setVisible(true);
	}

}

class Frame extends JFrame{
	public Frame() {
		setTitle("Maquna Traga Monedas");
		setSize(1050, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Panel panel = new Panel();
		
		add(panel);
		
	}
}

class Panel extends JPanel implements ActionListener{
	static boolean isBtn1;
	static boolean isBtn2;
	static boolean isBtn3;
	ImageIcon imagen1, imagen2, imagen3;
	JButton boton1;
	JButton boton2;
	static JButton boton3;
	JButton botonInicio;
	JLabel lblApuesta;
	JLabel lblImagen1;
	JLabel lblImagen2;
	JLabel lblImagen3;
	JTextField txtApuesta;
	ThreadMonedas run1;
	ThreadMonedas run2;
	ThreadMonedas run3; 



	public Panel() {
		iniciar();
		posicionar();
		agregar();
		setLayout(null);
	}


	private void iniciar() {
		 
		 
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/kiwi.png"));
		lblImagen1 = new JLabel(imagen1);
		
		imagen2 = new ImageIcon(getClass().getResource("/imagenes/manzanas.png"));
		lblImagen2 = new JLabel(imagen2);
		
		imagen3 = new ImageIcon(getClass().getResource("/imagenes/platanos.png"));
		lblImagen3 = new JLabel(imagen3);
		
		boton1 = new JButton("stop");
		boton1.addActionListener(this);
		boton1.setActionCommand("boton1");
		
		boton2 = new JButton("stop");
		boton2.addActionListener(this);
		boton2.setActionCommand("boton2");
		
		boton3 = new JButton("stop");
		boton3.addActionListener(this);
		boton3.setActionCommand("boton3");
		
		
		lblApuesta = new JLabel("bet amount");
		txtApuesta = new JTextField();
		botonInicio = new JButton("jugar");
		botonInicio.addActionListener(this);
		
		 run1 = new ThreadMonedas(lblImagen1);
		 run2 = new ThreadMonedas(lblImagen2);
		 run3 = new ThreadMonedas(lblImagen3);
		
	
	}
//	g.drawImage(imagen1, 10, 0, null);
//	g.drawImage(imagen2, imagen1.getWidth(this)+10, 0, null);
//	g.drawImage(imagen3, imagen1.getWidth(this)*2+10, 0, null);



	private void posicionar() {
		lblImagen1.setBounds(10,0,250,250);
		lblImagen2.setBounds(260, 0, 250, 250);
		lblImagen3.setBounds(520, 0, 250, 250);
		boton1.setBounds(35, 250, 200, 50);
		boton2.setBounds(275, 250, 200, 50);
		boton3.setBounds(525, 250, 200, 50);
		lblApuesta.setBounds(810, 50, 200, 50);
		lblApuesta.setFont(new Font(lblApuesta.getFont().getFontName(), lblApuesta.getFont().getStyle(), 30));
		txtApuesta.setBounds(810, 125, 200, 50);
		botonInicio.setBounds(810, 200, 200, 100);
		
		
	}
	private void agregar() {
		
		add(boton1);
		add(boton2);
		add(boton3);
		add(lblApuesta);
		add(txtApuesta);
		add(botonInicio);
		add(lblImagen1);
		add(lblImagen2);
		add(lblImagen3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
try {
		switch (e.getActionCommand()) {
		case "boton1":
			isBtn1 = true;
			run1.endThread();
			validar();
			break;

		case "boton2":
			isBtn2 =  true;
			run2.endThread();
			validar();
			break;
			
		case "boton3":
			isBtn3 = true;
			run3.endThread();
			validar();
			break;
			
		case "jugar":
			isBtn1 = false; 
			isBtn2 = false;
			isBtn3 = false;
			run1.restartThread();
			run2.restartThread();
			run3.restartThread();
			Thread thread1 = new Thread(run1);
			Thread thread2 = new Thread(run2);
			Thread thread3 = new Thread(run3);
			thread1.start();
			thread2.start();
			thread3.start();
			break;
			
		default:
			break;
		}

	} catch (NumberFormatException e1) {
		JOptionPane.showMessageDialog(null, "You did not enter any amount");
	}
	}

public static void timeout() {
	isBtn1=true;
	isBtn2=true;
	isBtn3=true;
	boton3.doClick();
	
	
}
	private void validar() {
		



		if (isBtn1 && isBtn2 &&isBtn3) {
			
				int creditos = Integer.parseInt(txtApuesta.getText());
				if (lblImagen1.getIcon().toString().equals(lblImagen2.getIcon().toString()) && lblImagen1.getIcon().toString().equals(lblImagen3.getIcon().toString())) {
					txtApuesta.setText("");
					JOptionPane.showMessageDialog(null, "You won the biggest prize, you bet: " + creditos + " credits, you win " + creditos*3 + " credits");
				}else if(lblImagen1.getIcon().toString().equals(lblImagen2.getIcon().toString()) || lblImagen1.getIcon().toString().equals(lblImagen3.getIcon().toString())
						|| lblImagen2.getIcon().toString().equals(lblImagen3.getIcon().toString())) {
					txtApuesta.setText("");
					JOptionPane.showMessageDialog(null, "You get 2 hits, you bet: " + creditos + " credits, you win " + creditos*2 + " credits");
				}else {
					txtApuesta.setText("");
					JOptionPane.showMessageDialog(null, "you loose, try again");
				}
			
		}

	}


}