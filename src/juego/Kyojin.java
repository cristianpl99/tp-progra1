package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Kyojin {
	
	public double x;
	public double y;
	public double velocidad;
	public double angulo;
	public int radio;
	boolean congelado;
	Image img3;
	Image freeze;
	
	public Kyojin(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		congelado = false;
		img3 = Herramientas.cargarImagen("kyojin.png");
		freeze = Herramientas.cargarImagen("kyojinfrio.png");
	}
	
	public void dibujarse(Entorno e) {
		if(this.congelado == false){
		e.dibujarImagen(img3, this.x, this.y, 0.3, 0.2);
		};
		if(this.congelado == true){
		e.dibujarImagen(freeze, this.x, this.y, 0.3, 0.2);
		};

	}
	public void cambiarAngulo(double x2, double y2){
		this.angulo = Math.atan2(y2 - this.y, x2 - this.x);
	}
	public void mover() {
		this.x += Math.cos(this.angulo)*(this.velocidad);
		this.y += Math.sin(this.angulo)*(this.velocidad);
		//forma ideada de mover los kyojines antes que cesar pase la version 
		//asteroides. funciona pero cuando sen = 0 o cos = 0 se ralentiza la 
		//velocidad de los kyojines
		/*
		if (Mikasa.x >= this.x){
			x += velocidad * Math.cos(angulo);
		}
		if (Mikasa.x <= this.x){
			x -= velocidad * Math.cos(angulo);
		}
		if (Mikasa.y >= this.y){
			y -= velocidad * Math.sin(angulo);
		}
		if (Mikasa.y <= this.y){
			y += velocidad * Math.sin(angulo);
		}
		*/
		
	}	
	public boolean chocasteCon(Entorno e) {
		return x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio;		
	}
	public boolean chocasteConObstaculo(double x, double y){
		if ((this.x >= x - 80) && (this.x <= x + 80) && (this.y >= y - 80) && (this.y <= y + 80) ){
			return true;  
	   }
	   else{
		   return false;
	   }
	}
	
	public void cambiarTrayectoria() {
		angulo += Math.PI/2;
	}
}
