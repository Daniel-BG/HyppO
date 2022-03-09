package io.headerio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.HeaderConstants;
import data.ImageHeaderData;
import misc.Utilities;


/**
 * Read/Write image headers
 * @author Daniel
 *
 */
public class ImageHeaderReaderWriter {
	private static final String DATA_PATTERN = "^([^=\\n\\r]+?)\\s+=\\s+([^\\{].*?|\\{.*?\\})\\s*$";

	/**
	 * @param stream Stream where to load from
	 * @param ihd 
	 * @throws IOException if something fails when reading
	 * @return if the data is embbeded in the input stream, the number of BYTES at which it is offset
	 * counting from where the pointer was when this function was called. Note that the pointer for
	 * stream might have gone past the data starting point!. If the data is not embedded, then return 0
	 */
	public static int loadFromStream(InputStream stream, ImageHeaderData ihd) throws IOException {
		ihd.clear();
		Scanner scn = new Scanner(stream);
		int horizon = 0; //for now no limit. If we find the "header offset" keyword, set the horizon to it
		while (true) {
			//find the next within horizon
			String s = scn.findWithinHorizon(Pattern.compile(DATA_PATTERN, Pattern.MULTILINE | Pattern.DOTALL), horizon);
			if (s == null) {
				break;
			}
			//split into individual parts and create the parameter
			Matcher m = Pattern.compile(DATA_PATTERN, Pattern.MULTILINE | Pattern.DOTALL).matcher(s);
			m.find(); //should always work since s is this pattern, we only use this to split it
			ParameterReaderWriter prw = new ParameterReaderWriter(m.group(1).trim());
			prw.parseData(m.group(2).trim());
			//if it is the offset, set the new horizon
			if (prw.getHeaderConstant() == HeaderConstants.HEADER_OFFSET) {
				/** 
				 * Note that setting the horizon this way will probably make the scanner go past
				 * the header limit, maybe with some other data structures this can be fixed so 
				 * that this function returns the input stream in the exact position it needs to be
				 * for the data to start to be read
				 */
				horizon = (int) prw.getData();
			} else {
				ihd.put(prw.getHeaderConstant(), prw.getData());
			}
		}
		scn.close();
		return horizon;
	}
	
	
	/**
	 * Save inner information into a stream
	 * @param ihd 
	 * @param brw where to save it
	 * @param essential if true only the essential information to read the image is output, everything else discarded
	 * @param embedded set to true if the data goes after in the same output stream (this enables calculation of header offset)
	 * @return the number of BYTES written
	 * @throws IOException 
	 */
	public static int saveToStream(ImageHeaderData ihd, OutputStream brw, boolean essential, boolean embedded) throws IOException {
		ArrayList<Byte> list = new ArrayList<Byte>();
		byte[] lineSeparator = "\n".getBytes(StandardCharsets.UTF_8);
		
		/** Add header and all entries */
		Utilities.addAllBytes(list, "ENVI\n".getBytes(StandardCharsets.UTF_8));
		for (Entry<String, Object> e: ihd.entrySet()) {
			ParameterReaderWriter prw = new ParameterReaderWriter(e.getKey());
			if (essential && !HeaderConstants.isEssential(prw.getHeaderConstant())) {
				continue;
			}
			
			prw.setData(e.getValue());
			//add the parameter
			Utilities.addAllBytes(list, prw.toString().getBytes(StandardCharsets.UTF_8));
			//add a newline
			Utilities.addAllBytes(list, lineSeparator);
		}
		
		/** If data comes afterwards, indicate header offset, otherwise ommit */
		if (embedded) {
			Utilities.addAllBytes(list, "header offset = ".getBytes(StandardCharsets.UTF_8));
			Integer listSize = list.size() + lineSeparator.length;
			Integer totalSize = listSize + listSize.toString().length();
			if (totalSize.toString().length() != listSize.toString().length()) {
				totalSize++;
			}
			Utilities.addAllBytes(list, totalSize.toString().getBytes());
			Utilities.addAllBytes(list, lineSeparator);
		}

		/** Output result */
		byte[] result = new byte[list.size()];
		int index = 0;
		for (Byte b: list) {
			result[index] = b;
			index++;
		}
		brw.write(result);
		
		return result.length;
	}
	
}
