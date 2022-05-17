package juego;
import java.awt.Color;

import javax.sound.midi.Soundbank;

import entorno.*;

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
 // otras variables del juego aqui
	Mikasa mikasa;
	Kyojin [] kyojines;
	Obstaculo [] obstaculos;
	Obstaculo [] crater;
	Proyectil proyectil;
	Pocima[] pocimas;
	Fondo fondo;
	//inicializar el tiempo en 3600 ticks / 60 segundos
	int tiempo = 3600;
	int segundos = 0;
	int kills = 0;
	boolean fin = false;
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
		// array de obstaculos con la imagen del crater (tope tentativo de 10)
		crater = new Obstaculo [10];
		//array de pocimas con distintos efectos
		pocimas = new Pocima [4];
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
		
		//si mata a los kyojines en pantalla, gana el juego
	if((fin == true)&& (mikasa != null)){
			entorno.cambiarFont("Arial", 50, Color.BLACK);
			entorno.escribirTexto("GANASTE!", 250, 100);
			
		}
		//chequea al principio del ciclo si mikasa esta viva
	if (mikasa == null || segundos <= 0){
		entorno.cambiarFont("Arial", 50, Color.BLACK);
		entorno.escribirTexto("GAME OVER ", 250, 100);
	}
	
		//si se dan las condiciones, el juego sigue
	if ((mikasa !=null)&&(segundos > 0)&&(fin == false)){
		{
			
		//mueve a mikasa
		mikasa.teclaRight(entorno); 
 		mikasa.teclaLeft(entorno); 
  		mikasa.teclaUp(entorno); 
  		mikasa.teclaDown(entorno);	
	
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
					
		//dibuja obstaculos de a uno a uno
		for (int i = 0; i <= obstaculos.length-1; i++) {
			if (i%2==0) {
				obstaculos[i].dibujarArbol(entorno);
			}
			else {
				obstaculos[i].dibujarCasa(entorno);
			}
		}
		//dibuja los crater
			for (int i = 0; i <= crater.length-1; i++) {
				if (crater[i]!=null) {
					crater[i].dibujarCrater(entorno);
				}
			}
		//cada cinco segundos pone una pocima de lentitud en el juego
		if (tiempo % 360 == 0){
			pocimas[0] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 1);
		}
		//cada diez segundos pone una pocima mata kyojin en juego
		if (tiempo % 720 == 0){
			pocimas[1] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 2);
		}
		//cada quince segundos pone una suero convertidor (?) en juego
		if (tiempo % 1080 == 0){
			pocimas[2] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 3);
		}
		
		for (int i = 0; i <= pocimas.length-1; i++) {
		if (pocimas[i]!=null) {
			pocimas[i].dibujarPocima(entorno);
			}
		}
		//si mikasa pasa agarra una pocima, chequea que tipo de pocima es.
		for (int i = 0; i <= pocimas.length-1; i++) {
			if (pocimas[i]!=null) {
				if ((mikasa.x >= pocimas[i].x - 15) && (mikasa.x <= pocimas[i].x + 15) && (mikasa.y >= pocimas[i].y - 15) && (mikasa.y <= pocimas[i].y + 15) ) {
					if (pocimas[i].tipo == 1){
						for (int j = 0; j <= kyojines.length-1; j++) {
							if (kyojines[j]!=null) {
								kyojines[j].velocidad = 0.2;
								pocimas[i] = null;
								break;
							}
						}
					}				
					if(pocimas[i].tipo == 2){
						for (int j = 0; j <= kyojines.length-1; j++) {
							if (kyojines[j]!=null) {
								kyojines[j]=null;
								pocimas[i] = null;
								break;
							}	
					}		
					}
				
					if(pocimas[i].tipo == 3){
						mikasa.convertida = true;
						pocimas[i] = null;
							break;
							}	
							
			
		}
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
		//si chocan con el borde, cambian de trayectoria
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if (kyojines[i].chocasteCon(entorno)) 
					kyojines[i].cambiarTrayectoria();
			}
		}
		
		for (int i = 0; i <= kyojines.length-1; i++) {
			
			//si el proyectil choca con un kyojin, lo mata
			if (proyectil !=null){
				if (kyojines[i]!=null) {
				if ((kyojines[i].x >= proyectil.x - 40) && (kyojines[i].x <= proyectil.x + 40) && (kyojines[i].y >= proyectil.y - 40) && (kyojines[i].y <= proyectil.y + 40) ){ 
					//crea craters en el array con la ubicacion donde mueren los kyojines
					crater [i] = new Obstaculo ((int)kyojines[i].x,(int) kyojines[i].y);
					kyojines[i]=null;
					//aumenta el numero de muertes
					kills +=1;
					}
			}
			}			
			}	
		//mueve los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				kyojines[i].mover();
			}
		}
		//si chocan con un obstaculo cambian de trayectoria
		for (int i = 0; i <= kyojines.length-1; i++) {
			for (int j = 0; j <= obstaculos.length-1; j++) {
			if (kyojines[i]!=null) {	
				if ((kyojines[i].x >= obstaculos[j].x - 35) && (kyojines[i].x <= obstaculos[j].x + 35) && (kyojines[i].y >= obstaculos[j].y - 35) && (kyojines[i].y <= obstaculos[j].y + 35) ){ 
					kyojines[i].cambiarTrayectoria();}
			}
		}
	}
		
		//cada diez segundos hace respawn de kyojines si hay menos de 4
		if (tiempo % 720 == 0) {
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]==null) {
				kyojines[i] = new Kyojin(40, 40, 1, Math.PI/4, 30);	
				kyojines[i].dibujarse(entorno);
				break;
			}
		}	
		}
		
		//chequea si quedan kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				fin = false;
				break;
				}
			else {
				fin = true;
			}
			}
		
		// si chocan con mykasa, mykasa muere
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if ((kyojines[i].x >= mikasa.x - 60) && (kyojines[i].x <= mikasa.x + 60) && (kyojines[i].y >= mikasa.y - 60) && (kyojines[i].y <= mikasa.y + 60)){ 
					if (mikasa.convertida == false){
						mikasa = null;
						fin = true;
				}
					if (mikasa.convertida == true){
							kyojines[i] = null;
							mikasa.convertida= false;
							kills +=1;
						;
				}
			}
			}
		}			
		//si quedan menos de 15 segundos, cambia el color del timer
		if (segundos >= 15){
			entorno.cambiarFont("Arial", 30, Color.white);	
			entorno.escribirTexto("TIME: " + segundos, 500, 100);
		}else{
			entorno.cambiarFont("Arial", 30, Color.red);
			entorno.escribirTexto("TIME: " + segundos, 500, 100);
			}
		entorno.cambiarFont("Arial", 25, Color.yellow);
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
		
