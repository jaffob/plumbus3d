import java.awt.Graphics;

public abstract class P3DRenderer {

	protected P3DCamera cam;
	protected P3DMap map;
	
	protected int width, height;
	
	public P3DRenderer(P3DCamera cam, P3DMap map, int width, int height) {
		this.setCam(cam);
		this.setMap(map);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public abstract void render(Graphics g);

	public P3DCamera getCam() {
		return cam;
	}

	public void setCam(P3DCamera cam) {
		this.cam = cam;
	}

	public P3DMap getMap() {
		return map;
	}

	public void setMap(P3DMap map) {
		this.map = map;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
