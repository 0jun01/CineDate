package com.tenco.movie.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FileDAO {
	private Connection conn;
	
	public FileDAO() {

		try {

			String dbURL = "jdbc:mysql://192.168.0.46/cinedate?serverTimezone=Asia/Seoul";

			String dbID = "jyj";

			String dbPW = "asd123";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPW);

		} catch(Exception e) {

			e.printStackTrace();

		}

	}
	
	public int upload(String fileName, String fileRealName) {

		String SQL = "INSERT INTO event_tb VALUES (?, ?) where id = ?";

		try {

			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1,  fileName);

			pstmt.setString(2,  fileRealName);

			return pstmt.executeUpdate();

		} catch(Exception e) {

			e.printStackTrace();

		}

		return -1;

	}
}
