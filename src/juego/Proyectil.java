package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	
	public double x;
	public double y;
	public double velocidad;
	public double angulo;
	public int radio;
	public boolean activo = false;
	Image img5;
	
	
	public Proyectil(double x, double y, double velocidad, double angulo, int radio, boolean activo) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		this.activo = false;
		img5 = Herramientas.cargarImagen("misil.png");
	}
	
	public void dibujar(Entorno e) {
			e.dibujarImagen(img5, this.x + 40, this.y, this.angulo+(0.75), 0.08);
	}

	public void mover() {
		this.x += (Math.cos(this.angulo)*2) * velocidad;
		this.y += (Math.sin(this.angulo)*2) * velocidad;
	}
	
	public boolean chocasteCon(Entorno e) {

		return  x >= e.ancho() || x <=0 || y >= e.alto()|| y <=0;		
	}
	
	public void acelerar() {
		velocidad += 0.05;
	}

	

	
}