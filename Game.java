import javax.swing.*;
import java.awt.*;

class Game extends JPanel {
    private static final long serialVersionUID = 1L;
    private final UserPanel bluePanel;
    private final UserPanel redPanel;
    private boolean modeS = true;
    private boolean playerTurn = true;
    private Board board;
    private JLabel turnLabel;

    public Game() {
        setLayout(new BorderLayout());
        int boardSize = takeGameInfo();
        bluePanel = new UserPanel("Blue Player", this);
        redPanel = new UserPanel("Red Player", this);

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("SOS Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        turnLabel = new JLabel("Current Player: Blue");
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(turnLabel);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        topPanel.add(resetButton);

        add(topPanel, BorderLayout.NORTH);
        add(bluePanel, BorderLayout.WEST);
        add(redPanel, BorderLayout.EAST);

        board = new Board(boardSize, this);
        add(board, BorderLayout.CENTER);
    }

    private int takeGameInfo() {
        String boardSize = (String) JOptionPane.showInputDialog(this, "Choose the board size", "Board Size", JOptionPane.QUESTION_MESSAGE, null, new String[]{"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}, "8");
        return Integer.parseInt(boardSize);
    }

    public boolean isModeS() {
        return modeS;
    }

    public void updateMode() {
        modeS = playerTurn ? bluePanel.isModeS() : redPanel.isModeS();
    }

    public void alterTurn() {
        playerTurn = !playerTurn;
        if (playerTurn) {
            turnLabel.setText("Current Player: Blue");
        } else {
            turnLabel.setText("Current Player: Red");
        }
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    private void resetGame() {
        playerTurn = true;
        turnLabel.setText("Current Player: Blue");

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                board.getBoardLabels()[i][j].setText("");
            }
        }

        bluePanel.resetPlayerMode();
        redPanel.resetPlayerMode();

        revalidate();
        repaint();
    }

    public String getCurrentPlayerSymbol() {
        return playerTurn ? "S" : "O";
    }

    public void playerWon(String playerName) {
        JOptionPane.showMessageDialog(this, playerName + " wins with an SOS!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    public void checkForDraw() {
        BoardLabel[][] boardLabels = board.getBoardLabels();
        boolean allOccupied = true;

        for (int i = 0; i < boardLabels.length; i++) {
            for (int j = 0; j < boardLabels.length; j++) {
                if (boardLabels[i][j].getText().isEmpty()) {
                    allOccupied = false;
                    break;
                }
            }
        }

        if (allOccupied) {
            JOptionPane.showMessageDialog(this, "No player won, Try again!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }
}
