import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

public class Plumbus3D extends JComponent implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	// Size of screen.
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	// Frame rate (frames per second).
	private final int FPS = 30;
	
	// Movement speed constants, in units per second.
	private final double MOVE_SPEED = 100.0;
	private final double ROT_SPEED = Math.PI;
	
	// Input flags and current speed.
	private boolean in_forward, in_backward, in_left, in_right;
	private double move_inc, rot_inc;
	
	// Camera and map.
	private P3DCamera cam;
	private P3DMap map;
	
	public Plumbus3D(String mapFile) throws IOException {
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        in_forward = false;
        in_backward = false;
        in_left = false;
        in_right = false;
        
        move_inc = 0;
        rot_inc = 0;
        
        cam = new P3DCamera();
        map = new P3DMap(mapFile);
	}
	
	public void run() {
		while (true) {
			
			processInput();
			moveRotateCamera();
			
			// Redraw the screen and sleep.
			repaint();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void debugPrint() {
		System.out.println("Camera at (" + cam.getX() + ", " + cam.getY() + ") and " + cam.getRotation() + " rads [move " + move_inc + ", rot " + rot_inc + "]");
	}

	private void processInput() {
		
		// Process movement.
		if (in_forward && !in_backward)
			move_inc = MOVE_SPEED / FPS;
		else if (in_backward && !in_forward)
			move_inc = -MOVE_SPEED / FPS;
		else
			move_inc = 0;
		
		// Process rotation.
		if (in_left && !in_right)
			rot_inc = ROT_SPEED / FPS;
		else if (in_right && !in_left)
			rot_inc = -ROT_SPEED / FPS;
		else
			rot_inc = 0;
	}
	
	private void moveRotateCamera() {
		if (move_inc != 0.0)
			cam.move(move_inc);
		if (rot_inc != 0.0)
			cam.rotate(rot_inc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPlayer2D(g);
	}

	private void drawPlayer2D(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillOval((int)cam.getX() - 5, (int)cam.getY() - 5, 10, 10);
		g.drawLine((int)cam.getX(), (int)cam.getY(), (int)(cam.getX() + 15.0 * Math.cos(cam.getRotation())), (int)(cam.getY() + 15.0 * Math.sin(cam.getRotation())));
	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		Plumbus3D p3d;
		try {
			p3d = new Plumbus3D("test.p3d");
		} catch (IOException e) {
			System.err.println("Error reading map file.");
			return;
		}
		window.getContentPane().add(p3d, BorderLayout.CENTER);
		
		window.setSize(WIDTH, HEIGHT);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		p3d.run();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			in_forward = true;
			break;
		case KeyEvent.VK_S:
			in_backward = true;
			break;
		case KeyEvent.VK_A:
			in_left = true;
			break;
		case KeyEvent.VK_D:
			in_right = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			in_forward = false;
			break;
		case KeyEvent.VK_S:
			in_backward = false;
			break;
		case KeyEvent.VK_A:
			in_left = false;
			break;
		case KeyEvent.VK_D:
			in_right = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
