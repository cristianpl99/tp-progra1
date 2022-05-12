package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo 
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img4;
	
	
	public Fondo(double x, double y) 
	{
		this.x = x;
		this.y = y;		
		img4 = Herramientas.cargarImagen("fondo.jpg");
	}
	public void dibujarse(Entorno entorno)
	{
//		entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
			entorno.dibujarImagen(img4, this.x, this.y, this.angulo, 1);
}
}
