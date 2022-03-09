package io.headerio.primitives;

/**
 * R/W for integers
 * @author Daniel
 */
public class IntegerValueReaderWriter extends SingleValueReaderWriter {

	private Integer value;

	@Override
	public void parse(Object obj) {
		value = Integer.parseInt(obj.toString());
	}

	@Override
	public Object getObject() {
		return value;
	}

	@Override
	public void setObject(Object obj) {
		this.value = (Integer) obj;
	}

}
