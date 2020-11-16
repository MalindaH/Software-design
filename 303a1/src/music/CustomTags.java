package music;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * CustonTags contain any tags that the client enters
 */
public class CustomTags {

	// use String instead of a Tag inner class so that we can check for repetitive tags (in addCustomTag())
	private HashMap<String, Object> aCustomTags;

	/**
	 * Constructs an empty CustomTags object
	 */
	public CustomTags() {
		aCustomTags = new HashMap<String, Object>();
	}

	/**
	 * Adds a custom tag of input tag name and value
	 * @param pTagName a String representing the tag name
	 * @param pValue an Object representing the tag value (i.e. any type)
	 * @pre pTagName != null && pValue != null
	 */
	public void addCustomTag(String pTagName, Object pValue) {
		try {
			// check the inputs are valid
			if(pTagName == null || pValue == null) {
				throw new IllegalArgumentException ("Input custom tag or value is invalid! Failed to add the tag.");
			}
			Object prev = aCustomTags.put(pTagName, pValue);
			// check if the same tag name exists already
			if(prev != null) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("A tag called "+pTagName+" already exists. Do you want to overwrite it? (y/n)");
				if(scanner.nextLine().charAt(0) == 'y') {
					System.out.println("Tag "+pTagName+" overwritten.");
				} else {
					aCustomTags.put(pTagName, prev);
					System.out.println("Tag "+pTagName+" not overwritten.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Gets all custom tags (private method to be called in toString())
	 * @return a String of all custom tags
	 */
	private String getAllCustomTags() {
		String output = "";
		Set keys = aCustomTags.keySet();
		Iterator i = keys.iterator();
		while (i.hasNext()) {
			String cur = (String) i.next();
			output = output.concat(cur.toString()+": ");
			output = output.concat(aCustomTags.get(cur).toString()+"\n");
		}
		return output;
	}

	/**
	 * Returns a String containing all custom tags and their values
	 */
	public String toString() {
		return "---Custom tags---\n"+this.getAllCustomTags();
	}
}




