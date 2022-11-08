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

public class drug extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String id = request.getParameter("name");
        String name = request.getParameter("expiry");
        String mob = request.getParameter("stock");
        response.setContentType("text/html");
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String q = "update drug_details set stock = "+ mob +" where generic_name= \""+ id+"\" and expiry = \""+name+"\";";
            stmt.executeUpdate(q);
            out.print("<center><h1> Stock Updated</h1><center>");
            RequestDispatcher rd=request.getRequestDispatcher("Drugs_details.html");
            rd.include(request, response);
            
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }   
}
