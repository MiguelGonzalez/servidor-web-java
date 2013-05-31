package servidorweb.procesadoresPeticion;

import servidorweb.http.datasInput.Formulario;

/**
 *
 * @author Miguel González Gómez
 */
public class ContruyeFormulario {
    public ContruyeFormulario() {
        
    }
    
    public Formulario getFormulario(String peticionCliente) {
        String datosCabeceraFilas[] = peticionCliente.split("\n");
        
        return procesarFormularioPeticion(datosCabeceraFilas);
    }
    
    public Formulario procesarFormularioPeticion(String[] datosCabeceraFilas) {
        Formulario formulario = new Formulario();
        
        boolean procesandoFormulario = false;
        for(String linea : datosCabeceraFilas) {
            if(linea.trim().isEmpty()) {
                //Nos saltamos las primeras líneas de la cabecera
                procesandoFormulario = true;
                continue;
            }
            
            if(procesandoFormulario) {
                String arrayDatosForm[] = linea.split("&");

                for(String datoForm : arrayDatosForm) {
                    String nombreValor[] = datoForm.split("=");

                    if(nombreValor.length == 2) {
                        formulario.datosFormulario.put(nombreValor[0], nombreValor[1]);
                    }
                }
            }
        }
        
        return formulario;
    }
}
