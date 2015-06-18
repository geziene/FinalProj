package Scale;

import java.io.*;
import java.net.*;
import java.util.*;


public class VaegtConsol {
	private static double brutto = 0;
	private static double tara = 0;
	private static String inline = "";
	private static String IndstruktionsDisplay = "";
	private static boolean running = true, loop = true, case6 = true;
	private static ServerSocket serversocket;
	private static Socket socket;
	static String temp = "1", pbId;
	static int port = 8000;
		
	public static void printmenu() {
		System.out.println("");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (brutto - tara) + " kg");
		System.out.println("Instruktionsdisplay: " + IndstruktionsDisplay);
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println("******");
		System.out.println("Brutto: " + (brutto) + " kg");
		System.out.println("******");
		System.out.println("Tast T for tara, B for ny brutto");
		System.out.print("Tast her: ");
	}
	
	public static void main(String[] args) {
		try {
			ASE ase = new ASE();
			serversocket = new ServerSocket(port);
			new Thread(ase).start();
			socket = serversocket.accept();
			PrintWriter ud = new PrintWriter(socket.getOutputStream());
	        BufferedReader ind = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner sc = new Scanner(System.in);
			ArrayList<String> raavareId = new ArrayList<String>();
			ArrayList<String> raavareNetto = new ArrayList<String>();

			while(running) {
				switch(temp) {
				
				case "1":
					System.out.print("Indtast dit operatoer ID: ");
					temp = sc.next();
					ud.println("1" + temp);
					ud.flush();
					String name = ind.readLine();
					System.out.println("Velkommen " + name);
					System.out.println("\nEr det overstaaende dit navn? (J/N)");
					temp = sc.next().toUpperCase();
					
					if(temp.equals("J")) 
						temp = "2";
					else 
						temp = "1";
					break;
					
				case "2":
					ud.println("2");
					ud.flush();
					System.out.print("Indtast oenskede produktbatch ID: ");
					pbId = sc.next();
					ud.println(pbId);
					ud.flush();
					System.out.println(ind.readLine());
					while(ind.ready()){
					System.out.println(ind.readLine());
					}
					
				case "3":
					ud.println("3");
					System.out.println("\nVaegten er klar til brug. Brutto: " + brutto);
					System.out.print("Tryk på vilkårlig tast og enter for at fortsætte.");
					sc.next();
					ud.println("1");
					ud.println(Integer.parseInt(pbId));
					ud.flush();
					printmenu();
					
				case "4":
					System.out.print("\nTarer vaegten: ");
					temp = sc.next().toUpperCase();
					if(temp.equals("T")){
						tara = brutto;
						printmenu();
					}
					else {
						System.out.print("Forkert kommando.");
						temp = "4";
						break;
					}
					
				case "5":
					System.out.print("\nPlacer en beholder på vaegten(brug b): ");
					temp = sc.next().toUpperCase();
					if(temp.equals("B")){
						System.out.print("Indtast brutto vaegt(kg): ");
						brutto = sc.nextDouble();
						printmenu();
					}
					else {
						System.out.print("Forkert kommando.");
						temp = "5";
						break;
					}
					while(loop){
					System.out.print("\nTarer vaegten: ");
					temp = sc.next().toUpperCase();
					if(temp.equals("T")){
						tara = brutto;
						printmenu();
						loop = false;
					}
					else {
						System.out.print("Forkert kommando.");
						}
					}
					
				case "6":
					System.out.print("\nIndtast en raavares ID inden du afvejer den, "
							+ "og indtast \"ok\" når du er færdig med afvejningen af alle raavare");
					while(case6){
					System.out.print("\nIndtast raavares ID: ");
					temp = sc.next();
					if(temp.equals("ok")){
						temp = "7";
						break;
					}
					raavareId.add(temp);
					System.out.print("Afvej raavare(brug b): ");
					temp = sc.next().toUpperCase();
					if(temp.equals("B")){
						System.out.print("Indtast brutto vaegt(kg): ");
						brutto = sc.nextDouble();
						raavareNetto.add(String.valueOf(brutto-tara));
						printmenu();
					}
					else {
						System.out.print("Forkert kommando.");
						temp = "6";
						break;
						}
					}
					break;
				case "7":
					ud.println("7");
					ud.flush();
					ud.println("2");
					ud.println(pbId);
					ud.flush();
					ud.println(raavareId.get(0));
					System.out.println("v " + raavareId.get(0));
					ud.println(raavareNetto.get(0));
					System.out.println("v " + raavareNetto.get(0));
					ud.println(raavareId.get(1));
					System.out.println("v " + raavareId.get(1));
					ud.println(raavareNetto.get(1));
					System.out.println("v " + raavareNetto.get(1));
					ud.println(raavareId.get(2));
					System.out.println("v " + raavareId.get(2));
					ud.println(raavareNetto.get(2));
					System.out.println("v " + raavareNetto.get(2));
					ud.flush();
					sc.next();
					
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
