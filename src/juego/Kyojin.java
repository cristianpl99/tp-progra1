package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Kyojin {
	
	public double x;
	public double y;
	public double velocidad;
	public double angulo;
	public int radio;
	Image img3;
	
	public Kyojin(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad*2;
		this.angulo = angulo;
		this.radio = radio;
		img3 = Herramientas.cargarImagen("kyojin.png");
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarImagen(img3, this.x, this.y, this.angulo, 0.3);
	}

	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	
	//FIXME
	public boolean chocasteCon(Entorno e) {
		return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;		
	}
	
	public void cambiarTrayectoria() {
		angulo += Math.PI/3;
	}
	
	public void acelerar() {
		velocidad += 0.8;
	}
	
	

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public double getAngulo() {
		return angulo;
	}

	public int getRadio() {
		return radio;
	}

}
