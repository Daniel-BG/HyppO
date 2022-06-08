package hyppo.data.imageDataTypes;

import java.nio.ByteBuffer;

import hyppo.io.IOUtilities;
import hyppo.io.headerio.enums.ByteOrdering;

public class DoubleImageDataType extends ImageDataType {
	private static DoubleImageDataType instance;
	
    private DoubleImageDataType() {}

    public static DoubleImageDataType instance() {
        if (instance == null) {
            instance = new DoubleImageDataType();
        }
        return instance;
    }

	@Override
	public int getBitDepth() {
		return 64;
	}

	@Override
	public long valueToData(double f) {
		return Double.doubleToLongBits(f);
	}

	@Override
	public long valueToData(long f) {
		return Double.doubleToLongBits((double) f);
	}

	@Override
	public double dataToValueD(long data) {
		return Double.longBitsToDouble(data);
	}

	@Override
	public long dataToValueL(long data) {
		return (long) Double.longBitsToDouble(data);
	}

	@Override
	public long parseAsLongData(ByteBuffer bb, ByteOrdering byteOrdering) {
		return IOUtilities.getBytes(byteOrdering, 8, bb);
	}

	@Override
	public int getByteLength() {
		return 8;
	}

	@Override
	public double getDynamicRange() {
		return Double.MAX_VALUE;
	}

	@Override
	public boolean isSigned() {
		return true;
	}

	@Override
	public boolean isFloating() {
		return true;
	}

}
