/**
Elvia Sanchez Leiva
9 de noviembre 2025.
* Clase: Reporte Mascota (abstracta)
* 
* Qué hace: Clase base abstracta que representa un reporte de mascota con todos
* los atributos comunes y validaciones basicas.
* 
* Características:
* - Atributos comunes a todos los reportes
* - Validaciones heredadas de clase Reporte original
* - Métodos comunes para formato y visualización

*/


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



public abstract class ReporteMascota {
    //atributos Protegidos para herencia
    protected String idReporte;
    protected LocalDate fechaReporte;
    protected boolean resuelto; //false = activo, true = resuelto
    protected String zonaReporte;
    protected Cliente cliente;
    protected Mascota mascota;
    
    
    //Constructor protegido para uso de subclases
    protected ReporteMascota(){
        //constructor base
    }
    
    //metodo abstracto
    public abstract String getTipoReporte();
    
    //Metodos set y validaciones
    public final void setIdReporte (String idReporte){
        if(idReporte!= null){
           idReporte = idReporte.trim();
           if (idReporte.matches("^REP-\\d{4}$")){ // Valida que el ID comience con "REP-" seguido de 4 dígitos
                this.idReporte = idReporte;
                return;
            }        
        }
        throw new IllegalArgumentException("ID inválido. Ejemplo válido: REP-1234");     
    }
    
    
    
    /*** Convierte String a LocalDate usando DateTimeFormatter (Java Time API) usando el formato dd/MM/yyyy.
 * Referencia: Java Documentation - DateTimeFormatter y LocalDate.parse()*/
    /**
  * Si el texto es nulo o vacío, se asigna la fecha actual.
 * @param fechaTexto la fecha en formato dd/MM/yyyy
 */
    public final void setFechaReporte (String fechaTexto){
        if (fechaTexto == null || fechaTexto.trim ().isEmpty()) {
            this.fechaReporte = LocalDate.now();
        } else {
            try { //Aqui se convierte el string a fecha sistema
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                this.fechaReporte = LocalDate.parse(fechaTexto.trim(), formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException ("Fecha inválida. Formato: dd/mm/aaaa");
            }
        }
    }    
        
    public final void setResuelto (boolean resuelto) {
        this.resuelto = resuelto;   
    }
    
    public final void setEstadoDesdeString (String estado){
        if (estado != null) {
            estado = estado.trim().toUpperCase();
            if (estado.equals("RESUELTO")) {
                this.resuelto = true;
            } else 
                if (estado.equals("ACTIVO")){
                this.resuelto = false;
            } else {
                throw new IllegalArgumentException ("Estado inválido. Debe ser 'ACTIVO' o 'RESUELTO'.");
            }
        } else {
            this.resuelto = false;
        }
    }
    
    public final void setZonaReporte (String zonaReporte){
        if (zonaReporte != null) {
            zonaReporte = zonaReporte.trim().toUpperCase();
            if (zonaReporte.length() <= 30 && zonaReporte.matches("[\\p{L}0-9 .,;:()#/-]+")){
                this.zonaReporte = zonaReporte;
                return;
            }
            
        }
        throw new IllegalArgumentException ("Zona inválida. Debe tener más de 30 caracteres.");
    }
    
    public final void setCliente (Cliente cliente){
        if (cliente != null){
            this.cliente = cliente;
            return;
        }
        throw new IllegalArgumentException ("Cliente no puede ser vacio");
    }
    
    public final void setMascota (Mascota mascota){
        if (mascota != null){
            this.mascota = mascota;
            return;
        }
        throw new IllegalArgumentException ("Mascota no pueder ser vacio");
    }
    
    //Metodos get
    public String getIdReporte () {return idReporte;}
    public LocalDate getFechaReporte () {return fechaReporte;}
    public boolean isResuelto () {return resuelto;}
    public String getZonaReporte () {return zonaReporte;}
    public Cliente getCliente () {return cliente;}
    public Mascota getMascota () {return mascota;}
    
    
    //Constructor con parametro
    protected ReporteMascota (String idReporte, String fechaTexto, boolean resuelto, String zonaReporte, Cliente cliente, Mascota mascota){
        setIdReporte(idReporte);
        setFechaReporte(fechaTexto);
        setResuelto(resuelto);
        setZonaReporte(zonaReporte);
        setCliente(cliente);
        setMascota(mascota);
    }
    
      
    //Metodos personalizados
    
    // metodo para mostrar fechas formateadas
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaReporte.format(formatter);
    }
    
    // Devuelve un resumen textual del reporte
    public String getFichaReporte(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo de Reporte: ").append(getTipoReporte()).append("\n")
            .append("Fecha del reporte: ").append(getFechaFormateada()).append("\n")
            .append("Estado del Reporte: ").append(resuelto ? "RESUELTO" : "ACTIVO").append("\n")
            .append("Zona del Reporte: ").append(zonaReporte).append("\n")
            .append("Nombre del Cliente: ").append(cliente.getNombreCompleto()).append("\n")
            .append("Mascota: ").append(mascota.resumenMascota());
        return sb.toString();
    }
        
    @Override
    public String toString(){
        return getFichaReporte();
    }
    
}//LLAVE clase Reporte
