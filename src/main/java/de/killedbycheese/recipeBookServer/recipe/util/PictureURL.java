package de.killedbycheese.recipeBookServer.recipe.util;

import java.io.Serializable;

public class PictureURL implements Serializable {
	
	private static final long serialVersionUID = -7171832567538101752L;
	
	private long pictureID;
	private long recipeID;
	private String pictureURL;
	
	public PictureURL(long pictureID, long recipeID, String pictureURL) {
		super();
		this.pictureID = pictureID;
		this.recipeID = recipeID;
		this.pictureURL = pictureURL;
	}

	public long getPictureID() {
		return pictureID;
	}

	public long getRecipeID() {
		return recipeID;
	}

	public String getPictureURL() {
		return pictureURL;
	}
	
	

}
