import javax.swing.*;
import java.awt.*;

class UserPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JCheckBox choiceBoxS;
    private final JCheckBox choiceBoxO;
    private boolean isModeS = true;

    public UserPanel(String name, Game game) {
        setLayout(new GridLayout(3, 1));

        JLabel playerLabel = new JLabel(name);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        choiceBoxS = new JCheckBox("S");
        choiceBoxO = new JCheckBox("O");

        choiceBoxS.setSelected(true);
        choiceBoxO.setSelected(false);

        add(playerLabel);
        add(choiceBoxS);
        add(choiceBoxO);

        choiceBoxS.addActionListener(e -> onChoiceBoxS());
        choiceBoxO.addActionListener(e -> onChoiceBoxO());
    }

    private void onChoiceBoxS() {
        choiceBoxS.setSelected(true);
        choiceBoxO.setSelected(false);
        isModeS = true;
    }

    private void onChoiceBoxO() {
        choiceBoxS.setSelected(false);
        choiceBoxO.setSelected(true);
        isModeS = false;
    }

    public boolean isModeS() {
        return isModeS;
    }
    
    public void resetPlayerMode() {
        choiceBoxS.setSelected(true);
        choiceBoxO.setSelected(false);
        isModeS = true;
    }
}
