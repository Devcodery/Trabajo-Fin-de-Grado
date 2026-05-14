package modelo;

public class Mensaje {
    private int idMensaje;
    private Usuario usuario;
    private Consulta consulta;
    private String asunto;
    private String contenido;

    public Mensaje(int idMensaje, Usuario usuario, Consulta consulta, String asunto, String contenido) {
        this.idMensaje = idMensaje;
        this.usuario = usuario;
        this.consulta = consulta;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
