package modelo;

import java.util.Date;

public class Consulta {
    private int idConsulta;
    private String estadoActual;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaFin;
    private int idServicio;
    private int idCliente;
    private int idConsultor;

    public Consulta(int idConsulta, String estadoActual, String titulo, String descripcion, Date fechaCreacion,
			Date fechaFin, int idServicio, int idCliente, int idConsultor) {
		this.idConsulta = idConsulta;
		this.estadoActual = estadoActual;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.idServicio = idServicio;
		this.idCliente = idCliente;
		this.idConsultor = idConsultor;
	}
    
    public String toString() {
    	return "TITULO: " + this.titulo;
    }

	public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(int idConsultor) {
        this.idConsultor = idConsultor;
    }
   
}
