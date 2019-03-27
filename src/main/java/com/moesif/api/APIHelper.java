/*
 * MoesifAPILib
 *

 */
package com.moesif.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.text.ParseException;
import java.text.ParsePosition;

import com.moesif.api.exceptions.APIException;
import com.mashape.unirest.http.Unirest;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class APIHelper {
    /* used for async execution of API calls using a thread pool */
    private static ExecutorService scheduler = null;
    private static Object syncRoot = new Object();
  
    /**
     * Singleton access to the threadpool scheduler
     * @return ExecutorService instance
     */
    public static ExecutorService getScheduler() {
        synchronized(syncRoot) {
            if(null == scheduler) {
                scheduler = Executors.newCachedThreadPool();
            }
            return scheduler;
        }
    }

    /**
     * Shutdown all the threads
     */
    public static void shutdown() {
        if(null != scheduler) {
            scheduler.shutdown();
        }
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            //do nothing
        }
    }

    /* used for deserialization of json data */
    public static ObjectMapper mapper = new ObjectMapper()
    {
        private static final long serialVersionUID = -174113593500315394L;
        {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    };

    /**
     * Parse a date from its string representation
     * @param date	ISO8601 encodede date string
     * @return Parsed Date object 
     */
    public static Date parseDate(String date) throws ParseException
    {
        return com.fasterxml.jackson.databind.util.ISO8601Utils.parse(date, new ParsePosition(0));
    }
    
    /**
     * Convert a date to an ISO8601 formatted string
     * @param date Date object to format
     * @return ISO8601 formatted date string
     */
    public static String dateToString(Date date)
    {
        return com.fasterxml.jackson.databind.util.ISO8601Utils.format(date);
    }

    /**
     * JSON Serialization of a given object.
     * @param  obj The object to serialize into JSON
     * @return The serialized Json string representation of the given object
     * @throws JsonProcessingException if error serializing JSON obj
     */
    public static String serialize(Object obj)
            throws JsonProcessingException {
        if(null == obj)
            return null;

        return mapper.writeValueAsString(obj);
    }

    /**
     * JSON Deserialization of the given json string.
     * @param   json The json string to deserialize
     * @param   <T>  The type of the object to deserialize into
     * @param   typeReference The TypeReference of the object to deserialize into
     * @return  The deserialized object
     * @throws IOException if error deserializing
     */
    public static <T extends Object> T deserialize(String json, TypeReference<T> typeReference)
            throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        return mapper.readValue(json, typeReference);
    }

    /**
     * JSON Deserialization of the given json string.
     * @param   json The json string to deserialize
     * @param   <T> The type of the object to deserialize into
     * @param   typeReference The Class of the object to deserialize into
     * @return  The deserialized object
     * @throws IOException if error deserializing
     */
    public static <T extends Object> T deserialize(String json, Class<T> typeReference)
            throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        return mapper.readValue(json, typeReference);
    }

    /**
     * Populates an object of an APIException subclass with the required properties.
     * @param   json The json string to deserialize
     * @param   obj  The object to populate
     * @throws IOException if error deserializing
     */
    public static void populate(String json, APIException obj)
            throws IOException {
        if (!isNullOrWhiteSpace(json))
            mapper.readerForUpdating(obj).readValue(json);;
    }

    /**
     * JSON Deserialization of the given json string.
     * @param   json    The json string to deserialize
     * @return  The deserialized json as a Map
     * @throws IOException if error deserializing
     */
    public static LinkedHashMap<String, Object> deserialize(String json)
            throws IOException {
        if (isNullOrWhiteSpace(json))
            return null;

        TypeReference<LinkedHashMap<String,Object>> typeRef 
            = new TypeReference<LinkedHashMap<String,Object>>() {};
        return deserialize(json, typeRef);
    }

    /**
     * Replaces template parameters in the given url
     * @param   queryBuilder    The query string builder to replace the template parameters
     * @param   parameters      The parameters to replace in the url
     */
    public static void appendUrlWithTemplateParameters(StringBuilder queryBuilder, Map<String, Object> parameters) {
        //perform parameter validation
        if (null == queryBuilder)
            throw new IllegalArgumentException("Given value for parameter \"queryBuilder\" is invalid." );

        if (null == parameters)
            return;

        //iterate and append parameters
        for (Map.Entry<String, Object> pair : parameters.entrySet()) {
             String replaceValue = "";

             //load element value as string
             if (null == pair.getValue())
                 replaceValue = "";
             else if (pair.getValue() instanceof Collection<?>)
                 replaceValue = flattenCollection("", (Collection<?>) pair.getValue(), "%s%s%s", '/');
             else if (pair.getValue() instanceof Date)
                 replaceValue = tryUrlEncode(dateToString((Date)pair.getValue()));
             else
                 replaceValue = tryUrlEncode(pair.getValue().toString());

             //find the template parameter and replace it with its value
             replaceAll(queryBuilder, "{" + pair.getKey() + "}", replaceValue);
        }
    }

    /**
     * Appends the given set of parameters to the given query string
     * @param   queryBuilder  The query url string to append the parameters
     * @param   parameters    The parameters to append
     */
    public static void appendUrlWithQueryParameters(StringBuilder queryBuilder, Map<String, Object> parameters) {
        //perform parameter validation
        if (null == queryBuilder)
            throw new IllegalArgumentException("Given value for parameter \"queryBuilder\" is invalid.");

        if (null == parameters)
            return;

        //does the query string already has parameters
        boolean hasParams = (queryBuilder.indexOf("?") > 0) || (queryBuilder.indexOf("http") != 0);
        queryBuilder.append((hasParams) ? '&' : '?');

        encodeObjectAsQueryString("", parameters, queryBuilder);
    }

    /**
     * Validates if the string is null, empty or whitespace
     * @param   s The string to validate
     * @return  The result of validation
     */
    public static boolean isNullOrWhiteSpace(String s) {
        if(s == null)
            return true;

        int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Replaces all occurrences of the given string in the string builder
     * @param   stringBuilder The string builder to update with replaced strings
     * @param   toReplace     The string to replace in the string builder
     * @param   replaceWith   The string to replace with
     */
    public static void replaceAll(StringBuilder stringBuilder, String toReplace, String replaceWith) {
        int index = stringBuilder.indexOf(toReplace);
        
        while (index != -1) {
            stringBuilder.replace(index, index + toReplace.length(), replaceWith);
            index += replaceWith.length(); // Move to the end of the replacement
            index = stringBuilder.indexOf(toReplace, index);
        }
    }

    /**
     * Removes null values from the given map
     * @param map the input map
     */
    public static void removeNullValues(Map<String, ?> map) {
        if(map == null)
            return;
        map.values().removeAll(Collections.singleton(null));
    }

    /**
     * Validates and processes the given Url
     * @param    url The given Url to process
     * @return   Pre-process Url as string
     */
    public static String cleanUrl(StringBuilder url)
    {
        //ensure that the urls are absolute
        Pattern pattern = Pattern.compile("^(https?://[^/]+)");
        Matcher matcher = pattern.matcher(url);
        if (!matcher.find())
            throw new IllegalArgumentException("Invalid Url format.");

        //get the http protocol match
        String protocol = matcher.group(1);

        //remove redundant forward slashes
        String query = url.substring(protocol.length());
        query = query.replaceAll("//+", "/");

        //return process url
        return protocol.concat(query);
    }

    /**
     * Prepares Array style form fields from a given array of values
     * @param   value   Value for the form fields
     * @return  Dictionary of form fields created from array elements
     */
    public static Map<String, Object> prepareFormFields(Object value) {
        Map<String, Object> formFields = new LinkedHashMap<String, Object>();
        if(value != null) {
            try {
                objectToMap("", value, formFields, new HashSet<Integer>());
            } catch (Exception ex) {
            }
        }
        return formFields;
    }

    /**
     * Get the current version of the moesifapi-java artifact
     * @return  Version string
     */
    public synchronized static String getVersion() {

        String mavenPackage = "com.moesif";
        String mavenArtifact = "moesifapi-java";

        // Try to get version number from pom.xml (available in Eclipse)
        try {
            String className = MoesifAPIClient.class.getName();
            String classfileName = "/" + className.replace('.', '/') + ".class";
            URL classfileResource = MoesifAPIClient.class.getResource(classfileName);
            if (classfileResource != null) {
                Path absolutePackagePath = Paths.get(classfileResource.toURI())
                        .getParent();
                int packagePathSegments = className.length()
                        - className.replace(".", "").length();
                // Remove package segments from path, plus two more levels
                // for "target/classes", which is the standard location for
                // classes in Eclipse.
                Path path = absolutePackagePath;
                for (int i = 0, segmentsToRemove = packagePathSegments + 2;
                     i < segmentsToRemove; i++) {
                    path = path.getParent();
                }
                Path pom = path.resolve("pom.xml");
                InputStream is = Files.newInputStream(pom);
                Document doc = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().parse(is);
                doc.getDocumentElement().normalize();
                String version = (String) XPathFactory.newInstance()
                        .newXPath().compile("/project/version")
                        .evaluate(doc, XPathConstants.STRING);
                if (version != null) {
                    version = version.trim();
                    if (!version.isEmpty()) {
                        return version;
                    }
                }

            }
        } catch (Exception e) {
            // Ignore
        }

        // Try to get version number from maven properties in jar's META-INF
        try {
            InputStream is = MoesifAPIClient.class
                    .getResourceAsStream("/META-INF/maven/" + mavenPackage + "/"
                        + mavenArtifact + "/pom.properties");

            if (is != null) {
                Properties p = new Properties();
                p.load(is);
                String version = p.getProperty("version", "").trim();
                if (!version.isEmpty()) {
                    return version;
                }
            }
        } catch (Exception e) {
            // Ignore
        }

        // Fallback to using Java API to get version from MANIFEST.MF
        String version = null;
        Package pkg = MoesifAPIClient.class.getPackage();
        if (pkg != null) {
            version = pkg.getImplementationVersion();
            if (version == null) {
                version = pkg.getSpecificationVersion();
            }
        }
        version = version == null ? "" : version.trim();
        return version.isEmpty() ? "unknown" : version;
    }

    /**
     * Encodes a given object to url encoded string
     * @param name
     * @param obj
     * @param objBuilder
     */
    private static void encodeObjectAsQueryString(String name, Object obj, StringBuilder objBuilder) {
        try {
            if(obj == null)
                return;

            Map<String, Object> objectMap = new LinkedHashMap<String, Object>();
            objectToMap(name, obj, objectMap, new HashSet<Integer>());
            boolean hasParam = false;

            for (Map.Entry<String, Object> pair : objectMap.entrySet()) {
                String paramKeyValPair;

                //ignore nulls
                Object value = pair.getValue();
                if(value == null)
                    continue;

                hasParam = true;
                //load element value as string
                paramKeyValPair = String.format("%s=%s&", pair.getKey(), tryUrlEncode(value.toString()));
                objBuilder.append(paramKeyValPair);
            }

            //remove the last &
            if(hasParam) {
                objBuilder.setLength(objBuilder.length() - 1);
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Used for flattening a collection of objects into a string
     * @param   array     Array of elements to flatten
     * @param   fmt       Format string to use for array flattening
     * @param   separator Separator to use for string concat
     * @return  Representative string made up of array elements
     */
    private static String flattenCollection(String elemName, Collection<?> array, String fmt, char separator) {
        StringBuilder builder = new StringBuilder();

        //append all elements in the array into a string
        for (Object element : array) {
            String elemValue = null;

            //replace null values with empty string to maintain index order
            if (null == element) {
                elemValue = "";
            } else if (element instanceof Date) {
                elemValue = dateToString((Date)element);
            } else {
                elemValue = element.toString();
            }
            elemValue = tryUrlEncode(elemValue);
            builder.append(String.format(fmt, elemName, elemValue, separator));
        }

        //remove the last separator, if appended
        if ((builder.length() > 1) && (builder.charAt(builder.length() - 1) == separator))
            builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    /**
     * Tries Url encode using UTF-8
     * @param value The value to url encode
     * @return
     */
    private static String tryUrlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        }catch (Exception ex) {
            return value;
        }
    }

    /**
     * Converts a given object to a form encoded map
     * @param objName Name of the object
     * @param obj The object to convert into a map
     * @param objectMap The object map to populate
     * @param processed List of objects hashCodes that are already parsed
     * @throws InvalidObjectException
     */
    private static void objectToMap(
            String objName, Object obj, Map<String,Object> objectMap, HashSet<Integer> processed)
    throws InvalidObjectException {
        //null values need not to be processed
        if(obj == null)
            return;

        //wrapper types are autoboxed, so reference checking is not needed
        if(!isWrapperType(obj.getClass())) {
            //avoid infinite recursion
            if(processed.contains(obj.hashCode()))
                return;
            processed.add(obj.hashCode());
        }

        //process arrays
        if(obj instanceof Collection<?>) {
            //process array
            if((objName == null) ||(objName.isEmpty()))
                throw new InvalidObjectException("Object name cannot be empty");

            Collection<?> array = (Collection<?>) obj;
            //append all elements in the array into a string
            int index = 0;
            for (Object element : array) {
                //load key value pair
                String key = String.format("%s[%d]", objName, index++);
                loadKeyValuePairForEncoding(key, element, objectMap, processed);
            }
        } else if(obj.getClass().isArray()) {
            //process array
            if((objName == null) ||(objName.isEmpty()))
                throw new InvalidObjectException("Object name cannot be empty");

            Object[] array = (Object[]) obj;
            //append all elements in the array into a string
            int index = 0;
            for (Object element : array) {
                //load key value pair
                String key = String.format("%s[%d]", objName, index++);
                loadKeyValuePairForEncoding(key, element, objectMap, processed);
            }
         } else if(obj instanceof Map) {
            //process map
            Map<?, ?> map = (Map<?, ?>) obj;
            //append all elements in the array into a string
            for (Map.Entry<?, ?> pair : map.entrySet()) {
                String attribName = pair.getKey().toString();
                String key = attribName;
                if((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }
                loadKeyValuePairForEncoding(key, pair.getValue(), objectMap, processed);
            }
        } else if(obj instanceof UUID) {
            String key = objName;
            String value = obj.toString();
            //UUIDs can be converted to string
            loadKeyValuePairForEncoding(key, value, objectMap, processed);
        } else if (obj instanceof Date) {
            String key = objName;
            String value = dateToString((Date)obj);
            //UUIDs can be converted to string
            loadKeyValuePairForEncoding(key, value, objectMap, processed);
        } else {
            //process objects
            // invoke getter methods
            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods) {
                //is a getter?
                if ((method.getParameterTypes().length != 0)
                        || (!method.getName().startsWith("get")))
                    continue;

                //get json attribute name
                Annotation getterAnnotation = method.getAnnotation(JsonGetter.class);
                if (getterAnnotation == null)
                    continue;

                //load key name
                String attribName = ((JsonGetter) getterAnnotation).value();
                String key = attribName;
                if ((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }

                try {
                    //load key value pair
                    Object value = method.invoke(obj);
                    loadKeyValuePairForEncoding(key, value, objectMap, processed);
                } catch (Exception ex) {
                }
            }
            // load fields
            Field[] fields = obj.getClass().getFields();
            for (Field field : fields) {
                //load key name
                String attribName = field.getName();
                String key = attribName;
                if ((objName != null) && (!objName.isEmpty())) {
                    key = String.format("%s[%s]", objName, attribName);
                }

                try {
                    //load key value pair
                    Object value = field.get(obj);
                    loadKeyValuePairForEncoding(key, value, objectMap, processed);
                } catch (Exception ex) { }
            }
        }
    }

    /**
     * While processing objects to map, decides whether to perform recursion or load value
     * @param key The key to used for creating key value pair
     * @param value The value to process against the given key
     * @param objectMap The object map to process with key value pair
     * @param processed List of processed objects hashCodes
     * @throws InvalidObjectException
     */
    private static void loadKeyValuePairForEncoding(
            String key, Object value, Map<String, Object> objectMap, HashSet<Integer> processed)
    throws InvalidObjectException {
        if(value == null)
            return;
        if (isWrapperType(value.getClass()))
            objectMap.put(key, value);
        else
            objectToMap(key, value, objectMap, processed);
    }

    /**
     * List of classes that are wrapped directly. This information is need when
     * traversing object trees for reference matching
     */
    private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(
            Boolean.class, Character.class, Byte.class, Short.class, String.class,
            Integer.class, Long.class, Float.class, Double.class, Void.class, File.class));

    /**
     * Checks if the given class can be wrapped directly
     * @param clazz The given class
     * @return true if the given class is an autoboxed class e.g., Integer
     */
    private static boolean isWrapperType(Class clazz) {
        return WRAPPER_TYPES.contains(clazz) || clazz.isPrimitive() || clazz.isEnum();
    }
}