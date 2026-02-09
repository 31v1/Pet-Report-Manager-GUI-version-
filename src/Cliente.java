/**
Elvia Sanchez Leiva
9 de noviembre 2025
* Clase: Cliente
* 
* Qué hace: Almacena y valida la información del reportante (cliente).
* 
* Validaciones implementadas:
* - Nombre: Mínimo 7 letras, solo caracteres alfabéticos y espacios
* - Cédula: Formato costarricense 1-1111-1111
* - Teléfono: Formato ####-####, evita números repetitivos (0000-0000)
* 
* Nota: Clase final para mantener integridad de los datos del reportante
 */




public final class Cliente {
    //atributos
    private String nombreCompleto;
    private String idReportante;
    private String telefonoContacto;
    
    //Metodos set y validaciones
    public void setNombreCompleto(String nombreCompleto){
        if (nombreCompleto != null) {
            nombreCompleto = nombreCompleto.trim();
            if (nombreCompleto.length() >= 7 && nombreCompleto.matches("[\\p{L} ]+")){
                this.nombreCompleto = nombreCompleto;
                return;
            }
        }
        throw new IllegalArgumentException("Nombre inválido. Debe tener al menos 7 letras, sin números ni símbolos.");
    }
    
    public void setIdReportante(String idReportante){
        if (idReportante != null){
            idReportante = idReportante.trim();
            if (idReportante.matches("^\\d-\\d{4}-\\d{4}$")){
                this.idReportante = idReportante;
                return; 
            } 
                      
        }
        throw new IllegalArgumentException("Cédula inválida. Formato: 1-1111-1111");
    }   
    
    public void setTelefonoContacto(String telefonoContacto){
        if (telefonoContacto != null){
            telefonoContacto = telefonoContacto.trim();
            if (telefonoContacto.matches("^\\d{4}-\\d{4}$")){ //expresiones regulares (regex) son estándar y se basan en la sintaxis de expresiones regulares de Java, presentes en los ejercicios del libro de texto.
                String sinGuion = telefonoContacto.replace("-", ""); //documentada en la clase String en Oracle Java Docs.
                if (!sinGuion.matches("(\\d)\\1{7}")){ // evita cosas como 0000-0000
                    this.telefonoContacto = telefonoContacto;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Teléfono inválido. Formato: ####-#### y no debe ser repetitivo: 0000-0000.");
    }
    
    //Metodos get
    public String getNombreCompleto(){return nombreCompleto; }
    public String getIdReportante(){return idReportante;}
    public String getTelefonoContacto(){return telefonoContacto;}
    
    //Constructor con parametros
    public Cliente (String nombreCompleto, String idReportante, String telefonoContacto){
        setNombreCompleto(nombreCompleto);
        setIdReportante(idReportante);
        setTelefonoContacto(telefonoContacto);
    }
    
    //Constructor por defecto
    public Cliente (){}
    
    //Metodos personalizados
    
    
    public String getInformacionCompletada(){
        return "Nombre Reportante: " + nombreCompleto + "\n" +
                "Número de Cédula: " + idReportante + "\n" +
                "Número de telefono: " + telefonoContacto;
    }
    
    @Override
    public String toString(){
        return getInformacionCompletada();
    }
    
} //LLAVE CLASE
