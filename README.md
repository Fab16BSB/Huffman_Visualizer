![License: MIT](https://img.shields.io/badge/Licence-MIT-green)
![IUT: Montreuil](https://img.shields.io/badge/IUT-Montreuil-red)
![Codage: Huffman](https://img.shields.io/badge/Codage-Huffman-purple)
![Java: 8+](https://img.shields.io/badge/Java-8%2B-brightgreen)
![Visualisation: Swing](https://img.shields.io/badge/Visualisation-Swing-yellow)
![Contributors](https://img.shields.io/badge/Contributors-1-orange)
![Stars](https://img.shields.io/github/stars/Fab16BSB/Huffman_Visualizer?color=orange)
![Forks](https://img.shields.io/github/forks/Fab16BSB/Huffman_Visualizer?color=orange)
![Watchers](https://img.shields.io/github/watchers/Fab16BSB/Huffman_Visualizer?color=orange)

# Huffman Compression Visualizer

## 🌍 Multilingual README Versions

- 🇫🇷 [Français](./README.fr.md)  
- 🇬🇧 [English (you are here)](#)  
- 🇪🇸 [Español](./README.es.md)  

## 📘 Project Overview

This project provides an implementation of **Huffman encoding at the character level** and a **graphical visualizer** of the resulting Huffman tree, developed in **Java**. Instead of treating entire words as symbols, the algorithm splits the input text into **characters**, calculates their frequencies, builds a Huffman tree, and displays it interactively using **Swing**.

The project was developed during my 2nd year of the **BUT** program at **IUT de Montreuil**.

The main objective was to understand how Huffman coding works by manipulating individual characters and implementing a visualizer for the resulting tree.

---

### 📊 Input & Tokenization

- **Input**: Any string entered by the user (word or phrase)  
- **Tokenization**: The text is split into individual characters; each character is considered a distinct symbol, including uppercase and lowercase letters  
- **Symbols**: Each unique character (`String`)  
- **Frequency Table**: Counts the number of occurrences of each character  

---

### ⚙️ Huffman Algorithm (Character-Level)

1. **Frequency Counting**  
   - Tokenize the text into characters  
   - Build a `Map<String,Integer>` to track frequencies  

2. **Tree Construction**  
   - Create a leaf node `ArbreCodage<String>` for each character and its frequency  
   - Insert all leaves into a `TreeSet` (sorted by weight, then content)  
   - Remove the two nodes with the lowest weight, merge them, reinsert, and repeat until a single tree remains  

3. **Code Generation**  
   - Traverse the final tree recursively  
   - Add `"0"` for left branches, `"1"` for right branches  
   - Store the code for each character in a `Map<String,String>`  

4. **Visualization**  
   - Compute (x, y) coordinates using in-order traversal  
   - Draw colored boxes for nodes, connect them with labeled edges (“0”/“1”)  
   - Display in a scrollable Swing window to handle large trees  

---

### 🧑‍💻 Technologies Used

- **Language:** Java 8+  
- **GUI:** `javax.swing`, `java.awt`  
- **Data Structures:** `HashMap`, `TreeSet`, generic class `Arbre<E>`  

---

### 📝 Compilation & Execution

1. **Clone the repository**  
   ```bash
   git clone https://github.com/Fab16BSB/Huffman_Visualizer.git
   cd Huffman_Visualizer
   ```

2. **Compile**
   ```bash
   javac *.java
   ```
3. **Run**
   ```bash
   java Lancement
   ```
### 🙌 Acknowledgements
Thanks to the teaching staff at IUT de Montreuil for guiding this project.
