package hyppo.io.imagedatareading;

import java.nio.ByteBuffer;

import hyppo.data.HyperspectralImageData;
import hyppo.data.imageDataTypes.ImageDataType;
import hyppo.io.headerio.enums.BandOrdering;
import hyppo.io.headerio.enums.ByteOrdering;


/**
 * Hyperspectral image reading (only raw data, not headers)
 * @author Daniel
 */
public class ImageDataReaderFactory {

	
	/**
	 * @param imgOrdering 
	 * @param byteOrdering 
	 * @param type 
	 * @return the imageWriter of your liking
	 */
	public static ImageDataReader getReader(BandOrdering imgOrdering, ByteOrdering byteOrdering, ImageDataType type) {
		
		switch(imgOrdering) {
		case BIL:
			return new BILImageReader(byteOrdering, type);
		case BIP:
			return new BIPImageReader(byteOrdering, type);
		case BSQ:
			return new BSQImageReader(byteOrdering, type);
		}
		
		throw new UnsupportedOperationException("The type of writer you requested is not implemented");
	}
	
	
	private static class BIPImageReader implements ImageDataReader {
		protected ByteOrdering byteOrdering;
		protected ImageDataType dataType;
		
		public BIPImageReader(ByteOrdering byteOrdering, ImageDataType type) {
			this.byteOrdering = byteOrdering;
			this.dataType = type;
		}
		@Override
		public void readFromBuffer(ByteBuffer bb, HyperspectralImageData hi) {
			for (int j = 0; j < hi.getNumberOfLines(); j++) {
				for (int k = 0; k < hi.getNumberOfSamples(); k++) {
					for (int i = 0; i < hi.getNumberOfBands(); i++) {
						long value = this.dataType.parseAsLongData(bb, this.byteOrdering);
						hi.setDataAt(value, i, j, k);
					}
				}
			}
		}
	}
	
	private static class BILImageReader implements ImageDataReader {
		protected ByteOrdering byteOrdering;
		protected ImageDataType dataType;
		
		public BILImageReader(ByteOrdering byteOrdering, ImageDataType type) {
			this.byteOrdering = byteOrdering;
			this.dataType = type;
		}
		@Override
		public void readFromBuffer(ByteBuffer bb, HyperspectralImageData hi) {
			for (int j = 0; j < hi.getNumberOfLines(); j++) {
				for (int i = 0; i < hi.getNumberOfBands(); i++) {
					for (int k = 0; k < hi.getNumberOfSamples(); k++) {
						long value = this.dataType.parseAsLongData(bb, this.byteOrdering);
						hi.setDataAt(value, i, j, k);
					}
				}
			}
		}
	}
	
	private static class BSQImageReader implements ImageDataReader {
		protected ByteOrdering byteOrdering;
		protected ImageDataType dataType;
		
		public BSQImageReader(ByteOrdering byteOrdering, ImageDataType type) {
			this.byteOrdering = byteOrdering;
			this.dataType = type;
		}
		@Override
		public void readFromBuffer(ByteBuffer bb, HyperspectralImageData hi) {
			for (int i = 0; i < hi.getNumberOfBands(); i++) {
				for (int j = 0; j < hi.getNumberOfLines(); j++) {
					for (int k = 0; k < hi.getNumberOfSamples(); k++) {
						long value = this.dataType.parseAsLongData(bb, this.byteOrdering);
						hi.setDataAt(value, i, j, k);
					}
				}
			}
		}
	}
	
}
