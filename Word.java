package dic1;

public class Word {
	private String  WordTarget;
	private String WordExplain;
	
	public String getWordTarget() {
		return WordTarget;
	}
	public void setWordTarget(String wordTarget) {
		WordTarget = wordTarget;
	}
	
	public String getWordExplain() {
		return WordExplain;
	}
	public void setWordExplain(String wordExplain) {
		WordExplain = wordExplain;
	}
	
	public Word(String wordTarget, String wordExplain) {
		super();
		WordTarget = wordTarget;
		WordExplain = wordExplain;
	}
	

}
