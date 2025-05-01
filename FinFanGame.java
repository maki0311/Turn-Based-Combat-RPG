import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinFanGame extends JFrame {
    private JButton startButton;
    private JTextArea gameTextArea;
    private FinFanCharacter player;
    private FinFanCharacter enemy;
    private JButton attackButton;
    private JButton spellButton;
    private JButton defendButton;
    private JButton curaButton;
    private boolean isDefending; // Track defense state

    private JPanel actionPanel;
    private JPanel spellPanel;

    public FinFanGame() {
        setTitle("Final Fantasy 0");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn("Attack");
            }
        });

        spellButton = new JButton("Spell");
        spellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn("Spell");
            }
        });

        defendButton = new JButton("Defend");
        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn("Defend");
            }
        });

        curaButton = new JButton("Cura");
        curaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerTurn("Cura");
            }
        });

        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        add(new JScrollPane(gameTextArea), BorderLayout.CENTER);

        actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(attackButton);
        actionPanel.add(spellButton);
        actionPanel.add(defendButton);
        actionPanel.setVisible(false); // Initially hidden

        spellPanel = new JPanel(new FlowLayout());
        spellPanel.add(curaButton);
        spellPanel.setVisible(false); // Initially hidden

        JPanel bottomPanel = new JPanel(new CardLayout());
        bottomPanel.add(actionPanel, "action");
        bottomPanel.add(spellPanel, "spell");
        add(bottomPanel, BorderLayout.SOUTH);
        add(startButton, BorderLayout.NORTH);
    }

    private void startGame() {
        player = new FinFanCharacter("Ezreal", 100, 25);
        enemy = new FinFanCharacter("Troll", 80, 15);
        isDefending = false; //resets defensive stance

        gameTextArea.setText("A wild Troll appears!\n");
        updateGameStatus();
        startBattle();
    }

    private void updateGameStatus() {
        gameTextArea.append("\nPlayer HP: " + player.getHealth());
        gameTextArea.append("\nEnemy HP: " + enemy.getHealth() + "\n");
    }

    private void startBattle() {
        actionPanel.setVisible(true);
        spellPanel.setVisible(false);
        revalidate();
        repaint();
    }

    private void playerTurn(String action) {
        if (action.equalsIgnoreCase("Attack")) {
            int damage = player.calcDamage(enemy);
            enemy.takeDamage(damage);
            gameTextArea.append("\nYou attack the Troll for " + damage + " damage.");
            isDefending = false; // Reset defense state after action
        } else if (action.equalsIgnoreCase("Defend")) {
            isDefending = true; // Set defense state for one round
            gameTextArea.append("\nYou are defending.");
            // Defense will only apply during the enemy's turn
        } else if (action.equalsIgnoreCase("Spell")) {
            showSpellMenu();
            return;
        } else if (action.equalsIgnoreCase("Cura")) {
            useCura();
            return;
        }
        updateGameStatus();
        if (enemy.getHealth() > 0) {
            enemyTurn();
        }
        // Reset defense after the player's turn
        isDefending = false;
    }
    

    private void showSpellMenu() {
        actionPanel.setVisible(false);
        spellPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void useCura() {
        player.heal(15);  // Assume FinFanCharacter has a heal method
        gameTextArea.append("\nYou use Cura and heal 15 HP.");
        spellPanel.setVisible(false);
        actionPanel.setVisible(true);
        revalidate();
        repaint();
    }

    private void enemyTurn() {
        if (!isDefending) {
            int damage = enemy.calcDamage(player);
            player.takeDamage(damage);
            gameTextArea.append("\nThe Troll attacks you for " + damage + " damage.");
        } else {
            gameTextArea.append("\nThe Troll's attack is blocked by your defense.");
        }
        updateGameStatus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinFanGame game = new FinFanGame();
            game.setVisible(true);
        });
    }
}
