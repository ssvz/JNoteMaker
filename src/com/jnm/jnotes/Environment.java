package com.jnm.jnotes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Environment extends JFrame implements ActionListener, FocusListener {

	JPanel panel;
	JTextArea noteDisplay;
	JButton addButton, delButton;
	static ArrayList<Note> notes;
	static String notesDirectory = "./notes/";
	static boolean addClicked=false;

	public Environment() {
		super("Note Maker");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		noteDisplay = new JTextArea(400, 400);
		addButton = new JButton("Add Note");
		addButton.addActionListener(this);
		delButton = new JButton("Delete Note");
		// delButton.addActionListener(this);
		panel.add("East", addButton);
		panel.add("Center", noteDisplay);
		panel.add("South", delButton);
		setContentPane(panel);
		notes = new ArrayList<Note>();
		loadNotes();
		addFocusListener(this);

	}

	public static void main(String[] arguments) {
		Environment sf = new Environment();
		sf.setVisible(true);
//		sf.showNote();	
		sf.scrollNotes();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == addButton) {
			addClicked=true;
			JFrame addNote = new NewNoteWindow();
			addNote.setSize(300, 300);
			addNote.setVisible(true);
			// scrollNotes();
			// bring a new window
			// TODO:take this there addNote();
		}

	}

	public void loadNotes() {
		char content[];
		// populate notes from dir
		File d = new File(notesDirectory);
		File files[] = d.listFiles();
		for (File f : files) {
			try {
				FileReader fr = new FileReader(f);
				content = new char[(int) f.length()];
				fr.read(content);
				Note n = new Note(f.getName());
				String scontent=String.copyValueOf(content);
				n.setNoteContent(scontent);
				notes.add(n);
				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void showNote()
	{		
		String content="";
		for(Note n:notes)
				content+=n.getNoteContent();				
		noteDisplay.setText(content);
	}

	public void scrollNotes() {
		while (true && !addClicked) {
			for (Note n : notes) {
				noteDisplay.setText(n.getNoteContent());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		addClicked=false;
		scrollNotes();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		addClicked=true;
		
	}
}