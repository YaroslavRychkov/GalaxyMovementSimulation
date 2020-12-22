import java.awt.*;

//Range shows in which octant a body is located

public class Range {
    private double xMin, xMax, yMin, yMax, zMin, zMax,mpX,mpY,mpZ;
    private double width, height, depth;
    private Vector3 mp;


    public Vector3 getMp() {
        return mp;
    }

    public Range(double width, double height, double depth) {       //nur für root
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.xMin = (-1) * width / 2;
        this.xMax = width / 2;
        this.yMin = (-1) * height / 2;
        this.yMax = height / 2;
        this.zMin = (-1) * depth / 2;
        this.zMax = depth / 2;
        this.mpX = (xMin + xMax) / 2;
        this.mpY = (yMin + yMax) / 2;
        this.mpZ = (zMin + zMax) / 2;

        this.mp = new Vector3(mpX,mpY,mpZ);

    }

    public Range(Range oldNRange, int whichSubnode) {
        this.width = oldNRange.width / 2;
        this.height = oldNRange.height / 2;
        this.depth = oldNRange.depth / 2;


        switch (whichSubnode) {
            case 1:
                this.xMin = oldNRange.xMin;
                this.xMax = oldNRange.mp.getX();
                this.yMin = oldNRange.mp.getY();
                this.yMax = oldNRange.yMax;
                this.zMin = oldNRange.zMin;
                this.zMax = oldNRange.mp.getZ();
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 2:
                this.xMin = oldNRange.mp.getX();
                this.xMax = oldNRange.xMax;
                this.yMin = oldNRange.mp.getY();
                this.yMax = oldNRange.yMax;
                this.zMin = oldNRange.zMin;
                this.zMax = oldNRange.mp.getZ();
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 3:
                this.xMin = oldNRange.xMin;
                this.xMax = oldNRange.mp.getX();
                this.yMin = oldNRange.yMin;
                this.yMax = oldNRange.mp.getY();
                this.zMin = oldNRange.zMin;
                this.zMax = oldNRange.mp.getZ();
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 4:
                this.xMin = oldNRange.mp.getX();
                this.xMax = oldNRange.xMax;
                this.yMin = oldNRange.yMin;
                this.yMax = oldNRange.mp.getY();
                this.zMin = oldNRange.zMin;
                this.zMax = oldNRange.mp.getZ();
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 5:
                this.xMin = oldNRange.xMin;
                this.xMax = oldNRange.mp.getX();
                this.yMin = oldNRange.mp.getY();
                this.yMax = oldNRange.yMax;
                this.zMin = oldNRange.mp.getZ();
                this.zMax = oldNRange.zMax;
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 6:
                this.xMin = oldNRange.mp.getX();
                this.xMax = oldNRange.xMax;
                this.yMin = oldNRange.mp.getY();
                this.yMax = oldNRange.yMax;
                this.zMin = oldNRange.mp.getZ();
                this.zMax = oldNRange.zMax;
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 7:
                this.xMin = oldNRange.xMin;
                this.xMax = oldNRange.mp.getX();
                this.yMin = oldNRange.yMin;
                this.yMax = oldNRange.mp.getY();
                this.zMin = oldNRange.mp.getZ();
                this.zMax = oldNRange.zMax;
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
            case 8:
                this.xMin = oldNRange.mp.getX();
                this.xMax = oldNRange.xMax;
                this.yMin = oldNRange.yMin;
                this.yMax = oldNRange.mp.getY();
                this.zMin = oldNRange.mp.getZ();
                this.zMax = oldNRange.zMax;
                this.mp = new Vector3((xMin + xMax) / 2, (yMin + yMax) / 2, (zMin + zMax) / 2);
                break;
        }
    }
    //new Range is 1/8 of the previous range when a N_Range node is split into 8 further nodes.

    public Range() {
    }

    public String coordinates() {
        return xMin + " x min," + xMax + "x max" + yMin + " y min," + yMax + "y max" + zMin + " z min," + zMax + "z max";
    }

    //checks in which octant the specified positions fall

    public boolean inRange(CelestialBody body) {
        Vector3 position = body.position;
        double x = position.getX();
        double y = position.getY();
        double z = position.getZ();

        return ((x <= xMax) && (x >= xMin)
                && (y <= yMax) && (y >= yMin)
                && (z <= zMax) && (z >= zMin));
    }

    public int isInRange(double posX, double posY, double posZ) {   //in what range
        if ((posX <= this.mp.getX()) && (posY >= this.mp.getY()) && (posZ <= mp.getZ())) {
            return 1;                                                                           //LE UP FR
        }
        if ((posX >= this.mp.getX()) && (posY >= this.mp.getY()) && (posZ <= mp.getZ())) {
            return 2;                                                                           //RI UP FR
        }
        if ((posX <= this.mp.getX()) && (posY <= this.mp.getY()) && (posZ <= mp.getZ())) {
            return 3;                                                                           //LE DO FR
        }
        if ((posX >= this.mp.getX()) && (posY <= this.mp.getY()) && (posZ <= mp.getZ())) {
            return 4;                                                                           //RI DO FR
        }
        if ((posX <= this.mp.getX()) && (posY >= this.mp.getY()) && (posZ >= mp.getZ())) {
            return 5;                                                                           //LE UP BA
        }
        if ((posX >= this.mp.getX()) && (posY >= this.mp.getY()) && (posZ >= mp.getZ())) {
            return 6;                                                                           //RI UP BA
        }
        if ((posX <= this.mp.getX()) && (posY <= this.mp.getY()) && (posZ >= mp.getZ())) {
            return 7;                                                                           //LE DO BA
        }
        if ((posX >= this.mp.getX()) && (posY <= this.mp.getY()) && (posZ >= mp.getZ())) {
            return 8;                                                                           //RI DO BA
        } else return -1;
    }

    public double getWidth() {
        return width;
    }

    public void drawnode() {
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.0006);
        StdDraw.line(xMin, yMin, xMax, yMin);
        StdDraw.line(xMax, yMin, xMax, yMax);
        StdDraw.line(xMax, yMax, xMin, yMax);
        StdDraw.line(xMin, yMax, xMin, yMin);
    }
}