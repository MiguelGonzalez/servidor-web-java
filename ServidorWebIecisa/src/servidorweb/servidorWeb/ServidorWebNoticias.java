package servidorweb.servidorWeb;

import java.io.File;
import servidorweb.domain.ServidorModel;
import servidorweb.http.HttpInputStream;
import servidorweb.http.HttpOutputStream;
import servidorweb.http.datasInput.Cabecera;
import servidorweb.http.datasInput.Cookie;
import servidorweb.http.datasInput.Formulario;

/**
 *
 * @author Miguel González Gómez
 */
public class ServidorWebNoticias extends ServicioServidor {
    
    public ServidorWebNoticias(ServidorModel servidorModel) {
        super(servidorModel);
    }
    
    @Override
    public void doPost(HttpInputStream inputStream, HttpOutputStream outputStream) {
        Cabecera cabecera = inputStream.getCabecera();
        
        if(cabecera.recursoSolicitado.endsWith("login")) {
            tryLogin(inputStream, outputStream);
        } else if(cabecera.recursoSolicitado.endsWith("salir")) {
            outputStream.appendCookieToSend(new Cookie("login", "false"));
            escribirPaginaLogin(outputStream);
        } else {
            Cookie cookieLogin = inputStream.getCookie("login");
            if(estaLogueado(cookieLogin)) {
                File fichEstatico = new File(servidorModel.getPath() +
                        inputStream.getCabecera().recursoSolicitado);
                outputStream.servirEstatico(fichEstatico);
            } else {
                escribirPaginaLogin(outputStream);
            }
        }
        
    }
    
    private void tryLogin(HttpInputStream inputStream, HttpOutputStream outputStream) {
        Formulario formulario = inputStream.getFormulario();

        String name = formulario.get("name");
        String password = formulario.get("password");

        if("admin".equals(name) && "entrar".equals(password)) {
            respuestaJSonLoginOk(outputStream);
        } else {
            respuestaJSonLoginError(outputStream);
        }
    }
    
    private void respuestaJSonLoginOk(HttpOutputStream outputStream) {
        outputStream.appendCookieToSend(new Cookie("login", "true"));
        outputStream.writeResponse("{\"successful\":true}".getBytes(), 
                "application/json");
    }
    
    private void respuestaJSonLoginError(HttpOutputStream outputStream) {
        outputStream.appendCookieToSend(new Cookie("login", "false"));
        outputStream.writeResponse("{\"successful\":false}".getBytes(), 
                "application/json");
    }
    
    private void escribirPaginaLogin(HttpOutputStream outputStream) {
        File fichEstatico = new File(servidorModel.getPath() +
                "/admin/login.htm");
        outputStream.servirEstatico(fichEstatico);
    }

    @Override
    public void doGet(HttpInputStream inputStream, HttpOutputStream outputStream) {
        Cookie cookieLogin = inputStream.getCookie("login");
        if(estaLogueado(cookieLogin)) {
            File fichEstatico = new File(servidorModel.getPath()+
                        inputStream.getCabecera().recursoSolicitado);
                outputStream.servirEstatico(fichEstatico);
        } else {
            File fichEstatico = new File(servidorModel.getPath()+
                        "/admin/login.htm");
                outputStream.servirEstatico(fichEstatico);
        }
        
    }

    @Override
    public void init() {
        
    }
    
    private boolean estaLogueado(Cookie cookieLogin) {
        return cookieLogin != null && "true".equals(cookieLogin.valueCookie);
    }
    
}
