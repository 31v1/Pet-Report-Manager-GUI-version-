/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
*
* Clase: Mascota (abstracta)
* 
* Qué hace: Clase base abstracta que almacena información común de todas las mascotas
*           con validaciones para garantizar que los datos sean correctos.
* 
* Características:
 * - Campos obligatorios: especie, color, señas particulares
 * - Campos opcionales: microchip, nombre  
 * - Validaciones estrictas en cada campo
 * - Método abstracto para resumen polimórfico
 */




public abstract class Mascota {
    //Atributos
    protected String especie;
    protected String colorPrincipal;
    protected String seniasParticulares;
    protected String microchip;
    protected String nombre;
    
    //metodo abstracto para poliformismo - las subclases lo usan, devuelve un resumen polimorfico de la mascota
    public abstract String resumenMascota();
    
    
    //Metodos set y validaciones
    public final void setEspecie(String especie){
        if (especie != null){ //se revisa que el valor NO este vacio, si esta vacio directo a throw.
            especie = especie.trim();
            if((especie.length() == 3) && (especie.equals("DOG")||especie.equals("CAT"))){ // revisa que sean 3 letras y que sean las correctas.
               this.especie = especie;
               return;
            } 
        } //llave IF 1
        throw new IllegalArgumentException("Especie inválida.Debe ser DOG O CAT exactamente en mayúsculas.");
    } 
        
    
    public final void setColorPrincipal(String colorPrincipal){
        if (colorPrincipal != null){
            colorPrincipal = colorPrincipal.trim();
            if (colorPrincipal.matches("[\\p{L} '\\-.,]+")){ //Consultado de Regular-Expressions.info, Regex Java Pattern class (Oracle) pagina oficial y Regex101
                this.colorPrincipal = colorPrincipal;
                return;
            }
        }
        throw new IllegalArgumentException ("Color inválido. Debe contener sólo letras, sin números ni símbolos.");
    }    
    
    public final void setSeniasParticulares (String seniasParticulares){
        if (seniasParticulares != null){
            seniasParticulares = seniasParticulares.trim();
            if (seniasParticulares.length() >=10 && seniasParticulares.matches ("[\\p{L}0-9 .,;:()\\-'!?]+")){
                this.seniasParticulares = seniasParticulares;
                return;
            }
        }
        throw new IllegalArgumentException ("Texto inválido. Debe contener sólo letras, sin números ni símbolos.");
    }    
    
    /**
 * @param microchip El código del microchip (alfanumérico, sin formato específico)
 */
    public final void setMicrochip (String microchip){
       if (microchip == null ||microchip.trim ().isEmpty()) {
           this.microchip = "";
           return;
       }
       microchip = microchip.trim();
       if (microchip.matches("[a-zA-Z0-9]+")) {
                this.microchip = microchip;
                return;
        }
        throw new IllegalArgumentException("Microchip inválido. Debe contener caracteres alfanuméricos, sin espacios ni símbolos.");
    }
       
    public final void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {//garantiza que no haya espacios adelante y atras
            this.nombre = "";
            return;
        }
        
        nombre = nombre.trim();    
        if (nombre.length() >= 2 && nombre.matches("[\\p{L} ]+")) {
            this.nombre = nombre;
            return;
        }
    
    throw new IllegalArgumentException("Nombre inválido. Debe tener al menos 2 letras, sin números ni símbolos.");
}
    
    
    //Metodos get
    public String getEspecie(){return especie; }
    public String getColorPrincipal (){return colorPrincipal; }
    public String getSeniasParticulares (){return seniasParticulares; }
    public String getMicrochip (){return microchip; }
    public String getNombre (){return nombre; }
    
    //Constructor con parametros
    public Mascota (String especie, String colorPrincipal, String seniasParticulares, String microchip, String nombre){
        setEspecie(especie);
        setColorPrincipal(colorPrincipal);
        setSeniasParticulares (seniasParticulares);
        setMicrochip(microchip);
        setNombre(nombre);
        
    } //llave constructor...
    
    //Constructor por defecto
    public Mascota (){
        //vacio luego para usarlo mas adelante se llamaria Mascota m = new Mascota();
    }
    
    //Metodos personalizados
       
    public boolean tieneChip(){
        return microchip != null && !microchip.isEmpty();
    }
    
    public String getFichaResumen(){
    StringBuilder sb = new StringBuilder();
    sb.append("Nombre: ").append(nombre).append("\n")
      .append("Especie: ").append(especie).append("\n")
      .append("Color: ").append(colorPrincipal).append("\n")
      .append("Señas: ").append(seniasParticulares).append("\n")
      .append("Microchip: ").append(microchip);
    return sb.toString();
}
        
    @Override // esto es para que cuando se imprima una mascota (System.out.println(mascota)), se muestre su ficha resumen. se usa automáticamente en lugares donde getFichaResumen() no se llama -tal vez se ocupe..-.”
    public String toString(){
        return getFichaResumen();
    }
    
}