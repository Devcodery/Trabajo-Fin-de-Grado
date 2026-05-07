package modelo;

public class Cliente extends Usuario {
    public Cliente(int idUsuario, String nombre, String apellidos, String direccion, String email) {
        super(idUsuario, nombre, apellidos, direccion, email);
    }

    public Cliente(String nombre, String apellidos, String direccion, String email) {
        super(nombre, apellidos, direccion, email);
    }

}
