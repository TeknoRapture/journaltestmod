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

import com.wurmonline.client.options.Options;
import com.wurmonline.client.resources.textures.ImageTexture;
import com.wurmonline.client.resources.textures.ImageTextureLoader;
import com.wurmonline.client.resources.textures.ResourceTexture;
import com.wurmonline.client.resources.textures.ResourceTextureLoader;

import tk.teknorapture.wurmunlimited.clientmods.journaltest.*;

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
		
		this.journal = new Journal();
		
		resizable = false;
		//iconImage = ///ignore for now
		
		WurmArrayPanel<WButton> buttons = new WurmArrayPanel<WButton>("Journal buttons", WurmArrayPanel.DIR_VERTICAL);
		buttons.setInitialSize(32, 256, false);
		
		
		//add buttons to the components
		/*
        buttons.addComponent(createButton("+", "Zoom in" , 0, new ButtonListener() {

			@Override
			public void buttonPressed(WButton p0) {
			}

			@Override
			public void buttonClicked(WButton p0) {
				liveMap.zoomIn();
			}
        }));
		*/
		
		JournalView journalView = new JournalView("Journal",journal,ViewWidth,ViewHeight);
		
		//add the components and "View" to the panel
        mainPanel.setComponent(journalView, WurmBorderPanel.WEST);
		//mainPanel.setComponent(buttons, WurmBorderPanel.EAST);
		
		setComponent(mainPanel);
		
		setInitialSize(ViewWidth + 6 + 32, ViewHeight + 25, false);
		layout();
		//sizeFlags = FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;
		
	}
	
	public void closePressed()
	{
		hud.toggleComponent(this);
	}

	public void toggle() {
		hud.toggleComponent(this);
	}
		
    /* // ago's code BEGIN
	private WButton createButton(String label, String tooltip, int textureIndex, ButtonListener listener) {
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
    // ago's code END */
	
}











