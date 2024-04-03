package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.EditorModel;
import view.EditorView;

public class EditorController implements ActionListener{
   private EditorView editorView;
   
	public EditorController(EditorView editorView) {
	super();
	this.editorView = editorView;
}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("Save")) {
			editorView.saveText();
		}if(src.equals("Load")) {
			editorView.loadText();
		}if(src.equals("Browse Files")) {
			editorView.browseFiles();;
		}
		
	}
  
}
