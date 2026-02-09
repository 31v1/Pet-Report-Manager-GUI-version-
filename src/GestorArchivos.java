/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
* Clase: GestorArchivos

* Responsabilidad: Puente entre la memoria y el almacenamiento permanente.
*           Maneja la lectura y escritura de reportes en archivos .txt.
* 
* Lo más importante:
* - Carga reportes desde archivos al iniciar la aplicación
* - Guarda reportes en archivos cuando se registran o actualizan
* - Mantiene la separación: PDR en ReportesPerdidas.txt, ENC en ReportesEncontradas.txt
* - Convierte objetos Java ↔ formato texto para persistencia
* 
* Se usan archivos .txt para demostrar manejo de flujos de E/S
* sin usar serialización de objetos.
* 
* DIFERENCIA CLAVE con otras clases:
 * - No representa un "objeto del mundo real" como Mascota o Cliente
 * - Es una clase "servicio" o "utilidad" que ofrece funcionalidad
 * - No tiene estado interno complejo, solo configuración (rutas de archivos)
 * - Sus métodos son las operaciones, no getters/setters de atributos
 * 
 * Por qué esta estructura diferente:
 * - No necesita encapsular "datos", sino ofrecer "operaciones"
 * - Las rutas de archivos son constantes (final), no cambian
 * - El enfoque está en los métodos de E/S, no en atributos variables
 * 
 * Orden indices
0: idReporte
1: idReportante  
2: resuelto
3: nombreCompleto
4: telefonoContacto
5: zonaReporte
6: fecha   
7: especie 
8: colorPrincipal
9: seniasParticulares
10: microchip
11: raza
12: talla/tipoPelaje
13: tieneCollar/esterilizado
14: nombre
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GestorArchivos {
    //Atributos - rutas de archivos (constantes)
    private final String RUTA_PERDIDAS = "ReportesPerdidas.txt";
    private final String RUTA_ENCONTRADAS = "ReportesEncontradas.txt";
    
    //Constructor
    public GestorArchivos (){ // Usar si se desea verificar que los archivos existen o crearlos sino.
        verificarArchivosExisten();
    }
    
    //Metodos AUXILIARES (Privados)
    
    // Verifica que los archivos existan, los crea vacíos si no existen
    private void verificarArchivosExisten(){
        try {
            File archivoPerdidas = new File(RUTA_PERDIDAS);
            File archivoEncontradas = new File(RUTA_ENCONTRADAS);
            
            //Crear archivos si no existen (no lanza error si ya existen,  no hace nada si el archivo ya existe)
            archivoPerdidas.createNewFile(); //retorna false si ya existe
            archivoEncontradas.createNewFile();
            
            System.out.println("Archivos verificados: " + RUTA_PERDIDAS + ", " + RUTA_ENCONTRADAS);
            
        } catch (IOException e) {
            throw new RuntimeException("Erro critico: No se pudieron crear los archivos necesarios", e);
        }          
        
    }//llave try
    
    private ArrayList <ReporteMascota> cargarReportesDesdeArchivo(String rutaArchivo, String tipoEsperado){
    System.out.println("=== CARGANDO ARCHIVO: " + rutaArchivo + " ==="); 
    ArrayList<ReporteMascota> reportes = new ArrayList <>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;
            System.out.println("Línea leída: " + linea);
            
            ReporteMascota reporte = parsearLineaAReporte (linea, tipoEsperado);
            if (reporte != null) {
                reportes.add(reporte);
                System.out.println("✓ Reporte agregado: " + reporte.getIdReporte());
            } else {
                System.out.println("✗ Reporte NO agregado (parseo falló)"); 
            }
        }
    } catch (IOException e) {
        System.err.println("Error leyendo archivo " + rutaArchivo + ": " + e.getMessage());
    }
    
    System.out.println("Total reportes cargados: " + reportes.size()); 
    return reportes;
}

    //* Convierte 1 línea de texto del archivo .txt → 1 objeto ReporteMascota
    
    private ReporteMascota parsearLineaAReporte(String linea, String tipoEsperado){
        try {
            String[] partes = linea.split("\\|");
            
            if (partes.length < 10) {
                System.err.println("Línea inválida (campos insuficientes): " + linea);
                return null;
            }
            
            Cliente cliente = new Cliente(
                partes[3].trim(),   //nombreCompleto
                partes[1].trim(),   //idReportante
                partes[4].trim()    //telefonoContacto
            );
            
            //Reconstruir Mascota (dependiendo de la especie)
            Mascota mascota;
            String especie = partes[7].trim();
            
            if ("DOG".equals(especie)){
                mascota = new Perro(
                    especie,
                    partes[8].trim(),   //colorPrincipal
                    partes[9].trim(),   //seniasParticulares
                    partes[10].trim(),   //microchip
                    partes[14].trim(),  //nombre
                    partes.length > 11 ? partes[11].trim() : "", //raza
                    partes.length > 12 ? partes[12].trim() : "", //talla
                    partes.length > 13 ? Boolean.parseBoolean(partes[13].trim()) : false //tieneCollar

                );
                
            } else { //Gato
                mascota = new Gato(
                    especie,
                    partes[8].trim(),   //colorPrincipal
                    partes[9].trim(),   //seniasParticulares
                    partes[10].trim(),   //microchip
                    partes[14].trim(),  //nombre
                    partes.length > 11 ? partes[11].trim() : "", //raza
                    partes.length > 12 ? partes[12].trim() : "", //tipoPelaje
                    partes.length > 13 ? Boolean.parseBoolean(partes[13].trim()) : false //esterilizado
                );
            }
            
            //Crear reporte segun el tipo esperado
            ReporteMascota reporte;
            if ("PDR".equals(tipoEsperado)){
                reporte = new ReportePerdida(
                        partes[0].trim(),   //idReporte
                        partes[6].trim(),   //fechaTexto
                        Boolean.parseBoolean(partes[2].trim()), //Resuelto
                        partes[5].trim(), //zonaReporte 
                        cliente,
                        mascota                                   
                );
            } else {
                reporte = new ReporteEncontrada(
                        partes[0].trim(),
                        partes[6].trim(),   //fechaTexto
                        Boolean.parseBoolean(partes[2].trim()), //Resuelto
                        partes[5].trim(), //zonaReporte 
                        cliente,
                        mascota  
                );
            }
            
            return reporte;
                
        } catch (Exception e) {
            System.err.println("Error parseando linea: " + linea + " - " + e.getMessage());
            return null;
        }
                
    }
    
