/**
 * 
 */
package tk.teknorapture.wurmunlimited.clientmods.junk.teknoswurmapi;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * Code borrowed from ago as noted below
 * Once again ran into issues with Wurm and also Java scope
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.wurmonline.client.resources.textures.ImageTexture;
import com.wurmonline.client.resources.textures.ImageTextureLoader;
import com.wurmonline.client.resources.textures.ResourceTexture;
import com.wurmonline.client.resources.textures.ResourceTextureLoader;
import com.wurmonline.client.renderer.gui.*;

import org.gotti.wurmunlimited.modloader.classhooks.HookManager;


public class ImageHelper {
	public ImageHelper()
	{
		
	}
	
	
	/*/*** Begin Ago's code
	static BufferedImage loadIconImage(String classname, String imagename) {
		try {
			URL url = this.getClass().getClassLoader().getResource(imagename);
			if (url == null && this.getClass().getClassLoader() == HookManager.getInstance().getLoader()) {
				url = HookManager.getInstance().getClassPool().find(classname);
				if (url != null) {
					String path = url.toString();
					int pos = path.lastIndexOf('!');
					if (pos != -1) {
						path = path.substring(0, pos) + "!/"+imagename;
					}
					url = new URL(path);
				}
			}
			if (url != null) {
				return ImageIO.read(url);
			} else {
				return null;
			}
		} catch (IOException e) {
			Logger.getLogger(classname).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
	}

	/*** WButton is hidden...Don't need this here anyway...just here as an example
	public WButton createButton(String label, String tooltip, int textureIndex, ButtonListener listener) {
		if (iconImage != null) {
			BufferedImage image = iconImage.getSubimage(textureIndex * 32, 0, 32, 32);
			ImageTexture texture = ImageTextureLoader.loadNowrapNearestTexture(image);
			return new LiveMapButton("", tooltip, 32, 32, texture, listener);
		} else {
	        final String themeName = Options.guiSkins.options[Options.guiSkins.value()].toLowerCase(Locale.ENGLISH).replace(" ", "");
			final ResourceTexture backgroundTexture = ResourceTextureLoader.getTexture("img.gui.button.mainmenu." + themeName);
			return new WTextureButton(label, tooltip, backgroundTexture, listener);
		}
	}
	//*** End Ago's code
	//*/
}
