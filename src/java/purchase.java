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

public class purchase extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String id = request.getParameter("did");
        String name = request.getParameter("dname");
        String mob = request.getParameter("dmob");
        String mob2 = request.getParameter("dmob2");
        String addr = request.getParameter("daddr");
        response.setContentType("text/html");
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String q2 = "insert into purchase_details2 values(" +'"'+ id+'"' + "," +'"'+ name+'"' + "," +'"'+ mob2+'"' + "," +'"'+ addr+'"' +");";
            String q = "insert into purchase_details1 values(" +'"'+ mob+'"' + "," +'"'+ id+'"' +");";
            
            String query = "select*from purchase_details1 where invoice_no ="+'"'+id+'"'+";";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                stmt.executeUpdate(q2);
                out.print("<center><h1> Medicine Added </h1><center>");
                RequestDispatcher rd = request.getRequestDispatcher("purchase.html");
                rd.include(request, response);
            }
            else{
                stmt.executeUpdate(q);
                stmt.executeUpdate(q2);
                out.print("<center><h1> Invoice Added</h1><center>");
                RequestDispatcher rd=request.getRequestDispatcher("purchase.html");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }   
}
