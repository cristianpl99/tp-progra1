package juego;
import java.awt.Color;

import javax.sound.midi.Soundbank;

import entorno.*;

public class Juego extends InterfaceJuego
{
	private Entorno entorno;
 // otras variables del juego aqui
 //objetos
	Mikasa mikasa;
	Proyectil proyectil;
	Fondo fondo;
	Boss boss;
//arrays de objetos
	Kyojin [] kyojines;
	Obstaculo [] obstaculos;
	Obstaculo [] crater;
	Pocima[] pocimas;
	Vidas[] vidas;
	

	//inicializar el tiempo en 3600 ticks / 60 segundos
	int tiempo = 6000;
	int segundos = 0;
	int kills = 0;
	boolean nivel = false;
	int cont_vidas = 3;
	boolean fin = false;
	
	Juego()
	{
		// Inicializa el objeto entorno, pero aun no lo inicia.
		entorno = new Entorno(this, "Attack on Titan", 800, 600);
		mikasa = new Mikasa(400, 300);
		fondo = new Fondo(0,0, 2);
		obstaculos = new Obstaculo [4];
			obstaculos [0] = new Obstaculo (200, 400);
			obstaculos [1] = new Obstaculo (600, 400);
			obstaculos [2] = new Obstaculo (200, 200);
			obstaculos [3] = new Obstaculo (600, 200);
		kyojines = new Kyojin [4];	
			kyojines[0] = new Kyojin(50, 50, 0.4, Math.PI/4, 30);
			kyojines[1] = new Kyojin(50, 550, 0.4, Math.PI/4, 30);
			kyojines[2] = new Kyojin(550, 50, 0.4, Math.PI/4, 30);
			kyojines[3] = new Kyojin(550, 550, 0.4, Math.PI/4, 30);	
		// array de obstaculos con la imagen del crater (tope tentativo de 10)
		crater = new Obstaculo [10];
		//array de pocimas con distintos efectos
		pocimas = new Pocima [4];
		//array de vidas
		vidas = new Vidas [3];
			vidas[0]= new Vidas (550, 550);
			vidas[1]= new Vidas (650, 550);
			vidas[2]= new Vidas (750, 550);

		/* 
		 * Es fundamental que recién al final del constructor de la clase Juego se 
		 * inicie el objeto entorno de la siguiente manera.
		 */
		entorno.iniciar();	
		//inicia la musica del juego
		//Herramientas.play("soundtrack.wav");	
	}
	/*
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick(){
		//pantalla de inicio con seleccion de niveles
		/*
		while (nivel == false){
			fondo.dibujarse(entorno);
			mikasa.teclaRight(entorno); 
 			mikasa.teclaLeft(entorno); 
  			mikasa.teclaUp(entorno); 
  			mikasa.teclaDown(entorno);
			mikasa.dibujarse(entorno);
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				nivel = true;
		}
	}
	*/



		//dibuja el fondo
		fondo.dibujarse(entorno);
		
		//setea el tiempo
		tiempo -= 1;
		segundos = tiempo / 100;		          
		