//Convierte un ReporteMascota a línea de texto para guardar en archivo
// Usa el formato definido: 15 campos separados por |
    
    private String convertirReporteATexto(ReporteMascota reporte){
        StringBuilder sb = new StringBuilder();
        
        //0-2 datos reporte
        sb.append(reporte.getIdReporte()).append("|");
        sb.append(reporte.getCliente().getIdReportante()).append("|");
        sb.append(reporte.isResuelto()).append("|");
        
        // 3-5 datos Cliente y Ubicacion
        sb.append(reporte.getCliente().getNombreCompleto()).append("|");
        sb.append(reporte.getCliente().getTelefonoContacto()).append("|");
        sb.append(reporte.getZonaReporte()).append("|");
        
        // Fecha
        sb.append(reporte.getFechaFormateada()).append("|");
        
        //Datos mascota
        Mascota mascota = reporte.getMascota();
        sb.append(mascota.getEspecie()).append("|");
        sb.append(mascota.getColorPrincipal()).append("|");
        sb.append(mascota.getSeniasParticulares()).append("|");
        
        //Microchio -puede ser vacio-
        sb.append(mascota.getMicrochip()).append("|");
        
        //Atributos especificos
        if (mascota instanceof Perro){
           Perro perro = (Perro) mascota;
           sb.append(perro.getRaza()).append("|");
           sb.append(perro.getTalla()).append("|");
           sb.append(perro.isTieneCollar()).append("|");
        } else if (mascota instanceof Gato){
            Gato gato = (Gato) mascota;
            sb.append(gato.getRaza()).append("|");
            sb.append(gato.getTipoPelaje()).append("|");
            sb.append(gato.isEsterilizado()).append("|");
        } else {
            //por si luego se expande a pericos o hamsters
            sb.append("|||");
            
        }
        //Nombre de la mascota
        sb.append(mascota.getNombre());
        
        return sb.toString();
        
    }
    
    /**
* Reescribe un archivo completo con la lista de reportes proporcionada
* @param lista Lista de reportes a guardar
* @param ruta Ruta del archivo donde guardar
* QUÉ HACE ESTO:
1. Recibe: Lista de reportes + ruta de archivo

2. Reescribe TODO el archivo desde cero

3. Usa convertirReporteATexto() y guarda cada reporte como línea en el .txt
    */
    private void guardarListaEnArchivo(ArrayList<ReporteMascota> lista, String ruta){
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
            for (ReporteMascota reporte : lista) {
                String linea = convertirReporteATexto(reporte);
                writer.println(linea);
            }
            System.out.println("Archivo guardado: " + ruta + " (" + lista.size() + " registros)");
        } catch (IOException e) {
            throw new RuntimeException ("Error guardando archivo: " + ruta, e);
        }
    }
    
 
    private ArrayList<ReporteMascota> cargarReportesPerdidas(){
        return cargarReportesDesdeArchivo(RUTA_PERDIDAS, "PDR");
        
    }   
    
    private ArrayList<ReporteMascota> cargarReportesEncontradas(){
        return cargarReportesDesdeArchivo(RUTA_ENCONTRADAS, "ENC");
    }
    
