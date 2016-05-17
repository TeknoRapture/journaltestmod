package com.wurmonline.client.renderer.gui;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.gotti.wurmunlimited.modloader.classhooks.HookManager;

import com.wurmonline.client.options.Options;
import com.wurmonline.client.resources.textures.ImageTexture;
import com.wurmonline.client.resources.textures.ImageTextureLoader;
import com.wurmonline.client.resources.textures.ResourceTexture;
import com.wurmonline.client.resources.textures.ResourceTextureLoader;

import tk.teknorapture.wurmunlimited.clientmods.journal.*;

public class JournalWindow extends WWindow  {
	
	private WurmBorderPanel mainPanel;
	private Journal journal;
	private BufferedImage iconImage;

	private static int ViewWidth = 400;
	private static int ViewHeight = 500;
	
	
	public JournalWindow()
	{
		super("Journal", true);
		setTitle("Journal");
		mainPanel = new WurmBorderPanel("Journal");
		
		JournalView journalView = new JournalView("Journal",ViewWidth,ViewHeight);
		this.journal = new Journal(journalView);
		
		resizable = false;
		//iconImage = loadIconImage();
		iconImage = this.loadIconImageFromRes();
		
		
		WurmArrayPanel<WButton> buttons = new WurmArrayPanel<WButton>("Journal buttons", WurmArrayPanel.DIR_VERTICAL);
		buttons.setInitialSize(67, 500, false);
		
		
		//add buttons to the button panel
        buttons.addComponent(createButton("Index", "Go to the Index Page of the journal." , 2, new ButtonListener() {

			@Override
			public void buttonPressed(WButton p0) {
			}

			@Override
			public void buttonClicked(WButton p0) {
				journal.showIndex();
			}
        }));
		
        buttons.addComponent(createButton("AddNew", "Add New Page." , 0, new ButtonListener() {

			@Override
			public void buttonPressed(WButton p0) {
			}

			@Override
			public void buttonClicked(WButton p0) {
				journal.addNewPage();
			}
        }));
		
        buttons.addComponent(createButton("Search", "Go to the Search Page of the journal." , 5, new ButtonListener() {

			@Override
			public void buttonPressed(WButton p0) {
			}

			@Override
			public void buttonClicked(WButton p0) {
				journal.showSearch();
			}
        }));
        
        buttons.addComponent(createButton("Options", "Show Options." , 3, new ButtonListener() {

			@Override
			public void buttonPressed(WButton p0) {
			}

			@Override
			public void buttonClicked(WButton p0) {
				journal.showOptions();
			}
        }));
        
		//JournalView journalView = new JournalView("Journal",journal,ViewWidth,ViewHeight);
        
        
        
		//add the components and "View" to the panel
        mainPanel.setComponent(journalView, WurmBorderPanel.EAST);
		mainPanel.setComponent(buttons, WurmBorderPanel.WEST);
		
		setComponent(mainPanel);
		
		setInitialSize(ViewWidth + 6 + 67, ViewHeight + 25, false);
		layout();
		//sizeFlags = FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;///makes it unresizable even for the shrink
		
	}
	
	public void closePressed()
	{
		hud.toggleComponent(this);
	}

	public void toggle() {
		hud.toggleComponent(this);
	}
	
	private BufferedImage loadIconImageFromRes()
	{
		return Journal.getIconImageFromResources();
	}
	
    /*  // ago's code BEGIN
     *  // I need to move this to my API 
     *  // why doesn't ago use the Resources system here?
     */
	private BufferedImage loadIconImage() {
		try {
			URL url = this.getClass().getClassLoader().getResource("journal_icons.png");
			if (url == null && this.getClass().getClassLoader() == HookManager.getInstance().getLoader()) {
				url = HookManager.getInstance().getClassPool().find(JournalWindow.class.getName());
				if (url != null) {
					String path = url.toString();
					int pos = path.lastIndexOf('!');
					if (pos != -1) {
						path = path.substring(0, pos) + "!/journal_icons.png";
					}
					url = new URL(path);
				}
			}
			if (url != null) {
				Logger.getLogger(JournalWindow.class.getName()).log(Level.INFO,"ImageIO.read(url) url="+url.toString());
				return ImageIO.read(url);
			} else {
				return null;
			}
		} catch (IOException e) {
			Logger.getLogger(JournalWindow.class.getName()).log(Level.WARNING, e.getMessage(), e);
			return null;
		}
	}
	
	//*///really don't like how its done here...too specific, fix it when moved to API
	private WButton createButton(String label, String tooltip, int textureIndex, ButtonListener listener) {
		if (iconImage != null) {
			//BufferedImage image = iconImage.getSubimage(textureIndex * 32, 0, 32, 32);
			BufferedImage image = iconImage.getSubimage(textureIndex * 67, 0, 67, 30);
			ImageTexture texture = ImageTextureLoader.loadNowrapNearestTexture(image);
			return new JournalButton("", tooltip, 67, 30, texture, listener);
		} else {
	        final String themeName = Options.guiSkins.options[Options.guiSkins.value()].toLowerCase(Locale.ENGLISH).replace(" ", "");
			final ResourceTexture backgroundTexture = ResourceTextureLoader.getTexture("img.gui.button.mainmenu." + themeName);
			return new WTextureButton(label, tooltip, backgroundTexture, listener);
		}
	}
	//*/
    // ago's code END */
	
}











