
import connection.DatabaseConnection;

public class App {

	public static void main(String[] args) {
		DatabaseConnection conn = new DatabaseConnection("postgres", "postgreDuarte98", "hadoop");
		conn.putWord("choquehuanca", 5);
	
	}

}
