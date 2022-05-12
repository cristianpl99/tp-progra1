package juego;
import java.awt.Color;
import entorno.*;

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
 // otras variables del juego aqui
	Mikasa mikasa;
	Kyojin [] kyojines;
	Obstaculo [] obstaculos;
	Proyectil proyectil;
	Pocima pocima;
	Fondo fondo;
	int tiempo = 3600;
	int segundos = 0;
	int kills = 0;
	
	Juego()
	{
		// Inicializa el objeto entorno, pero aun no lo inicia.
		entorno = new Entorno(this, "Attack on Titan", 800, 600);
		mikasa = new Mikasa(400, 300);
		fondo = new Fondo(0,0);
		obstaculos = new Obstaculo [4];
			obstaculos [0] = new Obstaculo (200, 400);
			obstaculos [1] = new Obstaculo (600, 400);
			obstaculos [2] = new Obstaculo (200, 200);
			obstaculos [3] = new Obstaculo (600, 200);
		kyojines = new Kyojin [4];	
			kyojines[0] = new Kyojin(50, 50, 1, Math.PI/4, 30);
			kyojines[1] = new Kyojin(50, 550, 1, Math.PI/4, 30);
			kyojines[2] = new Kyojin(550, 50, 1, Math.PI/4, 30);
			kyojines[3] = new Kyojin(550, 550, 1, Math.PI/4, 30);	
		
		/* 
		 * Es fundamental que recién al final del constructor de la clase Juego se 
		 * inicie el objeto entorno de la siguiente manera.
		 */
		entorno.iniciar();		
	}
	/*
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */	
	public void tick(){
		//dibuja el fondo
		fondo.dibujarse(entorno);
		// setea el tiempo
		tiempo -= 1;
		segundos = tiempo / 60;		          
		
		//si mata a los cuatro kyojines, gana el juego
	if(kills == 4){
			entorno.cambiarFont("Arial", 50, Color.BLACK);
			entorno.escribirTexto("GANASTE", 250, 100);
			
		}
		//chequea al principio del ciclo si mikasa esta viva
	if (mikasa == null || tiempo <= 0){
		entorno.cambiarFont("Arial", 50, Color.BLACK);
		entorno.escribirTexto("GAME OVER ", 250, 100);
	}
	
	
	else{
		{	
		//mueve a mikasa
		if (entorno.estaPresionada(entorno.TECLA_DERECHA))
			mikasa.girar(Herramientas.radianes(1));

		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
			mikasa.girar(Herramientas.radianes(-1));

		if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
			mikasa.moverAdelante();
		
		if (entorno.estaPresionada(entorno.TECLA_ABAJO))
			mikasa.moverAtras();
		
	
		// proyectil
		if (entorno.sePresiono(entorno.TECLA_ESPACIO)&& proyectil == null){
			double direccion;
			direccion =  mikasa.angulo;
			proyectil = new Proyectil(mikasa.x, mikasa.y, 5, direccion, 1, true);	
			}		
		if (proyectil != null){  
			proyectil.dibujar(entorno);
			proyectil.mover();
		}
		//si sale del limite de la pantalla, lo pone en null
		if((proyectil != null) &&(proyectil.chocasteCon(entorno)== true)) {
				proyectil = null;
			}
			
	
		
				
		//dibuja obstaculos de a uno en uno
		for (int i = 0; i <= obstaculos.length-1; i++) {
			if (i%2==0) {
				obstaculos[i].dibujarArbol(entorno);
			}
			else {
				obstaculos[i].dibujarCasa(entorno);
			}
		}
		//cada cinco segundos pone una pocima en el juego
		if (tiempo % 360 == 0){
			pocima = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1));
		}
		if (pocima!=null) {
			pocima.dibujarPocima(entorno);
		}
		
		if (pocima!=null) {
		if ((mikasa.x >= pocima.x - 15) && (mikasa.x <= pocima.x + 15) && (mikasa.y >= pocima.y - 15) && (mikasa.y <= pocima.y + 15) ) {
			for (int i = 0; i <= kyojines.length-1; i++) {
				if (kyojines[i]!=null) {
				kyojines[i].velocidad = 0.2;		
			}
			}
			pocima = null;
		}
		}
		if (mikasa != null){
			mikasa.dibujarse(entorno);
		}
		//dibuja los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
			kyojines[i].dibujarse(entorno);
			}
		}
		//mueve los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				kyojines[i].mover();
			}
		}
		//si chocan con el borde, cambian de trayectoria
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if (kyojines[i].chocasteCon(entorno)) 
					kyojines[i].cambiarTrayectoria();
			}
		}
		//si chocan con obstaculos, cambian de trayectoria. 
		//hay que hacerlo como metodo de clase???
		for (int i = 0; i <= kyojines.length-1; i++) {
			for (int j = 0; j <= obstaculos.length-1; j++) {
			if (kyojines[i]!=null) {	
				if ((kyojines[i].x >= obstaculos[j].x - 15) && (kyojines[i].x <= obstaculos[j].x + 15) && (kyojines[i].y >= obstaculos[j].y - 15) && (kyojines[i].y <= obstaculos[j].y + 15) ){ 
					kyojines[i].cambiarTrayectoria();}
			}
			//si el proyectil choca con un kyojin, lo mata
			if (proyectil !=null){
				if (kyojines[i]!=null) {
				if ((kyojines[i].x >= proyectil.x - 30) && (kyojines[i].x <= proyectil.x + 30) && (kyojines[i].y >= proyectil.y - 30) && (kyojines[i].y <= proyectil.y + 30) ){ 
					kyojines[i]=null;
					kills +=1;
					}
			}
			}
			
			}
		}
		// si chocan con mykasa, mykasa muere
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if ((kyojines[i].x >= mikasa.x - 60) && (kyojines[i].x <= mikasa.x + 60) && (kyojines[i].y >= mikasa.y - 60) && (kyojines[i].y <= mikasa.y + 60)){ 
					mikasa = null;
				}
			}
		}
				
		entorno.cambiarFont("Arial", 25, Color.white);
		entorno.escribirTexto("TIME: " + segundos, 500, 100);
		entorno.cambiarFont("Arial", 25, Color.red);
		entorno.escribirTexto("KILLS: " + kills, 500, 130);
		
	}	
	}
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{	
		Juego juego = new Juego();
	

	}
}

		
