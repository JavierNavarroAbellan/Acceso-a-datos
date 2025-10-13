package Marshalling_Objeto_a_XML;

/**
 * @author Javier Navarro Abellán
 * @date 09/10/2025
 */

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String rol;

    // Constructor por defecto
    public Usuario() {}

    // Constructor con argumentos
    public Usuario(String id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }
    @XmlElement
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    @XmlElement
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    @XmlElement
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @XmlElement
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}