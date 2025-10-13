package Unmarshalling_XML_a_Objeto;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class DeserializarUsuario {
    public static void main(String[] args) {
        //Comprobamos si existe el fichero usuario (el xml donde está la información a deserializar)
        File xmlFile = new File("usuario.xml");
        if (!xmlFile.exists()) {
            System.out.println("El archivo no existe en el directorio del proyecto");
        }

        try {
            //1º Creamos el contexto para la clase Usuario
            JAXBContext context = JAXBContext.newInstance(Usuario.class);

            //2º Creal el unmarshaller (convierte XML en objeto)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            //3º Deserializamos el archivo XML (leer el archivo XML y obtener el objeto
            Usuario usuario = (Usuario) unmarshaller.unmarshal(xmlFile);

            //4º Mostrar los datos por consola
            System.out.println("Datos del usuario: ");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Rol: " + usuario.getRol());


        }catch(Exception e){
            System.err.println("Error haciendo unmarshalling: " + e.getMessage());
        }
    }
}