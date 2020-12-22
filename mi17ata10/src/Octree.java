import java.util.ArrayList;

public class Octree {
    public Node root;
    private double width, height, depth;
    private Range treeNRange;
    private double theta;
    private ArrayList<CelestialBody> bodies = new ArrayList<CelestialBody>();
    private ArrayList<CelestialBody> nbodies = new ArrayList<CelestialBody>();


    //create new Octree
    public Octree(double width, double height, double depth) {
        this.treeNRange = new Range(width, height, depth);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    //insert Celestial Body, check if root == null, if null make new body the root, else insert recursively into accurate subnode
    public void insert(CelestialBody body) {
        if (treeNRange.inRange(body)) {             //check if in Range of the Universe
            if (root != null) {
                root.insert(body);
            } else {
                this.root = new Node(body, width, height, depth);
            }
            bodies.add(body);                       //if body added, add also to "bodies"
        }
    }


    // moves all bodies within tree according to the force exhibited on them by the universe
    public void moveAll() {
        //root.moveAll(this.root);
        nbodies.clear();
        for (CelestialBody b : bodies) {                        //copy into help-Arraylist "nbodies"
            nbodies.add(b);
        }
        for (CelestialBody b : nbodies) {                       //move all bodies
            b.move(root.force(b, new Vector3()));
        }
        this.clear();                                           //clear the tree
        for (CelestialBody b : nbodies) {                       //insert moved bodies in cleared tree
            this.insert(b);
        }
    }

    //deletes all nodes in Octree and clears Arraylist "bodies"
    public void clear() {
        if (root != null) {
            this.root.remove();
            this.root = null;
        }
        this.bodies.clear();
    }

    //draw all bodies in the tree
    public void drawTree() {
        for (CelestialBody b : bodies) {
            b.draw();
        }
    }
}




