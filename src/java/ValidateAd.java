import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ValidateAd extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String un = request.getParameter("Ausername");
        String pw = request.getParameter("Apassword");
        response.setContentType("text/html");
        
        String u1 = "Aman";
        String p1 = "aman";
        
        
        String u2 = "Nityanand";
        String p2 = "mathur";
        
        
        String u3 = "Ayush";
        String p3 = "singh";
        
        
        String u4 = "Ashmit";
        String p4 = "jaiswal";
        
        if(un.equals(u1)&&pw.equals(p1)){
            RequestDispatcher rd = request.getRequestDispatcher("temp.html"); 
            rd.forward(request, response);
        }
        else if(un.equals(u2)&&pw.equals(p2)){
            RequestDispatcher rd = request.getRequestDispatcher("temp.html");    
            rd.forward(request, response);
        }
        else if(un.equals(u3)&&pw.equals(p3)){
            RequestDispatcher rd = request.getRequestDispatcher("temp.html");    
            rd.forward(request, response);
        }
        else if(un.equals(u4)&&pw.equals(p4)){
            RequestDispatcher rd = request.getRequestDispatcher("temp.html");    
            rd.forward(request, response);
        }   
        else{
            out.println("<center><h1>!! Please Enter Valid Username & Password for Admin !!</h1></center>");
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
        out.close();
    }   
}
