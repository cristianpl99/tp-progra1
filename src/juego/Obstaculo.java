package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo 
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	Image img1;
	Image img2;
	Image img3;
	
	public Obstaculo(int x, int y) 
	{
		this.x = x;
		this.y = y;
	
		img1 = Herramientas.cargarImagen("arbol.png");
		img2 = Herramientas.cargarImagen("casa.png");
	}
	
	public void dibujarArbol(Entorno entorno)
	{
//		entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);		
	}
	public void dibujarCasa(Entorno entorno)
	{
//		entorno.dibujarTriangulo(this.x, this.y, 50, 30, this.angulo, Color.yellow);
			entorno.dibujarImagen(img2, this.x, this.y, this.angulo, 0.4);		
	}

}
