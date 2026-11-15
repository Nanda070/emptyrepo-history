package main.java.view;

import javax.swing.*;
import java.awt.*;

/** Customization Panel for players */
public class PlayerCustomizationPanel extends JPanel {
    /** Input for player name. */
    private final JTextField playerNameInput;

    /** Label that shows player color. */
    private final JLabel playerColorLabel;

    /** Number of players. */
    private static short playerNumber = 1;

    /**
     * Constructor.
     * @param defaultColor default color for player
     * @param controls     image of the players controls
     */
    public PlayerCustomizationPanel(Color defaultColor, ImageIcon controls) {
        // установить размер компонента
        this.setPreferredSize(new Dimension(150, 350));

        // создать заголовок для компонента
        JLabel playerNumberLabel = new JLabel("Játékos #" + PlayerCustomizationPanel.playerNumber);
        playerNumberLabel.setFont(playerNumberLabel.getFont().deriveFont(24f));
        this.add(playerNumberLabel);

        // создать приглашение для ввода имени
        JLabel nameLabel = new JLabel("Név", SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
        this.add(nameLabel);

        // создать ввод имени
        this.playerNameInput = new JTextField("P" + PlayerCustomizationPanel.playerNumber);
        this.playerNameInput.setPreferredSize(new Dimension(125, 20));
        this.add(this.playerNameInput);

        // создать заголовок для ввода цвета
        JLabel colorLabel = new JLabel("Szín", SwingConstants.CENTER);
        colorLabel.setPreferredSize(new Dimension(150,18));
        colorLabel.setFont(colorLabel.getFont().deriveFont(16f));
        this.add(colorLabel);

        // создать цветную этикетку
        this.playerColorLabel = new JLabel();
        this.playerColorLabel.setOpaque(true);
        this.playerColorLabel.setPreferredSize(new Dimension(75,75));
        this.playerColorLabel.setBackground(defaultColor);
        this.add(this.playerColorLabel);

        // создать палитру цветов
        JButton playerColorPickerButton = new JButton("Kiválaszt");
        playerColorPickerButton.setPreferredSize(new Dimension(100,20));
        this.add(playerColorPickerButton);
        playerColorPickerButton.addActionListener(e -> playerColorLabel.setBackground(JColorChooser.showDialog(null,"Válaszd ki a színt.", playerColorLabel.getBackground())));

        // создать раскладку клавиатуры
        JLabel controlLabel = new JLabel(controls);
        this.add(controlLabel);

        // увеличить счетчик количества игроков
        PlayerCustomizationPanel.playerNumber++;
    }

    /** @return text inputted in {@code JTextField playerNameInput} */
    public String getPlayerName() {
        return playerNameInput.getText();
    }

    /** @return background color of {@code JLabel playerColorLabel} */
    public Color getPlayerColor() {
        return playerColorLabel.getBackground();
    }
}
