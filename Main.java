import javax.swing.JFrame;
import javax.swing.*;


public class Main extends JFrame {
    private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("SOS Game");
            frame.setSize(600, 600);
            frame.setVisible(true);
        });
    }

    public Main() {
        Game game = new Game();
        add(game);
    }
}
