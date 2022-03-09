package io.headerio.primitives;


/**
 * Reads/Writes arrays of data. Assumes a "{data1, data2, ...}" format. <br>
 * The inner data types cannot be arrays //TODO eventually add nested arrays
 * @author Daniel
 */
public class ArrayValueReaderWriter extends ValueReaderWriter {

	SingleValueReaderWriter childComDec;
	private Object[] values;
	
	/**
	 * Create an array value decompressor which stores values of the given type
	 * @param childComDec
	 */
	public ArrayValueReaderWriter(SingleValueReaderWriter childComDec) {
		this.childComDec = childComDec;
	}

	@Override
	public void parse(Object obj) {
		String str = obj.toString();
		//remove initial and ending braces, then split 
		str = str.substring(1, str.length() - 1).trim();
		String[] strings = str.split(",");
		
		this.values = new Object[strings.length];
		for (int i = 0; i < values.length; i++) {
			childComDec.parse(strings[i]);
			values[i] = childComDec.getObject();
		}
	}

	@Override
	public Object getObject() {
		return this.values;
	}

	@Override
	public void setObject(Object obj) {
		this.values = (Object[]) obj;
	}

	@Override
	public String unParse() {
		String res = "{";
		for (int i = 0; i < this.values.length; i++) {
			childComDec.setObject(values[i]);
			if (i < this.values.length - 1) {
				res += childComDec.unParse() + ",";
			} else {
				res += childComDec.unParse();
			}
		}
		
		return res + "}";
	}

}
