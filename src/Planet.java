import static java.lang.Math.*;

public class Planet{
	
	double myXPos;            // current x position
	double myYPos;            // current y position
	double myXVel;            // current velocity in x direction 
	double myYVel;            // current velocity in y direction
	double myMass;            // mass of planet
	String myFileName;        // file name (in images folder)
	
	
	
	public Planet(double xp, double yp, double xv,double yv,  		//constructor
			double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	
	public Planet(Planet p) {						//a constructor that clones a planet p
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public double calcDistance(Planet otherPlanet) {			//calculates distance between 2 planets
		double dx = this.myXPos - otherPlanet.myXPos;
		double dy = this.myYPos - otherPlanet.myYPos;
		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		return distance;
	}
	
	public double calcForceExertedBy(Planet otherPlanet) {			//calculates the force exerted by another planet on a given planet
		double g = 6.67 * Math.pow(10, -11);
		double r = calcDistance(otherPlanet);
		double force = g * this.myMass * otherPlanet.myMass / Math.pow(r, 2);
		return force;	
	}
	
	public double calcForceExertedByX(Planet otherPlanet) {			//calculates the force exerted by another planet on a given planet in the x direction
		double dx = otherPlanet.myXPos - myXPos;
		double r = calcDistance(otherPlanet);
		double xForce = calcForceExertedBy(otherPlanet) * dx / r;
		return xForce;
	}
	
	public double calcForceExertedByY(Planet otherPlanet) {			//calculates the force exerted by another planet on a given planet in the y direction
		double dy = otherPlanet.myYPos - myYPos;
		double r = calcDistance(otherPlanet);
		double yForce = calcForceExertedBy(otherPlanet) * dy / r;
		return yForce;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {			//calculates the net forces acting on a planet p in the x direction
		double sum = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (! allPlanets[i].equals(this)) {
			    sum += calcForceExertedByX(allPlanets[i]);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {			//calculates the net forces acting on a planet p in the y direction
		double sum = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (! allPlanets[i].equals(this)) {
			    sum += calcForceExertedByY(allPlanets[i]);
			}
		}
		return sum;
	}
	
	public void update(double seconds, double xForce, double yForce) {			//updates the position and velocity of a planet
		double accelX = xForce / myMass;
		double accelY = yForce / myMass;
		myXVel = myXVel + seconds * accelX;
		myYVel = myYVel + seconds * accelY;
		myXPos = myXPos + seconds * myXVel;
		myYPos = myYPos + seconds * myYVel;
	}
	
	public void draw() {												//draws the planet on its position
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}

