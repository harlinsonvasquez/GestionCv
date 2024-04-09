package entity;

import java.time.LocalDate;

public class Contratacion {
    private int id;
    private Empresa empresa;
    private Coder coder;
    private Vacante vacante;
    private LocalDate fecha_contratacion;
    private String estado;
    private double salario;

    public Contratacion(){}
    public Contratacion(int id, Empresa empresa, Coder coder, LocalDate fecha_contratacion, String estado, double salario){
        this.id = id;
        this.empresa = empresa;
        this.coder = coder;
        this.fecha_contratacion = fecha_contratacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Coder getCoder() {
        return coder;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    public LocalDate getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "id contratacion =" + id +"  ,"+
                "  id_vacante =" + vacante.getId() +"  ,"+
                "  id_empresa =" + empresa.getId() +"  ,"+
                "  empresa =" + empresa.getNombre() +"  ,"+
                "  coder_id =" + coder.getId() +"  ,"+
                "  nombre_coder =" + coder.getNombre() +"  ,"+
                "  fecha_contratacion =" + fecha_contratacion +"  ,"+
                "  estado ='" + estado + '\'' +"  ,"+
                "  salario =" + salario +"  ,"+
                '}';
    }
}
