import javax.servlet.annotation.MultipartConfig;import java.io.*;import javax.servlet.*;import javax.servlet.http.*;import javax.servlet.http.HttpServletRequest;import java.sql.Connection;import java.sql.DriverManager;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Statement;import java.util.logging.Level;import java.util.logging.Logger;@MultipartConfigpublic class import3 extends HttpServlet {//    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {//        response.setContentType("text/html");//        try (PrintWriter out = response.getWriter()) {//            Part part = request.getPart("file");//            String fileName = part.getSubmittedFileName() ;//            //            String path = getServletContext().getRealPath("/files/"+fileName);//            InputStream is = part.getInputStream();//            boolean s = upload(is, path);//            if(s==true){//                out.println("File Uploaded Successfully");//            }//            else{//                out.println("error");//            }//            //        }//    }//    public boolean upload(InputStream is, String path){//        boolean test = false;//        try{//            byte[] byt = new byte[is.available()];//            is.read();//            FileOutputStream fos = new FileOutputStream(path);//            fos.write(byt);//            fos.flush();//            fos.close();//            test = true;//        } catch (IOException ex) {//            Logger.getLogger(import2.class.getName()).log(Level.SEVERE, null, ex);//        }//        return test;//    }    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {    /* Receive file uploaded to the Servlet from the HTML5 form */    Part filePart = request.getPart("file");    String fileName = getSubmittedFileName(filePart);    String path = getServletContext().getRealPath("/files/"+fileName);    filePart.write(path);    response.setContentType("text/html");    PrintWriter out = response.getWriter();    try {        upload.add(path);    } catch (ClassNotFoundException ex) {        Logger.getLogger(import3.class.getName()).log(Level.SEVERE, null, ex);    } catch (SQLException ex) {        Logger.getLogger(import3.class.getName()).log(Level.SEVERE, null, ex);    }    out.print("<center><h1> File Uploaded Successfully</h1><center>");    RequestDispatcher rd = request.getRequestDispatcher("temp.html");    rd.include(request,response);  }    private static String getSubmittedFileName(Part part) {    for (String cd : part.getHeader("content-disposition").split(";")) {        if (cd.trim().startsWith("filename")) {            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.        }    }    return null;}}class upload {    public static void add(String path) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{        Class.forName("com.mysql.jdbc.Driver");        Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?user=root&password=budhrani");        Statement stmt=connect.createStatement();        ResultSet rs = null;        String line = "";        BufferedReader br = new BufferedReader(new FileReader(path));          String name = "";        while ((line = br.readLine()) != null){              String[] arr = line.split(",");            if(arr.length == 1){                name = arr[0];            }            else{                String query = "insert into "+name+" values ("+line+");";                stmt.executeUpdate(query);            }         }    }}