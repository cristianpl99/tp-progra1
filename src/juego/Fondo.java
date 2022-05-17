package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Fondo 
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	int fase;
	Image inicio;
	Image principal;
	
	
	public Fondo(double x, double y, int fase) 
	{
		this.x = x;
		this.y = y;		
		this.fase = fase;
		inicio = Herramientas.cargarImagen("inicio.jpg");
		principal = Herramientas.cargarImagen("fondo.jpg");
	}
	public void dibujarse(Entorno entorno)
	{ 
		if (this.fase == 1){
			entorno.dibujarImagen(inicio, this.x, this.y, this.angulo, 1);
		}
		if (this.fase == 2){
			entorno.dibujarImagen(principal, this.x, this.y, this.angulo, 1);
		}
		}

}