/**
* Crea un nuevo objeto ReporteMascota del tipo especificado manteniendo todos los datos originales
* Esto permite cambiar un reporte de PDR a ENC o viceversa sin perder información
* 
* @param reporteOriginal El reporte original que se va a convertir
* @param nuevoTipo El nuevo tipo del reporte ("PDR" o "ENC")
* @return Un NUEVO objeto ReporteMascota del tipo especificado
* @throws IllegalArgumentException Si el reporte es null o el tipo es inválido
*/
    
    private ReporteMascota cambiarTipoReporte (ReporteMascota reporteOriginal, String nuevoTipo){
        if (reporteOriginal == null){
            throw new IllegalArgumentException ("El reporte original no puede ser null");
        }
        if (!nuevoTipo.equals("PDR") && !nuevoTipo.equals("ENC")){
            throw new IllegalArgumentException ("El nuevo tipo debe ser PDR o ENC");
        }
        
//NUEVO objeto del tipo correcto manteniendo todos los datos
        if (nuevoTipo.equals("PDR")){
            return new ReportePerdida(
            reporteOriginal.getIdReporte(),
            reporteOriginal.getFechaFormateada(),
            reporteOriginal.isResuelto(),
            reporteOriginal.getZonaReporte(),
            reporteOriginal.getCliente(),
            reporteOriginal.getMascota()
            );
        } else {
            return new ReporteEncontrada(
            reporteOriginal.getIdReporte(),
            reporteOriginal.getFechaFormateada(),
            reporteOriginal.isResuelto(),
            reporteOriginal.getZonaReporte(),
            reporteOriginal.getCliente(),
            reporteOriginal.getMascota()
            );
        }
    }
    
    
// Metodos PRINCIPALES (Públicos)
    
//Carga TODOS los reportes de ambos archivos .txt
    public ArrayList<ReporteMascota> cargarTodosLosReportes(){
        ArrayList<ReporteMascota> todos = new ArrayList<>();
        todos.addAll(cargarReportesPerdidas());
        todos.addAll(cargarReportesEncontradas());
        return todos;
    }
    
/**
* Verifica si existe un reporte con el ID especificado en cualquiera de los archivos
* Busca en ambos archivos (ReportesPerdidas.txt y ReportesEncontradas.txt)
* 
* @param idReporte El ID del reporte a verificar (formato REP-0001)
* @return true si el ID existe en alguno de los archivos, false si no existe
* @throws IllegalArgumentException Si el idReporte es null o vacío
*/
    public boolean existeIdReporte(String idReporte) {
        if (idReporte == null || idReporte.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del reporte no puede ser nulo o vacío");
        }

        String idBuscado = idReporte.trim();

        // Buscar en reportes perdidos
        ArrayList<ReporteMascota> perdidas = cargarReportesPerdidas();
        for (ReporteMascota reporte : perdidas) {
            if (reporte.getIdReporte().equals(idBuscado)) {
                return true;
            }
        }

        // Buscar en reportes encontrados
        ArrayList<ReporteMascota> encontradas = cargarReportesEncontradas();
        for (ReporteMascota reporte : encontradas) {
            if (reporte.getIdReporte().equals(idBuscado)) {
                return true;
            }
        }

        return false;
    }
    
/**
* Guarda un reporte en el archivo correspondiente según su tipo (PDR/ENC)
* Si el reporte ya existe (mismo idReporte), lanza una excepción
* 
* @param reporte El reporte a guardar en el archivo
* @throws IllegalArgumentException Si el reporte es null o el ID ya existe
* @throws RuntimeException Si ocurre un error de escritura en el archivo
*/
    public void guardarReporte(ReporteMascota reporte) {
        if (reporte == null) {
            throw new IllegalArgumentException("El reporte no puede ser null");
        }

        // Verificar que el ID no exista ya en los archivos
        if (existeIdReporte(reporte.getIdReporte())) {
            throw new IllegalArgumentException("Ya existe un reporte con ID: " + reporte.getIdReporte());
        }

        // Determinar en qué archivo guardar según el tipo
        String rutaArchivo = reporte.getTipoReporte().equals("PDR") ? RUTA_PERDIDAS : RUTA_ENCONTRADAS;

        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter writer = new PrintWriter(fw)) {

            String linea = convertirReporteATexto(reporte);
            writer.println(linea);
            System.out.println("Reporte guardado: " + reporte.getIdReporte() + " en " + rutaArchivo);

        } catch (IOException e) {
            throw new RuntimeException("Error guardando reporte en archivo: " + rutaArchivo, e);
        }
    }
    
