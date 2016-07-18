
public class P3DCamera {

	private double x, y, rotation;
	
	public P3DCamera() {
		this.x = 0.0;
		this.y = 0.0;
		this.rotation = 0.0;
	}
	
	public P3DCamera(double x, double y, double rot) {
		this.x = x;
		this.y = y;
		this.rotation = rot;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	/**
	 * Move forward or backward along the direction the camera
	 * is facing.
	 * @param dist The distance to move (negative for backward).
	 */
	public void move(double dist) {
		x += dist * Math.cos(rotation);
		y += dist * Math.sin(rotation);
	}
	
	/**
	 * Rotate the camera. Positive rotates left/CCW, negative
	 * rotates right/CW.
	 * @param rads Radians to rotate.
	 */
	public void rotate(double rads) {
		rotation = (rotation + rads) % (Math.PI * 2.0);
	}

}
