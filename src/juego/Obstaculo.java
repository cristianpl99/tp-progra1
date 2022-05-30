package juego;

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
	Image entrada;
	
	
	public Obstaculo(int x, int y) 
	{
		this.x = x;
		this.y = y;
		img1 = Herramientas.cargarImagen("arbol.png");
		img2 = Herramientas.cargarImagen("casa.png");
		img3 = Herramientas.cargarImagen("crater.png");
		entrada = Herramientas.cargarImagen("entrada.png");
	}
	//cambiar todos estos metodos por uno solo sumando una variable mas al constructor
	//de tipo para saber que hay que dibujar
	public void dibujarArbol(Entorno entorno)
	{
			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.2);		
	}
	public void dibujarCasa(Entorno entorno)
	{
			entorno.dibujarImagen(img2, this.x, this.y, this.angulo, 0.4);		
	}
	public void dibujarCrater(Entorno entorno)
	{
			entorno.dibujarImagen(img3, this.x, this.y, this.angulo, 0.6);		
	}
	public void dibujarEntrada(Entorno entorno)
	{
			entorno.dibujarImagen(entrada, this.x, this.y, this.angulo, 0.2);		
	}

}
