import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class NBody{
	
	public static void main(String[] args){								//runs an animation using NBody and Planet classes
		double totalTime = 100.0;
		double dt = 1.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}
		
		String fname= "./data/planets.txt";
		Planet[] planets = readPlanets(fname); // readPlanets(fname);
		double radius = readRadius(fname); // readRadius(fname);
		
//		System.out.printf("%d\n", planets.length);								// initial
//		System.out.printf("%.2e\n", radius);
//		for (int i = 0; i < planets.length; i++) {
//		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
//		   		              planets[i].myXPos, planets[i].myYPos, 
//		                      planets[i].myXVel, planets[i].myYVel, 
//		                      planets[i].myMass, planets[i].myFileName);	
//		}
		
		for(double t = 0.0; t < totalTime; t += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int k = 0; k < planets.length; k++) {
				xForces[k] = planets[k].calcNetForceExertedByX(planets);
				yForces[k] = planets[k].calcNetForceExertedByY(planets);
				
			}
			for (int k = 0; k < planets.length; k++) {
				planets[k].update(dt, xForces[k], yForces[k]);
			
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0,0,"images/starfield.jpg");
			}
			for (int i = 0; i < planets.length; i++) {
					planets[i].draw();
			}
			StdDraw.show(10);
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                      planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);	
			}
		
		
		
	}

	public static double readRadius(String fname) {				//extracts the radius size from file
	    try {
	        Scanner scan = new Scanner(new File(fname));
	        int numberOfPlanets = scan.nextInt();
	        double universeSize = scan.nextDouble();
	        scan.close();
	        return universeSize;
	    } 
	    catch (FileNotFoundException e) {
	        System.out.println("error"); 
	    	System.exit(0);
	    }
	    return -1;
	}
	
	public static Planet[] readPlanets(String fname) {			//extracts inputs for Planet parameters, creates Planet objects and adds them to an array
	    try {
	    	Scanner scan = new Scanner(new File(fname));
	        int numberOfPlanets = scan.nextInt();
	        Planet[] planets = new Planet[numberOfPlanets];
			double universeSize = scan.nextDouble();
			for (int k = 0; k < planets.length; k++) {
				double xp = Double.parseDouble(scan.next());
				double yp = Double.parseDouble(scan.next());
				double xv = Double.parseDouble(scan.next());
				double yv = Double.parseDouble(scan.next());
				double mass = Double.parseDouble(scan.next());
				String fileName = scan.next();
				Planet planet = new Planet(xp, yp, xv, yv, mass, fileName);
				planets[k] = planet;	
				}
			scan.close();
			return planets;
	    } 
	    catch (FileNotFoundException e) {
	        System.out.println("error"); 
	    	System.exit(0);
	    }
	    return null;
	}
}
