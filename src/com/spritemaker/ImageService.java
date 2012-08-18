package com.spritemaker;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageService {
	
	public void loadImagesAndSaveSprite(int maxColumns) {
		ImageSize imageSize = calculateFinalImageSize(maxColumns);
		BufferedImage outputImage = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = outputImage.createGraphics();
		int x = 0, y = 0, column = 0, maxHeight = 0;
		File[] inputFiles = listInputFiles();
		for (File inputFile : inputFiles) {
			try {
				column++;
				if (column > maxColumns) {
					x = 0;
					y += maxHeight;
					maxHeight = 0;
					column = 1;
				}
				BufferedImage inputImage = ImageIO.read(inputFile);
				graphics.drawImage(inputImage, x, y, null);
				if (inputImage.getHeight() > maxHeight) {
					maxHeight = inputImage.getHeight();
				}
				x += inputImage.getWidth();
			} catch (IOException ex) {
				System.out.println("Error on draw the file " + inputFile.getName() + ": " + ex.toString());
				break;
			}
		}
		saveOutputImage(outputImage);
	}
	
	private void saveOutputImage(BufferedImage image) {
		try {
			ImageIO.write(image, "png", new File("output.png"));
		} catch (Exception ex) {
			System.out.println("Error on save the output image: " + ex.toString());
		}
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
				System.out.println("Error on calculate the size required for the file " + file.getName() + ": " + ex.toString());
				continue;
			}
		}
		return new Calculator().calculateFinalImageSize(inputImagesSizes, maxColumns);
	}
	
	private File[] listInputFiles() {
		File currentDir = new File("input/");
		File[] files = currentDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".png");
			}
		});
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return files;
	}
}
