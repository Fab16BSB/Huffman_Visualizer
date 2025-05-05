import java.util.HashMap;
import java.util.Set;

/**
 * This class represents a binary tree used for character encoding, 
 * such as in Huffman coding. It extends the generic binary tree {@link Arbre}
 * and implements {@link Comparable} to allow sorting based on weight.
 * 
 * @author Fab.16
 */
public class ArbreCodage extends Arbre<Character> implements Comparable<ArbreCodage> {

    private int poids;

    /**
     * Constructs a new internal coding tree by combining two subtrees.
     * The resulting weight is the sum of the weights of the two subtrees.
     *
     * @param arbre1 the left subtree
     * @param arbre2 the right subtree
     */
    public ArbreCodage(ArbreCodage arbre1, ArbreCodage arbre2) {
        super(arbre1, (char) 0, arbre2);
        this.poids = arbre1.poids + arbre2.poids;
    }

    /**
     * Constructs a leaf node for a specific character with its associated weight.
     *
     * @param c the character stored in this node
     * @param poids the weight of the character (e.g. frequency)
     */
    public ArbreCodage(Character c, int poids) {
        super(c);
        this.poids = poids;
    }

    /**
     * Initializes a mapping for each character in the given set to an empty string.
     * This can be used as a base to build the Huffman coding map.
     *
     * @param s the set of characters to initialize
     * @return a HashMap with each character mapped to an empty string
     */
    public HashMap<Character, String> codage(Set<Character> s) {
        HashMap<Character, String> map = new HashMap<>();
        for (Character c : s) {
            map.put(c, "");
        }
        return map;
    }

    /**
     * Recursively finds the binary encoding for a given character in the tree.
     *
     * @param c the character to encode
     * @param v the current binary prefix built during traversal
     * @return the binary encoding for the character
     * @throws Error if the character is not found in the tree
     */
    public String codageParChar(Character c, String v) {
        if (estFeuille(this)) {
            if (this.contenu.equals(c)) {
                return v;
            } else {
                throw new Error("Character not found in leaf");
            }
        } else {
            try {
                return ((ArbreCodage) this.filsD).codageParChar(c, v + "1");
            } catch (Error e) {
                return ((ArbreCodage) this.filsG).codageParChar(c, v + "0");
            }
        }
    }

    /**
     * Compares this coding tree to another based on weight.
     *
     * @param arbre the other coding tree to compare
     * @return -1 if this tree is lighter, 1 otherwise (0 only if same reference)
     */
    @Override
    public int compareTo(ArbreCodage other) {
        // 1) d’abord sur le poids
        int cmp = Integer.compare(this.poids, other.poids);
        if (cmp != 0) return cmp;

        // 2) si même poids et feuilles, comparer leur contenu
        if (this.estFeuille(this) && other.estFeuille(other)) {
            return Character.compare(this.contenu, other.contenu);
        }

        // 3) sinon, comparer l’hashCode global pour obtenir un ordre total
        return Integer.compare(this.hashCode(), other.hashCode());
    }

    /**
     * Returns the weight of the tree (sum of all frequencies for subtrees).
     *
     * @return the weight of the node
     */
    public int getPoids() {
        return this.poids;
    }
}