		//si mata a los kyojines en pantalla, gana el juego
	if((fin == true)&& (mikasa != null)){
			entorno.cambiarFont("Arial", 70, Color.yellow);
			entorno.escribirTexto("VICTORY", 230, 280);
			entorno.cambiarFont("Arial", 40, Color.red);
			entorno.escribirTexto(kills + "  KYOJIN KILLED", 170, 450);
			entorno.escribirTexto("TITAN ELIMINATED", 170, 500);
		}
		//chequea al principio del ciclo si mikasa esta viva
	if (mikasa == null || segundos <= 0){
		fondo.fase = 3;
		entorno.cambiarFont("Arial", 70, Color.yellow);
		entorno.escribirTexto("GAME OVER", 210, 280);
		fin = true;
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
			Herramientas.play("misil.wav");
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
		if (tiempo == 5400 || tiempo == 4800 || tiempo == 4200 || tiempo == 3600 || tiempo == 3000 || tiempo == 2400 || tiempo == 1800 || tiempo == 1200 || tiempo == 600){
			pocimas[0] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 1);
		}
		//cada diez segundos pone una pocima mata kyojin en juego
		if (tiempo  == 5000 || tiempo == 4000 || tiempo == 3000 || tiempo == 2000 || tiempo == 1000){
			pocimas[1] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 2);
		}
		//cada quince segundos pone una suero convertidor (?) en juego
		if (tiempo  == 4500 || tiempo == 3000 || tiempo == 1500){
			pocimas[2] = new Pocima ((int) (Math.random() * 500 + 1), (int) (Math.random() * 500 + 1), 3);
		}
		
		for (int i = 0; i <= pocimas.length-1; i++) {
		if (pocimas[i]!=null) {
			pocimas[i].dibujarPocima(entorno);
			}
		}
		//dibuja el proyectil por encima de todo
		if (proyectil != null){  
			proyectil.dibujar(entorno);
			proyectil.mover();
		}
		//si mikasa agarra una pocima, chequea que tipo de pocima es y su efecto.
		//tipo 1 = baja la velocidad de los kyojines en juego
		//tipo 2 = mata a un kyojin al azar 
		//tipo 3 = suero convertidor
		for (int i = 0; i <= pocimas.length-1; i++) {
			if (pocimas[i]!=null) {
				if ((mikasa.x >= pocimas[i].x - 15) && (mikasa.x <= pocimas[i].x + 15) && (mikasa.y >= pocimas[i].y - 15) && (mikasa.y <= pocimas[i].y + 15) ) {
					if (pocimas[i].tipo == 1){
						for (int j = 0; j <= kyojines.length-1; j++) {
							if (kyojines[j]!=null) {
								kyojines[j].velocidad = 0.1;
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
		//
		//dibuja los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
			kyojines[i].cambiarAngulo(mikasa.x, mikasa.y);
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
					proyectil = null;
					//reproduce sonido de explosion
					Herramientas.play("explosion.wav");
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
		//y no esta el jefe en juego
		if ((tiempo % 720 == 0)&&(boss ==null)) {
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
			if ((kyojines[i]!=null)&& boss ==null) {
				fin = false;
				break;
				}
				//MALISIMO el codigo, mejorar
				if ((boss == null)&&(kyojines[0] == null)&&(kyojines[1] == null)&&(kyojines[2] == null)&&(kyojines[3] == null)){
				//si mueren todos los kyojines, pone en juego al jefe final
				boss = new Boss(1, 1, 01, Math.PI/4, 30, 3);
			}
		}
		
		// si chocan con mykasa, mykasa muere. si mikasa esta convertida, el kyojin muere
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if ((kyojines[i].x >= mikasa.x - 60) && (kyojines[i].x <= mikasa.x + 60) && (kyojines[i].y >= mikasa.y - 60) && (kyojines[i].y <= mikasa.y + 60)){ 
					if ((mikasa != null) && (mikasa.convertida == false)){
							//baja las vidas
							if (vidas[cont_vidas-1]!=null){
								vidas[cont_vidas-1]=null;
								cont_vidas --;
							//respawnwa a mikasa en un lugar random	
								mikasa.x = (int) (Math.random() * 800 + 1);
								mikasa.y = (int) (Math.random() * 600 + 1);
							}
						}
				}
					if ((mikasa != null) && (mikasa.convertida == true)){
							kyojines[i] = null;
							mikasa.convertida= false;
							kills +=1;
						;
				}
			}
			}

		//jefe final
		if (boss!=null){
			boss.cambiarAngulo(mikasa.x, mikasa.y);
			boss.dibujarse(entorno);
			boss.mover();
			if(boss.chocasteCon(entorno)){
				boss.cambiarTrayectoria();
			}
			if ((boss.x >= mikasa.x - 60) && (boss.x <= mikasa.x + 60) && (boss.y >= mikasa.y - 60) && (boss.y <= mikasa.y + 60)){
				mikasa = null;
				fin=true;
			}
			if (proyectil !=null){
				if ((boss.x >= proyectil.x - 40) && (boss.x <= proyectil.x + 40) && (boss.y >= proyectil.y - 40) && (boss.y <= proyectil.y + 40) ){ 
				proyectil = null;
				boss.vidas --;
				}
			}
			if(boss.vidas == 0){
				boss = null;
				fin = true;
			}
			entorno.cambiarFont("Arial", 25, Color.yellow);
			entorno.escribirTexto("BOSS HITS REMAINING  " + boss.vidas, 450, 160);
			}
		

		
		//si quedan vidas, las dibuja. si no quedan pone null a mikasa
		
		for (int i = 0; i <= vidas.length-1; i++) {
			if (vidas[i]!=null) {
				vidas[i].dibujarse(entorno);
				}
			}
		
		// si no quedan vidas, pone a mikasa en null

			if (cont_vidas==0){
				mikasa = null;
				fin = true;
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
		
