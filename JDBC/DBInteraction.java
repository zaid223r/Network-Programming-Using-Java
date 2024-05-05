import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.beans.*;

public class DBInteraction {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;

        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            statement = connection.createStatement();

            String SqlQuery = "SELECT * FROM users";
            results = statement.executeQuery(SqlQuery);

            while (results.next()) {
                String status = false;
                if (results.getBoolean("isactive"))
                    status = true;
                System.out.println("Name is:  " + results.getString("firstname") + results.getString("lastname") + ".\n");
                System.out.println("Number of visits is:  " + results.getInt("visits") + "visit.\n");
                System.out.println("Status:  " + status + ".\n");
            }

        } catch (SQLException sqlError) {

        }

    }

}
