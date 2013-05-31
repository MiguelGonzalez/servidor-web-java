package servidorweb.http.datasInput;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Miguel González Gómez
 */
public class ContentType {
    
    public final static String TYPE_APPLICATION_JSON = ".json";
    public final static String TYPE_APPLICATION_JAVASCRIPT = ".js";
    public final static String TYPE_APPLICATION_OGG = ".ogx";
    public final static String TYPE_APPLICATION_PDF = ".pdf";
    public final static String TYPE_APPLICATION_XML = ".xml";
    
    public final static String TYPE_IMAGE_GIF = ".gif";
    public final static String TYPE_IMAGE_JPEG_1 = ".jpg";
    public final static String TYPE_IMAGE_JPEG_2 = ".jpgv";
    public final static String TYPE_IMAGE_PNG = ".png";
    
    public final static String TYPE_TEXT_CSS = ".css";
    public final static String TYPE_TEXT_HTML_1 = ".html";
    public final static String TYPE_TEXT_HTML_2 = ".htm";
    public final static String TYPE_TEXT_CSV = ".csv";
    public final static String TYPE_TEXT_PLAIN = ""; //Por defecto
    
    public final static String TYPE_VIDEO_MPEG = ".mpeg";
    public final static String TYPE_VIDEO_MP4 = ".mp4";
    public final static String TYPE_VIDEO_OGG = ".ogv";
    
    private final static HashMap<String, String> contentTypes;
    
    static {
        contentTypes = new HashMap<>();
        
        contentTypes.put(TYPE_APPLICATION_JSON, "application/json");
        contentTypes.put(TYPE_APPLICATION_JAVASCRIPT, "application/javascript");
        contentTypes.put(TYPE_APPLICATION_OGG, "application/ogg");
        contentTypes.put(TYPE_APPLICATION_PDF, "application/pdf");
        contentTypes.put(TYPE_APPLICATION_XML, "application/xml");
        
        contentTypes.put(TYPE_IMAGE_GIF, "image/gif");
        contentTypes.put(TYPE_IMAGE_JPEG_1, "image/jpeg");
        contentTypes.put(TYPE_IMAGE_JPEG_2, "image/jpeg");
        contentTypes.put(TYPE_IMAGE_PNG, "image/png");
        
        contentTypes.put(TYPE_TEXT_CSS, "text/css");
        contentTypes.put(TYPE_TEXT_HTML_1, "text/html");
        contentTypes.put(TYPE_TEXT_HTML_2, "text/html");
        contentTypes.put(TYPE_TEXT_CSV, "image/csv");
        contentTypes.put(TYPE_TEXT_PLAIN, "text/plain");
        
        contentTypes.put(TYPE_VIDEO_MPEG, "video/mpeg");
        contentTypes.put(TYPE_VIDEO_MP4, "video/mp4");
        contentTypes.put(TYPE_VIDEO_OGG, "video/ogg");
    }
    
    public static String getContentType(File fich) {
        String nameFich = fich.getName();
        int inicioExtension = nameFich.lastIndexOf(".");
        String extension = "";
        if(inicioExtension != -1) {
            extension = nameFich.substring(inicioExtension);
        }
        
        return contentTypes.get(extension.toLowerCase());
    }
    
    public String getContentTypeReal(String tipoContentType) {
        return contentTypes.get(tipoContentType);
    }
}
