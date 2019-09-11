package connectivity;

import java.sql.*;

public class ConnectionClass{
    public static Connection connectdb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/bloodbank","raka","raka");
            return connection;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
