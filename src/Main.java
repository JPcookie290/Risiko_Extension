import javax.swing.*;



public class Main {
    /*
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame startFrame = new JFrame("Start Screen");
            startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            startFrame.setSize(400, 300);

            Start startScreen = new Start((playerNames, selectedMap, numPlayers) -> {
                Game game = new Game(playerNames, selectedMap, numPlayers);
                GUI gui = new GUI(game);
                SwingUtilities.invokeLater(gui::createAndShowGUI);
                startFrame.dispose();
            });

            startFrame.add(startScreen.getMainPanel());
            startFrame.setVisible(true);
        });
    }

    private static void startGame(String[] playerNames, String selectedMap, int numPlayers) {
        Game game = new Game(playerNames, selectedMap, numPlayers);
        GUI gui = new GUI(game);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }
    */


    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Start startScreen = new Start();

        if (startScreen.getMainPanel() == null) {
            System.out.println("Error: mainPanel is null.");
        } else {
            frame.add(startScreen.getMainPanel());
            frame.setVisible(true);
        }
    }
}