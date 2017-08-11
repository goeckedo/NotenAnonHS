package servlet;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;

import dao.ExamDetailManager;
import entity.ExamDetails;
import entity.Faculty;
import entity.Student;


@WebServlet(
		name="ExportServlet",
		description="Export handels the file of the Export",
		urlPatterns="/export"
)
public class ExportServlet extends HttpServlet{
	 /**
     * @see HttpServlet#HttpServlet()
     */
	private static final long serialVersionUID = 1L;
	
	ExamDetailManager managered = new ExamDetailManager("webProj");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Collection<ExamDetails> edCol = managered.getEDByPidName(id, name);
		Iterator<ExamDetails> edIter = edCol.iterator();
		int x=5;
		
		
//		Erstellen der xls datei:
		try {
            String filename = "C:\\Users\\Dodo\\Desktop\\prf_id_abschl_token__pversion_pordnr_semester_02.xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("First Sheet");  

//			Erstellen von weiteren Zeilen
            HSSFRow row3 = sheet.createRow((short)3);
            row3.createCell(0).setCellValue("startHISsheet");
            row3.createCell(16).setCellValue("endHISsheet");
            
            HSSFRow row4 = sheet.createRow((short)4);
            row4.createCell(0).setCellValue("abschl");
            row4.createCell(1).setCellValue("stg");
            row4.createCell(2).setCellValue("pnr");
            row4.createCell(3).setCellValue("pversion");
            row4.createCell(4).setCellValue("vert");
            row4.createCell(5).setCellValue("sortname");
            row4.createCell(6).setCellValue("mtknr");
            row4.createCell(7).setCellValue("bewertung");
            row4.createCell(8).setCellValue("pstatus");
            row4.createCell(9).setCellValue("pdatum");
            row4.createCell(10).setCellValue("pversuch");
            row4.createCell(11).setCellValue("labnr");
            row4.createCell(12).setCellValue("semester");
            row4.createCell(13).setCellValue("pordnr");
            row4.createCell(14).setCellValue("porgnr");
            row4.createCell(15).setCellValue("bonus");
            row4.createCell(16).setCellValue("malus");
            
            
            while(edIter.hasNext()){
    			ExamDetails edInfo = edIter.next();
    			edInfo.getVert();
    			edInfo.getBewertung();
    			edInfo.getPstatus();
    			edInfo.getPversuch();
    			edInfo.getLabnr();
    			edInfo.getPordnr();
    			edInfo.getPorgnr();
    			edInfo.getBonus();
    			edInfo.getMalus();
    			edInfo.getStud();
    			edInfo.getStud();
    			edInfo.getExam();
    			
//			Creating Header
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue(edInfo.getExam().getpNR()+" "+edInfo.getExam().getSortname()+" "+edInfo.getStud().getAbschl()+" "+ edInfo.getExam().getFaculty().getToken() +" "+edInfo.getExam().getPversion()+" "+edInfo.getPordnr());

//			Dreating Table
            HSSFRow rowy = sheet.createRow((short)x);
            rowy.createCell(0).setCellValue(edInfo.getStud().getAbschl());
            rowy.createCell(1).setCellValue(edInfo.getExam().getFaculty().getToken());
            rowy.createCell(2).setCellValue(edInfo.getExam().getpNR());
            rowy.createCell(3).setCellValue(edInfo.getExam().getPversion());
            rowy.createCell(4).setCellValue(edInfo.getVert());
            rowy.createCell(5).setCellValue(edInfo.getExam().getSortname());
            rowy.createCell(6).setCellValue(edInfo.getStud().getMtknr());
            rowy.createCell(7).setCellValue(edInfo.getBewertung());
            rowy.createCell(8).setCellValue(edInfo.getPstatus());
            rowy.createCell(9).setCellValue(edInfo.getExam().getPdatum());
            rowy.createCell(10).setCellValue(edInfo.getPversuch());
            rowy.createCell(11).setCellValue(edInfo.getLabnr());
            rowy.createCell(12).setCellValue(edInfo.getStud().getSemester());
            rowy.createCell(13).setCellValue(edInfo.getPordnr());
            rowy.createCell(14).setCellValue(edInfo.getPorgnr());
            rowy.createCell(15).setCellValue(edInfo.getBonus());
            rowy.createCell(16).setCellValue(edInfo.getMalus());
            
//			Creating the Finish of Table
            HSSFRow rowx = sheet.createRow((short)x+1);
            x++;
            rowx.createCell(0).setCellValue("endHISsheet");
            }

//			Finish the xls            
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");
            
//          Code Teil der für den Download zuständig ist/sein soll.
            File fileToDownload = new File(filename);
            InputStream in = new FileInputStream(fileToDownload);

//          Erkennt was für eine Datei runtergeladen wird
            String mimeType = new MimetypesFileTypeMap().getContentType(filename);

            if (mimeType == null) {
//			Setzt den Datentyp auf Binär falls kein mime-typ erkannt wird.
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

//          Modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) fileToDownload.length());

//          Donwload "erzwingen"
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileToDownload.getName());
            response.setHeader(headerKey, headerValue);

//          Ausgehender Datenstrom für Java
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = in.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            in.close();
            outStream.close();

            System.out.println("File downloaded at client successfully");
        } catch ( Exception ex ) {
           ex.printStackTrace();
        }
	
	}

	public Collection<ExamDetails> getExamDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
