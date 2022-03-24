package hyppo.io.headerio.primitives;

/**
 * R/W for Strings
 * @author Daniel
 *
 */
public class StringValueReaderWriter extends SingleValueReaderWriter {

	private String value;

	@Override
	public void parse(Object obj) {
		value = obj.toString().trim();
	}

	@Override
	public Object getObject() {
		return value;
	}

	@Override
	public void setObject(Object obj) {
		this.value = (String) obj;
	}

}
