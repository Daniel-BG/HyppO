package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import bits.BitInputStream;
import data.HeaderConstants;
import data.HyperspectralImage;
import data.HyperspectralImageData;
import data.HyperspectralImageIntegerData;
import data.ImageDataType;
import data.ImageHeaderData;
import debug.Logger;
import io.headerio.ImageHeaderReaderWriter;
import io.headerio.enums.BandOrdering;
import io.headerio.enums.ByteOrdering;

/**
 * Class to read hyperspectral images (header + data)
 * Either compressed or uncompressed
 * @author Daniel
 */
public class HyperspectralImageReader {
	
	private static final String[] commonDataFormats = {"", ".rfl", ".dat"}; 
	/**
	 * @param path the path where the header metadata 
	 * + image data is stored. Or only the header if ending in .hdr, and the data will be looked for with no extension
	 * @param floatRep if true the matrix read will be stored as floats (may be useful for memory reasons)
	 * @param verbose if info is to be output when decompressing
	 * @return the read image, containing both header and data
	 * @throws IOException 
	 */
	public static HyperspectralImage read(String path) throws IOException {
		if (path.endsWith(".hdr")) {
			String dataPath = path.substring(0, path.length() - 4);
			for (String s: commonDataFormats) {
				File f = new File(dataPath + s);
				if(f.exists() && !f.isDirectory()) { 
					System.out.println("file " + f.toString() + "  exists");
					return HyperspectralImageReader.read(dataPath + s, path);
				}
			}
			throw new IllegalArgumentException("Could not find associated data file with the given header");
		}
		
		return HyperspectralImageReader.read(path, null);
	}

	
	/**
	 * @param dataPath where the image data is stored
	 * @param headerPath where the image metadata is stored
	 * @param verbose if info is to be output when decompressing
	 * @return the read image
	 * @throws IOException 
	 */
	public static HyperspectralImage read(String dataPath, String headerPath) throws IOException {
		/** Load header */
		ImageHeaderData header = new ImageHeaderData();
		String realHeaderPath = headerPath != null ? headerPath : dataPath;
		BitInputStream bis = new BitInputStream(new FileInputStream(realHeaderPath)); 
		Logger.getLogger().log("Reading image header: " + realHeaderPath);
		int offset = ImageHeaderReaderWriter.loadFromStream(bis, header);
		if (headerPath != null) {
			offset = 0;
		}
		
		/** Load image data */
		HyperspectralImageData data;
		Logger.getLogger().log("Reading raw data: " + dataPath);
		int bands = (int) header.get(HeaderConstants.HEADER_BANDS);
		int lines = (int) header.get(HeaderConstants.HEADER_LINES);
		int samples = (int) header.get(HeaderConstants.HEADER_SAMPLES);
		Logger.getLogger().log("Image size is: " + bands + "bands x " + lines + "lines x " + samples + "samples");
		ImageDataType type = ImageDataType.fromHeaderCode((byte) header.get(HeaderConstants.HEADER_DATA_TYPE));
		
		data = new HyperspectralImageIntegerData(type, bands, lines, samples);
		
		HyperspectralImageDataReader.readImageData(dataPath, offset, data,
				(BandOrdering) header.get(HeaderConstants.HEADER_INTERLEAVE), 
				(ByteOrdering) header.get(HeaderConstants.HEADER_BYTE_ORDER));
		
		
		/** Return the hyperspectral image */
		return new HyperspectralImage(data, header);
	}
	
}
