package hyppo.data.imageDataTypes;

import java.nio.ByteBuffer;

import hyppo.io.IOUtilities;
import hyppo.io.headerio.enums.ByteOrdering;
import javelin.misc.MathOperations;

public class TwoByteSignedImageDataType extends ImageDataType {
	
	private static TwoByteSignedImageDataType instance;
	
    private TwoByteSignedImageDataType() {}

    public static TwoByteSignedImageDataType instance() {
        if (instance == null) {
            instance = new TwoByteSignedImageDataType();
        }
        return instance;
    }
    
	@Override
	public int getBitDepth() {
		return 16;
	}

	@Override
	public long valueToData(double f) {
		long data = (long) f;
		return MathOperations.clamp(data, -(1l << 15l), (1l << 15l) - 1);
	}

	@Override
	public long valueToData(long f) {
		return MathOperations.clamp(f, -(1l << 15l), (1l << 15l) - 1);
	}

	@Override
	public double dataToValueD(long data) {
		return (double) data;
	}

	@Override
	public long dataToValueL(long data) {
		return data;
	}

	@Override
	public long parseAsLongData(ByteBuffer bb, ByteOrdering byteOrdering) {
		return (long) ((short) IOUtilities.getBytes(byteOrdering, 2, bb));
	}

	@Override
	public int getByteLength() {
		return 2;
	}

	@Override
	public double getDynamicRange() {
		return (double) (1 << 16) - 1;
	}

	@Override
	public boolean isSigned() {
		return true;
	}

	@Override
	public boolean isFloating() {
		return false;
	}

}
