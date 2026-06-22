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

    // Vincular los componentes del FXML usando @FXML y el fx:id exacto
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

        // 2. Instanciar el Analizador Léxico (generado por JFlex) pasándole el lector
        Lexer lexer = new Lexer(reader); // Asegurarse de que el nombre coincida con su clase JFlex [cite: 745]

        // 3. Instanciar el Analizador Sintáctico (generado por CUP) pasándole el Lexer [cite: 872, 879]
        parser parserSintactico = new parser(lexer); // Asegurarse de que el nombre coincida con su clase CUP [cite: 872]

        try {
            txt_salida.setText("Iniciando análisis léxico y sintáctico...\n");

            // Disparar el parseo sintáctico (que consume los tokens del lexer automáticamente)
            parserSintactico.parse();

            // Si el metodo parse() no arroja ninguna excepción, el programa es legal
            txt_salida.appendText(">> [ÉXITO] El programa cumple con las reglas sintácticas del lenguaje.\n");
            txt_salida.appendText(">> Análisis completado con éxito. ¡Sintaxis Válida!");

        } catch (Exception e) {
            // Capturar y mostrar los errores en la consola inferior
            txt_salida.appendText(">> [ERROR SINTÁCTICO / LÉXICO]: " + e.getMessage() + "\n");
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
