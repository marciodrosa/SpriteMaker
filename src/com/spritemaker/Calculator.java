package com.spritemaker;

import java.util.List;

/**
 * Utility class to calculate.
 */
public class Calculator {
	
	/**
	 * Calculates the size that the final image must have to fit all images into.
	 * @param imagesSizes a list with the sizes of all inner images
	 * @param maxColumns max columns by row
	 * @return the size of the final image
	 */
	public ImageSize calculateFinalImageSize(List<ImageSize> imagesSizes, int maxColumns) {
		ImageSize finalImageSize = new ImageSize();
		int x = 0, y = 0, column = 0;
		for (ImageSize size : imagesSizes) {
			column++;
			if (column > maxColumns) {
				column = 1;
				x = 0;
				y = finalImageSize.height;
			}
			x += size.width;
			if (x > finalImageSize.width) {
				finalImageSize.width = x;
			}
			if (y + size.height > finalImageSize.height) {
				finalImageSize.height = y + size.height;
			}
		}
		return finalImageSize;
	}
}
