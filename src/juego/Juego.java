package juego;

import java.awt.Color;
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
	Obstaculo [] entradas;
//arrays de objetos
	Kyojin [] kyojines;
	Obstaculo [] obstaculos;
	Obstaculo [] crater;
	Pocima[] pocimas;
	Vidas[] vidas;
	Fireball[] fireball;
	//inicializar el tiempo en 6000 ticks / 60 segundos
	int tiempo = 6000;
	int segundos = 0;
	int kills = 0;
	boolean nivel = false;
	int cont_vidas = 3;
	boolean fin = false;
	int pausa = 1;
	
	Juego()
	{
		// Inicializa el objeto entorno, pero aun no lo inicia.
		entorno = new Entorno(this, "Attack on Titan", 800, 600);
		mikasa = new Mikasa(100, 500);
		fondo = new Fondo(0,0, 2);
		//array de entradas y obstaculos que son comunes a los tres niveles del juego
		entradas = new Obstaculo [3];
			entradas [0] = new Obstaculo (150, 300);
			entradas [1] = new Obstaculo (400, 150);
			entradas [2] = new Obstaculo (650 , 300);
		obstaculos = new Obstaculo [4];
			obstaculos [0] = new Obstaculo (200, 400);
			obstaculos [1] = new Obstaculo (600, 400);
			obstaculos [2] = new Obstaculo (200, 200);
			obstaculos [3] = new Obstaculo (600, 200);
		// array de obstaculos con la imagen del crater (tope tentativo de 20)
		crater = new Obstaculo [20];
		//array de pocimas con distintos efectos
		pocimas = new Pocima [4];
		//array de fireball del boss final
		fireball = new Fireball [3];
		/* 
		 * Es fundamental que recién al final del constructor de la clase Juego se 
		 * inicie el objeto entorno de la siguiente manera.
		 */
		entorno.iniciar();	
		//inicia la musica del juego
		Herramientas.loop("soundtrack.wav");
		//Herramientas.cargarSonido("soundtrack.wav").stop();	
	}
	
	public void tick(){
	
	if(nivel==false) {	
		fondo.fase = 1;
		fondo.dibujarse(entorno);
		entorno.cambiarFont("Arial", 45, Color.yellow);
		entorno.escribirTexto("WELCOME TO KARANES DISTRICT", 20, 70);
		//niveles como el Doom
		entorno.cambiarFont("Arial", 30, Color.red);
		entorno.escribirTexto("I'm Too Young to Die", 20, 420);
		entorno.escribirTexto("Ultra-Violence", 320, 270);
		entorno.escribirTexto("Nightmare", 590, 420);
		//dibuja las tres entradas a los niveles del juego
		for (int i = 0; i<= entradas.length -1; i++){
			entradas[i].dibujarEntrada(entorno);
		}
		mikasa.teclaRight(entorno); 
 		mikasa.teclaLeft(entorno); 
  		mikasa.teclaUp(entorno); 
  		mikasa.teclaDown(entorno);
		mikasa.dibujarse(entorno);		
		//nivel facil
		if (mikasa.seleccionNivel(mikasa.x, mikasa.y)== 1){
			/*opcion para inicarlos en posiciones random. por jugabilidad, preferimos iniciarlos en posiciones
			predeterminadas 
			kyojines = new Kyojin [4];
			for (int i = 0; i <= kyojines.length-1; i++) {
				kyojines[i] = new Kyojin((int) (Math.random() * 800 + 1), (int) (Math.random() * 600 + 1), 0.3, Math.PI/4, 30);
				for (int j = 0; j <= obstaculos.length-1; j++){
					if((kyojines[i].chocasteConObstaculo(obstaculos[j].x, obstaculos[j].y))||kyojines[i].chocasteCon(entorno)){
					i--;
					}
				}
			}
			*/
			//inicia kyojines y vidas de acuerdo al nivel de dificultad seleccionado		
			kyojines = new Kyojin [4];	
			kyojines[0] = new Kyojin(50, 50, 1, Math.PI/4, 30);
			kyojines[1] = new Kyojin(50, 550,1, Math.PI/4, 30);
			kyojines[2] = new Kyojin(550, 50,1, Math.PI/4, 30);
			kyojines[3] = new Kyojin(550, 550,1, Math.PI/4, 30);
			vidas = new Vidas [5];
			int ubicacion_vidas = 500;
			for (int i = 0; i <= vidas.length-1; i++) {
				vidas[i] = new Vidas(ubicacion_vidas, 550);
				ubicacion_vidas+=50;
			}
			//contador de vidas igual a longitud del array
			cont_vidas = vidas.length;
		    nivel=true;
			mikasa.reset();
		}
		//nivel medio
		if ((mikasa.seleccionNivel(mikasa.x, mikasa.y)== 2)){
			//inicia kyojines y vidas de acuerdo al nivel de dificultad seleccionado
			kyojines = new Kyojin [4];	
			kyojines[0] = new Kyojin(50, 50, 1.5, Math.PI/4, 30);
			kyojines[1] = new Kyojin(50, 550, 1.5, Math.PI/4, 30);
			kyojines[2] = new Kyojin(550, 50, 1.5, Math.PI/4, 30);
			kyojines[3] = new Kyojin(550, 550, 1.5, Math.PI/4, 30);		    
			vidas = new Vidas [4];
			int ubicacion_vidas = 500;
			for (int i = 0; i <= vidas.length-1; i++) {
				vidas[i] = new Vidas(ubicacion_vidas, 550);
				ubicacion_vidas+=50;
			}
			//contador de vidas igual a longitud del array
			cont_vidas = vidas.length;
			nivel=true;
			mikasa.reset();
		}
		//nivel dificil
		if ( (mikasa.seleccionNivel(mikasa.x, mikasa.y)== 3)){
			//inicia kyojines y vidas de acuerdo al nivel de dificultad seleccionado
			kyojines = new Kyojin [5];	
			kyojines[0] = new Kyojin(50, 50, 2.2, Math.PI/4, 30);
			kyojines[1] = new Kyojin(50, 550, 2.2, Math.PI/4, 30);
			kyojines[2] = new Kyojin(550, 50, 2.2, Math.PI/4, 30);
			kyojines[3] = new Kyojin(550, 550, 2.2, Math.PI/4, 30);
			kyojines[4] = new Kyojin(660, 490, 2.2, Math.PI/4, 30);
			vidas = new Vidas [3];
			int ubicacion_vidas = 500;
			for (int i = 0; i <= vidas.length-1; i++) {
				vidas[i] = new Vidas(ubicacion_vidas, 550);
				ubicacion_vidas+=50;
			}
			//contador de vidas igual a longitud del array
			cont_vidas = vidas.length;
			nivel=true;
			mikasa.reset();
		}
	}
	else {
		fondo.fase = 2;
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
		//chequea al principio del ciclo si mikasa esta viva, si esta muerta cambia el fondo.
	if (mikasa == null || segundos <= 0){
		fondo.fase = 3;
		fondo.dibujarse(entorno);
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
		//mikasa evita los obstaculos
		  for (int j = 0; j <= obstaculos.length-1; j++) {
			if (mikasa !=null){
				if (mikasa.chocasteCon(obstaculos[j].x, obstaculos[j].y) == true){
					mikasa.rodearObstaculo(obstaculos[j].x, obstaculos[j].y);
			} 
		}
		   }
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
		//si el boss no esta en juego, pone pocimas en lugares al azar de la pantalla
		if (boss == null){
		//cada cinco segundos pone una pocima de lentitud en el juego
		if (tiempo %600 == 0){
			pocimas[0] = new Pocima ((int) (Math.random() * 500 + 50), (int) (Math.random() * 500 + 50), 1);
		}
		//cada diez segundos pone una pocima mata kyojin en juego
		if (tiempo  %1000 == 0){
			pocimas[1] = new Pocima ((int) (Math.random() * 500 + 50), (int) (Math.random() * 500 + 50), 2);
		}
		//cada quince segundos pone una suero convertidor (?) en juego
		if (tiempo %1500 == 0){
			pocimas[2] = new Pocima ((int) (Math.random() * 500 + 50), (int) (Math.random() * 500 + 50), 3);
		}
		//dibuja las pocimas existentes
		for (int i = 0; i <= pocimas.length-1; i++) {
		if (pocimas[i]!=null) {
			pocimas[i].dibujarPocima(entorno);
			}
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
					
					if (mikasa.chocasteCon(pocimas[i].x, pocimas[i].y)== true){
					//reproduce sonido de pocima
					Herramientas.play("potion_drink.wav");
						if (pocimas[i].tipo == 1){
						for (int j = 0; j <= kyojines.length-1; j++) {
							if (kyojines[j]!=null) {
								kyojines[j].velocidad = 0.1;
								kyojines[j].congelado = true;
								pocimas[i] = null;
							}
						}
					}
					if(pocimas[i]!= null){	
					if(pocimas[i].tipo == 2){
						for (int j = 0; j <= kyojines.length-1; j++) {
							if (kyojines[j]!=null) {
								kyojines[j]=null;
								pocimas[i] = null;
								break;
							}	
					}		
					}
					}
					if(pocimas[i]!= null){	
					if(pocimas[i].tipo == 3){
						mikasa.convertida = true;
						pocimas[i] = null;
						break;
						}
					}				
				}
				}
			}
		//si quedan vidas, las dibuja. 
		for (int i = 0; i <= vidas.length-1; i++) {
			if (vidas[i]!=null) {
				vidas[i].dibujarse(entorno);
				}
			}
		//dibuja a mikasa			
		if (mikasa != null){
			mikasa.dibujarse(entorno);
		}
		//dibuja los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
			kyojines[i].cambiarAngulo(mikasa.x, mikasa.y);
			kyojines[i].dibujarse(entorno);
			}
		}		
		for (int i = 0; i <= kyojines.length-1; i++) {		
			//si el proyectil impacta con un kyojin, lo mata
			if (proyectil !=null){
				if (kyojines[i]!=null) {
				if ((proyectil.impacto(kyojines[i].x, kyojines[i].y))){ 
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
		//si chocan con un obstaculo cambian de trayectoria
		for (int i = 0; i <= kyojines.length-1; i++) {
			for (int j = 0; j <= obstaculos.length-1; j++) {
			if (kyojines[i]!=null) {	
				if (kyojines[i].chocasteConObstaculo(obstaculos[j].x, obstaculos[j].y) == true){	
					kyojines[i].cambiarTrayectoria();
			}
		}
		}
	}
		//si choca con otro kyojin, se distancian	
		for (int i = 0; i <= kyojines.length-1; i++) {
			for (int j = 0; j <= kyojines.length-1; j++) {
				if ((kyojines[i]!=null)&&(kyojines[j]!=null)) {	
					if ((kyojines[i].chocasteConKyojin(kyojines[j].x, kyojines[j].y,obstaculos[j].x, obstaculos[j].y) == true)&&(i != j)&&(kyojines[i].chocasteCon(entorno)==false)){	
						kyojines[i].distanciarVerticeSuperior();
						kyojines[j].distanciarVerticeInferior();
						break;
					}
				}
			}
		}	
		//si chocan con el borde, cambian de trayectoria
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
				if (kyojines[i].chocasteCon(entorno)==true){
					kyojines[i].cambiarTrayectoria();
				}
			}
		}		
		//mueve los kyojines
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null){
				kyojines[i].mover();
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
				//si mueren todos los kyojines, pone en juego al jefe final
				if ((boss == null)&&(kyojines[0] == null)&&(kyojines[1] == null)&&(kyojines[2] == null)&&(kyojines[3] == null)){
				//si mueren todos los kyojines, pone en juego al jefe final
				//al Boss en la esquina superior derecha y a Mikasa en la esquina inferior izquierda
				boss = new Boss(40, 40, 1.2, Math.PI/4, 30, 10);
				mikasa.x = 770;
				mikasa.y = 570;
			}
		}	
		// si chocan con mykasa, mykasa muere. si mikasa esta convertida, el kyojin muere
		for (int i = 0; i <= kyojines.length-1; i++) {
			if (kyojines[i]!=null) {
					if (mikasa.chocasteCon(kyojines[i].x, kyojines[i].y) == true){
					if ((mikasa != null) && (mikasa.convertida == false)){
							//baja las vidas
							if (vidas[cont_vidas-1]!=null){
								vidas[cont_vidas-1]=null;
								cont_vidas --;
								//reproduce sonido mikasa gritando
								Herramientas.play("mikasa_scream.wav");
								//respawnwa a mikasa en un lugar random	
								mikasa.x = (int) (Math.random() * 800 + 1);
								mikasa.y = (int) (Math.random() * 600 + 1);
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
		}
		//jefe final
		if (boss!=null){
			boss.cambiarAngulo(mikasa.x, mikasa.y);
			boss.dibujarse(entorno);
			boss.mover();
		//si el Boss toca a Mikasa, la mata	
		if (mikasa.chocasteCon(boss.x, boss.y) == true){
			mikasa = null;
			fin=true;
			}
		//si el proyectil impacta al Boss, le quita una vida.
		if (proyectil !=null){
			if(proyectil.impacto(boss.x, boss.y)){
				proyectil = null;
				boss.vidas --;
				}
			}
			entorno.cambiarFont("Arial", 25, Color.yellow);
			entorno.escribirTexto("BOSS HITS REMAINING  " + boss.vidas, 450, 160);
			if(boss.vidas == 0){
				boss = null;
				fin = true;
			}
		//cada cinco segundos el boss tira una bola de fuego
		if ((tiempo % 600 == 0)&& (boss != null)){
			for (int i = 0; i <= fireball.length-1; i++) {
				if (fireball[i]==null) {
					fireball[i] = new Fireball(boss.x, boss.y, 2.5, Math.PI/4, 30);	
					//reproduce sonido de fireball
					Herramientas.play("fireball.wav");
					break;		
					}
				}
			}
		//dibuja las bolas de fuego
			for (int i = 0; i <= fireball.length-1; i++) {
				if (fireball[i]!=null) {
					fireball[i].dibujar(entorno);
			}
		}
		//mueve las bolas de fuego
			for (int i = 0; i <= fireball.length-1; i++) {
				if (fireball[i]!=null) {
					fireball[i].mover();
			}
		}
		//si es necesario las hace rebotar con el limite de la pantalla
			for (int i = 0; i <= fireball.length-1; i++) {
				if (fireball[i]!=null) {
					if (fireball[i].chocasteCon(entorno)) 
						fireball[i].cambiarTrayectoria();
			}
		}
		//si una bola de fuego choca a mikasa, le baja una vida y hace un respawn random
			for (int i = 0; i <= fireball.length-1; i++) {
				if (fireball[i]!=null){
					if (mikasa !=null){
						if (mikasa.chocasteCon(fireball[i].x, fireball[i].y)) {
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
			}
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
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{	
		Juego juego = new Juego();
	

	}
}
		