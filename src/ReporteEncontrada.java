/**
Elvia Sanchez Leiva
9 de noviembre 2025.
* Clase: ReporteEncontrada  
* Subclase de ReporteMascota para reportes de mascotas ENCONTRADAS
* Tipo fijo: ENC
*/

public class ReporteEncontrada extends ReporteMascota {

    //Constructor por defecto
    public ReporteEncontrada() {
        super();
    }
    
    //Constructor completo con todos los parametros
    public ReporteEncontrada(String idReporte, String fechaTexto, boolean resuelto,
                           String zonaReporte, Cliente cliente, Mascota mascota) {
        super(idReporte, fechaTexto, resuelto, zonaReporte, cliente, mascota);
    }
    
    /**
     * Implementación del método abstracto - siempre retorna ENC
     * @return "ENC" - tipo fijo para reportes de hallazgo
     */
    @Override
    public String getTipoReporte() {
        return "ENC";
    }
    
    
}
