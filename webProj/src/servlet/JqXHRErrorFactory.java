package servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * Class handles Exceptions and Error Messages. Converts them to normal text and modifies the response
 * header to an error code. This will be received clientside and has to be processed
 * 
 * @author Robert Kuti, INF, WIN
 * @version 1.0, 2016-12-07
 */
public class JqXHRErrorFactory {

	public static final int DEFAULT_STATUS = 444;
	public static final String DEFAULT_MESSAGE = "An Error occured";
	
	/**
	 * Sends the error message, sets an status code and the text itself.
	 * Needs the HttpServlet Response object
	 * Any status code above 200 is allowed, if not given or smaller, default will be set
	 * If no message is given, default will be used
	 * 
	 * @param response, the http servlet response object
	 * @param status, the status number / code
	 * @param message, the message to be sent
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response, int status, String message) {
		if (status < 200) status = DEFAULT_STATUS;
		if (message == null) message = DEFAULT_MESSAGE;
		if (message.isEmpty()) message = DEFAULT_MESSAGE;
		
		response.setStatus(status);
		try {
			response.getWriter().write(message);
		} catch (IOException e) {
			// can`t do anything, if exception occures here, user is not informed
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Sends a default status code and error message
	 * Needs the HttpServlet Response object
	 * 
	 * @param response, the http servlet response object
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response) {
		return Send(response, DEFAULT_STATUS, DEFAULT_MESSAGE);
	}
	
	/**
	 * Sends a status code and error message
	 * Needs the HttpServlet Response object
	 * Any status code above 200 is allowed, if not given or smaller, default will be set
	 * 
	 * @param response, the http servlet response object
	 * @param status, the status number / code
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response, int status) {
		return Send(response, status, DEFAULT_MESSAGE);
	}

	/**
	 * Sends the error message
	 * Needs the HttpServlet Response object
	 * If no message is given, default will be used
	 * Status code is set to default value
	 * 
	 * @param response, the http servlet response object
	 * @param message, the message to be sent
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response, String message) {
		return Send(response, DEFAULT_STATUS, message);
	}
	
	/**
	 * Sends the error message
	 * Creates out of an exception the message
	 * Needs the HttpServlet Response object and the exception
	 * 
	 * @param response, the http servlet response object
	 * @param exception, the exception which is used to create the message
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response, Exception exception) {
		return Send(response, DEFAULT_STATUS, exception);
	}
	
	/**
	 * Sends the error message
	 * Creates out of an exception the message, also sets the status code
	 * Needs the HttpServlet Response object and the exception
	 * Any status code above 200 is allowed, if not given or smaller, default will be set
	 * 
	 * @param response, the http servlet response object
	 * @param status, the status number / code
	 * @param exception, the exception which is used to create the message
	 * @return true if ok, otherwise false
	 */
	public static boolean Send(HttpServletResponse response, int status, Exception exception) {
		String msg = "ERROR: An Exception occured";
		
		if (exception != null) {
			msg += "\n";
			msg += exception.getLocalizedMessage() + "\n";
			StackTraceElement[] lines = exception.getStackTrace();
			for (int i=0; i<lines.length; i++) {
				msg += lines[i].toString() + "\n";
			}
		}
		
		return Send(response, status, msg);
	}
}
