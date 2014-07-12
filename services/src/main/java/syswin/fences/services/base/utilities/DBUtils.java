package syswin.fences.services.base.utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.core.util.FileUtils;

public class DBUtils {

    public final static String  DB_FOLDER_NAME      = Constants.DB_FOLDER_NAME;
    private final static String PROJECT_HOME_FOLDER = Constants.PROJECT_HOME_FOLDER;
    public final static String  DB_SYSTEM_PROPERTY  = Constants.DB_SYSTEM_PROPERTY;

    public static boolean init () {

        // Check that the project home folder has been set
        String projectHomeFolter = System.getProperty (PROJECT_HOME_FOLDER);
        if (projectHomeFolter == null || projectHomeFolter.isEmpty ()) {
            return false;
        }

        // Set the database folder
        String dbDirPath = projectHomeFolter + "/" + DB_FOLDER_NAME;
        System.setProperty (DB_SYSTEM_PROPERTY, dbDirPath);

        // Check that it was set
        dbDirPath = System.getProperty (DB_SYSTEM_PROPERTY);
        if (dbDirPath == null || dbDirPath.isEmpty ()) {
            return false;
        }

        File dbDir = new File (dbDirPath);
        if (!dbDir.exists () && !dbDir.mkdirs () && !dbDir.exists ()) {
            return false;
        }
/*
        String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

        String DB_URL = "jdbc:derby:crocoDB;create=true";

        // Database credentials
        String USER = "username";
        String PASS = "password";

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName (JDBC_DRIVER);

            // STEP 3: Open a connection
            System.out.println ("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL + "derbyDB;create=true");

            // STEP 4: Execute a query
            System.out.println ("Creating database...");
            stmt = conn.createStatement ();

            //String sql = "CREATE TABLE HY_Address (ID INT, StreetName VARCHAR(20)," + " City VARCHAR(20))";
            stmt.execute("insert into HY_Address values (2 ,'Bucegi','Bucuresti')");
            //stmt.executeUpdate (sql);
            System.out.println ("Database created successfully...");
            
            
            
            try
            {
                stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("select * from HY_Address");
                ResultSetMetaData rsmd = results.getMetaData();
                int numberCols = rsmd.getColumnCount();
                for (int i=1; i<=numberCols; i++)
                {
                    //print Column Names
                    System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
                }

                System.out.println("\n-------------------------------------------------");

                while(results.next())
                {
                    int id = results.getInt(1);
                    String restName = results.getString(2);
                    String cityName = results.getString(3);
                    System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
                }
                results.close();
                stmt.close();
            }
            catch (SQLException sqlExcept)
            {
                sqlExcept.printStackTrace();
            }
            
            
            
        }
        catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace ();
        }
        catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace ();
        }
        finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close ();
            }
            catch (SQLException se2) {}// nothing we can do
            try {
                if (conn != null)
                    conn.close ();
            }
            catch (SQLException se) {
                se.printStackTrace ();
            }// end finally try
        }
*/
        return true;
    }
}
