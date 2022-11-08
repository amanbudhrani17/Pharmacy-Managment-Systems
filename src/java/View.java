
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Pharmacy Tables</title>");
            out.println("<link href=\"https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap\" rel=\"stylesgeet\">");
            out.println("</head>");
            out.println("<body>");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman&allowPublicKeyRetrieval=true&useSSL=false");
            Statement stmt=connect.createStatement();
            String query = "select*from distributor2 left join distributor1 on distributor1.dist_id = distributor2.dist_id";
            ResultSet rs = stmt.executeQuery(query);
            out.println("<center><h1>Distributor</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>dist_id</th><th>dist_name</th><th>dist_mob</th><th>dist_addr</th></tr>");
            while(rs.next()){
                String id = rs.getString(1);
                String mob= rs.getString(2);
                String name = rs.getString(4);
                String addr = rs.getString(5);
                out.println("<tr><td>"+id+"</td><td>"+name+"</td><td>"+mob+"</td><td>"+addr+"</td></tr>");
            }
            out.println("</table>");
            String query2 = "select*from drugs2 as d2 left join drugs1 as d1 on d1.generic_name = d2.generic_name";
            ResultSet rs2 = stmt.executeQuery(query2);
            out.println("<center><h1>Drugs</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>generic_name</th><th>cost_price</th><th>sell_price</th><th>Invoice_No.</th><th>tot_stock</th><th>location</th></tr>");
            while(rs2.next()){
                String id = rs2.getString(1);
                String mob= rs2.getString(2);
                String name = rs2.getString(3);
                String price = rs2.getString(4);
                String stock = rs2.getString(6);
                String  addr = rs2.getString(7);
                
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td><td>"+price+"</td><td>"+stock+"</td><td>"+addr+"</td></tr>");
            }
            out.println("</table>");
            String query3 = "select*from drug_details";
            ResultSet rs3 = stmt.executeQuery(query3);
            out.println("<center><h1>Drug Details</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>generic_name</th><th>stock</th><th>expiry</th></tr>");
             while(rs3.next()){
                String id = rs3.getString(1);
                String mob= rs3.getString(2);
                String name = rs3.getString(3);
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td></tr>");
            }
            out.println("</table>");
            String query4 = "select*from transaction_details";
            ResultSet rs4 = stmt.executeQuery(query4);
            out.println("<center><h1>Transaction Details</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>sale_id</th><th>cust_name</th><th>tot_amt</th><th>date</th></tr>");
             while(rs4.next()){
                String id = rs4.getString(1);
                String mob= rs4.getString(2);
                String name = rs4.getString(3);
                String date = rs4.getString(4);
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td><td>"+date+"</td></tr>");
            }
            out.println("</table>");
            String query5 = "select*from sales_details1 left join sales_details2 on sales_details1.sale_id=sales_details2.sale_id";
            ResultSet rs5 = stmt.executeQuery(query5);
            out.println("<center><h1>Sales Details</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>cust_name</th><th>sale_id</th><th>tot_stock</th><th>generic_name</th></tr>");
             while(rs5.next()){
                String id = rs5.getString(1);
                String mob= rs5.getString(2);
                String name = rs5.getString(3);
                String date = rs5.getString(5);
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td><td>"+date+"</td></tr>");
            }
            out.println("</table>");
            String query6 = "select*from employee";
            ResultSet rs6 = stmt.executeQuery(query6);
            out.println("<center><h1>Employees</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>emp_id</th><th>emp_username</th><th>emp_name</th><th>emp_mob</th><th>emp_pass</th><th>salary</th></tr>");
             while(rs6.next()){
                String id = rs6.getString(1);
                String mob= rs6.getString(2);
                String name = rs6.getString(3);
                String date = rs6.getString(4);
                String mobile = rs6.getString(5);
                String cname = rs6.getString(6);
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td><td>"+date+"</td><td>"+mobile+"</td><td>"+cname+"</td></tr>");
            }
            out.println("</table>");
            String query7 = "SELECT * FROM pharmacy.purchase_details1 as p1 left join pharmacy.purchase_details2 as p2 ON p1.invoice_no = p2.invoice_no ;";
            ResultSet rs7 = stmt.executeQuery(query7);
            out.println("<center><h1>Purchase_Details</h1></center>");
            out.println("<br>");
            out.println("<div>");
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>dist_id</th><th>Invoice_No</th><th>generic_name</th><th>qty</th><th>cost_price</th></tr>");
             while(rs7.next()){
                String id = rs7.getString(1);
                String mob= rs7.getString(2);
                String name = rs7.getString(4);
                String date = rs7.getString(5);
                String mobile = rs7.getString(6);
                out.println("<tr><td>"+id+"</td><td>"+mob+"</td><td>"+name+"</td><td>"+date+"</td><td>"+mobile+"</td></tr>");
            }
            out.println("</table>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
