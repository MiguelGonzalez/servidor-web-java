/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Cabecera;

public abstract class IServidorWeb {
    public abstract void init();
    public abstract void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream);
    public abstract void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream);
    
    public String PATH_INI;
    
    public Map<String, String> valores;
    
    public IServidorWeb(Map<String, String> valores) {
        this.valores = valores;
        
        PATH_INI = valores.get("path");
    }
    
    public int getPort() {
        String port = valores.get("port");
        
        return Integer.parseInt(port);
    }
    
    public String getServicio() {
        String servicio = valores.get("servicio");
        
        return servicio;
    }
    
    public String getConfiguracion(String data) {
        return valores.get(data);
    }
    
    public final void servirEstatico(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        System.out.println("Servido estático");
        Cabecera cabeceraPeticion = inputStream.getCabecera();

        String recursoSolicitado = cabeceraPeticion.recursoSolicitado;
        if(existeRecursoSolicitado(recursoSolicitado)) {
            ouputStream.writeResponse(
                    getContenidoPagina(recursoSolicitado));
        } else {
            byte[] error404;
            if(existeRecursoSolicitado(recursoSolicitado)) {
                error404 = getContenidoPagina("error404.htm");
            } else {
                error404 = "<h1>Error 404 - El recurso solicitado no existe".getBytes();
            }
            
            ouputStream.writeError(error404);
        }
    }
    
    public byte[] getContenidoPagina(String recursoSolicitado) {
        File fileRecurso = new File(PATH_INI, recursoSolicitado);
        byte fileContent[] = null;
        
        if(fileRecurso.exists()) {
            FileInputStream  fis = null;
            InputStreamReader isr = null;
            
            try {
                fis = new FileInputStream(fileRecurso);
                
                fileContent = new byte[(int)fileRecurso.length()];
                fis.read(fileContent);
            } catch (FileNotFoundException ex) {
                ServidorWebIecisa.log.error(ex);
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ServidorWebIecisa.log.error(ex);
                }
            }
            return fileContent;
        } else {
            return fileContent;
        }
    }
    
    public boolean existeRecursoSolicitado(String recursoSolicitado) {
        return new File(PATH_INI, recursoSolicitado).exists();
    }
}
