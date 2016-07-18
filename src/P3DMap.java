import java.io.*;

public class P3DMap {
	
	private P3DWall[] walls;
	
	public P3DMap(String mapFile) throws IOException {
		loadMap(mapFile);
	}

	private void loadMap(String mapFile) throws IOException {
		// Open the file.
		BufferedReader file = new BufferedReader(new FileReader(mapFile));
		
		int nWalls = Integer.parseInt(file.readLine());
		walls = new P3DWall[nWalls];
		
		for (int i = 0; i < nWalls; i++) {
			String[] pointStrs = file.readLine().split(" ", 4);
			walls[i] = new P3DWall(Double.parseDouble(pointStrs[0]),
					Double.parseDouble(pointStrs[1]),
					Double.parseDouble(pointStrs[2]),
					Double.parseDouble(pointStrs[3]));
		}
		file.close();
	}
}
