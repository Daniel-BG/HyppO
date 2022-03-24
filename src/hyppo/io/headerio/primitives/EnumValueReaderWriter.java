package hyppo.io.headerio.primitives;

/**
 * Class to read/write header fields that are enums
 * @author Daniel
 * @param <T> enum class
 */
public class EnumValueReaderWriter<T extends Enum<T>> extends SingleValueReaderWriter {

	private T value;
	private final Class<T> type;
	
	/**
	 * Create a value compressor decompressor for the given enum.
	 * @param type the class of the parametrized type
	 */
	public EnumValueReaderWriter(Class<T> type) {
		this.type = type;
	}
	
	
	@Override
	public void parse(Object obj) {
		this.value = T.valueOf(type, obj.toString().toUpperCase().trim());
		
	}

	@Override
	public Object getObject() {
		return this.value;
	}

	@Override
	public void setObject(Object obj) {
		//toString then parse to avoid type impossible type checking with
		//this.value = (T) obj;
		this.parse(obj);
	}

}
