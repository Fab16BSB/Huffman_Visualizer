![License: MIT](https://img.shields.io/badge/Licence-MIT-green)
![IUT: Montreuil](https://img.shields.io/badge/IUT-Montreuil-red)
![Codage: Huffman](https://img.shields.io/badge/Codage-Huffman-purple)
![Java: 8+](https://img.shields.io/badge/Java-8%2B-brightgreen)
![Visualisation: Swing](https://img.shields.io/badge/Visualisation-Swing-yellow)
![Docker Ready](https://img.shields.io/badge/Docker-Ready-blue?logo=docker)
![Contributors](https://img.shields.io/badge/Contributors-1-orange)
![Stars](https://img.shields.io/github/stars/Fab16BSB/Huffman_Visualizer?color=orange)
![Forks](https://img.shields.io/github/forks/Fab16BSB/Huffman_Visualizer?color=orange)
![Watchers](https://img.shields.io/github/watchers/Fab16BSB/Huffman_Visualizer?color=orange)

# Visualiseur de Compression Huffman

## 🌍 Versions Multilingues du README

| 🇫🇷 Français | 🇬🇧 English | 🇪🇸 Español |
|-------------|------------|------------|
| Vous êtes ici ! | [README.md](./README.md) | [README.es.md](./README.es.md) |


## 📘 Vue d'Ensemble du Projet

Ce projet propose une implémentation du **codage de Huffman au niveau des mots** et un **visualiseur graphique** de l’arbre de Huffman résultant, développé en **Java**. Au lieu de traiter chaque caractère comme symbole, l’algorithme découpe le texte en **mots**, calcule leurs fréquences, construit un arbre de Huffman, puis l’affiche de façon interactive avec **Swing**.

Le projet a été réalisé lors de ma 2e année de **BUT** à l'**IUT de Montreuil**. 

L'objectif principal était de comprendre le fonctionnement du codage de Huffman en travaillant directement avec des mots plutôt qu'avec des caractères, et d'implémenter un visualiseur pour l’arbre de Huffman généré.

---

### 📊 Entrée & Tokenisation

- **Entrée** : n’importe quelle chaîne de texte saisie par l’utilisateur (mot ou phrase)  
- **Tokenisation** : découpe du texte en caractères individuels, chaque caractère est traité de manière distincte, y compris les majuscules et minuscules qui sont considérées comme des caractères différents  
- **Symboles** : chaque caractère distinct (`String`)  
- **Table de fréquences** : compte les occurrences de chaque caractère dans le texte  
---

### ⚙️ Algorithme de Huffman au Niveau des Mots

1. **Comptage des Fréquences**  
   - Tokeniser le texte en mots  
   - Construire une `Map<String,Integer>` des fréquences  

2. **Construction de l’Arbre**  
   - Créer un nœud feuille `ArbreCodage<String>` pour chaque mot et sa fréquence  
   - Insérer toutes les feuilles dans un `TreeSet` (trié par poids, puis contenu)  
   - Retirer les deux arbres de plus faible poids, les fusionner, réinsérer, répéter jusqu’à un seul arbre  

3. **Génération des Codes**  
   - Parcourir l’arbre final de façon récursive  
   - Ajouter `"0"` pour une branche gauche, `"1"` pour une branche droite  
   - Stocker le code de chaque mot dans une `Map<String,String>`  

4. **Visualisation**  
   - Calculer les coordonnées (x,y) via un placement infixe  
   - Dessiner les nœuds en boîtes colorées, relier par des traits étiquetés “0”/“1”  
   - Afficher dans une fenêtre Swing défilable pour les grands arbres  

---

### 🧑‍💻 Technologies Utilisées

- **Langage :** Java 8+  
- **Interface :** `javax.swing`, `java.awt`  
- **Structures de données :** `HashMap`, `TreeSet`, classe générique `Arbre<E>`  

---

### 💻 Installer Java (Si vous n'avez pas Java installé)

Si vous n'avez pas Java installé, vous pouvez suivre les instructions dans l'une de mes vidéos YouTube pour installer Java sur différentes plateformes :

- **Linux**: [Installer Java sur Linux](https://www.youtube.com/watch?v=-9G2YARJ0jM)
- **Mac**: [Installer Java sur Mac](https://www.youtube.com/watch?v=hts1lGSKZfc&t=1s)
- **Windows**: [Installer Java sur Windows](https://www.youtube.com/watch?v=vCQHCYM_OVY)

---

### 📝 Compilation et Exécution
Vous pouvez compiler et exécuter le projet soit **localement**, soit avec **Docker**.

#### ⚡ Option 1 : Compilation et Exécution Locale

1. **Cloner le dépôt**  
   ```bash
   git clone https://github.com/Fab16BSB/Huffman_Visualizer.git
   cd Huffman_Visualizer
   ```

2. **Compiler**
   ```bash
   javac *.java
   ```

3. **Exécuter**
   ```bash
   java Lancement
   ```
   
#### 🐳 Option 2 : Utilisation de Docker

1. **Construire l’image Docker**
   ```bash
   docker build -t huffman .
   ```

2. **Exécuter le conteneur Docker**
   ```bash
   docker run -it --name huffman_container huffman
   ```

3. **Copier l’image PNG générée du conteneur vers votre machine hôte**
   ```bash
   docker cp huffman_container:/app/output/huffman.png ./result.png
   ```

4. **Supprimer le conteneur**
   ```bash
   docker rm huffman_container
   ```
---

### 📈 Résultats

```
Entrez le texte à compresser : abracadabra
Image de l'arbre sauvegardé sous : output/huffman.png
```
<img src="exemple.png">

---

### 🙌 Remerciements
Merci à l’équipe pédagogique de l’IUT de Montreuil pour l’encadrement du projet.


