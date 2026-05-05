package modelo;

public class Cliente extends Usuario {
    public Cliente(int idUsuario, String nombre, String apellidos, String rol, String direccion, String email, String passwd) {
        super(idUsuario, nombre, apellidos, rol, direccion, email, passwd);
    }

    public Cliente(String nombre, String apellidos, String rol, String direccion, String email, String passwd) {
        super(nombre, apellidos, rol, direccion, email, passwd);
    }

}
