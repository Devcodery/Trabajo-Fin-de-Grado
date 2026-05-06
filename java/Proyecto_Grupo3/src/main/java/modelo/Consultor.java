package modelo;

public class Consultor extends Usuario {
    private String sede;
    private String departamento;

    public Consultor(int idUsuario, String nombre, String apellidos, String rol, String direccion, 
                    String email, String sede, String departamento, String passwd) {
        super(idUsuario, nombre, apellidos, rol, direccion, email, passwd);
        this.sede = sede;
        this.departamento = departamento;
    }

    public Consultor(String nombre, String apellidos, String rol, String direccion, 
                    String email, String sede, String departamento, String passwd) {
        super(nombre, apellidos, rol, direccion, email, passwd);
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
