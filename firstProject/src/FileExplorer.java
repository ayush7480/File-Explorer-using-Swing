import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileExplorer extends JFrame {
    private JList<File> fileList;
    private JTextArea textArea;
    private JScrollPane textScrollPane;
    private File currentDirectory;

    public FileExplorer() {
     
        setTitle("File Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        
        fileList = new JList<>();
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        textArea = new JTextArea();
        textArea.setEditable(false);

        textScrollPane = new JScrollPane(textArea);

      
        fileList.addListSelectionListener(e -> {
            File selectedFile = fileList.getSelectedValue();
            if (selectedFile != null) {
                if (selectedFile.isDirectory()) {
                    listFiles(selectedFile);
                } else if (selectedFile.isFile()) {
                    openFile(selectedFile);
                }
            }
        });

      
        currentDirectory = new File(System.getProperty("user.home"));

        listFiles(currentDirectory);

      
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(fileList), BorderLayout.WEST);
        contentPane.add(textScrollPane, BorderLayout.CENTER);
    }

    private void listFiles(File directory) {
        currentDirectory = directory;
        File[] files = directory.listFiles();

        DefaultListModel<File> model = new DefaultListModel<>();
        for (File file : files) {
            model.addElement(file);
        }
        fileList.setModel(model);
    }

    private void openFile(File file) {
        if (file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileExplorer fileExplorer = new FileExplorer();
                fileExplorer.setVisible(true);
            }
        });
    }
}
