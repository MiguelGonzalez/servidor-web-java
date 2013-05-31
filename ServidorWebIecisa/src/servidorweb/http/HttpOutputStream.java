package servidorweb.http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import servidorweb.MainServidor;
import servidorweb.http.datasInput.Cookie;
import servidorweb.http.datasInput.ContentType;

/**
 *
 * @author Miguel González Gómez
 */
public class HttpOutputStream {
    private DataOutputStream streamOutput;
    private SimpleDateFormat parserDate;

    private List<Cookie> cookiesEnviar;
    
    public HttpOutputStream(DataOutputStream streamOutput) {
        this.streamOutput = streamOutput;

        parserDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        cookiesEnviar = new ArrayList<>();
    }
    
    public DataOutputStream getDataOuputStream() {
        return streamOutput;
    }
    
    public synchronized void writeResponse(byte[] response, String contentType) {
        try {
            writeHeader(contentType);

            streamOutput.writeBytes("Content-Length: " + response.length + "\r\n");
            streamOutput.writeBytes("\r\n");
            streamOutput.write(response);
        } catch (IOException ex) {
            MainServidor.log.error(ex);
        }
    }
    
    private synchronized void writeHeader(String contentType) {
        try {
            streamOutput.writeBytes("HTTP/1.1 200 OK\r\n");
            streamOutput.writeBytes("Date: " + parserDate.format(new Date()) + " GMT\r\n");
            streamOutput.writeBytes("Server: Apache/0.8.4\r\n");
            streamOutput.writeBytes("Connection: Keep-Alive\r\n");
            streamOutput.writeBytes("Content-Type: " + contentType + "\r\n");
            for(Cookie cookie : cookiesEnviar) {
                streamOutput.writeBytes("Set-Cookie: " + cookie.nameCookie + "=" + cookie.valueCookie + "\r\n");
            }
        } catch (IOException ex) {
            MainServidor.log.error(ex);
        }
    }
    
    private synchronized void writeError(byte[] response) {
        try {
            writeHeader("text/html");
            streamOutput.writeBytes("Content-Length: " + response.length + "\r\n");
            streamOutput.writeBytes("\r\n");
            streamOutput.write(response);
        } catch (IOException ex) {
            MainServidor.log.error(ex);
        }
    }
    
    public void appendCookieToSend(Cookie cookie) {
        cookiesEnviar.add(cookie);
    }
    
    public final void servirEstatico(File fileEstatico) {
        if(existeRecursoSolicitado(fileEstatico)) {
            writeResponse(
                    getContenidoPagina(fileEstatico),
                    getContentTypeRecurso(fileEstatico));
        } else {
            byte[] error404 = "<h1>Error 404 - El recurso solicitado no existe</h1>".
                    getBytes();
            writeError(error404);
        }
    }
    
    private byte[] getContenidoPagina(File fileEstatico) {
        byte fileContent[] = null;
        
        if(fileEstatico.exists()) {
            FileInputStream  fis = null;
            
            try {
                fis = new FileInputStream(fileEstatico);
                
                fileContent = new byte[(int)fileEstatico.length()];
                fis.read(fileContent);
            } catch (FileNotFoundException ex) {
                MainServidor.log.error(ex);
            } catch (IOException ex) {
                MainServidor.log.error(ex);
            } finally {
                try {
                    if(fis != null) {
                        fis.close();
                    }
                } catch (IOException ex) {
                    MainServidor.log.error(ex);
                }
            }
            return fileContent;
        } else {
            return fileContent;
        }
    }
    
    private String getContentTypeRecurso(File fichRecurso) {
        return ContentType.getContentType(fichRecurso);
    }
    
    private boolean existeRecursoSolicitado(File recursoSolicitado) {
        return recursoSolicitado.exists() && recursoSolicitado.isFile();
    }
}
