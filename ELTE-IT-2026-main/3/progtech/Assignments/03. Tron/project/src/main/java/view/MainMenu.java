package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/** Main menu of the application. */
public class MainMenu extends JPanel {
    /** Customization panel for player 1. */
    private final PlayerCustomizationPanel player1Panel;

    /** Customization panel for player 2. */
    private final PlayerCustomizationPanel player2Panel;

    /** Input field for horizontal map size. */
    private final JTextField horizontalSizeInput;

    /** Input field for vertical map size. */
    private final JTextField verticalSizeInput;

    /** Parent of the component. */
    private final GameView parent;

    /**
     * Constructor.
     * @param parent parent of this component
     */
    public MainMenu(GameView parent) {
        this.parent = parent;
        // установить атрибуты панели
        this.setLayout(new BorderLayout());
        this.setPreferredSize(this.parent.FRAME_DIMENSION);

        // добавить заголовок на панель
        JLabel title = new JLabel("TRON", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(150,38));
        title.setFont(title.getFont().deriveFont(38f));
        this.add(title, BorderLayout.NORTH);

        // создать панель настройки player1
        this.player1Panel = new PlayerCustomizationPanel(Color.BLUE, new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resources/controls1.png"))));
        this.add(player1Panel, BorderLayout.WEST);

        // создать панель настройки player2
        this.player2Panel = new PlayerCustomizationPanel(Color.RED, new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resources/controls2.png"))));
        this.add(player2Panel, BorderLayout.EAST);

        // создать меню навигации
        JPanel navigationMenu = new JPanel();

        // создать ввод размера карты
        Dimension inputSize = new Dimension(25, 25);
        JLabel mapSizeLabel = new JLabel("Pályaméret:");

        this.horizontalSizeInput = new JTextField("17");
        this.horizontalSizeInput.setPreferredSize(inputSize);

        this.verticalSizeInput = new JTextField("17");
        this.verticalSizeInput.setPreferredSize(inputSize);

        navigationMenu.add(mapSizeLabel);
        navigationMenu.add(horizontalSizeInput);
        navigationMenu.add(new JLabel("x")); // разделитель между полями ввода
        navigationMenu.add(verticalSizeInput);

        // создать кнопки главного меню
        Dimension buttonDimension = new Dimension(150,40);

        // кнопка запуска, вызывает функцию newGame
        JButton startButton = new JButton("Játék indítása");
        startButton.setPreferredSize(buttonDimension);
        startButton.addActionListener(e -> {
            this.parent.newGame(this.player1Panel.getPlayerName(),  this.player1Panel.getPlayerColor(),
                                this.player2Panel.getPlayerName(),  this.player2Panel.getPlayerColor(),
                                this.horizontalSizeInput.getText(), this.verticalSizeInput.getText()   );
        });

        // кнопка меню оценки, переключает графический интерфейс на highScoreMenu, запрашивает обновление таблицы через родительский элемент
        JButton scoreButton = new JButton("Eredmények");
        scoreButton.setPreferredSize(buttonDimension);
        scoreButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) (this.parent.getContentPane().getLayout());
            cardLayout.show(this.parent.getContentPane(), "Score menu");
            this.parent.requestScores();
        });

        // кнопка выхода, закрывает приложение при действии
        JButton exitButton = new JButton("Kilépés");
        exitButton.setPreferredSize(buttonDimension);
        exitButton.addActionListener(e -> System.exit(0));

        navigationMenu.add(startButton);
        navigationMenu.add(scoreButton);
        navigationMenu.add(exitButton);

        this.add(navigationMenu, BorderLayout.CENTER);
    }
}