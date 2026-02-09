/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
*
* Clase: Gato
* Subclase de mascota con atributos especificos para gatos

 */

public class Gato extends Mascota {
    //Atributos
    private String raza;
    private String tipoPelaje;
    private boolean esterilizado;
    
    // Metodos set y validaciones
    public final void setRaza(String raza){
        if (raza == null || raza.isEmpty()){
           this.raza = "";
           return;
        }
        
        raza = raza.trim();
            if (raza.matches("[\\p{L} '\\-.,]+")){ //clase de caracteres que representa cualquier letra de cualquier idioma
                this.raza = raza;
                return;        
            } 
              
        throw new IllegalArgumentException("Raza inválida. Use sólo letras, espacios, apóstrofes (') o guiones.");   
    }
    
    public final void setTipoPelaje(String tipoPelaje){
        if (tipoPelaje == null || tipoPelaje.isEmpty()){
            this.tipoPelaje="";
            return;
        }
        
        tipoPelaje = tipoPelaje.trim().toUpperCase();
        
        if(tipoPelaje.equals("CORTO") || tipoPelaje.equals("LARGO")){
                this.tipoPelaje = tipoPelaje;
                return;                
            }
        throw new IllegalArgumentException("Tipo de pelaje inválido. Debe ser CORTO o LARGO exactamente.");
    }
    
    public final void setEsterilizado(boolean esterilizado){
        this.esterilizado = esterilizado;
    }
    
    // Métodos get
    public String getRaza() { return raza; }
    public String getTipoPelaje() { return tipoPelaje; }
    public boolean isEsterilizado() { return esterilizado; }
    
    // Constructor con parametros
    public Gato (String especie, String colorPrincipal, String seniasParticulares, String microchip,
            String nombre, String raza, String tipoPelaje, boolean esterilizado){
        
        super(especie, colorPrincipal, seniasParticulares, microchip, nombre);
        
        setRaza(raza);
        setTipoPelaje(tipoPelaje);
        this.esterilizado = esterilizado;
        
    }
    
    // Constructor por defecto
    public Gato() {
        super();
    }
    
    // Implementación del método abstracto - resumen específico para Gato
    @Override
    public String resumenMascota() {
        return "Gato " + raza + " - " + getColorPrincipal() + 
                " - Pelaje: " + tipoPelaje + " - " + (esterilizado ? "Esterilizado" : "No esterilizado");
    }

    //Resumen completo del gato
    @Override
    public String getFichaResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(getNombre()).append("\n")
          .append("Especie: ").append(getEspecie()).append("\n")
          .append("Raza: ").append(raza).append("\n")
          .append("Color: ").append(getColorPrincipal()).append("\n")
          .append("Señas: ").append(getSeniasParticulares()).append("\n")
          .append("Microchip: ").append(getMicrochip()).append("\n")
          .append("Tipo de pelaje: ").append(tipoPelaje).append("\n")
          .append("Esterilizado: ").append(esterilizado ? "Sí" : "No");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getFichaResumen();
    }

    
    
}//llave clase gato