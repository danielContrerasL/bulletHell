package View;

import java.util.Scanner;

import Model.Taste;
import Model.Icecream;


public class Main {

	private static Scanner scanner;

	public static void main(String[] args){
		scanner = new Scanner(System.in);
		String storeName = "La Hoja";
		String userName = "";
		int icecreamSize = 0;
		String icecreamType = "";
		int tasteCode = 0;
		int tasteType = 0;

		System.out.println("Bienvenidos a la heladeria " + storeName);
		System.out.print("Ingrese su nombre: ");
		userName = scanner.nextLine();
		System.out.println("Seleccione el tamaño de su helado.");
		System.out
				.println("1. Extra grande 4 bolas\n2. Grande 3 bolas\n3. Mediano 2 bolas \n4. Pequeño 1 bola");
		icecreamSize = scanner.nextInt();
		tasteCode = scanner.nextInt();
		
		
		
		
		System.out
				.println("Seleccione los sabores de su helado \"Puede repetir un mismo sabor\".");
		System.out
				.println("1. Durazno\n2. Mora\n3. Fresa\n4. Melocotón\n5. Coco\n6. Brownie");
		

		System.out.println("Heladeria " + storeName + "\n	Nombre: "
				
				
				
				
				
				+ icecream.getName() + "\n	Helado: "
				+ icecream.getIcecreamType() + "\n	Sabores: "
				+ taste.getTastesList()+"\nTotal: " + icecream.getValue());

	}

}
