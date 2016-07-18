import java.awt.Color;
import java.awt.Graphics;

public class P3DRenderer_Basic2D implements P3DRenderer {

	public P3DRenderer_Basic2D() {

	}


	@Override
	public void render(Graphics g, P3DCamera cam, P3DMap map, int width, int height) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		// Draw player.
		g.setColor(Color.BLACK);
		g.fillOval((int)cam.getX() - 5, (int)cam.getY() - 5, 10, 10);
		g.drawLine((int)cam.getX(), (int)cam.getY(), (int)(cam.getX() + 15.0 * Math.cos(cam.getRotation())), (int)(cam.getY() + 15.0 * Math.sin(cam.getRotation())));
		
		// Draw walls.
		for (P3DWall wall : map.getWalls()) {
			g.drawLine((int)wall.x1, (int)wall.y1, (int)wall.x2, (int)wall.y2);
		}
	}

}
