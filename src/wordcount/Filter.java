package wordcount;

import java.util.LinkedList;

public class Filter {
	
	private String[] filters = {"la", "las", "por", "a", "al", "de", "del", "dónde", "donde", "el", "en", "él", "ella", "entre", "es", 
			"este", "hacia", "lo", "los", "me", "más", "otras", "por", "que", "se", "porque", "porqué", "su", "sus", "un", "una", "y", 
			"durante","desde","hasta","entre","hasta","para","sin","sobre","pro","versus","sin","mediante","tras","ante","bajo","arriba",
			"sube","según","vía", "con", "contra", "cabe","como","otros","otro","otra","aún","sin","sobre","debajo","también","menudo",
			"usualmente",
			"casualmente","casual","te","tu","tuyo","suyo","nosotros","pero","alto", "o", "e", "u", "so", "si", "no", "tambien", "sea",
			"una", "uno", "las", "la", "en", "ellos", "tu", "tú", "mi", "mis", "vosotros", "nuestro", "nuestros", "carlos", "lr", "enero", "febrero", 
			"marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre", " ", "flores","mal","bien",
			"lunes","martes","miercoles","jueves","viernes","sabado","domingo","una","uno","dos","tres","cuatro","cinco","seis","siete","ocho",
			"nueve","diez", "fin", "a costa", "supongamos", "que", "supongo", "best", "mejor", "", "nacional", "pais", "sociedad", "corz",
			"memoria", "ciudades", "voces", "ciudad"};
	private LinkedList<String> filtersList;
	
	Filter(){
		filtersList = new LinkedList<String>();
		for(String filter : this.filters) {
			filtersList.add(filter);
		}
	}
		
	public boolean filterWord(String word) {
		return filtersList.contains(removeNotLetters(word.toLowerCase()));
	}
	
	public String removeNotLetters(String word) {
		String letters = "";
		for(int i=0; i<word.length(); i++) {
			if(Character.isLetter(word.charAt(i))) {
				letters += charSinAcento(word.charAt(i));
			}
		}
		return letters;
	}
	
	public char charSinAcento(char ch) {
		if(ch == 160) {
			return 97;
		}
		if(ch == 130) {
			return 101;
		}
		if(ch == 161) {
			return 105;
		}
		if(ch == 162) {
			return 111;
		}
		if(ch == 163) {
			return 117;
		}
		return ch;
	}
	
}
