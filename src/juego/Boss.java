package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Boss {
	
	public double x;
	public double y;
	public double velocidad;
	public double angulo;
	public int radio;
	Image boss;
    int vidas;
	
	public Boss(double x, double y, double velocidad, double angulo, int radio, int vidas) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
		boss = Herramientas.cargarImagen("final_boss.png");
        this.vidas = vidas;
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarImagen(boss, this.x, this.y, 0.3, 0.2);
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
	
	public void cambiarTrayectoria() {
		angulo += Math.PI/3;
	}

}