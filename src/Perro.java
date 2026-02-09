/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
*
* Clase: Perro
* Subclase de mascota con atributos especificos para perros

 */

public class Perro extends Mascota {
    //Atributos
    private String raza;
    private String talla; 
    private boolean tieneCollar;
    
     //Metodos set y validaciones
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
              
        throw new IllegalArgumentException("Raza inválida. Debe contener sólo letras, sin números ni símbolos.");   
    }    
    
    
    public final void setTalla(String talla){
        if (talla == null || talla.isEmpty()){
            this.talla = "";
            return;
        }
        
        talla = talla.trim().toUpperCase();
       
        if (talla.equals ("PEQ") || talla.equals("MED") || talla.equals ("GRA")){
                this.talla = talla;
                return;
            }
        throw new IllegalArgumentException("Talla inválida. Debe ser PEQ, MED o GRA exactamente.");
    }
    
    public final void setTieneCollar(boolean tieneCollar){
        this.tieneCollar = tieneCollar;
    }
    
    //metodos get
    public String getRaza (){return raza;}
    public String getTalla (){return talla;}
    public boolean isTieneCollar () {return tieneCollar;}
    
    //Constructor con parametros
    public Perro (String especie, String colorPrincipal, String seniasParticulares, String microchip, 
            String nombre, String raza, String talla, boolean tieneCollar){
        
        super(especie, colorPrincipal, seniasParticulares, microchip, nombre);
        
        setRaza(raza);
        setTalla(talla);
        this.tieneCollar = tieneCollar;
 
    }
    //constructor por defecto            
    public Perro(){
        super();
    
    }
    
    // Implementación del método abstracto - resumen específico para Perro
    @Override
    public String resumenMascota() {
        return "Perro " + raza + " - " + getColorPrincipal() + 
               " - Talla: " + talla + " - " + (tieneCollar ? "Con collar" : "Sin collar");
    }
    
    //Resumen completo del perro
    @Override
    public String getFichaResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(getNombre()).append("\n")
          .append("Especie: ").append(getEspecie()).append("\n")
          .append("Raza: ").append(raza).append("\n")
          .append("Color: ").append(getColorPrincipal()).append("\n")
          .append("Señas: ").append(getSeniasParticulares()).append("\n")
          .append("Microchip: ").append(getMicrochip()).append("\n")
          .append("Talla: ").append(talla).append("\n")
          .append("Collar: ").append(tieneCollar ? "Sí" : "No");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getFichaResumen();
    }
    
    
} //llave clase perro
