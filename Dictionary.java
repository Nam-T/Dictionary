package dic1;

public class Dictionary {
	private int n;	// khai bao so tu
	
	public Word[] words;	// khai bao mang tu
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	public void Dictionary(Word[] words) {
		this.words = words;
	}
}
