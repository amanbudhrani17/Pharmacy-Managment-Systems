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

public class sat extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String id = request.getParameter("sid");
        String cname = request.getParameter("cname");
        String amt = request.getParameter("amt");
        String gn = request.getParameter("gn");
        String eid = request.getParameter("eid");
         String date = request.getParameter("date");
        
        response.setContentType("text/html");
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String q = "insert into sales_details1 values(" +'"'+ cname+'"' + "," +'"'+ id+'"' + ","+'"'+ amt +'"'+ ","+'"'+ eid +'"' +");";
            String q2 = "insert into sales_details2 values(" +'"'+ id+'"' + "," +'"'+ gn+'"' +");";
            String q3 = "insert into transaction_details values(" +'"'+ id+'"' + "," +'"'+ cname+'"' + ","+'"'+ amt +'"'+ ","+'"'+ date +'"' +");";

            String query = "select*from sales_details1 where sale_id="+'"'+id+'"'+";";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                stmt.executeUpdate(q2);
                out.print("<center><h1> Medicine Added</h1><center>");
                RequestDispatcher rd = request.getRequestDispatcher("sat.html");
                rd.include(request, response);
            }
            else{
                stmt.executeUpdate(q);
                stmt.executeUpdate(q2);
                stmt.executeUpdate(q3);
                out.print("<center><h1> Transaction and Medicine Added</h1><center>");
                RequestDispatcher rd=request.getRequestDispatcher("sat.html");
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
