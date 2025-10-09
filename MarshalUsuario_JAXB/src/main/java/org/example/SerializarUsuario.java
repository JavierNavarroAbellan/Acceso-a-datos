package org.example;

/**
 * @author Javier Navarro Abellán
 * @date 09/10/2025
 */

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class SerializarUsuario {
    public static void main(String[] args) {
        try {
            Usuario usuario = new Usuario("U001", "Juan Pérez", "juan.perez@email.com", "admin");
            JAXBContext context = JAXBContext.newInstance(Usuario.class);

            //Marshaller que convierte objeto a XML
            Marshaller marshaller = context.createMarshaller();

            //Formatea el archivo para que se vea como XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Serializa el objeto a usuario.xml
            marshaller.marshal(usuario, new File("usuario.xml"));

            // Imprime el XML por consola
            marshaller.marshal(usuario, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}