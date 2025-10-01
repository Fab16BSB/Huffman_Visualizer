import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;       
import java.io.File;               
import java.io.IOException; 



/**
 * A Swing component that displays a binary tree (Arbre) graphically.
 * It computes the layout coordinates of each node and renders the tree in a window.
 *
 * @param <E> the type of elements stored in the tree nodes
 * @author Fab.16
 */
public class Afficheur<E> extends JPanel {

    private static final long serialVersionUID = -390030957914099477L;

    /** The root of the tree to display. */
    private Arbre<E> arbre;

    /** A map of tree nodes to their coordinates in the panel. */
    private Map<Arbre<E>, Point> coord;

    /** Width and height of each node box. */
    private final int LARG_BOITE = 20;
    private final int HAUT_BOITE = 20;

    /** Horizontal and vertical spacing between nodes. */
    private final int dx = 17;
    private final int dy = 60;

    /** Margins for box drawing. */
    private final int mx = dx / 2;
    private final int my = dy / 2;

    /** Node colors. */
    private final Color couleurNoeud = Color.PINK;
    private final Color couleurFeuille = Color.PINK;

    /** Scroll pane to handle large trees. */
    private JScrollPane scroll;

    /** Frame displaying the panel. */
    private JFrame f;

    /** Initial window dimensions. */
    private final int LARGEUR_FENETRE = 800;
    private final int HAUTEUR_FENETRE = 600;

   
    /**
    * Constructs an Afficheur with the specified tree and saves it as a PNG.
    * Initializes the scroll pane and attempts to open a display window.
    * In headless environments the window cannot open but the image is still saved.
    *
    * @param arbre the root tree to display
    * @param path  file path where the PNG image will be saved
    * @throws java.awt.HeadlessException if a graphical display cannot be created (headless environment)
    */

    public Afficheur(Arbre<E> arbre, String path) {
        this.arbre = arbre;
        this.coord = new HashMap<>();
        setBackground(Color.WHITE);
        this.scroll = new JScrollPane();
        scroll.getViewport().add(this);
        sauvegarderImage(path, "png");
        try{
            construitFenetre();
        } catch (java.awt.HeadlessException e) { // Nommer l'exception e est facultatif mais bonne pratique
            // Message d'erreur corrigé pour la concaténation en Java
            System.out.println("Échec de l'affichage de la fenêtre graphique (Headless mode détecté).");
            System.out.println("Vous pouvez consulter l'image sauvegardée ici : " + path);
        }
    }

