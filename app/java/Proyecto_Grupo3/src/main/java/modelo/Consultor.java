package modelo;

public class Consultor extends Usuario {
    private int sede;
    private int departamento;

    public Consultor(int idUsuario, String nombre, String apellidos, String direccion, 
                    String correo, int sede, int departamento) {
        super(idUsuario, nombre, apellidos, direccion, correo);
        this.sede = sede;
        this.departamento = departamento;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

}
