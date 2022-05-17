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
	Image game_over;
	
	
	public Fondo(double x, double y, int fase) 
	{
		this.x = x;
		this.y = y;		
		this.fase = fase;
		inicio = Herramientas.cargarImagen("inicio.jpg");
		principal = Herramientas.cargarImagen("fondo.jpg");
		game_over = Herramientas.cargarImagen("game_over.jpg");
	}
	public void dibujarse(Entorno entorno)
	{ 	
		//fondo durante el juego
		if (this.fase == 2){
			entorno.dibujarImagen(principal, this.x, this.y, this.angulo, 1);
		}
		//fondo de game over
		if (this.fase == 3){
			entorno.dibujarImagen(game_over, this.x, this.y, this.angulo, 2);
		}
		}

}
