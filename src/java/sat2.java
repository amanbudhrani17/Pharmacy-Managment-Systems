import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sat2 extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String id = request.getParameter("did");
        response.setContentType("text/html");

       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String q = "delete from  sales_details1 where sale_id = "+id+";";
            String q2 = "delete from  sales_details2 where sale_id = "+id+";";
            String q3 = "delete from  transaction_details where sale_id = "+id+";";
            
            stmt.executeUpdate(q);
            stmt.executeUpdate(q2);
            stmt.executeUpdate(q3);         
            out.print("<center><h1>Sale Removed</h1><center>");
            RequestDispatcher rd=request.getRequestDispatcher("sat2.html");
            rd.include(request, response);
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }   
}
