/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import java.util.Map;
import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.procesadoresPeticion.ProcesarContentTypePeticion;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Formulario;

/**
 *
 * @author Administrator
 */
public class ServidorWeb extends IServidorWeb {

    public ServidorWeb(Map<String, String> valores) {
        super(valores);
    }
    
    @Override
    public void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        ProcesarContentTypePeticion procesarContentType = new ProcesarContentTypePeticion();
        
        Formulario formularioPeticion = inputStream.getFormulario();
        
        System.out.println("Nombre: " + formularioPeticion.get("name"));
        System.out.println("Contraseña: " + formularioPeticion.get("password"));
        
        ouputStream.writeResponse("{\"successful\":\"true\"}".getBytes(),
                procesarContentType.getContentTypeReal(ProcesarContentTypePeticion.TYPE_APPLICATION_JSON));
    }

    @Override
    public void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        Cabecera cabeceraPeticion = inputStream.getCabecera();
        
        
        String recursoSolicitado = cabeceraPeticion.recursoSolicitado;
        if(existeRecursoSolicitado(recursoSolicitado)) {
            ouputStream.writeResponse(
                    getContenidoPagina(recursoSolicitado));
        } else {
            byte[] error404 = getContenidoPagina("error404.htm");
            
            ouputStream.writeError(error404);
        }
    }

    @Override
    public void init() {
        
    }
    
}
