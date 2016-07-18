import java.awt.Color;
import java.awt.Graphics;

public class P3DRenderer_v1 extends P3DRenderer {

	private double fov;
	
	// Color constants.
	private final Color COLOR_FLOOR = new Color(91, 127, 0);
	private final Color COLOR_SKY = new Color(127, 201, 255);
	
	public P3DRenderer_v1(P3DCamera cam, P3DMap map, int width, int height, double fov) {
		super(cam, map, width, height);
		this.setFov(fov);
	}

	@Override
	public void render(Graphics g) {
		renderBackground(g);
		
		// Placeholder loop; later we will render these in the right order.
		for (P3DWall w : map.getWalls()) {
			renderWall(g, w);
		}
	}
	
	private void renderBackground(Graphics g) {
		g.setColor(COLOR_SKY);
		g.fillRect(0, 0, width, height / 2);
		g.setColor(COLOR_FLOOR);
		g.fillRect(0, height / 2, width, height / 2);
	}
	
	private void renderWall(Graphics g, P3DWall w) {
		
	}

	public double getFov() {
		return fov;
	}

	public void setFov(double fov) {
		this.fov = fov;
	}

}
