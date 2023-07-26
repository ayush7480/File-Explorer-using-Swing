import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;


public class MainLauncher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Launch the FileExplorer application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileExplorer fileExplorer = new FileExplorer();
                fileExplorer.setVisible(true);
            }
        });
    }
}
