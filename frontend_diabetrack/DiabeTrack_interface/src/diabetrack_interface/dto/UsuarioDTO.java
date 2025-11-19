/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.dto;

/**
 *
 * @author ESDPC
 */
public class UsuarioDTO {

    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String sexo;
    private Integer peso;
    private Integer altura;
    private String tipoInsulina;
    private String marcaInsulina;
    private Integer yearDiagnostico;

    // --- Getters y Setters ---
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getPeso() {
        return peso;
    }
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }
    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getTipoInsulina() {
        return tipoInsulina;
    }
    public void setTipoInsulina(String tipoInsulina) {
        this.tipoInsulina = tipoInsulina;
    }

    public String getMarcaInsulina() {
        return marcaInsulina;
    }
    public void setMarcaInsulina(String marcaInsulina) {
        this.marcaInsulina = marcaInsulina;
    }

    public Integer getYearDiagnostico() {
        return yearDiagnostico;
    }
    public void setYearDiagnostico(Integer yearDiagnostico) {
        this.yearDiagnostico = yearDiagnostico;
    }
}


