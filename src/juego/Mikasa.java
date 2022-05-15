package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa 
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	boolean convertida;
	Image img1;
	Image img2;
	
	
	public Mikasa(int x, int y) 
	{
		this.x = x;
		this.y = y;
		convertida = false;
		
		img1 = Herramientas.cargarImagen("mikasa.png");
		img2 = Herramientas.cargarImagen("convertida.png");
	}
	
	public void dibujarse(Entorno entorno)
	{
		if (this.convertida == false){
	
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.1);
		else
			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.1);
	}
	if (this.convertida == true){
	
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
			entorno.dibujarImagen(img2, this.x, this.y, this.angulo, 0.2);
		else
			entorno.dibujarImagen(img2, this.x, this.y, this.angulo, 0.2);
	}
	}

	public void girar(double modificador) 
	{
		this.angulo = this.angulo + modificador;
	}
	
	public void moverAdelante() {
		this.x += Math.cos(this.angulo)*3;
		this.y += Math.sin(this.angulo)*3;
	}
	public void moverAtras() {
		this.x -= Math.cos(this.angulo)*3;
		this.y -= Math.sin(this.angulo)*3;
	}
	
}
