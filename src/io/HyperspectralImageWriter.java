package io;

import java.io.FileOutputStream;
import java.io.IOException;

import bits.BitOutputStream;
import data.HeaderConstants;
import data.HyperspectralImage;
import io.headerio.ImageHeaderReaderWriter;
import io.headerio.enums.BandOrdering;
import io.headerio.enums.ByteOrdering;

/**
 * Write hyperspectral images to files
 * @author Daniel
 *
 */
public class HyperspectralImageWriter {
	
	/**
	 * Default write which writes data to path.dat, and
	 * header info to path.hdr
	 * @param hi
	 * @param path
	 * @throws IOException
	 */
	public static void write(HyperspectralImage hi, String path, boolean essentialHeader) throws IOException {
		HyperspectralImageWriter.write(hi, path + ".dat", path + ".hdr", essentialHeader);
	}

	/**
	 * @param hi the image to write
	 * @param args the arguments with which to write it (output location and such)
	 * @throws IOException
	 */
	public static void write(HyperspectralImage hi, String path, String headerPath, boolean essentialHeader) throws IOException {
		BitOutputStream bos;
		
		
		if (headerPath != null) {
			//create the output header
			bos = new BitOutputStream(new FileOutputStream(headerPath));
			ImageHeaderReaderWriter.saveToStream(hi.getHeader(), bos, essentialHeader, false);
		}
		
		HyperspectralImageDataWriter.writeImageData(hi.getData(), 0, path, 
				(BandOrdering) hi.getHeader().get(HeaderConstants.HEADER_INTERLEAVE), 
				(ByteOrdering) hi.getHeader().get(HeaderConstants.HEADER_BYTE_ORDER));		
	}

}
