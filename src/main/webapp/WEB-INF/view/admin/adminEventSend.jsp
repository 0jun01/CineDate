<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DashBoard</title>
</head>
<body>
<%

		 // 해당 폴더에 이미지를 저장시킨다

  	 	 String uploadDir =this.getClass().getResource("").getPath();

		 uploadDir = uploadDir.substring(1,uploadDir.indexOf(".metadata"))+"uploadTest/WebContent/uploadImage";

		 out.println("절대경로 : " + uploadDir + "<br/>"); 

	 

		

		// 총 100M 까지 저장 가능하게 함

		int maxSize = 1024 * 1024 * 100;

		String encoding = "UTF-8";

		

		// 사용자가 전송한 파일정보 토대로 업로드 장소에 파일 업로드 수행할 수 있게 함

		MultipartRequest multipartRequest

		= new MultipartRequest(request, uploadDir, maxSize, encoding,

				new DefaultFileRenamePolicy());

		

                // 중복된 파일이름이 있기에 fileRealName이 실제로 서버에 저장된 경로이자 파일

                // fineName은 사용자가 올린 파일의 이름이다

		// 이전 클래스 name = "file" 실제 사용자가 저장한 실제 네임

		String fileName = multipartRequest.getOriginalFileName("file");

		// 실제 서버에 업로드 된 파일시스템 네임

		String fileRealName = multipartRequest.getFilesystemName("file");

	

		// 디비에 업로드 메소드

		new fileDAO().upload(fileName, fileRealName);

		out.write("파일명 : " + fileName + "<br>");

		out.write("실제파일명 : " + fileRealName + "<br>");

	%>
</body>
</html>