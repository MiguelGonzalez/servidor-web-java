/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import java.util.HashMap;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.http.datasInput.Cookie;
import servidorwebiecisa.http.datasInput.Formulario;

/**
 *
 * @author paracaidista
 */
public class ServidorWebNoticias extends IServidorWeb {
    
    public ServidorWebNoticias(HashMap<String, String> valores) {
        super(valores);
    }
    
    @Override
    public void doPost(HttpInputStream inputStream, HttpOutputStream outputStream) {
        Cabecera cabecera = inputStream.getCabecera();
        
        if(cabecera.recursoSolicitado.endsWith("login")) {
            Formulario formulario = inputStream.getFormulario();
            
            String name = formulario.get("name");
            String password = formulario.get("password");
            
            if("admin".equals(name) && "entrar".equals(password)) {
                outputStream.establecerCookie(new Cookie("login", "true"));
                outputStream.writeResponse("{\"successful\":true}".getBytes(), 
                        "application/json");
            } else {
                outputStream.establecerCookie(new Cookie("login", "false"));
                outputStream.writeResponse("{\"successful\":false}".getBytes(), 
                        "application/json");
            }
        } else if(cabecera.recursoSolicitado.endsWith("salir")) {
            outputStream.establecerCookie(new Cookie("login", "false"));
            outputStream.writeResponse(getContenidoPagina("admin/login.htm"), "text/html");
        } else {
            Cookie cookieLogin = inputStream.getCookie("login");
            if(controlLogin(cookieLogin)) {
                servirEstatico(inputStream, outputStream);
            } else {
                outputStream.writeResponse(getContenidoPagina("admin/login.htm"));
            }
        }
        
    }

    @Override
    public void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        Cookie cookieLogin = inputStream.getCookie("login");
        if(controlLogin(cookieLogin)) {
            servirEstatico(inputStream, ouputStream);
        } else {
            ouputStream.writeResponse(getContenidoPagina("admin/login.htm"));
        }
        
    }

    @Override
    public void init() {
        
    }
    
    private boolean controlLogin(Cookie cookieLogin) {
        return cookieLogin != null && "true".equals(cookieLogin.valueCookie);
    }
    
}
