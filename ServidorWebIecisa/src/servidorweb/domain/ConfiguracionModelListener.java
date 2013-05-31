package servidorweb.domain;

/**
 *
 * @author Miguel González Gómez
 */
public interface ConfiguracionModelListener {
    public void addedServidor(ServidorModel servidorModel);
    public void removedServidor(ServidorModel servidorModel);
}
