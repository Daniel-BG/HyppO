package io.imagedatareading;

import java.nio.ByteBuffer;

import data.HyperspectralImageData;


/**
 * Interface to define image data readers
 * @author Daniel
 */
public interface ImageDataReader {
	
	/**
	 * Reads a hyperspectral image from a buffer
	 * @param bb
	 * @param hi
	 */
	public void readFromBuffer(ByteBuffer bb, HyperspectralImageData hi);

}
