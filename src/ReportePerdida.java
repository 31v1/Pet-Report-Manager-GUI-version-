/**
Elvia Sanchez Leiva
9 de noviembre 2025.

* Clase: ReportePerdida
* Subclase de ReporteMascota para reportes de mascotas PERDIDAS
* Tipo fijo: PDR
*/

public class ReportePerdida extends ReporteMascota {
    
    //Constructor por defecto
    public ReportePerdida() {
        super();
    }
    
   
     // Constructor con parametros
    public ReportePerdida(String idReporte, String fechaTexto, boolean resuelto,
                         String zonaReporte, Cliente cliente, Mascota mascota) {
        super(idReporte, fechaTexto, resuelto, zonaReporte, cliente, mascota);
    }
    
    /**
     * Implementación del método abstracto - siempre retorna PDR
     * @return "PDR" - tipo fijo para reportes de pérdida
     */
    @Override
    public String getTipoReporte() {
        return "PDR";
    }
    
}