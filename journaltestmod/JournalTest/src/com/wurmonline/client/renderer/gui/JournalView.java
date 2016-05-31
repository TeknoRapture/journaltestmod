/**
 * 
 */
package com.wurmonline.client.renderer.gui;
/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * 
 * Parent view of the pages for the Journal mod
 */

import org.lwjgl.opengl.GL11;

import com.wurmonline.client.renderer.gui.JournalIndexPageView;

import tk.teknorapture.wurmunlimited.clientmods.journal.*;

public class JournalView extends WurmBorderPanel{

	//private final Journal journal;
	private JournalIndexPageView indexPageView;//probably should be a final type
	
	
	JournalView(String name)
	{
		super(name);
		
		
	}
	//JournalView(String name, Journal journal, int width, int height)
	JournalView(String name, int width, int height)
	{
		super(name);
		setInitialSize(width, height, false);
		sizeFlags = FlexComponent.FIXED_WIDTH | FlexComponent.FIXED_HEIGHT;//change for resizable
		 
		//this.journal = journal;
		
		//this.indexPageView = new JournalIndexPageView( width, height);
		
		/*//not needed
		if(this.journal.getFirstRun())
		{
			//placeholder
		}
		*/
		
	}
	
	protected void renderComponent(float alpha) {
		super.renderComponent(alpha);
		
		//liveMap.update(x, y);
				
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(r, g, b, 1.0F);

		//liveMap.render(0.0F, 0.0F, 1.0F);
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
}