/**
* Elimina un reporte del sistema buscándolo por ID en ambos archivos
* Si encuentra el reporte, lo elimina del archivo correspondiente y reescribe el archivo sin ese reporte
* 
* @param idReporte El ID del reporte a eliminar (formato REP-0001)
* @return true si se eliminó correctamente, false si no se encontró el reporte
* @throws IllegalArgumentException Si el idReporte es null o vacío
* @throws RuntimeException Si ocurre un error al reescribir los archivos
*/
    public boolean eliminarReporte(String idReporte) {
        if (idReporte == null || idReporte.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del reporte no puede ser nulo o vacío");
        }

        String idBuscado = idReporte.trim();
        boolean eliminado = false;

        // Intentar eliminar de reportes perdidos
        ArrayList<ReporteMascota> perdidas = cargarReportesPerdidas();
        ArrayList<ReporteMascota> nuevasPerdidas = new ArrayList<>();

        for (ReporteMascota reporte : perdidas) {
            if (!reporte.getIdReporte().equals(idBuscado)) {
                nuevasPerdidas.add(reporte);
            } else {
                eliminado = true;
            }
        }

        // Si se eliminó de perdidas, reescribir el archivo
        if (eliminado) {
            guardarListaEnArchivo(nuevasPerdidas, RUTA_PERDIDAS);
            System.out.println("Reporte eliminado de perdidas: " + idBuscado);
            return true;
        }

        // Si no estaba en perdidas, buscar en encontradas
        ArrayList<ReporteMascota> encontradas = cargarReportesEncontradas();
        ArrayList<ReporteMascota> nuevasEncontradas = new ArrayList<>();

        for (ReporteMascota reporte : encontradas) {
            if (!reporte.getIdReporte().equals(idBuscado)) {
                nuevasEncontradas.add(reporte);
            } else {
                eliminado = true;
            }
        }

        // Si se eliminó de encontradas, reescribir el archivo
        if (eliminado) {
            guardarListaEnArchivo(nuevasEncontradas, RUTA_ENCONTRADAS);
            System.out.println("Reporte eliminado de encontradas: " + idBuscado);
        } else {
            System.out.println("Reporte no encontrado: " + idBuscado);
        }

        return eliminado;
    }
    

    
/**
* Mueve un reporte entre archivos cambiando su tipo (PDR ↔ ENC)
* Esto permite que un reporte de pérdida pase a encontrado o viceversa
* 
* @param idReporte El ID del reporte a mover (formato REP-0001)
* @param nuevoTipo El nuevo tipo del reporte ("PDR" o "ENC")
* @return true si se movió correctamente, false si no se encontró el reporte
* @throws IllegalArgumentException Si los parámetros son inválidos
*/
    public boolean moverReporteEntreArchivos (String idReporte, String nuevoTipo){
        if (idReporte == null || idReporte.trim().isEmpty()){
            throw new IllegalArgumentException("El ID del reporte no puede ser nulo o vacío.");
        }
        
        if (!nuevoTipo.equals("PDR") && !nuevoTipo.equals("ENC")){
            throw new IllegalArgumentException("El nuevo tipo debe ser PDR o ENC");
        }
        
        String idBuscado = idReporte.trim();
        
        //Buscar el reporte en ambos archivos
        ReporteMascota reporteOriginal = null;
        String tipoOriginal = null;
        
        //Buscar en reportes perdidos
        ArrayList<ReporteMascota> perdidas = cargarReportesPerdidas();
        for (ReporteMascota reporte : perdidas) {
            if (reporte.getIdReporte().equals(idBuscado)){
              reporteOriginal = reporte;
              tipoOriginal = "PDR";
              break;
            }
        }
        
        //Si no esta en perdidas, buscar en encontradas
        if (reporteOriginal == null) {
            ArrayList<ReporteMascota> encontradas = cargarReportesEncontradas();
            for (ReporteMascota reporte : encontradas){
                if (reporte.getIdReporte().equals(idBuscado)){
                    reporteOriginal = reporte;
                    tipoOriginal = "ENC";
                    break;
                }
            }        
        }
        
        //Si no se encuentra el reporte, devuelve false
        if (reporteOriginal == null){
            System.out.println("Reporte no encontrado: " + idBuscado);
            return false;
        }
        
        //Revisar que el nuevo tipo sea diferente al actual
        if (tipoOriginal.equals(nuevoTipo)){
            throw new IllegalArgumentException("El reporte ya es de tipo: " +nuevoTipo);
        }
        
        //Crear nuevo reporte del tipo correcto
        ReporteMascota nuevoReporte = cambiarTipoReporte(reporteOriginal, nuevoTipo);
        
        //Eliminar el reporte original y guardar el nuevo
        eliminarReporte(idBuscado);
        guardarReporte(nuevoReporte);
        
        System.out.println("Reporte movido: " +idBuscado + " de " + tipoOriginal +  " a " + nuevoTipo);
        return true;
        
    }//llave metodo
    
    
    
}//llave clase GestorArchivos
