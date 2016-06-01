package com.wurmonline.client.renderer.gui;

import tk.teknorapture.wurmunlimited.clientmods.journal.Journal;

public class JournalSearchView extends WurmBorderPanel{

	JournalSearchView() {
		
	}

	public JournalSearchView(int width, int height) {
		super("Journal Search Page");
		
		setInitialSize(width,height,false);
		
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}
		
		WurmArrayPanel<FlexComponent> mainPanel = new WurmArrayPanel<FlexComponent>("Search",WurmArrayPanel.DIR_VERTICAL);
		
		WurmLabel testLabel = new WurmLabel("Search Page: SEARCH NOT YET IMPLEMENTED!","Test Search Page");
		
		mainPanel.addComponent(testLabel);
		
		this.setComponent(mainPanel, WurmBorderPanel.CENTER);
		
		this.layout();
		
		
	}
}
