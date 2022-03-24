package hyppo.io.headerio.primitives;

/**
 * Compresses/uncompresses a byte (8 bit) value
 * @author Daniel
 *
 */
public class ByteValueReaderWriter extends SingleValueReaderWriter {
	
	private Byte value;


	@Override
	public void parse(Object obj) {
		this.value = (byte) Integer.parseInt(obj.toString());
	}

	@Override
	public Object getObject() {
		return this.value;
	}

	@Override
	public void setObject(Object obj) {
		this.value = (Byte) obj;
	}

}
