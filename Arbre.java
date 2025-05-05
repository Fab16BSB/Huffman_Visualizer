import java.util.LinkedList;

/**
 * A generic binary tree class.
 * 
 * @param <E> the type of elements stored in the tree nodes
 * 
 * @author @Fab.16
 */
public class Arbre<E> {

    /** The content of the current node. */
    protected E contenu;

    /** The left child of the node. */
    protected Arbre<E> filsG;

    /** The right child of the node. */
    protected Arbre<E> filsD;

    /**
     * Constructs a binary tree node with given left child, content, and right child.
     *
     * @param filsG the left child
     * @param contenu the content of the node
     * @param filsD the right child
     */
    public Arbre(Arbre<E> filsG, E contenu, Arbre<E> filsD) {
        this.contenu = contenu;
        this.filsG = filsG;
        this.filsD = filsD;
    }

    /**
     * Constructs a binary tree node with given content and no children.
     *
     * @param contenu the content of the node
     */
    public Arbre(E contenu) {
        this.contenu = contenu;
        this.filsG = null;
        this.filsD = null;
    }

    /**
     * Calculates the total number of nodes in the tree.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     * @return the size of the tree
     */
    public static <E> int taille(Arbre<E> a) {
    	int taille = 0;

        if (a != null) {
            taille = 1 + taille(a.filsD) + taille(a.filsG);
        }
        return taille;
    }

    /**
     * Checks if a node is a leaf (i.e., has no children).
     *
     * @param a the tree node
     * @param <E> the type of the tree elements
     * @return true if the node is a leaf, false otherwise
     */
    public static <E> boolean estFeuille(Arbre<E> a) {
        return taille(a) == 1;
    }

    /**
     * Counts the number of leaf nodes in the tree.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     * @return the number of leaves
     */
    public static <E> int nbFeuilles(Arbre<E> a) {
    	int nbFeuille = 0;

    	if(a != null){
	        if (estFeuille(a)) {
	            nbFeuille = 1;
	        } 
	        else {
	            nbFeuille = nbFeuilles(a.filsG) + nbFeuilles(a.filsD);
	        }
	    }
        return nbFeuille;
    }

    /**
     * Computes the height of the tree.
     * The height of an empty tree is -1, and the height of a leaf is 0.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     * @return the height of the tree
     */
    public static <E> int hauteur(Arbre<E> a) {
		int hauteur = -1;

	    if (a != null) {
	        if (estFeuille(a)) {
	            hauteur = 0;
	        } 
	        else {
	            hauteur = 1 + Math.max(hauteur(a.filsD), hauteur(a.filsG));
	        }
	    }
        return hauteur;
    }

    /**
     * Performs a pre-order traversal of the tree and prints each node's content.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     */
    public static <E> void parcoursPrefixe(Arbre<E> a) {
        if (a != null) {
            System.out.println(a.contenu);
            parcoursPrefixe(a.filsG);
            parcoursPrefixe(a.filsD);
        }
    }

    /**
     * Performs an in-order traversal of the tree and prints each node's content.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     */
    public static <E> void parcoursInfixe(Arbre<E> a) {
        if (a != null) {
            parcoursPrefixe(a.filsG);
            System.out.println(a.contenu);
            parcoursPrefixe(a.filsD);
        }
    }

    /**
     * Performs a post-order traversal of the tree and prints each node's content.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     */
    public static <E> void parcoursPostfixe(Arbre<E> a) {
        if (a != null) {
            parcoursPrefixe(a.filsG);
            parcoursPrefixe(a.filsD);
            System.out.println(a.contenu);
        }
    }

    /**
     * Performs a breadth-first traversal (level-order) of the tree and prints each node's content.
     *
     * @param a the tree
     * @param <E> the type of the tree elements
     */
    public static <E> void parcoursLargeur(Arbre<E> a) {
        LinkedList<Arbre<E>> liste = new LinkedList<Arbre<E>>();
        int i = 0;

        if (a != null) {
            liste.addFirst(a);
        }

        while (i < taille(a)) {
            if (liste.getLast().filsG != null) {
                liste.addFirst(liste.getLast().filsG);
            }
            if (liste.getLast().filsD != null) {
                liste.addFirst(liste.getLast().filsD);
            }

            System.out.println(liste.getLast().contenu);
            liste.removeLast();
            i++;
        }
    }

    /**
     * Gets the left child of a node.
     *
     * @param a the node
     * @param <E> the type of the tree elements
     * @return the left child
     */
    public static <E> Arbre<E> getFilsG(Arbre<E> a) {
        return a.filsG;
    }

    /**
     * Gets the right child of a node.
     *
     * @param a the node
     * @param <E> the type of the tree elements
     * @return the right child
     */
    public static <E> Arbre<E> getFilsD(Arbre<E> a) {
        return a.filsD;
    }

    /**
     * Gets the content of a node.
     *
     * @param a the node
     * @param <E> the type of the tree elements
     * @return the content of the node
     */
    public static <E> E getContenu(Arbre<E> a) {
        return a.contenu;
    }

    /**
     * Computes the hash code for the tree based on its content and structure.
     *
     * @return the hash code of the tree
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contenu == null) ? 0 : contenu.hashCode());
        result = prime * result + ((filsD == null) ? 0 : filsD.hashCode());
        result = prime * result + ((filsG == null) ? 0 : filsG.hashCode());
        return result;
    }

    /**
     * Compares this tree to another object for equality.
     * Two trees are equal if their structure and content are the same.
     *
     * @param obj the object to compare to
     * @return true if the trees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Arbre))
            return false;
        Arbre<?> other = (Arbre<?>) obj;
        if (contenu == null) {
            if (other.contenu != null)
                return false;
        } else if (!contenu.equals(other.contenu))
            return false;
        if (filsD == null) {
            if (other.filsD != null)
                return false;
        } else if (!filsD.equals(other.filsD))
            return false;
        if (filsG == null) {
            if (other.filsG != null)
                return false;
        } else if (!filsG.equals(other.filsG))
            return false;
        return true;
    }
}