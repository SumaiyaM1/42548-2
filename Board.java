import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Board extends JPanel {
    private static final long serialVersionUID = 1L; 
    private final BoardLabel[][] boardLabels;
    private final Game game;

    public Board(int size, Game game) {
        this.game = game;
        setLayout(new GridLayout(size, size));
        boardLabels = new BoardLabel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardLabels[i][j] = new BoardLabel(i, j);
                boardLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardLabels[i][j].setPreferredSize(new Dimension(50, 50));
                boardLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                boardLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                boardLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        BoardLabel label = (BoardLabel) e.getSource();
                        if (label.getText().equals("S") || label.getText().equals("O")) {
                            JOptionPane.showMessageDialog(Board.this, "Invalid Move: This cell is already occupied. Try again!", "Invalid Move", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        game.updateMode();
                        if (game.isModeS()) {
                            label.setText("S");
                        } else {
                            label.setText("O");
                        }

                        if (checkForSOS(label.getI(), label.getJ())) {
                            String playerName = game.isPlayerTurn() ? "Blue Player" : "Red Player"; 
                            game.playerWon(playerName);
                        } else {
                            game.checkForDraw();
                            game.alterTurn();
                        }
                    }
                });
                add(boardLabels[i][j]);
            }
        }
    }

    public int getBoardSize() {
        return boardLabels.length;
    }

    public BoardLabel[][] getBoardLabels() { 
        return boardLabels;
    }

    private boolean checkForSOS(int x, int y) {
        String currentPlayerSymbol = game.getCurrentPlayerSymbol();

        return (checkDirection(x, y, 0, 1, currentPlayerSymbol) || 
                checkDirection(x, y, 1, 0, currentPlayerSymbol) || 
                checkDirection(x, y, 1, 1, currentPlayerSymbol) || 
                checkDirection(x, y, 1, -1, currentPlayerSymbol));  
    }

    private boolean checkDirection(int x, int y, int dx, int dy, String symbol) {
        int count = 0;

        for (int i = -2; i <= 2; i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (newX >= 0 && newY >= 0 && newX < boardLabels.length && newY < boardLabels.length) {
                if (boardLabels[newX][newY].getText().equals(symbol)) {
                    count++;
                }
            }
            
            if (count == 3) {
                return true;
            }
        }
        return false;
    }
}
