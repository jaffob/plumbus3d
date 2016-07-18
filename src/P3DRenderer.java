import java.awt.Graphics;

public interface P3DRenderer {

	public void render(Graphics g, P3DCamera cam, P3DMap map, int width, int height);
	
}
