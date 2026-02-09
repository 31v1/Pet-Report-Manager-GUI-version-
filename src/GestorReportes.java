/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
* Clase: GestorReportes
* Responsabilidad: Cerebro del sistema. Aquí se guardan y procesan 
*           todos los reportes de mascotas.
* 
 * Lo más importante:
 * - Guarda todos los reportes en una lista
 * - Busca reportes por diferentes criterios  
 * - Encuentra coincidencias entre mascotas perdidas y encontradas
 * - Actualiza reportes existentes
* 
* Se usan array por ser dinamicos.
* 
*/


import java.util.ArrayList;


public class GestorReportes {
    //Atributos
    private ArrayList<ReporteMascota> listaReportes;
   
    
    //Metodos set
    public void setListaReportes(ArrayList<ReporteMascota> listaReportes) {
        this.listaReportes = listaReportes;
    }
    
    //Metodos get
    public ArrayList<ReporteMascota> getListaReportes (){
        return listaReportes;
    }
    
    //Constructor
    public GestorReportes(){
        this.listaReportes = new ArrayList<>();
    }
        
    //Metodos personalizados
    public boolean existeIdReporte (String idReporte){
        return buscarReportePorId(idReporte) != null;
    }
    
    public ReporteMascota buscarReportePorId(String idReporte){
        if (idReporte == null || idReporte.trim().isEmpty ()){
        return null;
    } 
        String idBuscado = idReporte.trim();
        for (ReporteMascota reporte : listaReportes) {
            if (reporte.getIdReporte().equals(idBuscado)){
                return reporte;
            }
        }
        return null;
    }
        
