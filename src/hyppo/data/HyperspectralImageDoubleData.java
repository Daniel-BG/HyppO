package hyppo.data;

import org.ejml.data.DMatrixRMaj;

import hyppo.data.imageDataTypes.ImageDataType;

/**
 * Float implementation of {@link HyperspectralImageData}, which internally
 * stores its contents in a {@link DMatrixRMaj}. Useful to avoid the memory
 * overhead of {@link #tofloatMatrix()}, since it returns the internal
 * pointer instead of creating a new matrix as {@link HyperspectralImageIntegerData} 
 * does.
 * Might lose information for some dataTypes
 * @author Daniel
 */
public class HyperspectralImageDoubleData extends HyperspectralImageData {

	private DMatrixRMaj data;
	
	/**
	 * Create a {@link HyperspectralImageData} with floating point inner representation
	 * @param type
	 * @param bands
	 * @param lines
	 * @param samples
	 */
	public HyperspectralImageDoubleData(ImageDataType type, int bands, int lines, int samples) {
		super(type, bands, lines, samples);
		this.data = new DMatrixRMaj(bands, this.bandElements);
	}

	/**
	 * Build a {@link HyperspectralImageDoubleData} with the given matrix as data
	 * @param data
	 * @param type
	 * @param bands
	 * @param lines
	 * @param samples
	 */
	public HyperspectralImageDoubleData(DMatrixRMaj data, ImageDataType type, int bands, int lines, int samples) {
		super(type, bands, lines, samples);
		if (data.numRows != bands || data.numCols != lines * samples) {
			throw new IllegalArgumentException("Dimensions do not match between the given data and the given dimensions");
		}
		this.data = data;
	}

	@Override
	public long getDataAt(int band, int line, int sample) {
		return this.dataType.valueToData(data.get(band*this.bandElements + line*this.samples + sample));
	}

	@Override
	public long getValueAt(int band, int line, int sample) {
		return (long) data.get(band*this.bandElements + line*this.samples + sample);
	}

	@Override
	public void setDataAt(long value, int band, int line, int sample) {
		double dtv = this.dataType.dataToValueD(value);
		data.set(band*this.bandElements + line*this.samples + sample, (float) dtv);
	}

	@Override
	public void setValueAt(double value, int band, int line, int sample) {
		data.set(band*this.bandElements + line*this.samples + sample, (float) value);
	}

	@Override
	public double[] getPixel(int line, int sample) {
		double[] pixel = new double[this.bands];
		for (int i = 0; i < this.bands; i++) {
			pixel[i] = this.getValueAt(i, line, sample);
		}
		return pixel;
	}

	@Override
	public void setPixel(double[] pixel, int line, int sample) {
		for (int i = 0; i < this.bands; i++) {
			this.setValueAt(pixel[i], i, line, sample);
		}
	}

	@Override
	/**
	 * Will just update the inner pointer to the given matrix
	 */
	public void copyDataFrom(DMatrixRMaj source) {
		if (this.data.numCols != source.numCols || this.data.numRows != source.numRows) {
			throw new IllegalArgumentException("Dimensions do not match");
		}
		this.data = source;
	}

	@Override
	/**
	 * Will just return the inner matrix
	 */
	public DMatrixRMaj toDoubleMatrix() {
		return this.data;
	}

	@Override
	public void free() {
		this.data = null;
	}

	@Override
	public HyperspectralImageData resize(int bands, int lines, int samples) {
		HyperspectralImageData newImage = new HyperspectralImageDoubleData(
				this.dataType, 
				bands, lines, samples);
		
		int cBands = this.getNumberOfBands();
		int cLines = this.getNumberOfLines();
		int cSamples = this.getNumberOfSamples();
		
		for (int i = 0; i < bands; i++) {
			for (int j = 0; j < lines; j++) {
				for (int k = 0; k < samples; k++) {
					if (i < cBands && j < cLines && k < cSamples) {
						newImage.setDataAt(this.getDataAt(i, j, k), i, j, k);
					} else {
						newImage.setValueAt(0, i, j, k);
					}
				}
			}
		}

		return newImage;
	}

	@Override
	public void normalizeData(long high) {
		int len = this.data.data.length;
		double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			double datum = this.data.data[i];
			if (datum > max)
				max = datum;
			if (datum < min)
				min = datum;
		}
		for (int i = 0; i < len; i++) {
			double datum = this.data.data[i];
			datum -= min;
			this.data.data[i] = datum > high ? high : datum;
		}	
	}

}
