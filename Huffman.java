import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Huffman coding class for compressing a text using Huffman tree.
 * It computes the character frequency from the input text, builds the Huffman tree,
 * and can generate codes for each character.
 *
 * @author Fab.16
 */
public class Huffman {

    /** The original input text to compress. */
    public String text;

    /** The Huffman tree constructed from character frequencies. */
    public ArbreCodage arbre;

    /** A map containing the frequency of each character in the text. */
    public Map<Character, Integer> frequence;

    /** A map containing the Huffman codes for each character. */
    public Map<Character, String> lesCodes;

    /**
     * Constructs a Huffman object from a given text.
     * It initializes the frequency map and builds the Huffman tree.
     *
     * @param text the input text to encode
     */
    public Huffman(String text) {
        this.text = text;
        this.frequence = litText();
        this.arbre = construitArbre();
    }

    /**
     * Reads the input text and counts the frequency of each character.
     *
     * @return a HashMap mapping each character to its frequency
     */
    private HashMap<Character, Integer> litText() {
        HashMap<Character, Integer> listeLettre = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!listeLettre.isEmpty() && listeLettre.containsKey(c)) {
                int count = listeLettre.get(c);
                listeLettre.replace(c, count + 1);
            } else {
                listeLettre.put(c, 1);
            }
        }

        return listeLettre;
    }

    /**
     * Constructs the Huffman tree from the character frequencies.
     *
     * @return the root of the Huffman tree
     */
    private ArbreCodage construitArbre() {
        TreeSet<ArbreCodage> tri = new TreeSet<>();

        for (Character c : this.frequence.keySet()) {
            tri.add(new ArbreCodage(c, frequence.get(c)));
        }

        ArbreCodage arbre;
        ArbreCodage sousArbre1;
        ArbreCodage sousArbre2;

        while (tri.size() > 1) {
            sousArbre1 = tri.pollFirst();
            sousArbre2 = tri.pollFirst();
            arbre = new ArbreCodage(sousArbre1, sousArbre2);
            tri.add(arbre);
        }

        return tri.first();
    }

    /**
     * Returns the Huffman tree generated from the text.
     *
     * @return the root node of the Huffman tree
     */
    public ArbreCodage getArbreHuffman() {
        return this.arbre;
    }

    /**
     * Displays the frequency table used for Huffman coding.
     * (For debugging or analysis purposes.)
     */
    private void afficheTableCodage() {
        for (Character c : this.frequence.keySet()) {
            System.out.print(c + frequence.get(c));
        }
    }
}
