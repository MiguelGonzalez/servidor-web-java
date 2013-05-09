/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http.datasInput;

/**
 *
 * @author Administrator
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
