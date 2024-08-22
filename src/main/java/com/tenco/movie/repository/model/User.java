package com.tenco.movie.repository.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
	
	private String loginId;
	private String name;
	private String password;
	private String email;
	private int phoneNum;
	private Date birthDay;
	private String gender;
	
	/*
	 * public void setBirthDay(String getFormattedBirthDay) { SimpleDateFormat sdf =
	 * new SimpleDateFormat("yyyy-MM-dd"); // Match the format used in DTO try { try
	 * { this.birthDay = (Date) sdf.parse(getFormattedBirthDay); } catch
	 * (java.text.ParseException e) { e.printStackTrace(); } } catch (ParseException
	 * e) { e.printStackTrace(); } }
	 */

}
