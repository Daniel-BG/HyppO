package hyppo.io.imagedatawriting;

import java.nio.ByteBuffer;

import hyppo.data.HyperspectralImageData;
import hyppo.data.imageDataTypes.ImageDataType;
import hyppo.io.IOUtilities;
import hyppo.io.headerio.enums.BandOrdering;
import hyppo.io.headerio.enums.ByteOrdering;


/**
 * Factory that gets you your desired ImageWriter object
 * @author Daniel
 *
 */
public class ImageDataWriterFactory {	
	
	/**
	 * @param imgOrdering 
	 * @param byteOrdering 
	 * @param type 
	 * @return the imageWriter of your liking
	 */
	public static ImageDataWriter getWriter(BandOrdering imgOrdering, ByteOrdering byteOrdering, ImageDataType type) {
		
		switch(imgOrdering) {
		case BIL:
			return new BILByteImageWriter(byteOrdering, type);
		case BIP:
			return new BIPByteImageWriter(byteOrdering, type);
		case BSQ:
			return new BSQByteImageWriter(byteOrdering, type);
		}
		
		throw new UnsupportedOperationException("The type of writer you requested is not implemented");
	}
	
	/**
	 * Useful class frame for writing images which type bit depth is a multiple of 8
	 * @author Daniel
	 *  * @see {@link BitImageWriter}
	 */
	private static abstract class ByteImageWriter implements ImageDataWriter {
		protected ByteOrdering byteOrdering;
		protected ImageDataType dataType;
		
		public ByteImageWriter(ByteOrdering byteOrdering, ImageDataType dataType) {
			this.byteOrdering = byteOrdering;
			this.dataType = dataType;
		}
	}
	
	private static class BIPByteImageWriter extends ByteImageWriter {
		public BIPByteImageWriter(ByteOrdering byteOrdering, ImageDataType dataType) {
			super(byteOrdering, dataType);
		}
		
		@Override
		public void writeToBuffer(HyperspectralImageData hi, ByteBuffer bb) {
			for (int j = 0; j < hi.getNumberOfLines(); j++) {
				for (int k = 0; k < hi.getNumberOfSamples(); k++) {
					for (int i = 0; i < hi.getNumberOfBands(); i++) {
						IOUtilities.putBytes(hi.getDataAt(i, j, k), this.byteOrdering, this.dataType.getByteLength(), bb);
					}
				}
			}
		}
	}
	
	private static class BILByteImageWriter extends ByteImageWriter {
		public BILByteImageWriter(ByteOrdering byteOrdering, ImageDataType dataType) {
			super(byteOrdering, dataType);
		}
		
		@Override
		public void writeToBuffer(HyperspectralImageData hi, ByteBuffer bb) {
			for (int j = 0; j < hi.getNumberOfLines(); j++) {
				for (int i = 0; i < hi.getNumberOfBands(); i++) {
					for (int k = 0; k < hi.getNumberOfSamples(); k++) {
						IOUtilities.putBytes(hi.getDataAt(i, j, k), this.byteOrdering, this.dataType.getByteLength(), bb);
					}
				}
			}
		}
	}
	
	private static class BSQByteImageWriter extends ByteImageWriter {
		public BSQByteImageWriter(ByteOrdering byteOrdering, ImageDataType dataType) {
			super(byteOrdering, dataType);
		}
		
		@Override
		public void writeToBuffer(HyperspectralImageData hi, ByteBuffer bb) {
			for (int i = 0; i < hi.getNumberOfBands(); i++) {
				for (int j = 0; j < hi.getNumberOfLines(); j++) {
					for (int k = 0; k < hi.getNumberOfSamples(); k++) {
						IOUtilities.putBytes(hi.getDataAt(i, j, k), this.byteOrdering, this.dataType.getByteLength(), bb);
					}
				}
			}
		}
	}
		

}
