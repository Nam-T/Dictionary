package dic1;



import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;

import java.util.Collection;


public class WriteToFile {
	FileOutputStream fos;
	OutputStreamWriter osw;
	public WriteToFile(String name) throws FileNotFoundException, UnsupportedEncodingException{
		fos = new FileOutputStream(name+".dat",false);
		osw = new OutputStreamWriter(fos,"utf-8");
    }
	public void Write(Collection<Word> cl) throws IOException{
		for(Word s:cl){
			osw.write(s.getWordTarget() + "\t" + s.getWordExplain()+"\r\n");
        }
		osw.flush();
        fos.close();
	}
}
