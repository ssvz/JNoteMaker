package com.jnm.jnotes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class NewNoteWindow extends JFrame implements ActionListener {

	JPanel panel;
	JTextArea editArea;
	JButton saveButton, cancelButton;

	public NewNoteWindow() {

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		editArea = new JTextArea();
		panel.add("Center", editArea);
		panel.add("East", saveButton);
		panel.add("South", cancelButton);
		setContentPane(panel);
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);

	}

	private void createNote() {
		String content = editArea.getText();
		String fileName = new GregorianCalendar().getTime().toString();
		Note newNote = new Note(fileName);
		newNote.createAndSetNoteContent(content);
		Environment.notes.add(newNote);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == saveButton) {
			createNote();
			Environment.addClicked = false;
			this.dispose();
		}
		if (arg0.getSource() == cancelButton) {
			Environment.addClicked = false;
			this.dispose();
		}
	}

}
