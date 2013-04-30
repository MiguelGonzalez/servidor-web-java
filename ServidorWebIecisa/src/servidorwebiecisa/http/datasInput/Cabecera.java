/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http.datasInput;

/**
 *
 * @author Administrator
 */
public class Cabecera {
    
    public static final int ACTION_GET = 1;
    public static final int ACTION_POST = 2;
    
    public int action;
    public String protocoloHtpp;
    public String recursoSolicitado;
    public String host;
    public String userAgent;
    public String accept;
    public String acceptLanguage;
    public String acceptEncoding;
    public String connection;
    
    public Cabecera() {
        action = -1;
        protocoloHtpp = "";
        recursoSolicitado = "";
        host = "";
        userAgent = "";
        accept = "";
        acceptLanguage = "";
        acceptEncoding = "";
        connection = "";
    }
}
