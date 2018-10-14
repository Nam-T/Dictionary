package dic1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Dictionary Dic = new Dictionary();
		
		DictionaryCommandline Dc = new DictionaryCommandline();
		Dc.insertFromFile();
		//Dc.dictionaryBasic(Dic);
		Dc.dictionaryAdvance(Dic);
	}

}
