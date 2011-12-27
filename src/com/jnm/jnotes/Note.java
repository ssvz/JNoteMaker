package com.jnm.jnotes;

import java.io.FileWriter;
import java.io.IOException;

public class Note {
	String noteContent;
	String fileName;

public	Note(String fileName) {
		this.fileName = Environment.notesDirectory+fileName;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String content)
	{
		noteContent=content;
	}
	public void createAndSetNoteContent(String content) {
		noteContent=content;
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getFileName() {
		return fileName;
	}

}
