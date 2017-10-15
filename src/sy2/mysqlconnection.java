package sy2;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mysqlconnection {
	private String driver = "com.mysql.jdbc.Driver";
	// 驱动程序名
	private String url = "jdbc:mysql://127.0.0.1:3306/bookdb";
	// URL指向要访问的数据库名scutcs
	private String user = "root";
	// MySQL配置时的用户名
	// MySQL配置时的密码
    private String password = "";
    
    public Connection connect()
            {
    	        Connection conn= null;
                try { 
                 // 
                 Class.forName(driver);
                 // 
                 conn = DriverManager.getConnection(url, user, password);
                 if(!conn.isClosed())	
                  System.out.println("Succeeded connecting to the Database!");
                 // 用来执行SQL语句
                 
                } catch(ClassNotFoundException e) {
                 System.out.println("Sorry,can`t find the Driver!"); 
                 e.printStackTrace();
                } catch(SQLException e) {
                 e.printStackTrace();
                } catch(Exception e) {
                 e.printStackTrace();
                } 
                return conn;
            }
    private static void closeAll(ResultSet rs, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn == null)
            return;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<book> getbook()
            {
    	         ArrayList<book> inf = new ArrayList<book>();
            	 Connection conn = null;
            	 conn = connect();
            	 ResultSet rs = null;
            	 try {
            		 Statement statement = conn.createStatement();
                     // 要执行的SQL语句
                     // 结果集
                     rs = statement.executeQuery("select * from book");
                     String name = null;
                     while(rs.next()) {
                      // 选择sname这列数据
                      // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                      // 然后使用GB2312字符集解码指定的字节数组
                      // 输出结果
                     book e = new book();
                     e.Isbn=rs.getString(1);
                     e.Title=rs.getString(2);
                     e.AuthorID=rs.getInt(3);
                     e.Publisher=rs.getString(4);
                     e.PublishDate=rs.getString(5);                                     
                     e.price=rs.getFloat(6);
                     inf.add(e);
                    
                     }
                     rs.close();
                     conn.close();
            	 }catch (Exception e) {
            		   e.printStackTrace();
            	  }finally {
            	        closeAll(rs,conn);
            	    }
            	return inf;
            }
    public ArrayList<author> getauthor(){
    	 ArrayList<author> inf = new ArrayList<author>();
    	 Connection conn = null;
    	 conn = connect();
    	 ResultSet rs = null;
    	 try {
    		 Statement statement = conn.createStatement();
             // 要执行的SQL语句
             // 结果集
             rs = statement.executeQuery("select * from author");
             String name = null;
             while(rs.next()) {
              // 选择sname这列数据
              name = rs.getString("Name");
              // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
              // 然后使用GB2312字符集解码指定的字节数组
              // 输出结果
             author e = new author();
             e.AuthorID = rs.getInt(1);
             e.Name = rs.getString(2);
             e.Age = rs.getString(3);
             e.Country = rs.getString(4);
             inf.add(e);
             }
             rs.close();
             conn.close();
    	 }catch (Exception e) {
    		   e.printStackTrace();
    	  }finally {
    	        closeAll(rs,conn);
    	    }
    	return inf;
    }
    public String addbook(book nbook){
        PreparedStatement ps=null; 
        Connection conn = null;
   	    conn = connect();
   	    String sql = "insert into book values(?, ?, ?, ?, ?,?)";
   	    ResultSet rs = null;
   	 try {
   		ps=conn.prepareStatement(sql);
   	    ps.setString(1, nbook.Isbn);
   	    ps.setString(2, nbook.Title);
   		ps.setInt(3, nbook.AuthorID);
   		ps.setString(4, nbook.Publisher);
   		ps.setString(5, nbook.PublishDate);
   		ps.setFloat(6, nbook.price);
   		int i = ps.executeUpdate();
   		if(i==0)return "fail";
   	 }catch (Exception e) {
   		   e.printStackTrace();
   	  }finally {
   	        closeAll(rs,conn);
   	    }     
    	return "success";
    }
   public String addauthor(author nauthor){
        PreparedStatement ps=null; 
        Connection conn = null;
   	    conn = connect();
   	    String sql = "insert into author values(?, ?, ?, ?)";
   	    ResultSet rs = null;
   	 try {
   		 
   		 ps=conn.prepareStatement(sql);
   		ps.setInt(1, nauthor.AuthorID);
   		ps.setString(2, nauthor.Name);
   		 ps.setString(3, nauthor.Age);	
   		ps.setString(4, nauthor.Country);
   		int i = ps.executeUpdate();
   		if(i==0)return "fail";
   	 }catch (Exception e) {
   		   e.printStackTrace();
   	  }finally {
   	        closeAll(rs,conn);
   	    }     
    	return "success";
    }
   public String delbook(String isbn){
	   
	   Statement ps=null; 
       Connection conn = null;
  	    conn = connect();
  	    String sql = "delete from book where ISBN="+'"'+isbn+'"';
  	    ResultSet rs = null;
  	 try {
  		 ps=conn.createStatement();
  		int i = ps.executeUpdate(sql);
  		if(i==0)return "fail";
  	 }catch (Exception e) {
  		   e.printStackTrace();
  	  }finally {
  	        closeAll(rs,conn);
  	    }     
   	return "success";
   }
public String updatebook(book new_book, String o_id){	   
	   Statement ps; 
       Connection conn = null;
  	    conn = connect();
  	    String sql = "update students set tel="+"default"+" where id=5"+'"'+'"';
  	    ResultSet rs = null;
  	 try {
  		 ps=conn.createStatement();
  		if (new_book.price != 0) {sql = "update book set price="+new_book.price+" where ISBN="+'"'+o_id+'"';
 		 ps.executeUpdate(sql);}
 		if (!new_book.Publisher.equals("")) {sql = "update book set Publisher="+'"'+new_book.Publisher+'"'+" where ISBN="+'"'+o_id+'"';
 		 ps.executeUpdate(sql);}
 		if (!new_book.PublishDate.equals("")) {sql = "update book set PublishDate="+'"'+new_book.PublishDate+'"'+" where ISBN="+'"'+o_id+'"';
 		 ps.executeUpdate(sql);}
 		if (new_book.AuthorID !=0) {sql = "update book set AuthorID="+new_book.AuthorID+" where ISBN="+'"'+o_id+'"';
 		 ps.executeUpdate(sql);}
 		 ps.close();
 		 conn.close();
  	 }catch (Exception e) {
  		   e.printStackTrace();
  	  }finally {
  	        closeAll(rs,conn);
  	    }     
   	return "success";
   }
    
}