    public void registrarReporte(ReporteMascota nuevoReporte) {  //Crea y guarda un nuevo reporte, verifica que no sea nulo, ni duplicado.
        if (nuevoReporte == null){
            throw new IllegalArgumentException ("El reporte no puede ser nulo");
        }
        
        if (nuevoReporte.getIdReporte() == null || nuevoReporte.getIdReporte().trim().isEmpty()){
            throw new IllegalArgumentException ("\"El reporte debe tener un ID válido");
        
        }
        if (existeIdReporte (nuevoReporte.getIdReporte())){
            throw new IllegalArgumentException ("Ya existe un reporte con el ID: " + nuevoReporte.getIdReporte());
        }
        
        listaReportes.add(nuevoReporte);
               
    }
    
    
//Actualizar el estado de un reporte especifico usando el id de reporte, especie, zona.
    public ArrayList<ReporteMascota> consultarPorCriterio(String criterio, String valor) {	//Busca reportes por criterios
        if (criterio ==null || criterio.trim().isEmpty()){ //// Paso 1: Validar que criterio/valor no sea null o vacío
            throw new IllegalArgumentException("El criterio no puede estar vacío"); 
        }
        
        if (valor ==null || valor.trim().isEmpty()){ 
            throw new IllegalArgumentException("El valor de búsqueda no puede estar vacío"); 
        }
                
        criterio = criterio.trim().toUpperCase(); // Paso 2: Normalizar el criterio (trim y toUpperCase)
        
        valor = valor.trim().toUpperCase();
            
        ArrayList<ReporteMascota> resultados =new ArrayList<>(); // Paso 3: Crear lista de resultados
    
        switch (criterio) {
            case "ID":
                for (ReporteMascota reporte : listaReportes) {
                    if (reporte.getCliente().getIdReportante().equals(valor)){
                        resultados.add(reporte);
                    }
                }
                break;
        
            case "ESPECIE":
                for (ReporteMascota reporte : listaReportes){
                    if (reporte.getMascota().getEspecie().equals(valor)){
                        resultados.add(reporte);
                    }
                }
                break;
            
            case "ZONA":
                for (ReporteMascota reporte :listaReportes){
                    if (reporte.getZonaReporte().equals(valor)){
                        resultados.add(reporte);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException ("Criterio no válido. Use: ID, ESPECIE o ZONA");
        }
    
    return resultados;
       
    }

    public void actualizacionDeDatos(String idReporte, ReporteMascota nuevosDatos) {	//Edita campos de un reporte
        if (idReporte == null || idReporte.trim().isEmpty()){ //// Paso 1: Validar que criterio/valor no sea null o vacío
            throw new IllegalArgumentException("El numero de identificacion de reporte no puede estar vacío"); 
        }
        if (nuevosDatos == null){
            throw new IllegalArgumentException("Los nuevos datos del reporte no pueden estar vacíos"); 
        }
        
        //usar el metodo de buscarReportePorID
        ReporteMascota reportePorActualizar = buscarReportePorId (idReporte);
        if (reportePorActualizar == null){
            throw new IllegalArgumentException ("No existe un reporte con ID: " + idReporte);
        }
    
    //actualizar datos del cliente -excepto ID
        if  (nuevosDatos.getCliente() != null){
            if (nuevosDatos.getCliente().getNombreCompleto() != null){
                reportePorActualizar.getCliente().setNombreCompleto(
                nuevosDatos.getCliente().getNombreCompleto());
            }
            if (nuevosDatos.getCliente().getTelefonoContacto() != null){
                reportePorActualizar.getCliente().setTelefonoContacto(
                        nuevosDatos.getCliente().getTelefonoContacto());
            }
               
        }
     //Actualizar datos mascota.
     
        if (nuevosDatos.getMascota() != null) {
            if (nuevosDatos.getMascota().getEspecie() != null){
                reportePorActualizar.getMascota().setEspecie(
                   nuevosDatos.getMascota().getEspecie());
            }
            if (nuevosDatos.getMascota().getColorPrincipal() != null){
                reportePorActualizar.getMascota().setColorPrincipal(
                    nuevosDatos.getMascota().getColorPrincipal());
            }
            if (nuevosDatos.getMascota().getSeniasParticulares() != null){
                reportePorActualizar.getMascota().setSeniasParticulares(
                    nuevosDatos.getMascota().getSeniasParticulares());
            }
             
            if (nuevosDatos.getMascota().getMicrochip() != null){
                reportePorActualizar.getMascota().setMicrochip(
                    nuevosDatos.getMascota().getMicrochip());
            }
            
   /**         if (nuevosDatos.getMascota().getRaza() != null){
                reportePorActualizar.getMascota().setRaza(
                    nuevosDatos.getMascota().getRaza());
            }
    */        
            if (nuevosDatos.getMascota().getNombre() != null){
                reportePorActualizar.getMascota().setNombre(
                    nuevosDatos.getMascota().getNombre());
            }
 
        }
        
    //Actualizar datos reporte
    /* if (nuevosDatos.getTipoReporte() != null){
            reportePorActualizar.setTipoReporte(nuevosDatos.getTipoReporte());
            }
    */
        
        if (nuevosDatos.getZonaReporte() != null){
            reportePorActualizar.setZonaReporte(nuevosDatos.getZonaReporte());
        }
                      
        reportePorActualizar.setResuelto(nuevosDatos.isResuelto());
     
    }//LLAVE METODO
    
    //obtener los reportes de mascotas PERDIDAS unicamente
    
    public ArrayList<ReporteMascota> obtenerPerdidos(){
        ArrayList<ReporteMascota> perdidos = new ArrayList<>();
        for (ReporteMascota reporte : listaReportes){
            if ("PDR".equals(reporte.getTipoReporte())){
                perdidos.add(reporte);
            }
        }
        return perdidos;
    }
    
    //obtener los reportes de mascotas ENCONTRADAS unicamente
    public ArrayList<ReporteMascota> obtenerEncontrados(){
        ArrayList<ReporteMascota> encontrados = new ArrayList<>();
        for (ReporteMascota reporte : listaReportes){
            if ("ENC".equals(reporte.getTipoReporte())){
                encontrados.add(reporte);
            }
        }
        return encontrados;
    }
    

/*Busca coincidencias entre reportes de mascotas perdidas y encontrada utilizando los 
criterios: especie, color, zona, fecha (7 días) y microchip
    * Cómo funciona:
 * - Toma todas las mascotas perdidas
 * - Toma todas las mascotas encontradas  
 * - Las compara una por una
 * - Si son misma especie, mismo color, misma zona y fechas cercanas → ¡COINCIDENCIA!
 * - Si tienen mismo microchip → ¡COINCIDENCIA SEGURA!
 */

    public ArrayList<String> coincidenciaEntreReportes(){
        ArrayList<String> resultados = new ArrayList<>();
        
        ArrayList<ReporteMascota> perdidos = obtenerPerdidos();
        ArrayList<ReporteMascota> encontrados = obtenerEncontrados();
        
        if (perdidos.isEmpty() || encontrados.isEmpty()){
            resultados.add("No hay suficientes datos para buscar coincidencias.");
            return resultados;
        }
        
        int coincidenciasEncontradas = 0;
        
        ArrayList<String> prioritarias = new ArrayList<>();
        ArrayList<String> normales = new ArrayList<>();
        
        for (ReporteMascota perdido : perdidos){
            for (ReporteMascota encontrado : encontrados){
                boolean mismaEspecie = perdido.getMascota().getEspecie().equals(encontrado.getMascota().getEspecie());
                boolean mismoColor = perdido.getMascota().getColorPrincipal().equalsIgnoreCase(encontrado.getMascota().getColorPrincipal());
                boolean mismaZona = perdido.getZonaReporte().equalsIgnoreCase(encontrado.getZonaReporte());
                
                //
/** aqui se calcula la diferencia de fechas en días entre dos fechas usando Java Time API
 * 
 * Fuente: Documentación Oficial de Java - ChronoUnit.between()
 * https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoUnit.html
 * 
 * @param perdido Fecha del reporte de mascota perdida
 * @param encontrado Fecha del reporte de mascota encontrada  
 * @return Valor absoluto de la diferencia en días*/
                
                long diasDiferencia = Math.abs(java.time.temporal.ChronoUnit.DAYS.between(perdido.getFechaReporte(), encontrado.getFechaReporte())); // 
                boolean fechaEnRango = diasDiferencia <= 7;
                
                //para confirmar microchip
                boolean microchipCoincide = !perdido.getMascota().getMicrochip().isEmpty() && !encontrado.getMascota().getMicrochip().isEmpty()
                        && perdido.getMascota().getMicrochip().equals(encontrado.getMascota().getMicrochip());
               
                //coincidencia usando prioridad
                if (microchipCoincide){
                    coincidenciasEncontradas++;
                    String resultado = "🔥 Prioritaria - " + formatearCoincidencia(perdido, encontrado, microchipCoincide);
                    prioritarias.add(resultado); //<- aqui se agrega al arraylist creado al inicio
                } else if (mismaEspecie && mismoColor && mismaZona && fechaEnRango){
                    coincidenciasEncontradas++;
                    String resultado = "Coincidencia - " + formatearCoincidencia(perdido, encontrado, microchipCoincide);
                    normales.add(resultado);
                }            
            }
        }
        
        if (coincidenciasEncontradas == 0){
            resultados.add("No se encontraron coincidencias entre los reportes.");
        } else {
            resultados.add(0, "---COINCIDENCIAS ENCONTRADAS: " + coincidenciasEncontradas + " ---");
            
            resultados.addAll(prioritarias);
            resultados.addAll(normales);
        }
        
        return resultados;
    }
       
/**
 * Construye el formato de presentación para una coincidencia usando StringBuilder
 * 
 * Por qué usar StringBuilder?
 * - Más eficiente que concatenación con "+" en bucles, aparte lo estaba probando...
 * - Mejor rendimiento con múltiples operaciones de string
 * - Práctica recomendada en Java para construcción de strings complejos, segun https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
 */
    private String formatearCoincidencia(ReporteMascota perdido, ReporteMascota encontrado, boolean microchipCoincide) {
        StringBuilder sb = new StringBuilder();
    
        sb.append("\n ");
        if (microchipCoincide) {
            sb.append("COINCIDENCIA CONFIRMADA (Microchip)");
        } else {
            sb.append("COINCIDENCIA POSIBLE");
        }
        sb.append("\n");

        sb.append("PÉRDIDA: ").append(perdido.getCliente().getNombreCompleto())
          .append(" - ").append(perdido.getMascota().getEspecie())
          .append(" ").append(perdido.getMascota().getColorPrincipal())
          .append(" - ").append(perdido.getZonaReporte())
          .append(" (").append(perdido.getFechaFormateada()).append(")\n");

        sb.append("ENCONTRADO: ").append(encontrado.getCliente().getNombreCompleto())
          .append(" - ").append(encontrado.getMascota().getEspecie())
          .append(" ").append(encontrado.getMascota().getColorPrincipal())
          .append(" - ").append(encontrado.getZonaReporte())
          .append(" (").append(encontrado.getFechaFormateada()).append(")");

        // Mostrar microchip si coincide
        if (microchipCoincide) {
            sb.append("\n MICROCHIP COINCIDENTE: ").append(perdido.getMascota().getMicrochip());
        }

        sb.append("\n─────────────────────────────────────────");

        return sb.toString();
    }
    
        
        
    	
    
} //LLAVE principal
