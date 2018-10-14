package dic1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.*;
public class DictionaryManagement {
	private Word nw;
	public TreeMap tm = new TreeMap<String, Word>();
	final static int exsit = 0,notFull = -1,ok = 1;
	// ham nhap du lieu cho cml so khai
	public void insertFromCommandline(Dictionary Dic){
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap so tu: ");
		int n = Integer.parseInt(scan.nextLine());
		Dic.setN(n);
		Word[] words = new Word[Dic.getN()];		
		for(int i = 0;i<Dic.getN();i++) {
			String wordtarget = scan.nextLine();
			
			String wordexplain = scan.nextLine();
			
			words[i] = new Word(wordtarget,wordexplain);
		}
		Dic.Dictionary(words);
	}
	// ham doc file
	public void insertFromFile() throws FileNotFoundException, IOException
    {

        Scanner read = new Scanner(new FileInputStream("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1.dat"),"UTF-8");

        String line;

        
        while(read.hasNextLine()) //khi chua het file
        {
            line = read.nextLine(); //doc 1 dong
            String[] temp;
            if(!line.contains("#")) // neu trong dong khong co ki tu #
            {
            	temp = line.split("\t");
        		String key = temp[0].trim();
        		String val = temp[1].trim();
        		tm.put(key,val);
            }
        }
    }
	
	// ham tim kiem tu bang dong lenh
	public void dictionaryLookup(Dictionary Dic) throws FileNotFoundException, IOException{

        DictionaryManagement readFile = new DictionaryManagement();
        readFile.insertFromFile();
        String word;
        Scanner sc = new Scanner(System.in);
        int i = 0;
		while( i < 1) {
			System.out.println("Nhap tu can dich : ");
        	word = sc.nextLine();
        	System.out.println("No" + "\t" +"| English" + "\t" + "| Vietnamese");
        	System.out.println(i+1 + "\t" + "| "+word + "\t\t" +"| "+ readFile.tm.get(word));
        	i++;
        }
        
    }
	// ham them tu
	public int addWord(Word word, TreeMap<String,Word> tm) {

        if((word.getWordTarget().compareTo("")==0)||(word.getWordExplain().compareTo("")==0)) return notFull;

        else
        {
            if(tm.containsKey(word.getWordTarget()))
                return exsit;

            else
            {
                String str = word.getWordTarget().toLowerCase();

                tm.put(str, word);
                return ok;
            }
        }
	}
	// ham xoa tu
	public void delete(String s) throws FileNotFoundException, IOException {
		Connect_RF.rf.tm.remove(s);
	}
}
