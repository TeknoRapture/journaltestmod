/**
 * 
 */
package com.wurmonline.client.renderer.gui;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * 
 * Parent view of the PAGES for the Journal mod
 */

import org.lwjgl.opengl.GL11;

import com.wurmonline.client.renderer.gui.JournalIndexPageView;

import tk.teknorapture.wurmunlimited.clientmods.journal.*;

public class JournalView extends WurmBorderPanel{//change to grid?

	//private final Journal journal;
	//private JournalIndexPageView indexPageView;//probably should be a final type
	ContainerComponent currentPage;
	
	JournalView(String name)
	{
		super(name);
		
		
	}
	//JournalView(String name, Journal journal, int width, int height)
	public JournalView(String name, int width, int height)
	{
		super(name);
		
		//*** NOTE: a constructor like WurmBorderPanel's
		//*** WurmBorderPanel(String _name, int x, int y, int width, int height)
		//*** May be required by the engine...
		//*** TODO: Testing
		
		setInitialSize(width, height, false);
		sizeFlags = FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;//change for resizable
		 
		//this.journal = journal;
		
		//this.indexPageView = new JournalIndexPageView( width, height);
		
		/* TEST BEGIN
		WurmArrayPanel<WurmLabel> testPanel = new WurmArrayPanel<WurmLabel>("test",WurmArrayPanel.DIR_VERTICAL);
		
		WurmLabel testLabel = new WurmLabel("Index Page","Test Index Page");
		WurmLabel testLabel1 = new WurmLabel("Index Page","Test Index Page");
		WurmLabel testLabel2 = new WurmLabel("Index Page","Test Index Page");
		WurmLabel testLabel3 = new WurmLabel("Index Page","Test Index Page");
		WurmLabel testLabel4 = new WurmLabel("Index Page","Test Index Page");
		WurmLabel testLabel5 = new WurmLabel("Index Page","Test Index Page");
		
		testPanel.addComponent(testLabel);
		testPanel.addComponent(testLabel1);
		testPanel.addComponent(testLabel2);
		testPanel.addComponent(testLabel3);
		testPanel.addComponent(testLabel4);
		testPanel.addComponent(testLabel5);
		
		this.setComponent(testPanel, WurmBorderPanel.CENTER);
		*///TEST END
		
		
		this.layout();
	}
	
	//probably need an interface here
	public void setPage(WurmBorderPanel page)
	{
		this.setComponent(page, WurmBorderPanel.CENTER);
		this.layout();
	}
	
	protected void renderComponent(float alpha) {
		super.renderComponent(alpha);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(r, g, b, 1.0F);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		
	}
	
}
