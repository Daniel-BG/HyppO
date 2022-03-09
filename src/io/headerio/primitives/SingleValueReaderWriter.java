package io.headerio.primitives;

/**
 * Class for single value R/W to inherit from
 * @author Daniel
 */
public abstract class SingleValueReaderWriter extends ValueReaderWriter {
	
	@Override
	public String unParse() {
		return this.getObject().toString();
	}
}
