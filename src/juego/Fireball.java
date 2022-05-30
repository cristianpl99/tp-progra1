package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Fireball {
	//Variables de instancia
	public double x;
    public double y;
	public double velocidad;
	public double angulo;
	public int radio;
    Image fireball;
	
	public Fireball(double x, double y, double velocidad, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.radio = radio;
        fireball = Herramientas.cargarImagen("fireball.png");
	}
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(fireball, this.x, this.y, 0.3, 0.2);
	}

	public void mover() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}

	public boolean chocasteCon(Entorno e) {
		if(x <= radio || y <= radio || x >= e.ancho() - radio || y >= e.alto() - radio){
		return true;
		}else{
			return false;
		}
	}
	
	public void cambiarTrayectoria() {
		angulo += Math.PI/2;
	}
	
	public void acelerar() {
		velocidad += 0.5;
	}
}
	