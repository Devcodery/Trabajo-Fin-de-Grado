package modelo;

public class Consultor extends Usuario {
    private String sede;
    private String departamento;

    public Consultor(int idUsuario, String nombre, String apellidos, String direccion, 
                    String email, String sede, String departamento) {
        super(idUsuario, nombre, apellidos, direccion, email);
        this.sede = sede;
        this.departamento = departamento;
    }

    public Consultor(String nombre, String apellidos, String direccion, 
                    String email, String sede, String departamento) {
        super(nombre, apellidos, direccion, email);
        this.sede = sede;
        this.departamento = departamento;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
