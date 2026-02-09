/**
Elvia Sanchez Leiva
9 de noviembre 2025
* 
* Clase: Main
 * Responsabilidad: Controlador principal que maneja la interfaz de usuario 
 *                  y orquesta las operaciones del sistema
 *
 

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    //Atributos
    private GestorReportes gestor;
    private Scanner scanner;
    
    //Constructor
    public Main() {
        this.gestor = new GestorReportes();
        this.scanner = new Scanner(System.in);
    }
    
    //Metodos
    private void mostrarMenu(){
        System.out.println("\n === Gestor de Reporte de mascotas ===");
        System.out.println("1. Registrar mascota desaparecida.");
        System.out.println("2. Consultar por ID / Especie / Zona");
        System.out.println("3. Reporte general.");
        System.out.println("4. Reporte agrupado.");
        System.out.println("5. Ver coincidencias.");
        System.out.println("6. Actualizar reporte.");
        System.out.println("7. Salir.");
        System.out.println("Ingrese una opción: ");
    }
    
    private int leerOpcion(){
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); //aqui se limpia el buffer
            if (opcion < 1 || opcion > 7){
                System.out.println("Opción inválida. Intente nuevamente.");
                return -1;
            }
            return opcion;
    
        } catch (Exception e){
            scanner.nextLine();
            System.out.println("Error: debe ingresar un número.");
            return -1;
        }
    }
    
    private void registrarReporte() {
    System.out.println("\n--- REGISTRAR NUEVO REPORTE ---");
    
    try {
        // Pide ID del reporte CON VALIDACIÓN
        String idReporte;
        while (true) {
            System.out.print("Ingrese ID del reporte (ejemplo: REP-0001): ");
            idReporte = scanner.nextLine().trim();
            
            if (idReporte.matches("^REP-\\d{4}$")) {
                break;
            } else {
                System.out.println("Error: Formato debe ser REP-0001 (ej: REP-0001)");
            }
        }
        
        // Revisar que no exista
        if (gestor.buscarReportePorId(idReporte) != null) {
            System.out.println("Error: Ya existe un reporte con ese ID.");
            return;
        }
        
        // datos del reportante (cliente) CON REINTENTOS
        String idReportante = null;
        String nombreCompleto = null;
        String telefonoContacto = null;
        
        // Solicitar ID reportante con reintentos
        while (idReportante == null) {
            try {
                System.out.print("Ingrese ID del reportante (1-1111-1111): ");
                String idInput = scanner.nextLine();
                Cliente temp = new Cliente();
                temp.setIdReportante(idInput);
                idReportante = idInput; // Si no lanza excepción, es válido
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // Solicitar nombre con reintentos
        while (nombreCompleto == null) {
            try {
                System.out.print("Ingrese nombre completo: ");
                String nombreInput = scanner.nextLine();
                Cliente temp = new Cliente();
                temp.setNombreCompleto(nombreInput);
                nombreCompleto = nombreInput;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        // Solicitar teléfono con reintentos
        while (telefonoContacto == null) {
            try {
                System.out.print("Ingrese teléfono de contacto (####-####): ");
                String telefonoInput = scanner.nextLine();
                Cliente temp = new Cliente();
                temp.setTelefonoContacto(telefonoInput);
                telefonoContacto = telefonoInput;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
       
        Cliente cliente = new Cliente(nombreCompleto, idReportante, telefonoContacto);
        
        //Datos de la mascota
        
         String especie = null;
         while (especie == null) {
             try {
                 System.out.print("Ingrese especie (DOG/CAT): ");
                 String especieInput = scanner.nextLine();
                 Mascota temp = new Mascota();
                 temp.setEspecie(especieInput);
                 especie = especieInput;
             } catch (IllegalArgumentException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }

         // 2. COLOR PRINCIPAL
         String colorPrincipal = null;
         while (colorPrincipal == null) {
             try {
                 System.out.print("Ingrese color principal: ");
                 String colorInput = scanner.nextLine();
                 Mascota temp = new Mascota();
                 temp.setColorPrincipal(colorInput);
                 colorPrincipal = colorInput;
             } catch (IllegalArgumentException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }

         // 3. SEÑAS PARTICULARES
         String seniasParticulares = null;
         while (seniasParticulares == null) {
             try {
                 System.out.print("Ingrese señas particulares (mínimo 10 caracteres): ");
                 String seniasInput = scanner.nextLine();
                 Mascota temp = new Mascota();
                 temp.setSeniasParticulares(seniasInput);
                 seniasParticulares = seniasInput;
             } catch (IllegalArgumentException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }
         


         // Microchip con reintentos
        String microchip = "";
        while (true) {
            try {
                System.out.print("Ingrese microchip (opcional, 6 caracteres alfanuméricos): ");
                String microchipInput = scanner.nextLine();

                if (microchipInput.trim().isEmpty()) {
                    microchip = "";
                    break;
                } else {
                    Mascota temp = new Mascota();
                    temp.setMicrochip(microchipInput);
                    microchip = microchipInput;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error en microchip: " + e.getMessage());
            }
        }

        // Raza con reintentos
        String raza = "";
        while (true) {
            try {
                System.out.print("Ingrese raza (opcional): ");
                String razaInput = scanner.nextLine();

                if (razaInput.trim().isEmpty()) {
                    raza = "";
                    break;
                } else {
                    Mascota temp = new Mascota();
                    temp.setRaza(razaInput);
                    raza = razaInput;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error en raza: " + e.getMessage());
            }
        }

        

        // Nombre mascota con reintentos
        String nombre = "";
        while (true) {
            try {
                System.out.print("Ingrese nombre de la mascota (opcional): ");
                String nombreInput = scanner.nextLine();

                if (nombreInput.trim().isEmpty()) {
                    nombre = "";
                    break;
                } else {
                    Mascota temp = new Mascota();
                    temp.setNombre(nombreInput);
                    nombre = nombreInput;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error en nombre: " + e.getMessage());
            }
        }

               
        // --Creacion de la mascota
        Mascota mascota = new Mascota();

     
        mascota.setEspecie(especie);
        mascota.setRaza(raza);
        mascota.setColorPrincipal(colorPrincipal);
        mascota.setSeniasParticulares(seniasParticulares);
        mascota.setMicrochip(microchip);
        mascota.setNombre(nombre);
        


//Datos del reporte                              
        String tipoReporte = "PDR"; // Valor por defecto
        while (true) {
            try {
                System.out.print("Tipo de reporte (PDR/ENC) [PDR por defecto]: ");
                String tipoInput = scanner.nextLine();

                if (tipoInput.trim().isEmpty()) {
                    tipoReporte = "PDR";
                    break;
                } else {
                    Reporte temp = new Reporte();
                    temp.setTipoReporte(tipoInput);
                    tipoReporte = tipoInput;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error en tipo de reporte: " + e.getMessage());
            }
        }

        // Zona con reintentos
        String zonaReporte = null;
        while (zonaReporte == null) {
            try {
                System.out.print("Ingrese zona, país, ciudad o distrito donde ocurrió el evento: ");
                String zonaInput = scanner.nextLine();
                Reporte temp = new Reporte();
                temp.setZonaReporte(zonaInput);
                zonaReporte = zonaInput;
            } catch (IllegalArgumentException e) {
                System.out.println("Error en zona: " + e.getMessage());
            }
        }

        // Fecha con reintentos
        String fechaTexto = null;
        while (true) {
            try {
                System.out.print("Ingrese la fecha del evento. Formato: dd/mm/aaaa (opcional, dejar vacío para fecha actual): ");
                String fechaInput = scanner.nextLine();

                if (fechaInput.trim().isEmpty()) {
                    fechaTexto = null; // Usará fecha actual
                    break;
                } else {
                    Reporte temp = new Reporte();
                    temp.setFechaReporte(fechaInput); // Esto valida el formato
                    fechaTexto = fechaInput;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error en fecha: " + e.getMessage());
            }
        }
        
        // --Creacion del reporte
        Reporte nuevoReporte = new Reporte(); // Constructor vacío
        nuevoReporte.setIdReporte(idReporte);
        nuevoReporte.setTipoReporte(tipoReporte);
        nuevoReporte.setFechaReporte(fechaTexto); // Esto maneja null para fecha actual
        nuevoReporte.setResuelto(false);
        nuevoReporte.setZonaReporte(zonaReporte);
        nuevoReporte.setCliente(cliente);
        nuevoReporte.setMascota(mascota);

        //Guardar en el gestor de reportes
        gestor.registrarReporte(nuevoReporte);
        
        System.out.println("¡Reporte registrado con éxito!");
        
    } catch (IllegalArgumentException e) {
        System.out.println("Error de validación: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
    }    
}
    
    
    
    private void consultarPorCriterio (){
    System.out.println("\n--- CONSULTA DE REPORTES POR CRITERIO ---");
        
        try {
            //submenu de criterios
            System.out.println("Seleccione el criterio de búsqueda:");
            System.out.println("1. ID del reportante");
            System.out.println("2. Especie");
            System.out.println("3. Zona");
            System.out.print("Ingrese opción: ");
            
            int opcionCriterio = Integer.parseInt(scanner.nextLine());
            
            String criterio = "";
            String valor = "";
            
            switch (opcionCriterio){
                case 1:
                    criterio ="ID";
                    System.out.print("Ingrese ID del reportante (1-1111-1111): ");
                    valor = scanner.nextLine();
                    break;
                    
                case 2:
                    criterio ="ESPECIE";
                    System.out.print("Ingrese especie (DOG/CAT): ");
                    valor = scanner.nextLine().toUpperCase();
                    break;
                    
                case 3:
                    criterio ="ZONA";
                    System.out.print("Ingrese zona: ");
                    valor = scanner.nextLine();
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
                    return;                            
            }
            
            //Realizando la consulta
            ArrayList<Reporte> resultados = gestor.consultarPorCriterio(criterio, valor);
            
            //Mostrando resultados
            if (resultados.isEmpty()){
                System.out.println("No se encontraron reportes con ese criterio.");
            } else {
                System.out.println("\nResultados encontrados (" + resultados.size() + "):");
                System.out.println("======================================================");
                System.out.printf("%-15s | %-20s | %-10s | %-15s | %-3s\n", 
                    "ID Reportante", "Nombre Completo", "Fecha", "Zona", "Tipo");
                System.out.println("======================================================");
                
            for (Reporte reporte : resultados){
                System.out.printf("%-15s | %-20s | %-10s | %-15s | %-3s\n",
                    reporte.getCliente().getIdReportante(),
                    reporte.getCliente().getNombreCompleto(), 
                    reporte.getFechaFormateada(),
                    reporte.getZonaReporte(),
                    reporte.getTipoReporte());
            }
        }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado: " + e.getMessage());
                 
        }
                      
    }//llave del metodo
    
    private void mostrarReporteGeneral(){            
        System.out.println("\n--- REPORTE GENERAL DE MASCOTAS ---");
        ArrayList<Reporte> reportes = gestor.getListaReportes();

            if (reportes.isEmpty()){
                System.out.println("No hay reportes registrados.");
                return;
            }

            System.out.println("======================================================");
            System.out.printf("%-15s | %-20s | %-10s | %-15s | %-3s\n", 
                "ID Reportante", "Nombre Completo", "Fecha", "Zona", "Tipo");
            System.out.println("======================================================");

            for (Reporte reporte : reportes){
                System.out.printf("%-15s | %-20s | %-10s | %-15s | %-3s\n",
                reporte.getCliente().getIdReportante(),
                reporte.getCliente().getNombreCompleto(),
                reporte.getFechaFormateada(),
                reporte.getZonaReporte(),
                reporte.getTipoReporte());
            }    

            System.out.println("Total de reportes: " + reportes.size());
        }

    private void mostrarReporteAgrupado(){
        System.out.println("\n--- REPORTE AGRUPADO DE MASCOTAS ---");
        ArrayList<Reporte> reportes = gestor.getListaReportes();

            if (reportes.isEmpty()) {
                System.out.println("No hay reportes registrados.");
                return;
            }

            // Contadores
            int contadorPDR = 0;
            int contadorENC = 0;
            int contadorDOG = 0;
            int contadorCAT = 0;
            int otrosTipos = 0;
            int otrasEspecies = 0;

            // Contar por tipo y especie
            for (Reporte reporte : reportes) {
                String tipo = reporte.getTipoReporte();// Contar por tipo de reporte
                if ("PDR".equals(tipo)) {
                    contadorPDR++;
                } else if ("ENC".equals(tipo)) {
                    contadorENC++;
                } else {
                    otrosTipos++;
                }

                // Contar por especie
                String especie = reporte.getMascota().getEspecie();
                if ("DOG".equals(especie)) {
                    contadorDOG++;
                } else if ("CAT".equals(especie)) {
                    contadorCAT++;
                } else {
                    otrasEspecies++;
                }
            }

            // ver resultados
            System.out.println("\nConteo por tipo:");
            System.out.println("PDR: " + contadorPDR);
            System.out.println("ENC: " + contadorENC);
            if (otrosTipos > 0) {
                System.out.println("Otros: " + otrosTipos);
            }

            System.out.println("\nConteo por especie:");
            System.out.println("DOG: " + contadorDOG);
            System.out.println("CAT: " + contadorCAT);
            if (otrasEspecies > 0) {
                System.out.println("Otras: " + otrasEspecies);
            }

            //  consistencia
            int totalPorTipo = contadorPDR + contadorENC + otrosTipos;
            int totalPorEspecie = contadorDOG + contadorCAT + otrasEspecies;

            System.out.println("\nTotal de reportes: " + reportes.size());
            System.out.println("Verificación: " + totalPorTipo + " (por tipo) = " + totalPorEspecie + " (por especie)");
    }
    
    private void actualizarReporte(){
        System.out.println("\n--- ACTUALIZAR REPORTE ---");
    
        try {
            // 1. Pedir ID del reporte a actualizar
            System.out.print("Ingrese ID del reporte a actualizar: ");
            String idReporte = scanner.nextLine();

            // 2. Buscar el reporte existente
            Reporte reporteActual = gestor.buscarReportePorId(idReporte);
            if (reporteActual == null) {
                System.out.println("No se encontró un reporte con ID: " + idReporte);
                return;
            }

            // 3. Mostrar reporte actual
            System.out.println("\nReporte encontrado:");
            System.out.println("==========================================");
            System.out.println(reporteActual.getFichaReporte());
            System.out.println("==========================================");

            // 4. Menú de opciones de actualización
            //Save acción:");
            System.out.println("1. Editar uystem.out.println(\"\\nSeleccione nun solo dato");
            System.out.println("2. Reingresar todos los datos");
            System.out.print("Ingrese opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            Reporte nuevosDatos = new Reporte();

            if (opcion == 1) {
                nuevosDatos = editarUnDato(reporteActual);
            } else if (opcion == 2) {
                nuevosDatos = reingresarTodosDatos(reporteActual);
            } else {
                System.out.println("Opción inválida.");
                return;
            }

            // 5. Ejecutar la actualización
            gestor.actualizacionDeDatos(idReporte, nuevosDatos);
            System.out.println("\n ¡Reporte actualizado exitosamente!");

            // 6. Mostrar el reporte actualizado
            Reporte actualizado = gestor.buscarReportePorId(idReporte);
            System.out.println("\nNuevo estado del reporte:");
            System.out.println("==========================================");
            System.out.println(actualizado.getFichaReporte());

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
           
    }
    
    private Reporte editarUnDato (Reporte reporteActual){
        Reporte nuevosDatos = new Reporte();
        
        System.out.println("\n¿Qué dato desea editar?");
        System.out.println("1. Nombre completo");
        System.out.println("2. Teléfono de contacto");
        System.out.println("3. Tipo de Reporte (PDR/ENC)");
        System.out.println("4. Zona");
        System.out.println("5. Especie (DOG/CAT)");
        System.out.println("6. Color principal");
        System.out.println("7. Señas particulares");
        System.out.println("8. Microchip");
        System.out.println("9. Raza");   
        System.out.println("10. Nombre de la mascota");
        System.out.println("11. Estado del reporte (Resuelto/Activo)");
        System.out.print("Ingrese opción: ");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion){
            case 1:
                System.out.print("Nuevo nombre completo: ");
                String nombre = scanner.nextLine();
                Cliente cli = new Cliente();
                cli.setNombreCompleto(nombre);
                nuevosDatos.setCliente(cli);
                break;
                
            case 2:
                System.out.print("Nuevo teléfono de contacto: ");
                String telefono = scanner.nextLine();
                Cliente cliTel = new Cliente();
                cliTel.setTelefonoContacto(telefono);
                nuevosDatos.setCliente(cliTel);
                break;
                
            case 3:
                System.out.print("Nuevo tipo de reporte (PDR/ENC): ");
                String tipo = scanner.nextLine();
                nuevosDatos.setTipoReporte(tipo);
                break;
                
            case 4:
                System.out.print("Nueva zona: ");
                String zona = scanner.nextLine();
                nuevosDatos.setZonaReporte(zona);
                break;
                
            case 5:
                System.out.print("Nueva especie (DOG/CAT): ");
                String especie = scanner.nextLine();
                Mascota mascotaEsp = new Mascota();
                mascotaEsp.setEspecie(especie);
                nuevosDatos.setMascota(mascotaEsp);
                break;
                
            case 6:
                System.out.print("Nuevo color principal: ");
                String color = scanner.nextLine();
                Mascota mascotaColor = new Mascota();
                mascotaColor.setColorPrincipal(color);
                nuevosDatos.setMascota(mascotaColor);
                break;
                
            case 7:
                System.out.print("Nuevas señas particulares: ");
                String senias = scanner.nextLine();
                Mascota mascotaSenias = new Mascota();
                mascotaSenias.setSeniasParticulares(senias);
                nuevosDatos.setMascota(mascotaSenias);
                break;
                
            case 8:
                System.out.print("Nuevo microchip: ");
                String microchip = scanner.nextLine();
                Mascota mascotaChip = new Mascota();
                mascotaChip.setMicrochip(microchip);
                nuevosDatos.setMascota(mascotaChip);
                break;
                
            case 9:
                System.out.print("Nueva raza: ");
                String raza = scanner.nextLine();
                Mascota mascotaRaza = new Mascota();
                mascotaRaza.setRaza(raza);
                nuevosDatos.setMascota(mascotaRaza);
                break;
                
                           
            case 10:
                System.out.print("Nuevo nombre de la mascota: ");
                String nombreMascota = scanner.nextLine();
                Mascota mascotaNombre = new Mascota();
                mascotaNombre.setNombre(nombreMascota);
                nuevosDatos.setMascota(mascotaNombre);
                break;
                
                            
            case 11:
                System.out.print("Nuevo estado (ACTIVO/RESUELTO): ");
                String estado = scanner.nextLine();
                nuevosDatos.setEstadoDesdeString(estado);
                break;
                
            default:
                System.out.println("Opción inválida.");
                
        }
        
        return  nuevosDatos;
    
    }
    
   private Reporte reingresarTodosDatos(Reporte reporteActual){
       Reporte nuevosDatos = new Reporte();
       Cliente cliente = new Cliente();
       Mascota mascota = new Mascota();
       
       System.out.println("\n--- REINGRESAR TODOS LOS DATOS ---");
       System.out.println("(Deje vacío para mantener el valor actual)");
       
       try {
           System.out.print("Nombre completo [" + reporteActual.getCliente().getNombreCompleto() + "]: ");
           String nombre = scanner.nextLine();
           cliente.setNombreCompleto(nombre.isEmpty() ? reporteActual.getCliente().getNombreCompleto() : nombre);
           System.out.print("Teléfono de contacto [" + reporteActual.getCliente().getTelefonoContacto() + "]: ");
           String telefono = scanner.nextLine();
           cliente.setTelefonoContacto(telefono.isEmpty() ? reporteActual.getCliente().getTelefonoContacto() : telefono);
       
           // Datos del reporte
            System.out.print("Tipo de reporte [" + reporteActual.getTipoReporte() + "]: ");
            String tipo = scanner.nextLine();
            nuevosDatos.setTipoReporte(tipo.isEmpty() ? reporteActual.getTipoReporte() : tipo);

            System.out.print("Zona [" + reporteActual.getZonaReporte() + "]: ");
            String zona = scanner.nextLine();
            nuevosDatos.setZonaReporte(zona.isEmpty() ? reporteActual.getZonaReporte() : zona);
            
            System.out.print("Estado (ACTIVO/RESUELTO) [" + (reporteActual.isResuelto() ? "RESUELTO" : "ACTIVO") + "]: ");
            String estado = scanner.nextLine();
            if (!estado.isEmpty()) {
                nuevosDatos.setEstadoDesdeString(estado);
            } else {
                nuevosDatos.setResuelto(reporteActual.isResuelto());
            }

            // Datos de la mascota
            System.out.print("Especie [" + reporteActual.getMascota().getEspecie() + "]: ");
            String especie = scanner.nextLine();
            mascota.setEspecie(especie.isEmpty() ? reporteActual.getMascota().getEspecie() : especie);

            System.out.print("Raza [" + reporteActual.getMascota().getRaza() + "]: ");
            String raza = scanner.nextLine();
            mascota.setRaza(raza.isEmpty() ? reporteActual.getMascota().getRaza() : raza);

            System.out.print("Color principal [" + reporteActual.getMascota().getColorPrincipal() + "]: ");
            String color = scanner.nextLine();
            mascota.setColorPrincipal(color.isEmpty() ? reporteActual.getMascota().getColorPrincipal() : color);

            System.out.print("Señas particulares [" + reporteActual.getMascota().getSeniasParticulares() + "]: ");
            String senias = scanner.nextLine();
            mascota.setSeniasParticulares(senias.isEmpty() ? reporteActual.getMascota().getSeniasParticulares() : senias);

            System.out.print("Microchip [" + reporteActual.getMascota().getMicrochip() + "]: ");
            String microchip = scanner.nextLine();
            mascota.setMicrochip(microchip.isEmpty() ? reporteActual.getMascota().getMicrochip() : microchip);
           
            System.out.print("Nombre de la mascota [" + reporteActual.getMascota().getNombre() + "]: ");
            String nombreMascota = scanner.nextLine();
            mascota.setNombre(nombreMascota.isEmpty() ? reporteActual.getMascota().getNombre() : nombreMascota);

            nuevosDatos.setCliente(cliente);
            nuevosDatos.setMascota(mascota);

        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos ingresados: " + e.getMessage());
            throw e;
        }

        return nuevosDatos;
    }
   
   
    private void verCoincidencias() {
       System.out.println("\n--- BUSCANDO COINCIDENCIAS ---");
       try {
           ArrayList<String> coincidencias = gestor.coincidenciaEntreReportes();
             
           for (String coincidencia : coincidencias) {
           System.out.println(coincidencia);
           
           }
           
       } catch (Exception e){
           System.out.println("Error al buscar coincidencias: " + e.getMessage());
       }
           
    }


            
    
    private void ejecutarOpcion (int opcion){
        switch (opcion){
            case 1: registrarReporte(); break;
            case 2: consultarPorCriterio(); break;
            case 3: mostrarReporteGeneral(); break;
            case 4: mostrarReporteAgrupado(); break;
            case 5: verCoincidencias(); break;
            case 6: actualizarReporte (); break;
            default: System.out.println("Opción no válida.");
        }
    }
    
    
    //Como decir: "Crea una casa y luego entra a vivir en ella" sin esto no funciona...
    public static void main(String[] args) {
    new Main().iniciar(); 
    }
       
    //Aqui ya empieza la diversion....
    public void iniciar(){
        System.out.println("¡Bienvenido al Gestor de Reportes de Mascotas!");   
        
        while (true){
            mostrarMenu();
            int opcion = leerOpcion();
            
            if (opcion == -1) continue;
            
            if (opcion == 7) {
                System.out.println("¡Gracias por usar el sistema! Hasta pronto.");
                System.out.println("¡Presione Enter para salir...");
                scanner.nextLine();
                break;
            }
            ejecutarOpcion(opcion);
        }
        scanner.close(); // Cerrar el scanner al final
          
    } //llave metodo iniciar
    
    
}//LLAVE CLASE MAIN
*/