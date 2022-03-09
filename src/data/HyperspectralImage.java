package data;

/**
 * Class that stores the hyperspectral image data as well
 * as the metadata
 * @author Daniel
 */
public class HyperspectralImage {

	private HyperspectralImageData data;
	private ImageHeaderData header;
	
	
	/**
	 * Build this image with the given data and header.
	 * Consistency checks are not done for data metadata and header data 
	 * @param data
	 * @param header
	 */
	public HyperspectralImage(HyperspectralImageData data, ImageHeaderData header) {
		this.data = data;
		this.header = header;
	}

	/**
	 * @return the image header
	 */
	public ImageHeaderData getHeader() {
		return header;
	}
	
	/**
	 * @return the image data
	 */
	public HyperspectralImageData getData() {
		return data;
	}

	/**
	 * Resize the image to the given size
	 * @param bands
	 * @param lines
	 * @param samples
	 */
	public void resize(int bands, int lines, int samples) {
		this.data = this.data.resize(bands, lines, samples);
		this.header.put(HeaderConstants.HEADER_BANDS, bands);
		this.header.put(HeaderConstants.HEADER_LINES, lines);
		this.header.put(HeaderConstants.HEADER_SAMPLES, samples);
	}

	/**
	 * @return Info for the reader
	 */
	public String getInfo() {
		return "HEADER:\n" + this.header.getInfo() + "\nDATA:\n" + this.data.getInfo();
	}
}
