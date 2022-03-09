package io.imagedatawriting;

import java.nio.ByteBuffer;

import data.HyperspectralImageData;


/**
 * Interface for writing hyperspectral images using {@link HyperspectralImageDataWriter}
 * @author Daniel
 *
 */
public interface ImageDataWriter {

	/**
	 * Writes a hyperspectralimage to a buffer, padding with zeros 
	 * at the end if the image bit size is not a multiple of 8
	 * @param hi
	 * @param bb
	 */
	public void writeToBuffer(HyperspectralImageData hi, ByteBuffer bb);
	
	
}
