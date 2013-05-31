package servidorweb.http.datasInput;

/**
 *
 * @author Miguel González Gómez
 */
public class Cookie {
    
    public String nameCookie;
    public String valueCookie;
    
    public Cookie() {
        nameCookie = "";
        valueCookie = "";
    }
    
    public Cookie(String nameCookie, String valueCookie) {
        this.nameCookie = nameCookie;
        this.valueCookie = valueCookie;
    }
}
