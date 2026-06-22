package org.integradorTC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;

public class PantallaIDE {

    @FXML
    private Button btn_cargar;

    @FXML
    private Button btn_ejecutar;

    @FXML
    private TextArea txt_entrada;

    @FXML
    private TextArea txt_salida;

    // logica para el botón abrir archivos, donde se abre el explorador de archivos
    @FXML
    void cargarArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo de código de prueba");

        // filtro para que el usuario solo pueda elegir archivos .txt o de código
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Texto (*.txt)", "*.txt")
        );

        // obtiene la ventana actual para montar el explorador encima
        Stage stage = (Stage) btn_cargar.getScene().getWindow();
        File archivoSeleccionado = fileChooser.showOpenDialog(stage);

        if (archivoSeleccionado != null) {
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                // muestra el código fuente en el JTextArea central
                txt_entrada.setText(contenido.toString());
                txt_salida.setText("Archivo cargado con éxito: " + archivoSeleccionado.getName());
            } catch (IOException e) {
                txt_salida.setText("Error al leer el archivo: " + e.getMessage());
            }
        }
    }
    // lógica del botón ejecutar, procesando secuencialmente a través de JFlex y CUP

    @FXML
    void ejecutarAnalisis(ActionEvent event) {
        String codigoFuente = txt_entrada.getText();

        if (codigoFuente.trim().isEmpty()) {
            txt_salida.setText("Consola: El editor se encuentra vacío. Ingrese código para analizar.");
            return;
        }

        // convierte el string del editor en un flujo legible por los analizadores
        StringReader reader = new StringReader(codigoFuente);

        // instancia el analizador lexico (generado por JFlex)
        Lexer lexer = new Lexer(reader);

        // instancia el analizador sintáctico (generado por CUP)
        parser parserSintactico = new parser(lexer);

        try {
            txt_salida.setText("Iniciando análisis léxico y sintáctico...\n");

            parserSintactico.parse();

            txt_salida.appendText(">> [ÉXITO] El programa cumple con las reglas sintácticas del lenguaje.\n");
            txt_salida.appendText(">> Análisis completado con éxito.");

        } catch (Exception e) {
            txt_salida.appendText(">> [ERROR SINTÁCTICO / LÉXICO]: \n" + e.getMessage() + "\n");
            txt_salida.appendText(">> Análisis abortado debido a fallas estructurales en el código fuente.");
        }
    }
    // lógica para borrar la consola
    @FXML
    void limpiarTodo(ActionEvent event) {
        txt_salida.clear();
        txt_entrada.clear();
    }
}
