import java.util.Scanner;

/**
 * Main class to launch the Huffman compression visualization.
 * Prompts the user to enter a string, then displays the corresponding Huffman tree.
 * 
 * @author Fab.16
 */
public class Lancement {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le texte à compresser : ");
        String texte = scanner.nextLine();

        Huffman huffman = new Huffman(texte);

        ArbreCodage arbre = huffman.getArbreHuffman();

        String path = "output/huffman.png";
        Afficheur<Character> affiche = new Afficheur<Character>(arbre, path);

        scanner.close();
    }
}

