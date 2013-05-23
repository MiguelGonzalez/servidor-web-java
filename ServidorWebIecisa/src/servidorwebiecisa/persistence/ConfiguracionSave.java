/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.domain.ServidorModel;

public class ConfiguracionSave {
    private ConfiguracionModel configuracionModel;
    
    public ConfiguracionSave(ConfiguracionModel configuracionModel) {
        this.configuracionModel = configuracionModel;
    }
    
    public void saveModel() {
        saveToPhisicalModel();
    }
    
    private void saveToPhisicalModel() {
        Document xmlDoc = null;
        Element elementoCabecera = null;
        
        DocumentBuilderFactory dbFactory;
        DocumentBuilder docBuilder;
        
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
            
            xmlDoc = docBuilder.newDocument();

            //Creamos el elemento que solo contendrá la cabecera
            elementoCabecera = xmlDoc.createElement("configuracion");
            xmlDoc.appendChild(elementoCabecera);
            
            Element numPoolSockets = xmlDoc.createElement("numPoolSockets");
            numPoolSockets.setTextContent(Integer.toString(configuracionModel.
                    getNumPoolSockets()));
            elementoCabecera.appendChild(numPoolSockets);
            
            Element keepAliveTimeOut = xmlDoc.createElement("keepAliveTimeOut");
            keepAliveTimeOut.setTextContent(Integer.toString(configuracionModel.
                    getKeepAliveTimeout()/1000));
            elementoCabecera.appendChild(keepAliveTimeOut);
            
            Element servidores = xmlDoc.createElement("servidores");
            
            for(ServidorModel servidorModel : configuracionModel.getServidores()) {
                Element servidor = xmlDoc.createElement("servidor");
                
                Element port = xmlDoc.createElement("port");
                port.setTextContent(Integer.toString(servidorModel.getPort()));
                servidor.appendChild(port);
                
                Element path = xmlDoc.createElement("path");
                path.setTextContent(servidorModel.getPath());
                servidor.appendChild(path);
                
                Element pathRelativeServicio = xmlDoc.createElement("pathRelativeServicio");
                pathRelativeServicio.setTextContent(servidorModel.getPathRelativeServicio());
                servidor.appendChild(pathRelativeServicio);
                
                Element classServicio = xmlDoc.createElement("classServicio");
                classServicio.setTextContent(servidorModel.getClassServicio());
                servidor.appendChild(classServicio);
                
                servidores.appendChild(servidor);
            }
            
            elementoCabecera.appendChild(servidores);
            
            //Escribimos el XML en el fichero
            OutputStream out = null;
            OutputStreamWriter osw = null;
            StreamResult result;
            DOMSource source;
            Transformer transformer;
            TransformerFactory transformerFactory;

            try {
                transformerFactory = TransformerFactory.newInstance();
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                source = new DOMSource(xmlDoc);
                
                File fichGuardar = new File(ServidorWebIecisa.
                        propiedadesServidor.getProperty("nombreFicheroXML"));
                out = new FileOutputStream(
                        fichGuardar, false);
                
                osw = new OutputStreamWriter(out, "UTF-8");
                result = new StreamResult(osw);

                transformer.transform(source, result);
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(ConfiguracionSave.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                ServidorWebIecisa.log.error("Error", ex);
            } catch(IOException ex) {
                ServidorWebIecisa.log.error("Error", ex);
            } finally {
                try {
                    if(osw != null) {
                        osw.close();
                    }
                    if(out != null) {
                        out.close();
                    }
                } catch(IOException ex) {
                    ServidorWebIecisa.log.error("Error", ex);
                }
            }
        } catch (ParserConfigurationException ex) {
            ServidorWebIecisa.log.error("Error", ex);
        }
    }
}
