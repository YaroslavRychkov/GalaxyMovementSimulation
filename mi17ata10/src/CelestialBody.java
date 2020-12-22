import java.awt.*;
import java.nio.charset.Charset;
import java.util.Random;

// This class represents celestial bodies like stars, planets, asteroids, etc..
public class CelestialBody {

    public Vector3 position; // position of the center.
    public double mass;
    private String name;
    private double radius;
    private Vector3 currentMovement;
    private Color color; // for drawing the body.


    public CelestialBody(String inName, double inMass, double inRadius, Vector3 inPosition, Vector3 inCurrentMovement, Color inColor) {
        this.name = inName;  //brauchen wir überhaupt einen namen??
        this.mass = inMass;
        this.radius = inRadius;
        this.position = inPosition;
        this.currentMovement = inCurrentMovement;
        this.color = inColor;
    }

    // Creates a random Celestial Body within the + - 5x5x5 au space

    public CelestialBody() {
        //random name
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        this.name = generatedString;
        //random Mass, min. 5.972e24 (erde), max 1.989e30 (sonne) + 5.972e24 (erde), in diesem Beispiel habe ich die maximale und minimale masse klein gehalten
        double MassMin = 5.972e2;
        double MaxMultiplier = 1.989e3;
        double multiplier = Math.random();
        this.mass = MassMin + (MaxMultiplier * multiplier);
        //selbes prinzip @radius
        double RadMin = 5.972e24;
        double RadMultiplier = 1.989e30;
        double multiplier2 = Math.random();
        this.radius = RadMin + (RadMultiplier * multiplier2);
        //Position sollte zwischen -(1/2) Universum und +1/2 universum sein; also random innerhalb des Universum Size machen, und halbes Universum abziehen - zufallsverteilung
        double AU = 150e9;
        double x, y, z;
        x = y = z = Simulation.UniverseSize * AU;
        x = (Math.random() * x) - (Simulation.UniverseSize/2 * AU);
        y = (Math.random() * y) - (Simulation.UniverseSize/2 * AU);
        z = (Math.random() * z) - (Simulation.UniverseSize/2 * AU);
        this.position = new Vector3(x, y, z);
        //movement nach selben prinzip, max geschwindigkeit aktuell die von Merkur; 47.87e3
        double tempo = 47.87e0;
        double x1, y1, z1;
        x1 = y1 = z1 = 2 * tempo;
        x1 = (Math.random() * x1) - tempo;
        y1 = (Math.random() * y1) - tempo;
        z1 = (Math.random() * z1) - tempo;
        //tempo test
        this.currentMovement = new Vector3(x1, y1, z1);
        //colour random
        double r, g, b;
        r = g = b = 255;
        Color random = new Color((int) (Math.random() * r), (int) (Math.random() * g), (int) (Math.random() * b));
        this.color = random;
    }

    // Returns the distance between this celestial body and the specified 'body'.
    public double distanceTo(CelestialBody body) {
        return this.position.distanceTo(body.position);
    }

    // Returns a vector representing the gravitational force exerted by 'body' on this celestial body.
    public Vector3 gravitationalForce(CelestialBody body) {
        Vector3 direction = body.position.minus(this.position);
        double r = direction.length();
        direction.normalize();
        double force = Simulation.G * this.mass * body.mass / (r * r);
        return direction.times(force);
    }

    public Vector3 gravitationalForce(Vector3 pos, double mass) {
        Vector3 direction = pos.minus(this.position);
        double r = direction.length();
        direction.normalize();
        double force = Simulation.G * this.mass * mass / (r * r);
        return direction.times(force);
    }


    // Moves this body to a new position, according to the specified force vector 'force' exerted
    // on it, and updates the current movement accordingly.
    // (Movement depends on the mass of this body, its current movement and the exerted force.)
    public void move(Vector3 force) {
        Vector3 newPosition = this.position.plus(this.currentMovement.plus(force.times(1 / this.mass)));
        Vector3 newMovement = newPosition.minus(this.position);
        //  System.out.println(this.position.toString()+"____***** old pos");
        this.position = newPosition;
        //  System.out.println(this.position.toString()+"____***** new pos");
        this.currentMovement = newMovement;
    }

    // Returns a string with the information about this celestial body including
    // name, mass, radius, position and current movement. Example:
    // "Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s."
    public String toString() {
        return "name: " + this.name + ", mass: " + this.mass + ", radius: " + this.radius + ", position: " +
                this.position.toString() + ", movement: " + this.currentMovement.toString();
    }

    // Draws the celestial body to the current StdDraw canvas as a dot using 'color' of this body.
    // The radius of the dot is in relation to the radius of the celestial body
    // (use a conversion based on the logarithm as in 'Simulation.java').
    public void draw() {
        this.position.drawAsDot((1e9 * Math.log10(this.radius)*50), this.color);

    }


}

