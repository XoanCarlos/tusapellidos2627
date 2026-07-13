package com.clase.informes;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import com.clase.persistencia.ConexionMySQL;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InformeClientes {

    // =====================================================
    // MUESTRA EL INFORME DE CLIENTES
    // =====================================================
    public static void mostrar() {

        try {

            // ===========================================
            // 1. OBTENER LA CONEXIÓN A LA BASE DE DATOS
            // ===========================================

            Connection conexion = ConexionMySQL.getConexion();

            // ===========================================
            // 2. CARGAR EL FICHERO JRXML DESDE RESOURCES
            // ===========================================

            InputStream informe =
                    InformeClientes.class.getResourceAsStream(
                            "/com/clase/informes/clientes.jrxml");

            // ===========================================
            // 3. COMPILAR EL INFORME
            // Convierte el JRXML en un JasperReport
            // ===========================================

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(informe);

            // ===========================================
            // 4. RELLENAR EL INFORME
            // En este ejemplo no enviamos parámetros,
            // por eso usamos un HashMap vacío.
            // ===========================================

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            new HashMap<>(),
                            conexion);

            // ===========================================
            // 5. MOSTRAR LA VISTA PREVIA
            // false = al cerrar el informe NO se cierra
            // la aplicación.
            // ===========================================

            JasperViewer.viewReport(jasperPrint, false);

            // ===========================================
            // 6. CERRAR LA CONEXIÓN
            // ===========================================

            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
