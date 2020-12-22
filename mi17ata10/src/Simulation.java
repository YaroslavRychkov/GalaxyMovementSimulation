public class Simulation {


    // gravitational constant
    public static final double G = 6.6743e-11;
    // one astronomical unit (AU) is the average distance of earth to the sun.
    public static final double AU = 150e9;
    // all quantities are based on units of kilogram respectively second and meter.
    //how many AU should the universe be and how many Planets we want
    public static final double UniverseSize = 10000;


    // The main simulation method using instances of other classes.
    public static void main(String[] args) {



        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(-UniverseSize/2 * AU, UniverseSize/2 * AU);
        StdDraw.setYscale(-UniverseSize/2 * AU, UniverseSize/2 * AU);
        StdDraw.enableDoubleBuffering();


        double seconds = 0;

        //create first octree - working as intended

        CelestialBody firstBody = new CelestialBody();
        Octree octtree = new Octree(UniverseSize * AU, UniverseSize * AU, UniverseSize * AU);

        // create specified number of celestial bodies and insert them into the Octree - working as intended

        int BodyCount = 100000;

        for (int i = 1; i < BodyCount; i++) {
            CelestialBody testBody = new CelestialBody();
            octtree.insert(testBody);
        }

        octtree.drawTree();
        StdDraw.show();

        // simulation loop, 3600 movements, then creating a new Octree with the new positions and drawing it

        while (true) {

            seconds++; // each iteration computes the movement of the celestial bodies within one second.
            octtree.moveAll();
            //to better see movement, comment out next line clear
            StdDraw.clear(StdDraw.WHITE);
            octtree.drawTree();
            StdDraw.show();
            //second counter to see how many times systems were moved
            if (seconds % (300) == 0) {
                System.out.println("300 seconds passed");
            }

        }


    }
}