/**
 * 
 */
package com.wurmonline.client.resources;

/**
 * @author Teknorapture
 * @email teknorapture@gmail.com
 * 
 * TODO: Has issues, Finish later
 */
import com.wurmonline.client.GameCrashedException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class TeksPackResourceUrl extends ResourceUrl
{
	  private Pack pack;
	  private String filePath;
	  private String rawFilePath;
	  private Map<String, String> overrides;
	  
	  TeksPackResourceUrl(Pack aPack, String aFilePath)
	  {
	    super(aPack.getName() + ":" + aFilePath);
	    
	    this.pack = aPack;
	    this.overrides = new HashMap();
	    this.rawFilePath = aFilePath;
	    
	    int qPos = aFilePath.indexOf('?');
	    if (qPos == -1)
	    {
	      this.filePath = aFilePath;
	    }
	    else
	    {
	      this.filePath = aFilePath.substring(0, qPos);
	      while (qPos != -1)
	      {
	        int qNext = aFilePath.indexOf('&', qPos + 1);
	        int qTo = qNext != -1 ? qNext : aFilePath.length();
	        int eq = aFilePath.indexOf('=', qPos + 1);
	        if ((eq < qTo) && (eq != -1))
	        {
	          String key = aFilePath.substring(qPos + 1, eq);
	          String val = aFilePath.substring(eq + 1, qTo);
	          this.overrides.put(key, val);
	        }
	        else
	        {
	          String key = aFilePath.substring(qPos + 1, qTo);
	          this.overrides.put(key, key);
	        }
	        qPos = qNext;
	      }
	    }
	  }
	  
	  public PackResourceUrl derive(String newFilename)
	  {
	    int pathEnd = this.filePath.lastIndexOf('/');
	    String newPath = this.filePath.substring(0, pathEnd + 1) + newFilename;
	    PackResourceUrl newUrl = new PackResourceUrl(this.pack, newPath);
	    if (!newUrl.exists()) {
	      throw GameCrashedException.forFailure("Derived resource " + newUrl + " does not exist (source " + this + ")");
	    }
	    return newUrl;
	  }
	  
	  public PackResourceUrl changeFilePath(String newFilename)
	  {
	    PackResourceUrl newUrl = new PackResourceUrl(this.pack, newFilename);
	    if (!newUrl.exists()) {
	      throw GameCrashedException.forFailure("Changed filepath resource " + newUrl + " does not exist (source " + this + ")");
	    }
	    return newUrl;
	  }
	  
	  public InputStream openStream()
	    throws IOException
	  {
	    return this.pack.openStream(this.filePath);
	  }
	  
	  public boolean exists()
	  {
	    return this.pack.contains(this.filePath);
	  }
	  
	  public Map<String, String> getOverrides()
	  {
	    return this.overrides;
	  }
	  
	  public String getFilePath()
	  {
	    return this.filePath;
	  }
	  
	  public long getSize()
	  {
	    return this.pack.getSize(this.filePath);
	  }
	  
	  public boolean equals(Object o)
	  {
	    if ((o instanceof PackResourceUrl))
	    {
	    	TeksPackResourceUrl other = (TeksPackResourceUrl)o;
	      return (this.pack.equals(other.pack)) && (this.rawFilePath.equals(other.rawFilePath));
	    }
	    return false;
	  }
	  
	  public String toString()
	  {
	    return this.pack.getName() + ":" + this.rawFilePath;
	  }
	}