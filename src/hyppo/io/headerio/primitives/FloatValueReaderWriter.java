package hyppo.io.headerio.primitives;

/**
 * Read/Write floats
 * @author Daniel
 * @see {@link ValueReaderWriter}
 */
public class FloatValueReaderWriter extends SingleValueReaderWriter {

	private Float value;

	@Override
	public void parse(Object obj) {
		this.value = Float.parseFloat(obj.toString());
	}

	@Override
	public Object getObject() {
		return this.value;
	}
	
	@Override
	public void setObject(Object obj) {
		this.value = (Float) obj;
	}

}
