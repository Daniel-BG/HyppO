package hyppo.data.imageDataTypes;

import java.nio.ByteBuffer;

import hyppo.io.headerio.enums.ByteOrdering;

/**
 * Types of data that can be contained in the images and functions to treat them properly
 * @author Daniel
 *
 */
public abstract class ImageDataType implements Cloneable {

	public abstract int getBitDepth();

	public abstract long valueToData(double f);
	public abstract long valueToData(long f);

	public abstract double dataToValueD(long data);
	public abstract long dataToValueL(long data);

	public abstract long parseAsLongData(ByteBuffer bb, ByteOrdering byteOrdering);

	public static ImageDataType fromHeaderCode(byte b) {
		switch(b) {
		case 2:
			return TwoByteSignedImageDataType.instance();
		case 4:
			return FloatImageDataType.instance();
		case 5:
			return DoubleImageDataType.instance();
		case 12:
			return TwoBytesUnsignedImageDataType.instance();
		default:
			throw new IllegalStateException("Not implemented for type: " + b);
		}
	}

	public abstract int getByteLength();

	public static ImageDataType fromParams(int bitDepth, boolean signed, boolean floating) {
		if (bitDepth == 16 && signed && !floating)
			return TwoByteSignedImageDataType.instance();
		if (bitDepth == 16 && !signed && !floating)
			return TwoBytesUnsignedImageDataType.instance();
		if (bitDepth == 64 && signed && floating)
			return DoubleImageDataType.instance();
		if (bitDepth == 32 && signed && floating)
			return FloatImageDataType.instance();
		throw new IllegalStateException("Not implemented for: " + bitDepth + "," + signed + "," + floating);
	}

	public abstract double getDynamicRange();

	public abstract boolean isSigned();

	public abstract boolean isFloating();
}
