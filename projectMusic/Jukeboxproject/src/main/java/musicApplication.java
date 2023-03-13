import javax.sound.sampled.UnsupportedAudioFileException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class musicApplication
{
    public static void main(String[] args)throws ClassNotFoundException, SQLException, UnsupportedAudioFileException {
        //Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc.mysql://127.0.0.1:3306/jukebox","root","Divya@0606");
       Statement statement=connection.createStatement();
       ResultSet resultSet=statement.executeQuery("select * from songs");


    }
}
