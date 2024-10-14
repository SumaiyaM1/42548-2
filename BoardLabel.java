import javax.swing.*;

class BoardLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private final int i, j;

    public BoardLabel(int i, int j) {
        this.i = i;
        this.j = j;
        setText("");
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
