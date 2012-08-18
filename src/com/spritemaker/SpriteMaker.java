package com.spritemaker;

public class SpriteMaker {

	public static void main(String[] args) {
		int maxColumns = 1;
		if (args.length > 0) {
			try {
				maxColumns = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException ex) {
				System.out.println("Error: first arg must be a number for the max columns.");
				maxColumns = 1;
			}
		}
		new ImageService().loadImagesAndSaveSprite(maxColumns);
	}
}
