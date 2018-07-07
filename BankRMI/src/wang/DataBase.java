package wang;

import java.sql.*;

public class DataBase {
    private String url;
    private String className;
    private String DBusername;
    private String DBpassword;
    private Connection conn;
    PreparedStatement pstmt;
    public DataBase(){
        className = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/bank";
        DBusername = "root";
        DBpassword = "wbl.123";
        try{
            Class.forName(className);
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载失败!");
            e.printStackTrace();
        }
    }
    public void getConnetion(){
        try{
            conn = DriverManager.getConnection(url,DBusername,DBpassword);
        }catch (SQLException e){
            System.out.println("获取数据库连接失败!");
            e.printStackTrace();
        }
    }
    public PreparedStatement PreparedStmt(String sql){
        try{
            pstmt = conn.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pstmt;
    }
    public void release(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
