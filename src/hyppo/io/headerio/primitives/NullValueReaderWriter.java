package hyppo.io.headerio.primitives;

/**
 * Dummy class for storing things that are flags in the compressed stream
 * @author Daniel
 */
public class NullValueReaderWriter extends ValueReaderWriter {

	@Override
	public void parse(Object obj) {}

	@Override
	public Object getObject() {
		return null;
	}

	@Override
	public void setObject(Object obj) {}

	@Override
	public String unParse() {
		return null;
	}

}
