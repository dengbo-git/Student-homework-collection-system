package fun.china1.utils;

import fun.china1.service.GetRe;

import java.io.*;
import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;
    public DBUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        BufferedReader filetxt = null;
        String ip="";
        String databasename="";
        String databasenum="";
        String databasecode="";
        String dkh="";
        try {
            filetxt = new BufferedReader( new InputStreamReader( new FileInputStream(new File(GetRe.getPath()+"mysql.txt")), "UTF-8" ));
             ip=filetxt.readLine();
             databasename=filetxt.readLine();
             databasenum=filetxt.readLine();
             databasecode=filetxt.readLine();
             dkh=filetxt.readLine();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                filetxt.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        conn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+dkh+"/"+databasename+"?useUnicode=true&characterEncoding=UTF-8&userSSL=true&serverTimezone=GMT%2B8",databasenum,databasecode);
        return conn;
    }
    public static PreparedStatement getstmt(String sql)  {

        try {
            ps = getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;

    }

    public static void close()  {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
