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

public class Employee extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String id = request.getParameter("did");
        String user = request.getParameter("dname");
        String name = request.getParameter("dmob");
        String mob = request.getParameter("dmob2");
        String salary = request.getParameter("salary");
        String pass = request.getParameter("daddr");
        response.setContentType("text/html");
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String q = "insert into employee values(" +'"'+ id+'"' + "," +'"'+ user+'"' + ","+'"'+ name+'"' +","+'"'+ mob+'"' + "," +'"'+ pass+'"' + ","+'"'+ salary+'"' +");";
            String query = "select*from employee  where emp_id="+'"'+id+'"'+";";
            rs=stmt.executeQuery(query);
            if(rs.next()){
                out.print("<center><h1> Employee id already taken </h1><center>");
                RequestDispatcher rd = request.getRequestDispatcher("Distributor.html");
                rd.include(request, response);
            }
            else{
                stmt.executeUpdate(q);
                out.print("<center><h1> Employee Added</h1><center>");
                RequestDispatcher rd=request.getRequestDispatcher("Distributor.html");
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
