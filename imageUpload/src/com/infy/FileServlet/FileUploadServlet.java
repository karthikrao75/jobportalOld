package com.infy.FileServlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 *4;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024*2;

	// Create a factory for disk-based file items
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		if (isMultiPart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Sets the size threshold beyond which files are written directly
			// to
			// disk.
			//factory.setSizeThreshold(MAX_MEMORY_SIZE);

			// Sets the directory used to temporarily store files that are
			// larger
			// than the configured size threshold. We use temporary directory
			// for
			// java
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload1 = new ServletFileUpload(factory);
			
			// Set overall request size constraint
			//upload1.setSizeMax(MAX_REQUEST_SIZE);

			String path = "E:\\eclipseWorkplace\\imageUpload\\WebContent\\images\\";
			try {
				List fileItems = upload1.parseRequest(request);
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						File savedFile = new File(path + new File(fi.getName()).getName());
						fi.write(savedFile);
						response.getWriter()
								.println("Image : " + new File(fi.getName()).getName() + " Uploaded Successfully");
					}
				}
			} catch (Exception e) {
				response.getWriter().println(e);
			}
		}
	}

}
