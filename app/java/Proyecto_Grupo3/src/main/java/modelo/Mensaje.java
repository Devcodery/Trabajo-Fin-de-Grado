package modelo;

public class Mensaje {
    private int idMensaje;
    private int idUsuario;
    private int idConsulta;
    private String asunto;
    private String contenido;
    
    public Mensaje(int idMensaje,int idUsuario, int idConsulta, String asunto, String contenido) {
        this.idMensaje = idMensaje;
        this.idUsuario = idUsuario;
        this.idConsulta = idConsulta;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
    
    
    
}
