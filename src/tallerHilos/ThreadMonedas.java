package tallerHilos;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class ThreadMonedas implements  Runnable{
	boolean endValue;
	ImageIcon imagen;
	int tiempo = 100;
	JLabel lblImagen;
	public ThreadMonedas(JLabel imagen) {
		this.lblImagen = imagen;
		endValue = true;
	}

	@Override
	public void run() {
		while(endValue) {
		
			int random = new Random().nextInt(3);
			switch (random) {
			case 0:
				imagen = new ImageIcon(getClass().getResource("/imagenes/kiwi.png"));
				lblImagen.setIcon(imagen);
				try {
					Thread.sleep(tiempo);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 1:
				imagen = new ImageIcon(getClass().getResource("/imagenes/manzanas.png"));
				lblImagen.setIcon(imagen);				
				try {
					Thread.sleep(tiempo);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				imagen = new ImageIcon(getClass().getResource("/imagenes/platanos.png"));
				lblImagen.setIcon(imagen);
				try {
					Thread.sleep(tiempo);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			if (tiempo<500) {
				tiempo = tiempo+10;
			}
			else {
				endThread();
			Panel.timeout();
		
			
		
			
			
				
			}
		}
	}
	public void endThread() {
		endValue = false;
		
	}
	public void restartThread() {
		endValue = true;
		tiempo = 100;
	}
}
