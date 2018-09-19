// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Design6;

/**
 * This class contains instances of coordinates in either polar or
 * cartesian format.  It also provides the utilities to convert
 * them into the other type. It is not an optimal design, it is used
 * only to illustrate some design issues.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @version July 2000
 */
public interface PointCP {

    //Interface methods **************************************************

    public double getX();

    public double getY();

    public double getRho();

    public double getTheta();


    /**
     * Calculates the distance in between two points using the Pythagorean
     * theorem  (C ^ 2 = A ^ 2 + B ^ 2). Not needed until E2.30.
     *
     * @param pointB The second point.
     * @return The distance between the two points.
     */
    public double getDistance(PointCP pointB);

    /**
     * Rotates the specified point by the specified number of degrees.
     * Not required until E2.30
     *
     * @param point    The point to rotate
     * @param rotation The number of degrees to rotate the point.
     * @return The rotated image of the original point.
     */
    public PointCP rotatePoint(double rotation);

    /**
     * Returns information about the coordinates.
     *
     * @return A String containing information about the coordinates.
     */
    default String toString() {
        return "Stored as " + (typeCoord == 'C'
                ? "Cartesian  (" + getX() + "," + getY() + ")"
                : "Polar [" + getRho() + "," + getTheta() + "]") + "\n";
    }
}