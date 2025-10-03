import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerLogs {
    public static void main(String[] args) {
        try {
            // 1. Cargar el archivo logs.xml
            File archivo = new File("logs.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            // Normalizar
            documento.getDocumentElement().normalize();

            // 2. Elemento raíz
            Element raiz = documento.getDocumentElement();
            System.out.println("Elemento raíz: " + raiz.getNodeName());

            // 3. Lista de <log>
            NodeList listaLogs = documento.getElementsByTagName("log");

            // 4. Recorrer cada <log>
            for (int i = 0; i < listaLogs.getLength(); i++) {
                Node nodo = listaLogs.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element log = (Element) nodo;

                    // Extraer datos
                    String id = log.getAttribute("id");
                    String nivel = log.getElementsByTagName("nivel").item(0).getTextContent();
                    String mensaje = log.getElementsByTagName("mensaje").item(0).getTextContent();
                    String usuario = log.getElementsByTagName("usuario").item(0).getTextContent();

                    // Mostrar en consola
                    System.out.println("----- Registro de log -----");
                    System.out.println("ID: " + id);
                    System.out.println("Nivel: " + nivel);
                    System.out.println("Mensaje: " + mensaje);
                    System.out.println("Usuario: " + usuario);
                    System.out.println("---------------------------");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}