    /**
     * Builds and displays the JFrame containing the scrollable panel.
     */
    private void construitFenetre() {
        f = new JFrame("Arbre");
        f.getContentPane().add(scroll);
        f.setSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

    /**
     * Calcule la taille optimale du panneau pour contenir l'arbre.
     */
    private Dimension calculerTaille() {
        if (arbre == null) {
            return new Dimension(1, 1);
        }
        // Recalculer les coordonnées pour déterminer les dimensions maximales
        calculerCoordonnees(arbre, 1, 0); 
        
        int largeur = (int) (coord.values().stream().mapToDouble(Point::getX).max().orElse(0.0) * dx + LARG_BOITE + mx);
        int hauteur = (int) (Arbre.hauteur(arbre) * dy + HAUT_BOITE + my);
        int max_x = (int) (calculerCoordonnees(arbre, 1, 0) * LARG_BOITE * 0.9);
        int max_y = (int) (Arbre.hauteur(arbre) * (HAUT_BOITE + dy) * 0.9);

        // On prend la plus grande des deux pour être sûr
        return new Dimension(
            Math.max(LARGEUR_FENETRE, max_x + dx * 2), 
            Math.max(HAUTEUR_FENETRE, max_y + dy * 2)
        );
    }
    
    /**
     * Sauvegarde le rendu du JPanel dans un fichier image.
     * @param cheminFichier Le chemin complet où sauvegarder l'image.
     * @param format Le format de l'image (ex: "png" ou "jpg").
     */
    private void sauvegarderImage(String cheminFichier, String format) {

        // 1. Déterminer la taille nécessaire
        Dimension taille = calculerTaille();
        
        // S'assurer que le JPanel a la bonne taille pour le rendu
        setSize(taille);
        setPreferredSize(taille);
        
        // 2. Créer l'image tampon (BufferedImage)
        BufferedImage image = new BufferedImage(
            taille.width, 
            taille.height, 
            BufferedImage.TYPE_INT_ARGB
        );

        // 3. Dessiner le JPanel sur le BufferedImage
        Graphics g = image.getGraphics();
        
        // Appeler la méthode de peinture pour dessiner le contenu de l'arbre
        paint(g); 
        
        g.dispose();

        // 4. Écrire l'image dans le fichier
        try {
            File outputfile = new File(cheminFichier);
            // S'assurer que le répertoire de destination existe
            outputfile.getParentFile().mkdirs(); 
            ImageIO.write(image, format, outputfile);
            System.out.println("Image de l'arbre sauvegardée sous : " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de l'image : " + e.getMessage());
        }
    }

    /**
     * Draws the subtree rooted at a given node.
     *
     * @param a the root of the subtree to draw
     * @param g the graphics context
     */
    private void dessiner(Arbre<E> a, Graphics g, String code) {
        int x1 = (int) (dx * coord.get(a).getX() + mx);
        int y1 = (int) (dy * coord.get(a).getY() + my);

        if (Arbre.getFilsG(a) != null) {
            Arbre<E> gche = Arbre.getFilsG(a);
            int x2 = (int) (dx * coord.get(gche).getX() + mx);
            int y2 = (int) (dy * coord.get(gche).getY() + my);
            g.drawLine(x1 - LARG_BOITE / 2, y1, x2 - LARG_BOITE / 2, y2 - HAUT_BOITE);

            // Affichage du "0" à gauche de la branche
            int midX = (x1 + x2) / 2 - 5;
            int midY = (y1 + y2) / 2;
            g.drawString("0", midX, midY);

            dessiner(gche, g, code + "0");
        }

        if (Arbre.getFilsD(a) != null) {
            Arbre<E> drt = Arbre.getFilsD(a);
            int x2 = (int) (dx * coord.get(drt).getX() + mx);
            int y2 = (int) (dy * coord.get(drt).getY() + my);
            g.drawLine(x1 - LARG_BOITE / 2, y1, x2 - LARG_BOITE / 2, y2 - HAUT_BOITE);

            // Affichage du "1" à droite de la branche
            int midX = (x1 + x2) / 2 + 5;
            int midY = (y1 + y2) / 2;
            g.drawString("1", midX, midY);

            dessiner(drt, g, code + "1");
        }

        g.setColor(Arbre.estFeuille(a) ? couleurFeuille : couleurNoeud);
        g.fillRect(x1 - LARG_BOITE, y1 - HAUT_BOITE, LARG_BOITE, HAUT_BOITE);
        g.setColor(Color.BLACK);
        g.drawRect(x1 - LARG_BOITE, y1 - HAUT_BOITE, LARG_BOITE, HAUT_BOITE);

        String valeur = "" + Arbre.getContenu(a).toString();
        g.drawString(valeur, x1 - LARG_BOITE / 2 - (valeur.length() * 4), y1 - HAUT_BOITE / 2 + 5);
    }



    /**
     * Computes and stores the coordinates of each node in the tree.
     *
     * @param a the current node
     * @param xCourant current x-coordinate
     * @param yCourant current y-coordinate (depth)
     * @return the next available x-coordinate after this subtree
     */
    public int calculerCoordonnees(Arbre<E> a, int xCourant, int yCourant) {
        if (Arbre.getFilsG(a) != null)
            xCourant = calculerCoordonnees(Arbre.getFilsG(a), xCourant, yCourant + 1);

        this.coord.put(a, new Point(xCourant, yCourant));
        xCourant++;

        if (Arbre.getFilsD(a) != null)
            xCourant = calculerCoordonnees(Arbre.getFilsD(a), xCourant, yCourant + 1);

        return xCourant;
    }

    /**
     * Paints the component, drawing the tree if it exists.
     *
     * @param g the graphics context
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (arbre != null) {
            setPreferredSize(new Dimension(
                (int) (calculerCoordonnees(arbre, 1, 0) * LARG_BOITE * 0.9),
                (int) (Arbre.hauteur(arbre) * (HAUT_BOITE + dy) * 0.9)
            ));
            scroll.getViewport().revalidate();
            dessiner(arbre, g, "");
        }
    }

    /**
     * Sets a new tree to display and updates the view.
     *
     * @param a the new root of the tree
     */
    public void setArbre(Arbre<E> a) {
        this.arbre = a;
        actualiser();
    }

    /**
     * Refreshes the panel by repainting it and updating the scroll view.
     */
    public void actualiser() {
        this.repaint();
        scroll.getViewport().revalidate();
    }
}

