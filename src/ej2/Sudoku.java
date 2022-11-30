package ej2;

import java.util.*;

public class Sudoku {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int[][] nums = new int[3][3];
		int num = 0;
		int repet = 0;
		String repetidos = "";
		boolean repe = false;
		int cont = 1;
		int cont2 = 1;
// for dentro de for para rellenar cada elemento de la matriz
		System.out.println("Enter values into the array:");
		for (int fila = 0; fila < nums.length; fila++) {
			for (int columna = 0; columna < nums[0].length; columna++) {
				System.out.println("Introduzca la coordenada ( " + cont + "," + cont2 + ") :");
				nums[fila][columna] = in.nextInt();
				while (nums[fila][columna]>9||nums[fila][columna]<1) {
					System.err.println("Número inválido");
					System.out.println("Introduzca la coordenada ( " + cont + "," + cont2 + ") :");
					nums[fila][columna] = in.nextInt();
				}
				cont2++;
			}
			cont++;
		}
//Bucle para imprimir cada fila del array con array.toString
		for (int fila = 0; fila < nums.length; fila++) {
			System.out.println(Arrays.toString(nums[fila]));
		}
// El primer doble bucle seleccionará cada número de la matriz
		for (int fila = 0; fila < nums.length; fila++) {
			for (int columna = 0; columna < nums[0].length; columna++) {
				cont = 0;
// El doble bucle interno compara cada elemento del array con cada elemento de todo el array
				for (int fil = 0; fil < nums.length; fil++) {
					for (int col = 0; col < nums[0].length; col++) {
						if (nums[fila][columna] == nums[fil][col]) {
							cont++;
// Si un número se repite más de una vez el sudoku está repetido
							if (cont > 1) {
								repe = true;
								repet = nums[fila][columna];
// Con esta condición evitaré que los números repetidos del resultado se repitan
								if (repet != 0 && num % 10 != repet) {
									repetidos += repet;
								}
// Convierto repetidos en un int para poder hacer el resto y sacar el último número
								num = Integer.parseInt(repetidos);
								repet = 0;
							}
						}
					}
				}
			}
		}
// Con el metodo string builder insert hechoen clase introduciré un espacio entre cada número de los repetidos
		StringBuilder miSBuilder = new StringBuilder(repetidos);
		for (int i = 1, conta = 0; i < repetidos.length(); i++) {
			miSBuilder.insert(i +conta, ",");
			conta++;
		}
		
// Imprimimos el resultado
		if (repe) {
			System.out.println("No, los números repetidos son: " + miSBuilder);
		} else {
			System.out.println("Si");
		}
	}
}
