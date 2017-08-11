package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import dao.ImportManager;

/**
 * Servlet implementation class ImportServlet
 */
@WebServlet(
        name = "ImportServlet",
        description = "Servlet handels the File import",
        urlPatterns = "/import"
)


@MultipartConfig
public class ImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private List<List<String>> fileArray = new ArrayList<List<String>>();
	private int rowi = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("import"); // Retrieves <input type="file" name="import">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
	    InputStream fileContent = filePart.getInputStream();
	    
	    // TODO Get persnr
	   	    
	   //Get Datei 
//	    response.getWriter().append("----------------------IMPORT DEBUG ---------------------------\n")
//	    .append("File Name: "+fileName+"\n")
//	    .append("File Format: "+filePart.getContentType());
    
	    Workbook workbook = new HSSFWorkbook(fileContent); //HSSF = 2003 / XSSF = 2007+
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	    while (iterator.hasNext()) {
		    Row nextRow = iterator.next();
		    Iterator<Cell> cellIterator = nextRow.cellIterator();
		    fileArray.add(new ArrayList<String>());
		    while (cellIterator.hasNext()) {
			    Cell cell = cellIterator.next();
			    switch (cell.getCellType()) {
				    case Cell.CELL_TYPE_STRING:
//				    	System.out.print(cell.getStringCellValue());
				    	fileArray.get(rowi).add(cell.getStringCellValue());
				    	break;
				    case Cell.CELL_TYPE_BOOLEAN:
//				    	System.out.print("*"+cell.getBooleanCellValue());
				    	fileArray.get(rowi).add(String.valueOf(cell.getBooleanCellValue()));

				    	break;
				    case Cell.CELL_TYPE_NUMERIC:
//				    	System.out.print("#"+cell.getNumericCellValue());
				    	fileArray.get(rowi).add(String.valueOf(cell.getNumericCellValue()));
				    	break;
				 }
//			    System.out.print(" | ");
		    }
		    rowi++;
//		    System.out.println(" -- ");
	    }
	    workbook.close();
	    fileContent.close();
//	    String persnr = (String) request.getAttribute("persnr");
	    
		request.setAttribute("import", insertIntoDatabase(fileArray));
		request.getRequestDispatcher("./intern/import.jsp").forward(request, response);
	}
	
	private String insertIntoDatabase(List<List<String>> fields){
		fields.remove(0);
		fields.remove(0);
		fields.remove(0);
		fields.remove(fields.size()-1);
		
		Iterator<List<String>> fieldArray = fields.iterator();
		Boolean check = true;
		while(fieldArray.hasNext()){
			List<String> nextRow = fieldArray.next();
			Iterator<String> data = nextRow.iterator();
			ImportManager importE = new ImportManager(data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next(), data.next());
			System.out.println(importE.toString());
			if(check){
				if(!importE.insertCheckStatic()){
					System.err.println("User nicht vorhanden bitte neu versuchen");
					return "false";
				}
			}
			importE.insertCheckVariable();
			return "true";
		}
		return "true";
	}
}
