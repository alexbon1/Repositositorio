package ej1;

import java.util.Arrays;
import java.util.Scanner;

import java.util.*;

public class Buscaminas {
	Scanner in = new Scanner(System.in);
	String[][] campo = new String[5][5];
	boolean gameOver = false;
	int minax;
	int minay;

//Método que hace que al tocar una mina el juego se acabe
	public boolean gameOver() {
		gameOver = campo[minax][minay] == "*" ? true : false;
		return gameOver;
	}

//Método para rellenar la matriz de minas o espacio en blanco (barro)
	public String[][] minar() {
		int fil = 0;
		int col = 0;
//Escaner para elegir si rellenar de forma manual o aleatoria
		System.out.println("Desea insertar las minas de forma aleatoria o a mano? : 1 aleatoria / 2 manual ");
		int modo = in.nextInt();
		if (modo == 1) {
			for (int fila = 0; fila < campo.length; fila++) {
				for (int columna = 0; columna < campo[0].length; columna++) {
// Math.random dará un resultado entre 1 o 0 por lo que si en <0.5 pondrá mina y si es mayor barro
					campo[fila][columna] = Math.random() < 0.5 ? "*" : "-";
				}
			}
		} else if (modo == 2) {
// Forma manual de rellenar la matriz
			for (int fila = 0; fila < campo.length; fila++) {
				col = 0;
				for (int columna = 0; columna < campo[0].length; columna++) {
					System.out.println("¿Qué hay en la posición " + fil + "," + col + " ?");
					campo[fila][columna] = in.next();
// Si lo introducido no es ni mina ni barro no obliga a volver a introducirlo
					while (!(campo[fila][columna].equals("*") || campo[fila][columna].equals("-"))) {
						System.err.println("Eso no es ni barro ni una mina soldado, intentalo de nuevo");
						campo[fila][columna] = in.next();
					}
					col++;
				}
				fil++;
			}

		}
//Imprime la matriz para que el programa sea más gráfico
		for (int fila = 0; fila < campo.length; fila++) {
			System.out.println(Arrays.toString(campo[fila]));
		}
		return campo;
	}
//Método para seleccionar contar las minas alrededor

	public int minas(int minasx, int minasy) {
		int minas = 0;
// Se elige la coordenada a consultar
		System.out.print("Introduzca coordenada x de la mina : ");
		minax = in.nextInt();
		System.out.print("Introduzca coordenada y de la mina : ");
		minay = in.nextInt();
// Este while previene un out of bounds
		while (minax > campo.length - 1 || minay > campo.length - 1) {
			System.err.println("Has salido de la zona soldado, vuelve y busca esa mina");
			minax = in.nextInt();
			minay = in.nextInt();
		}
//LLamada al método gameOver 
		gameOver();
		/*
		 * Esto delímita la posición desde que el programa empezará a contar las minas y
		 * hasta donde llegará Si se cumple la condición de minimo no se tomará valor a
		 * partir del número y si no se le resta 1 y máximos al contrario
		 */

		int arriba = minax == 0 ? minax : minax - 1;
		int abajo = minax == (campo.length - 1) ? minax : minax + 1;
		int izquierda = minay == 0 ? minay : minay - 1;
		int derecha = minay == (campo.length - 1) ? minay : minay + 1;
// Este bucle aumentará el número de  minas cuando al recorrer se encuentre con una sin contar el mismo número
		for (int i = arriba; i <= abajo; i++) {
			for (int j = izquierda; j <= derecha; j++) {
				if (campo[i][j].equals("*") && !(i == minax && j == minay)) {
					minas++;
				}
			}
		}
//Devuelve la cantidad de minas
		return minas;
	}

	public static void main(String[] args) {
		Buscaminas ejercicio = new Buscaminas();
// LLamada al método de minar
		ejercicio.minar();
		int minasx = 0;
		int minasy = 0;
		boolean end = false;
// Si no es Game Over el programa continua
		while (end == false) {
// Al imprimir se llama a minas y en el se verá si es Game Over o nó
			System.out.println(ejercicio.minas(minasx, minasy) + " minas te rodean");
			if (ejercicio.gameOver()) {
// Si es Game Over se imprimiráde forma gráfica 
				for (int i = 0; i < 2; i++) {
					System.err.println("-----------------------------------");
					esperar(1);
				}
				System.err.println("-------------GAME OVER-------------");
				esperar(1);
				System.err.println("----------¡¡Has Muerto!!-----------");
				for (int i = 0; i < 2; i++) {
					esperar(1);
					System.err.println("-----------------------------------");
				}
				end = true;
			} else {
// Si no es Game Over imprime y el programa se repite
				System.out.println("Enhorabuena soldado, vivirás un día más");
			}

		}
	}

// Método ya utilizado en varias ocasiones para esperar
	public static void esperar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}