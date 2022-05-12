package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pocima
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img6;
	
	public Pocima(int x, int y) 
	{
		this.x = x;
		this.y = y;
	
		img6 = Herramientas.cargarImagen("pocima.png");
	}
	
	public void dibujarPocima(Entorno entorno)
	{
//		entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
		entorno.dibujarImagen(img6, this.x, this.y, this.angulo, 0.1);		
	}

}
