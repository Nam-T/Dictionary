package dic1;
import java.util.*;

import dic1.Dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class DictionaryCommandline extends DictionaryManagement{
	//hien thi tu va nghia cua tu
	public void showAllWords(Dictionary Dic) {		
		//System.out.println("No" + "\t" +"| English" + "\t" + "| Vietnamese");
		for(int i=0;i<Dic.getN();i++) {
			System.out.println((i+1) + "\t" + "| " + Dic.words[i].getWordTarget() + "\t \t" + "| " + Dic.words[i].getWordExplain());
		}
		
	}
	//phien ban cml so khai : nhap tu va nghia
	public void dictionaryBasic(Dictionary Dic) {
		insertFromCommandline(Dic);
		showAllWords(Dic);
		
	}
	//phien ban cml cai tien
	public void dictionaryAdvance(Dictionary Dic) throws FileNotFoundException, IOException {
		insertFromFile();
		showAllWords(Dic);
		
		System.out.println("1. tra tu Anh - Viet");
		System.out.println("2. them tu");
		System.out.println("3. tra tu theo tu khoa");
		System.out.println("4. xoa tu");
		System.out.println("5. thoat.");
		
		
		while(1>0) {
			System.out.println("nhap chuc nang: " );
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			if(num == 1 ) {
				dictionaryLookup(Dic);
			}
			else if(num == 2) {
				System.out.println("nhap tu muon them:");
				DictionaryManagement dm = new DictionaryManagement();
				Connect_RF.rf.fReader();
				String wordTarget;
				String wordExplain;
				Scanner sc1 = new Scanner(System.in);
				wordTarget = sc1.nextLine();
				wordExplain = sc1.nextLine();
				Word word = new Word(wordTarget, wordExplain);
				int check;
				WriteToFile wtf ;
				check = dm.addWord(word, Connect_RF.rf.tm);
				if(check == dm.exsit) {
					System.out.println("tu da ton tai.");
				}
				else if(check == dm.ok) {
					System.out.println("them tu thanh cong.");
				}
				try {
					wtf = new WriteToFile("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1");

					wtf.Write(Connect_RF.rf.tm.values());
				}catch (FileNotFoundException ex){
					
				}catch(IOException ex){
					
				}
			}
			else if(num == 3) {
		
				System.out.println("nhap tu khoa: ");
				Scanner read = new Scanner(new FileInputStream("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1.dat"),"UTF-8");

		        String line;
		        String s;
		        Scanner sc1= new Scanner(System.in);
		        s=sc1.nextLine();

		        System.out.println("nhung tu bat dau bang " + s + " la :");
		        while(read.hasNextLine()) 			//khi chua het file
		        {
		            line = read.nextLine(); 		//doc 1 dong

		            if(!line.contains("#")) 	// neu trong dong khong co ki tu #
		            {

		                StringTokenizer st = new StringTokenizer(line,"\t");	// chia line thanh cac phan ngan cach bang dau tab
		                while(st.hasMoreTokens()){


		                    String key = st.nextToken();

		                    String val = st.nextToken();

		                    key = key.trim();		// xoa bo khoang trang 

		                    val = val.trim();
		                    if(key.startsWith(s)){
		                        System.out.println(key+"\t"+val);
		                    }
		                }
		            }
		        }
			}
			else if(num == 4) {
				System.out.println("nhap tu can xoa: ");
				Connect_RF.rf.fReader();
				WriteToFile wtf ;
				String s;
				Scanner sc2 = new Scanner(System.in);
				s=sc2.nextLine();
				delete(s);
				try {
					wtf = new WriteToFile("C:\\Users\\g1\\eclipse-workspace\\Dictionary\\src\\dic1\\loanword1");

					wtf.Write(Connect_RF.rf.tm.values());
				}catch (FileNotFoundException ex){
					
				}catch(IOException ex){
					
				}
			}
			else if(num == 5) {
				break;
			}
		}
	}
}

