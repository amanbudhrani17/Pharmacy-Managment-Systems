import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class export2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        File file = new File("C:\\ProgramData/MySQL/MySQL Server 8.0/Uploads/output.csv");
        file.delete();
        response.setContentType("text/html;");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        HashSet<String> set = new HashSet(); 
        set.add("distributor1");
        set.add("distributor2");
        set.add("drug_details");
        set.add("drugs1");
        set.add("drugs2");
        set.add("employee");
        set.add("purchase_details1");
        set.add("sales_details1");
        set.add("sales_details2");
        set.add("transaction_details");
        if(set.contains(name)){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=aman");
                Statement stmt=connect.createStatement();
                ResultSet rs = null;
    //            String query = "SELECT * INTO OUTFILE '\myExportFile.csv' FIELDS ENCLOSED BY '"' TERMINATED BY ';' ESCAPED BY '"' LINES TERMINATED BY '\r\n' FROM distributor1;";
                String query =  "table "+name+" INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/output.csv'FIELDS TERMINATED BY ','OPTIONALLY ENCLOSED BY '"+'"'+"'ESCAPED BY ''LINES TERMINATED BY '\n';";
                stmt.executeQuery(query);
                String a = "C:\\ProgramData/MySQL/MySQL Server 8.0/Uploads/output.csv";
//                InputStream in = request.getServletContext().getResourceAsStream(a);
//                OutputStream out2 = response.getOutputStream();
//                 byte[] buffer = new byte[1048];
//        
//                int numBytesRead;
//                while ((numBytesRead = in.read(buffer)) > 0) {
//                    out2.write(buffer, 0, numBytesRead);
//                }
                response.setContentType("APPLICATION/OCTET-STREAM");   
                response.setHeader("Content-Disposition","attachment; filename=\"output.csv\"");   

                FileInputStream fileInputStream = new FileInputStream(a);  

                int i;   
                while ((i=fileInputStream.read()) != -1) {  
                out.write(i);   
                }   
                fileInputStream.close();   
                out.close();   
                out.print("Done");
                RequestDispatcher rd = request.getRequestDispatcher("export.html");
                rd.include(request,response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(export2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(export2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            out.print("<center><h1> Invalid Table Name</h1><center>");
            RequestDispatcher rd = request.getRequestDispatcher("export.html");
            rd.include(request,response);
        }
    }
}

