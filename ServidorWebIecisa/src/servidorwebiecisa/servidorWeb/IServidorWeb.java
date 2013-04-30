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
import servidorwebiecisa.ConfiguracionServidor;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;

public abstract class IServidorWeb {
    public abstract void init();
    public abstract void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream);
    public abstract void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream);
    
    public static String PATH_INI;
    
    private ConfiguracionServidor configuracionServidor;
    
    public IServidorWeb() {
        configuracionServidor = ConfiguracionServidor.getInstance();
        
        PATH_INI = configuracionServidor.getPathIni();
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
