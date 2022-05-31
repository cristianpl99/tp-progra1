package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Vidas
{
	// Variables de instancia
	double x;
	double y;
	int cantidad;
    double angulo;
	Image img1;
    
	public Vidas(int x, int y) 
	{
		this.x = x;
		this.y = y;
		img1 = Herramientas.cargarImagen("vidas.png");
	}
    
	public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.1);
    }
}