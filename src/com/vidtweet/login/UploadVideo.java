package com.vidtweet.login;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;


/**
 * Servlet implementation class FileUploadHandler
 */
public class UploadVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String bucketName = "com.tt.videobucket";
	private static String keyName;
	File tempFile;
	Boolean uploadedToS3=false;
	Boolean uploadedToEc2=false;
	ResultSet res=null;
	int convId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadVideo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	private final String UPLOAD_DIRECTORY = "/Users/harshavyaspalli/upload";
	  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    	try {
			
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					keyName = item.getName();
					System.out.println("New broadcast Msg keyname:"+keyName);
					InputStream is = item.getInputStream();
					tempFile = new File(keyName);
					OutputStream outputStream = new FileOutputStream(tempFile);

					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = is.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
					}
				}
			}
			uploadedToEc2 = true;
			DBConnect conn=new DBConnect();
			Statement statement=conn.getNewStatement();
			
			} catch (Exception e) {
				System.out.println(e);
			}
		
		AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
		AmazonS3 s3client = new AmazonS3Client(credentialsProvider);
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            
            AccessControlList acl = new AccessControlList();
            acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);             	
            s3client.putObject(new PutObjectRequest(bucketName, keyName, tempFile).withAccessControlList(acl));
            uploadedToS3 = true;
         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        if(uploadedToEc2 && uploadedToS3)
        {
        	DBConnect conn=new DBConnect();
        	Statement statement=conn.getNewStatement();
        	ResultSet res_max=null;
        	int count=0;
        	String fetchMaxObjId="select max(obj_id) from object_tbl";
        	HttpSession session=request.getSession(false);
        	String user_name=(String)session.getAttribute("user");
        	convId=(int)session.getAttribute("conv_id");
        
        	try
        	{
    		
        		res_max=statement.executeQuery(fetchMaxObjId);
        		while(res_max.next())
        		{
        			count=res_max.getInt(1);
        		}
        		count++;
        	} 
        	catch (SQLException e)
        	{
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        
        	try {
        		String insertObjectQuery="insert into object_tbl values ("+count+",'"+keyName+"',"+"CURRENT_TIMESTAMP)";
        		statement.executeUpdate(insertObjectQuery);
        		String insertBelongstoQuery="insert into belongsto values (" + convId + "," +count+")";
        		statement.executeUpdate(insertBelongstoQuery);
        		String updateConversationQuery="update conversation set last_mod_date=CURRENT_TIMESTAMP where conv_id="+convId;
        		statement.executeUpdate(updateConversationQuery);
        		String insertAddedByQuery="insert into addedby values ('"+user_name+"',"+count+")";
        		statement.executeUpdate(insertAddedByQuery);
        		
        		
        		String currentObjectsQuery ="select a.uname,temp_tab.oname, temp_tab.oid, temp_tab.odate from auth a "
        				+ " inner join addedby ab on a.uname=ab.uname inner join ("
        				+ " select o.obj_name as oname, o.obj_id as oid, o.obj_create_date as odate"
        				+ " from 	object_tbl o inner join belongsto b on b.obj_id=o.obj_id "
        				+ "inner join conversation c on c.conv_id=b.conv_id "
        				+ "where c.conv_id=" +convId +") as temp_tab on temp_tab.oid=ab.obj_id order by temp_tab.odate DESC";
        		try 
        		{
        			res = (ResultSet)statement.executeQuery(currentObjectsQuery);
        			request.setAttribute("objectres", res);
        			request.setAttribute("conv_name", "");
        		} catch (SQLException e) 
        		{
        			e.printStackTrace();
        		}
        		finally
                {
                	try {
        				if (res != null) {
        					res.close();
        				}

        				if (statement != null) {
        					statement.close();
        				}
        				if (conn.connect != null) {
        					conn.close_conn();
        				}
        			} catch (Exception e) {

        			}
                }
			
        	} catch (SQLException e) {
			// TODO Auto-generated catch block
        		e.printStackTrace();
		}
     }
        
        request.setAttribute("qwerty", convId);
        request.getRequestDispatcher("FriendConversation").forward(request,response);
	}
    

}
