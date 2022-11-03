package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ToolImg {
	public static BufferedImage getImg(String path) {
		BufferedImage img;
		try {
			img = ImageIO.read(ToolImg.class.getResource(path));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
