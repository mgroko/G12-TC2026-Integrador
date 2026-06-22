# Teoría de la Computación - Trabajo integrador 2026
### Grupo 12 - Integrantes
* Donda Melisa Ileana
* Roko María Guillermina

## Mini-lenguaje Orientado a Objetos - Análisis Léxico y Sintáctico
El objetivo principal del proyecto es aplicar los conceptos formales de la teoría de autómatas y gramáticas para construir las fases iniciales de un compilador funcional. Para lograrlo, se integraron las siguientes herramientas metacompiladoras en un entorno desarrollado en Java:

* **Analizador Léxico (JFlex):** Encargado de leer el flujo de caracteres y empaquetarlos en tokens válidos (palabras reservadas, identificadores, símbolos) utilizando expresiones regulares.
* **Analizador Sintáctico (CUP):** Recibe los tokens y valida que el código cumpla con la estructura jerárquica definida por nuestra Gramática Libre de Contexto (GLC). Se aplicaron refactorizaciones para garantizar un autómata LALR(1) libre de ambigüedades (Shift/Reduce).

## Características del Lenguaje

El diseño soporta construcciones clave del paradigma orientado a objetos y programación estructurada, incluyendo:
* Definición e instanciación de clases y objetos.
* Declaración de atributos y métodos con control de retorno.
* Manejo de estructuras de control y bifurcaciones (`if`, `else`, `while`).
* Soporte para estructuras de datos consecutivas (arreglos/vectores).
* Capacidad algorítmica probada para interpretar lógicas complejas como el **Método de Ordenamiento Burbuja**.

Todo el análisis se puede probar visualmente a través de un Mini-IDE integrado con interfaz gráfica construida en JavaFX.
