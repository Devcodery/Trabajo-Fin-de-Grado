package modelo;

import java.sql.Date;

public class Servicio {
    private int idServicio;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String sede;
    private String tecnologiasImplicadas;
    private String objetivos;
    private String alcance;
    private String beneficios;
    private Date fechaCreacion;
    private boolean estado;

    public Servicio(int idServicio, String nombre, String descripcion, String tecnoImpli, String objetivos,
                    String alcance, String beneficios, String categoria, String sede, Date fechaCreacion, boolean estado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.tecnologiasImplicadas = tecnoImpli;
        this.objetivos = objetivos;
        this.alcance = alcance;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Servicio(String nombre, String descripcion, String categoria, String tecnoImpli, String objetivos,
                    String alcance, String beneficios, String sede, Date fechaCreacion, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sede = sede;
        this.tecnologiasImplicadas = tecnoImpli;
        this.objetivos = objetivos;
        this.alcance = alcance;
        this.beneficios = beneficios;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTecnologiasImplicadas() {
        return tecnologiasImplicadas;
    }

    public void setTecnologiasImplicadas(String tecnologiasImplicadas) {
        this.tecnologiasImplicadas = tecnologiasImplicadas;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }
}
