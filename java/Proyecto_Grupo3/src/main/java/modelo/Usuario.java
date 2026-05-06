package modelo;

public abstract class Usuario {
    protected int idUsuario;
    protected String nombre;
    protected String apellidos;
    protected String direccion;
    protected String rol;
    protected String email;
    protected String passwd;

    public Usuario(int idUsuario, String nombre, String apellidos, String rol, String direccion, String email, String passwd) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.direccion = direccion;
        this.email = email;
        this.passwd = passwd;
    }

    public Usuario(String nombre, String apellidos, String rol, String direccion, String email, String passwd) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.direccion = direccion;
        this.email = email;
        this.passwd = passwd;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
