package io.headerio;

import data.HeaderConstants;
import io.headerio.primitives.ValueReaderWriter;

/**
 * Reads and/or writes envi header parameters from compressed to uncompressed
 * form and vice versa
 * @author Daniel
 */
public class ParameterReaderWriter {


	private String headerConstant;
	private ValueReaderWriter rw;
	private String parameter;
	
	/**
	 * Creates a parameter reader/writer for the given headerConstant. 
	 * For a list of constants go to {@link HeaderConstants}
	 * @param headerConstant
	 * @param comDec 
	 */
	public ParameterReaderWriter(String headerConstant) {
		this.headerConstant = headerConstant;
		this.setUp();
	}
	
	
	
	private void setUp() {
		this.rw = HeaderConstants.getValueComDec(this.headerConstant);
	}
	
	/**
	 * Parses the data 
	 * @param data to be parsed
	 */
	public void parseData(String data) {
		this.rw.parse(data);
	}
	
	
	/**
	 * @return the data processed with {@link #parseData(String)}
	 */
	public Object getData() {
		return this.rw.getObject();
	}
	
	/**
	 * Sets the data to be written
	 * @param data
	 */
	public void setData(Object data) {
		this.rw.setObject(data);
	}
	
	/**
	 * @return the type of header constant this ParameterReaderWriter reads/writes
	 */
	public String getHeaderConstant() {
		return this.headerConstant;
	}
	
	@Override
	public String toString() {
		return this.headerConstant.toString() + " = " + this.rw.unParse();
	}
	
}
