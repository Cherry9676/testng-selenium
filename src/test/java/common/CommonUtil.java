package common;

import java.io.File;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class CommonUtil {

	private static Document xmlDoc;
	private static Map<String, Object> jsonData;
	private static Map<String, Object> testData;

	// Loads an XML configuration file.
	public static void loadXMLConfigurationFile(String filepath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			xmlDoc = builder.parse(filepath);
			xmlDoc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading XML configuration file: " + e.getMessage());
		}
	}

	// Retrieves the value of an XML tag by name.
	public static String getXMLTagValue(String tagName) {
		if (xmlDoc == null) {
			throw new IllegalStateException("XML file is not loaded. Call loadXMLConfigurationFile() first.");
		}
		NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		}
		return null;
	}

	// Loads a JSON configuration file.
	@SuppressWarnings("unchecked")
	public static void loadJSONFile(String filepath) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonData = mapper.readValue(new File(filepath), Map.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load JSON file: " + e.getMessage());
		}
	}

	// Retrieves a JSON value by dot-separated keys.
	@SuppressWarnings("unchecked")
	public static String getObjectRepositoryFromJson(String keys) {
		if (jsonData == null) {
			throw new IllegalStateException("JSON file is not loaded. Call loadJSONFile() first.");
		}
		String[] keyParts = keys.split("\\.");
		Map<String, Object> currentMap = jsonData;
		try {
			for (int i = 0; i < keyParts.length - 1; i++) {
				String currentKey = keyParts[i].trim();
				Object value = currentMap.get(currentKey);
				if (value instanceof Map<?, ?>) {
					currentMap = (Map<String, Object>) value;
				} else {
					return keys;
				}
			}
			String finalKey = keyParts[keyParts.length - 1].trim();
			Object result = currentMap.getOrDefault(finalKey, keys);
			return result != null ? result.toString() : "Key not found: " + keys;
		} catch (Exception ex) {
			return keys;
		}
	}

	// Loads test data from a JSON file (if required).
	@SuppressWarnings("unchecked")
	public static void loadJSONTestData(String filepath) {
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        testData = mapper.readValue(new File(filepath), Map.class);
	    } catch (Exception e) {
	        testData = null; // Set to null to indicate no test data is loaded.
	    }
	}

	// Retrieves test data value by key.
	public static String getJSONTestData(String key) {
	    if (testData == null) {
	        return key;
	    }
	    Object value = testData.get(key);
	    if (value != null) {
	        return value.toString();
	    }
	    return key;
	}

}
