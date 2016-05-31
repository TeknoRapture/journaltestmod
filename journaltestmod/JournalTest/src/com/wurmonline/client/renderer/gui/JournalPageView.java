/**
 * 
 */
package com.wurmonline.client.renderer.gui;

import tk.teknorapture.wurmunlimited.clientmods.journal.Journal;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 */
public class JournalPageView extends JournalView{

	/**
	 * 
	 */
	public JournalPageView() {
		super("JournalPageView");
		
		// New page
		WurmArrayPanel<WurmLabel> mainView = new WurmArrayPanel<WurmLabel>("Journal Labels", WurmArrayPanel.DIR_VERTICAL);
		WurmLabel testLabel = new WurmLabel("JournalPageView");
		
		/*
		if(!Journal.RESIZABLE)
		{
			sizeFlags= FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;// I wish there was a "fill available space" hopefully default is that...
		}
		*/
		
		mainView.addComponent(testLabel);
		setComponent(mainView,WurmBorderPanel.CENTER);
		
		layout();
	}

}
