package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Stores image header data
 * @author Daniel
 */
public class ImageHeaderData {
	
	private HashMap<String, Object> data;
	
	/**
	 * Create a new header data
	 */
	public ImageHeaderData() {
		this.data = new HashMap<String, Object>();
	}

	/**
	 * Removes all HeaderConstant-value pairs
	 */
	public void clear() {
		this.data.clear();
	}

	/**
	 * Adds a HeaderConstant-value pair. Will throw an exception
	 * if the added key allows only one object
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		if (this.data.containsKey(key)) {
			throw new IllegalStateException("Cannot have two pairings for key: " + key);
		}
		this.data.put(key, value);
	}

	/**
	 * @return the set of all entries (keys with multiple values
	 * will have multiple entries)
	 */
	public Collection<Entry<String, Object>> entrySet() {
		return this.data.entrySet();
	}

	/**
	 * @param key the key to look for
	 * @return the value paired with the given key. will throw exception
	 * if the key can be paired with multiple values
	 */
	public Object get(String key) {
		return this.data.get(key);
	}

	/**
	 * @return The header information in digestible form
	 */
	public String getInfo() {
		String info = "";
		for (Entry<String, Object> e: this.entrySet()) {
			info += e.getKey() + "->" + e.getValue() + "\n";
		}
		return info;
	}
	
	
	
}
