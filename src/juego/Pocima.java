package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Pocima
{
	// Variables de instancia
	double x;
	double y;
	double angulo;
	int tipo = 0;
	Image img6;
	Image img7;
	Image img8;
	
	public Pocima(int x, int y, int tipo) 
	{
		this.x = x;
		this.y = y;
		this.tipo = tipo;
		img6 = Herramientas.cargarImagen("pocima.png");
		img7 = Herramientas.cargarImagen("pocima2.png");
		img8 = Herramientas.cargarImagen("suero.png");
	}
	
	public void dibujarPocima(Entorno entorno)
	{
	if (tipo == 1) {
		entorno.dibujarImagen(img6, this.x, this.y, this.angulo, 0.1);		
	}
	if (tipo == 2) {
		entorno.dibujarImagen(img7, this.x, this.y, this.angulo, 0.1);		
	}
	if (tipo == 3) {
		entorno.dibujarImagen(img8, this.x, this.y, this.angulo, 0.1);		
	}

}
}
	



