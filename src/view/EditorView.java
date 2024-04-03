package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.EditorController;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

import java.awt.BorderLayout;
import java.awt.Event;

public class EditorView extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorView frame = new EditorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextArea textArea;
	private JTree fileTree;
	private File directory;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public EditorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 453);
		EditorController ls = new EditorController(this);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		JMenuItem jMenuItem_save = new JMenuItem("Save");
		jMenuItem_save.addActionListener(ls);
		JMenuItem jMenuItem_load = new JMenuItem("Load");
		jMenuItem_load.addActionListener(ls);
		JMenuItem jMenuItem_duyet = new JMenuItem("Browse Files");
		jMenuItem_duyet.addActionListener(ls);
		mnNewMenu.add(jMenuItem_duyet);
		mnNewMenu.add(jMenuItem_save);
		mnNewMenu.add(jMenuItem_load);
		
		getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(180, 11, 539, 381);
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 160, 381);
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
	}
	  public void saveText() {
	        String text = textArea.getText();
	        try (PrintWriter writer = new PrintWriter("saved_text.txt")) {
	            writer.write(text);
	        } catch (IOException e) {
	           JOptionPane.showMessageDialog(this, "Failed to save text.");
	        }
	        JOptionPane.showMessageDialog(this, "Text saved successfully!.");
	    }
	  public void loadText() {
	        try (BufferedReader reader = new BufferedReader(new FileReader("saved_text.txt"))) {
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line).append("\n");
	            }
	            textArea.setText(sb.toString());
	            JOptionPane.showMessageDialog(this, "Text loaded successfully!");
	        } catch (IOException e) {
	        	JOptionPane.showMessageDialog(this, "No saved text found!");
	        }
	    }
	  public void browseFiles() {
		  File rootDirectory = new File("D:\\"); // Thay đổi đường dẫn thư mục cần duyệt tại đây
	        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootDirectory.getName());
	        JTree tree = new JTree(rootNode);

	        JScrollPane scrollPane = new JScrollPane(tree);
	        panel.add(scrollPane,BorderLayout.CENTER);
	        
	        buildTree(rootNode, rootDirectory);
	  }
	  private void buildTree(DefaultMutableTreeNode parentNode, File parentDirectory) {
	        File[] files = parentDirectory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
	                parentNode.add(node);
	                if (file.isDirectory()) {
	                    buildTree(node, file); // Đệ quy nếu là thư mục
	                }
	            }
	        }

}
}
