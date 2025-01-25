package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.UserBean;
import com.util.DBConnection;

public class UserDao
{

	// CHECK EMAIL ALREADY EXISTS IN users TABLE From userSignup.jsp if email already exists then show error message
	public boolean isEmailRegistered(String email)
	{
		boolean isRegisterd = false;

		String query = "SELECT COUNT(*) FROM st_crud_users WHERE email = ?";
		System.out.println(query);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			PreparedStatement pstmt;
			try
			{
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, email);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next() && rs.getInt(1) > 0)
				{
					isRegisterd = true;
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("UserDao :: isEmailRegistered : DB not Connected : " + conn);
		}
		return isRegisterd;
	}

	// INSERT USER DETAILS INTO users TABLE From userSignup.jsp
	public int insertUser(UserBean ubean)
	{
		int rowsAffected = 0;

		String insertQuery = "INSERT INTO st_crud_users(full_name, email, password) VALUES (?, ?, ?)";
		System.out.println("insertQuery : " + insertQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				PreparedStatement pstmt = conn.prepareStatement(insertQuery);

				pstmt.setString(1, ubean.getFull_name());
				pstmt.setString(2, ubean.getEmail());
				pstmt.setString(3, ubean.getPassword());

				rowsAffected = pstmt.executeUpdate();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("UserDao :: insertUser : DB not Connected : " + conn);
		}
		return rowsAffected;
	}

	// CHECK USER LOGIN DETAILS WITH users TABLE From userLogin.jsp
	public UserBean checkUser(UserBean userBean)
	{
		UserBean ubean = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectQuery = "SELECT * FROM st_crud_users WHERE email = ? AND password = ?";
		System.out.println("selectQuery : " + selectQuery);

		conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				pstmt = conn.prepareStatement(selectQuery);

				pstmt.setString(1, userBean.getEmail());
				pstmt.setString(2, userBean.getPassword());

				rs = pstmt.executeQuery();

				if (rs.next())
				{
					ubean = new UserBean();
					ubean.setId(rs.getInt("id"));
					ubean.setFull_name(rs.getString("full_name"));
					ubean.setEmail(rs.getString("email"));
					ubean.setPassword(rs.getString("password"));
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("UserDao :: checkUser : DB not Connected : " + conn);
		}
		return ubean;
	}

	//	FORGET PASSWORD FUNCTIONALITY ==> RESETPASSWORD
	public int updatePassword(String email, String newPassword)
	{
		int rowsAffected = 0;

		String updateQuery = "UPDATE st_crud_users SET password = ? WHERE email = ?";
		System.out.println(updateQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			PreparedStatement pstmt;
			try
			{
				pstmt = conn.prepareStatement(updateQuery);

				pstmt.setString(1, newPassword);
				pstmt.setString(2, email);

				rowsAffected = pstmt.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("UserDao :: updatePassword() :: DB not connected");
		}
		return rowsAffected;
	}

}
