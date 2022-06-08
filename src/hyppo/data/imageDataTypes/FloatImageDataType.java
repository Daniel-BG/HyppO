package hyppo.data.imageDataTypes;

import java.nio.ByteBuffer;

import hyppo.io.IOUtilities;
import hyppo.io.headerio.enums.ByteOrdering;

public class FloatImageDataType extends ImageDataType {

	private static FloatImageDataType instance;
	
    private FloatImageDataType() {}

    public static FloatImageDataType instance() {
        if (instance == null) {
            instance = new FloatImageDataType();
        }
        return instance;
    }

	@Override
	public int getBitDepth() {
		return 32;
	}

	@Override
	public long valueToData(double f) {
		return Float.floatToIntBits((float) f) & 0xffffffffl;
	}

	@Override
	public long valueToData(long f) {
		return Float.floatToIntBits((float) f) & 0xffffffffl;
	}

	@Override
	public double dataToValueD(long data) {
		return (double) Float.intBitsToFloat((int) (data & 0xffffffff));
	}

	@Override
	public long dataToValueL(long data) {
		return (long) Float.intBitsToFloat((int) (data & 0xffffffff));
	}

	@Override
	public long parseAsLongData(ByteBuffer bb, ByteOrdering byteOrdering) {
		return IOUtilities.getBytes(byteOrdering, 4, bb);
	}

	@Override
	public int getByteLength() {
		return 4;
	}

	@Override
	public double getDynamicRange() {
		return Float.MAX_VALUE;
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
