package hyppo.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import hyppo.data.HyperspectralImageData;
import hyppo.io.headerio.enums.BandOrdering;
import hyppo.io.headerio.enums.ByteOrdering;
import hyppo.io.imagedatawriting.ImageDataWriterFactory;

/**
 * Class for storing data matrices (mostly hyperspectral images)
 * 
 * @author Daniel
 *
 */
public class HyperspectralImageDataWriter {

	/**
	 * From
	 * https://stackoverflow.com/questions/4358875/fastest-way-to-write-an-array-of-integers-to-a-file-in-java
	 * 
	 * @param hi image to save
	 * @param offset start writing at this position (useful if a header is present)
	 * @param fileName name of file where to write image
	 * @param imageOrdering bsq, bip, bil
	 * @param byteOrdering lil endian or big endian
	 */
	public static void writeImageData(HyperspectralImageData hi, int offset, String fileName, BandOrdering imageOrdering, ByteOrdering byteOrdering) {
		RandomAccessFile out = null;
		try {
			out = new RandomAccessFile(fileName, "rw"); //need rw for the file.map function to work
			FileChannel file = out.getChannel();
			int expectedBytes = hi.getNumberOfBands() * hi.getNumberOfLines() * hi.getNumberOfSamples() * hi.getDataType().getByteLength();
			
			ByteBuffer buf = file.map(FileChannel.MapMode.READ_WRITE, offset, expectedBytes);
			ImageDataWriterFactory.getWriter(imageOrdering, byteOrdering, hi.getDataType()).writeToBuffer(hi, buf);

			file.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtilities.safeClose(out);
		}
	}



}
