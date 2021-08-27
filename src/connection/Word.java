package connection;

public class Word {
	long id;
	String word;
	int count;

	public Word(long id, String word, int count) {
		this.id = id;
		this.word = word;
		this.count = count;
	}
	
	public Word(String word) {
		this.id = 0;
		this.word = word;
		this.count = 0;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id)+" "+word+" "+String.valueOf(count);
	}
	
	@Override
	public boolean equals(Object obj) {
		return word.toLowerCase() == ((Word)obj).word.toLowerCase();
	}

}
