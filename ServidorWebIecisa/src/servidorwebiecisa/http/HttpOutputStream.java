/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import servidorwebiecisa.ServidorWebIecisa;

/**
 *
 * @author Administrator
 */
public class HttpOutputStream {
    private DataOutputStream streamOutput;
    private SimpleDateFormat parserDate;
    private String contentTypeDefault;
    
    private boolean escritaCabecera = false;
    
    public HttpOutputStream(DataOutputStream streamOutput, String contentTypeDefault) {
        this.streamOutput = streamOutput;
        this.contentTypeDefault = contentTypeDefault;
        
        parserDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");        
    }
    
    public DataOutputStream getDataOuputStream() {
        return streamOutput;
    }
    
    public synchronized void writeError(byte[] response) {
        try {
            streamOutput.writeBytes("HTTP/1.0 404 Not Found\r\n");
            streamOutput.writeBytes("\r\n");
            streamOutput.write(response);
        } catch (IOException ex) {
            ServidorWebIecisa.log.error(ex);
        }
    }
    
    public synchronized void writeHeader() {
        try {
            streamOutput.writeBytes("HTTP/1.1 200 OK\r\n");
            streamOutput.writeBytes("Date: " + parserDate.format(new Date()) + " GMT\r\n");
            streamOutput.writeBytes("Server: Apache/0.8.4\r\n");
            streamOutput.writeBytes("Connection: Keep-Alive\r\n");
            streamOutput.writeBytes("Content-Type: " + contentTypeDefault + "\r\n");
        } catch (IOException ex) {
            ServidorWebIecisa.log.error(ex);
        }
    }
    
    public synchronized void writeHeader(String contentType) {
        try {
            escritaCabecera = true;
            streamOutput.writeBytes("HTTP/1.1 200 OK\r\n");
            streamOutput.writeBytes("Date: " + parserDate.format(new Date()) + " GMT\r\n");
            streamOutput.writeBytes("Server: Apache/0.8.4\r\n");
            streamOutput.writeBytes("Connection: Keep-Alive\r\n");
            streamOutput.writeBytes("Content-Type: " + contentType + "\r\n");
        } catch (IOException ex) {
            ServidorWebIecisa.log.error(ex);
        }
    }
    
    public synchronized void writeResponse(byte[] response) {
        try {
            if(!escritaCabecera) {
                writeHeader();
            }
            streamOutput.writeBytes("Content-Length: " + response.length + "\r\n");
            //streamOutput.writeBytes("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            //streamOutput.writeBytes("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            streamOutput.writeBytes("\r\n");
            streamOutput.write(response);
        } catch (IOException ex) {
            ServidorWebIecisa.log.error(ex);
        }
    }
    
    public synchronized void writeResponse(byte[] response, String contentType) {
        if(!escritaCabecera) {
            writeHeader(contentType);
        }
        
        writeResponse(response);
    }
}
