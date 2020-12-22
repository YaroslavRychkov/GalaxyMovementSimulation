import java.awt.*;

// This class represents vectors in a 3D vector space.
public class Vector3 {

    private double x;
    private double y;
    private double z;

    public Vector3(float inX, float inY, float inZ) {
        this.x = inX;
        this.y = inY;
        this.z = inZ;
    }

    public Vector3(double inX, double inY, double inZ) {
        this.x = inX;
        this.y = inY;
        this.z = inZ;
    }

    public Vector3() {
    }

    // Returns the sum of this vector and vector 'v'.
    public Vector3 plus(Vector3 v) {
        Vector3 result = new Vector3();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        result.z = this.z + v.z;
        return result;
    }

    // Returns the product of this vector and 'd'.
    public Vector3 times(double d) {
        Vector3 result = new Vector3();
        result.x = this.x * d;
        result.y = this.y * d;
        result.z = this.z * d;
        return result;
    }

    // Returns the sum of this vector and -1*v.
    public Vector3 minus(Vector3 v) {
        Vector3 result = new Vector3();
        result.x = this.x + (-1 * v.x);
        result.y = this.y + (-1 * v.y);
        result.z = this.z + (-1 * v.z);
        return result;
    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3 v) {
        Vector3 dif = this.minus(v);
        return Math.sqrt((dif.x * dif.x) + (dif.y * dif.y) + (dif.z * dif.z));
    }

    // Returns the length (norm) of this vector.
    public double length() {
        return this.distanceTo(new Vector3());
    }

    // Normalizes this vector: changes the length of this vector such that it becomes one.
    // The direction and orientation of the vector is not affected.
    public void normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
    }

    // Draws a filled circle with the center at (x,y) coordinates of this vector
    // in the existing StdDraw canvas. The z-coordinate is not used.
    public void drawAsDot(double radius, Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(this.x, this.y, radius);
    }

    // Returns the coordinates of this vector in brackets as a string
    // in the form "[x,y,z]", e.g., "[1.48E11,0.0,0.0]".
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }



    public static Vector3 comCal(Vector3 oldCOM, double oldMass, Vector3 bodyPos, double bodyMass) {
        Vector3 result = new Vector3();
        result.x = (oldCOM.x * oldMass + bodyPos.x * bodyMass) / (oldMass + bodyMass);
        result.y = (oldCOM.y * oldMass + bodyPos.y * bodyMass) / (oldMass + bodyMass);
        result.z = (oldCOM.z * oldMass + bodyPos.z * bodyMass) / (oldMass + bodyMass);
        return result;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

}

