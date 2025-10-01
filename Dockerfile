FROM openjdk:17.0.2-jdk

LABEL author="Fab.16" \
	  version="1.0.0" \
	  description="Image du projet Huffman visualiser"

# Définition du répertoire de travail
WORKDIR /app

# Copie des fichiers java
COPY . .

# Compiler les fichiers Java et placer les fichiers .class dans le répertoire 'bin'
RUN mkdir bin && javac -d bin *.java

# Exécution de l'application
CMD ["java", "-cp", "bin", "Lancement"]
