package hyppo.io.headerio.primitives;

/**
 * Class to transform strings to values (uncompressed to compressed) <br>
 * or values to strings (compressed to uncompressed)
 * @author Daniel
 */
public abstract class ValueReaderWriter {
	
	/**
	 * Interpret the given object (usually a string) and save it internally,
	 * parsed according to this compressorDecompressor's parsing.
	 * It can be retrieved by {@link #getObject()}
	 * @param obj
	 */
	public abstract void parse(Object obj);
	
	/**
	 * @return the object that has been parsed by {@link #parse(Object)} 
	 */
	public abstract Object getObject();
	
	/**
	 * Sets the inner object
	 * @param obj
	 */
	public abstract void setObject(Object obj);
	
	/**
	 * @return the inner object representation as a String for saving
	 * (not always equal to {@link #getObject()}.toString()
	 */
	public abstract String unParse();

}
