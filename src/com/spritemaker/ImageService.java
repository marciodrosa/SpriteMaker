package com.spritemaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageService {
	
	public void loadImagesAndSaveSprite(int maxColumns) {
		
	}
	
	private ImageSize calculateFinalImageSize(int maxColumns) {
		File[] inputFiles = listInputFiles();
		List<ImageSize> inputImagesSizes = new ArrayList<>();
		for (File file : inputFiles) {
			try {
				BufferedImage image = ImageIO.read(file);
				ImageSize imageSize = new ImageSize();
				imageSize.width = image.getWidth();
				imageSize.height = image.getHeight();
				inputImagesSizes.add(imageSize);
			} catch (Exception ex) {
				System.out.println(ex.toString());
				continue;
			}
		}
		return new Calculator().calculateFinalImageSize(inputImagesSizes, maxColumns);
	}
	
	private File[] listInputFiles() {
		File currentDir = new File("");
		return currentDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
	}
	
	private void createSpriteImage() {
		
	}
	
}
