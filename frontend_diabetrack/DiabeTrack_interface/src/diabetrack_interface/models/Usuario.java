/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.models;

/**
 *
 * @author ESDPC
 */
public class Usuario {

    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String fecha_nacimiento;
    private String sexo;
    private Double peso;
    private Double altura;
    private String año_diagnostico;
    private String tipo_insulina;
    private String marca_insulina;

    private Rol rol;

    // --- GETTERS Y SETTERS ----
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(String fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public String getAño_diagnostico() { return año_diagnostico; }
    public void setAño_diagnostico(String año_diagnostico) { this.año_diagnostico = año_diagnostico; }

    public String getTipo_insulina() { return tipo_insulina; }
    public void setTipo_insulina(String tipo_insulina) { this.tipo_insulina = tipo_insulina; }

    public String getMarca_insulina() { return marca_insulina; }
    public void setMarca_insulina(String marca_insulina) { this.marca_insulina = marca_insulina; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
