![License: MIT](https://img.shields.io/badge/Licence-MIT-green)
![IUT: Montreuil](https://img.shields.io/badge/IUT-Montreuil-red)
![Codage: Huffman](https://img.shields.io/badge/Codage-Huffman-purple)
![Java: 8+](https://img.shields.io/badge/Java-8%2B-brightgreen)
![Visualisation: Swing](https://img.shields.io/badge/Visualisation-Swing-yellow)
![Contributors](https://img.shields.io/badge/Contributors-1-orange)
![Stars](https://img.shields.io/github/stars/Fab16BSB/Huffman_Visualizer?color=orange)
![Forks](https://img.shields.io/github/forks/Fab16BSB/Huffman_Visualizer?color=orange)
![Watchers](https://img.shields.io/github/watchers/Fab16BSB/Huffman_Visualizer?color=orange)

# Visualizador de Compresión Huffman

## 🌍 Versiones Multilingües del README

- 🇫🇷 [Francés](./README.fr.md)  
- 🇬🇧 [Inglés](./README.md)  
- 🇪🇸 [Español (usted está aquí)](#)  

## 📘 Visión General del Proyecto

Este proyecto ofrece una implementación de la **codificación Huffman a nivel de caracteres** y un **visualizador gráfico** del árbol de Huffman resultante, desarrollado en **Java**. En lugar de tratar palabras completas como símbolos, el algoritmo divide el texto en **caracteres**, calcula sus frecuencias, construye un árbol de Huffman y lo muestra de forma interactiva con **Swing**.

El proyecto fue realizado durante mi segundo año del programa **BUT** en el **IUT de Montreuil**.

El objetivo principal era comprender cómo funciona el algoritmo de Huffman manipulando caracteres individuales y desarrollando un visualizador del árbol generado.

---

### 📊 Entrada y Tokenización

- **Entrada**: cualquier cadena de texto introducida por el usuario (palabra o frase)  
- **Tokenización**: el texto se divide en caracteres individuales; cada carácter se considera un símbolo distinto, incluyendo mayúsculas y minúsculas  
- **Símbolos**: cada carácter único (`String`)  
- **Tabla de Frecuencias**: cuenta las apariciones de cada carácter  

---

### ⚙️ Algoritmo de Huffman (Nivel Carácter)

1. **Conteo de Frecuencias**  
   - Tokenizar el texto en caracteres  
   - Construir un `Map<String,Integer>` con las frecuencias  

2. **Construcción del Árbol**  
   - Crear un nodo hoja `ArbreCodage<String>` para cada carácter y su frecuencia  
   - Insertar todas las hojas en un `TreeSet` (ordenado por peso y contenido)  
   - Extraer los dos nodos con menor peso, fusionarlos, reinsertarlos y repetir hasta obtener un solo árbol  

3. **Generación de Códigos**  
   - Recorrer el árbol de forma recursiva  
   - Añadir `"0"` para ramas izquierdas, `"1"` para ramas derechas  
   - Guardar el código de cada carácter en un `Map<String,String>`  

4. **Visualización**  
   - Calcular coordenadas (x, y) usando recorrido inorden  
   - Dibujar nodos con cuadros de colores, conectar con líneas etiquetadas “0”/“1”  
   - Mostrar en una ventana Swing con scroll para árboles grandes  

---

### 🧑‍💻 Tecnologías Utilizadas

- **Lenguaje:** Java 8+  
- **Interfaz Gráfica:** `javax.swing`, `java.awt`  
- **Estructuras de Datos:** `HashMap`, `TreeSet`, clase genérica `Arbre<E>`  

---

### 💻 Instalar Java (Si no tienes Java instalado)

Si no tienes Java instalado, puedes seguir las instrucciones en uno de mis videos de YouTube para instalar Java en diferentes plataformas:

- **Linux**: [Instalar Java en Linux](https://www.youtube.com/watch?v=-9G2YARJ0jM)
- **Mac**: [Instalar Java en Mac](https://www.youtube.com/watch?v=hts1lGSKZfc&t=1s)
- **Windows**: [Instalar Java en Windows](https://www.youtube.com/watch?v=vCQHCYM_OVY)
  
---

### 📝 Compilación y Ejecución

1. **Clonar el repositorio**  
   ```bash
   git clone https://github.com/Fab16BSB/Huffman_Visualizer.git
   cd Huffman_Visualizer
   ```

2. **Compilar**
   ```bash
   javac *.java
   ```

3. **Ejecutar**
   ```bash
   java Lancement
   ```
   
---

### 🙌 Agradecimientos
Gracias al equipo docente del IUT de Montreuil por su acompañamiento durante el proyecto.
