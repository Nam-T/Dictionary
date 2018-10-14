package dic1;


import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.Scanner;

import java.util.StringTokenizer;

import java.util.TreeMap;


public class ReadFile {
	
    private Word mw;

    public TreeMap tm = new TreeMap<String, Word>();

    public void fReader() throws FileNotFoundException, IOException
    {
    	Scanner read = new Scanner(new FileInputStream("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1.dat"),"UTF-8");
    	String line;
    	while(read.hasNextLine())
        {
            line = read.nextLine();

            if(!line.contains("#"))
            {

            	String[] temp = line.split("\t");
        		String key = temp[0].trim();
        		String val = temp[1].trim();
        		mw = new Word(key,val);
        		tm.put(key.toLowerCase(),mw);
            }
        }
    }

}

