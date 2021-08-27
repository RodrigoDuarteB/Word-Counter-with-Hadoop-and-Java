package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class DatabaseConnection {
	
	private String user;
	private String password;
	private String database;
	private Connection connection;
	

	public DatabaseConnection(String user, String password, String database) {
		this.user = user;
		this.password = password;
		this.database = database;
		connect();
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	private void connect() { 
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+this.database, this.user, this.password);
			setConnection(connection);
			System.out.println("Successful connection!");
		} catch (Exception e) {
			System.out.println("Something Went Wrong!");
		}
	}
	
	public void insertWord(String word, int count) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO words (word,count) VALUES (?,?)");
			statement.setString(1, word);
			statement.setInt(2, count);
			statement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong! Try again");
		}	
	}
	
	public boolean putWord(String word, int count) {
		boolean b = false;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM words");
			ResultSet result = statement.executeQuery();	
			while(result.next() && !b) {
				String currentWord = result.getString("word");
				if(currentWord.compareToIgnoreCase(word) == 0) {
					int currentCount = result.getInt("count");
					long currentId = result.getLong("id");
					updateCount(currentId, currentCount+count);
					b = true;
				}
			}
			if(!b) {
				insertWord(word, count);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong! Try again");
		}
		return b;
	}
	
	public LinkedList<Word> getWords() {
		LinkedList<Word> list = new LinkedList<Word>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM words");
			ResultSet result = statement.executeQuery();
			while(result.next()) {	
				list.add(new Word(result.getLong("id"), result.getString("word"), result.getInt("count")));
			}
		} catch (Exception e) {
			System.out.println("Something went wrong! Try again");
		}
		return list;
	}
	
	public void updateCount (long id, int count) {
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE words SET count = ? WHERE id = ?");
			statement.setInt(1, count);
			statement.setLong(2, id);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Something went wrong! Try again");
		}
	}
}
