package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.bean.StudentBean;
import com.util.DBConnection;

public class StudentDao
{
	public static int insertStudent(StudentBean sbean)
	{
		int rowsAffected = 0;

		String insertQuery = "INSERT INTO ST_CRUD(NAME, STD, MARKS) VALUES (?, ?, ?)";
		System.out.println("insertQuery : " + insertQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				PreparedStatement pstmt = conn.prepareStatement(insertQuery);

				pstmt.setString(1, sbean.getName());
				pstmt.setInt(2, sbean.getStd());
				pstmt.setInt(3, sbean.getMarks());

				rowsAffected = pstmt.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("StudentDao :: insertStudent() :: DB not connected");
		}

		return rowsAffected;
	}

	public static int updateStudent(StudentBean sbean)
	{
		int rowsAffected = 0;

		String updateQuery = "UPDATE ST_CRUD SET NAME = ?, STD = ?, MARKS = ? WHERE RNO = ?";
		System.out.println("updateQuery : " + updateQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				PreparedStatement pstmt = conn.prepareStatement(updateQuery);

				pstmt.setString(1, sbean.getName());
				pstmt.setInt(2, sbean.getStd());
				pstmt.setInt(3, sbean.getMarks());
				pstmt.setInt(4, sbean.getRno());

				rowsAffected = pstmt.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("StudentDao :: updateStudent() :: DB not connected");
		}
		return rowsAffected;
	}

	public int deleteStudent(int rno)
	{
		int rowsAffected = 0;

		String deleteQuery = "DELETE FROM ST_CRUD WHERE RNO = ?";
		System.out.println("deleteQuery : " + deleteQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

				pstmt.setInt(1, rno);

				rowsAffected = pstmt.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("prepareStatement : StudentDao deleteStudent() :: DB not connected");
		}

		return rowsAffected;
	}

	public ArrayList<StudentBean> getAllStudents()
	{
		ArrayList<StudentBean> list = new ArrayList<StudentBean>();
		StudentBean sbean = null;

		String selectQuery = "SELECT * FROM ST_CRUD";
		System.out.println("selectQuery : " + selectQuery);

		Connection conn = DBConnection.getConnection();

		if (conn != null)
		{
			try
			{
				PreparedStatement pstmt = conn.prepareStatement(selectQuery);

				ResultSet result = pstmt.executeQuery();

				System.out.println("\n--> Using ResultSet");
				System.out.println("RNO\tNAME\tSTD\tMARKS");
				System.out.println("---\t----\t---\t-----");
				while (result.next())
				{
					int rno = result.getInt(1);
					String name = result.getString(2);
					int std = result.getInt(3);
					int marks = result.getInt(4);
					System.out.println(rno + "\t" + name + "\t" + std + "\t" + marks);

					sbean = new StudentBean(rno, name, std, marks);
					list.add(sbean);
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("prepareStatement : StudentDao getAllStudents() :: DB not connected");
		}

		return list;
	}

	// get Student record by id 
	public StudentBean getStudentByRno(int rno1)
	{
		String selectStudentByRno = "SELECT * FROM ST_CRUD WHERE RNO = ?";
	
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentBean sbean = null;

		try
		{
			if (conn != null)
			{
				pstmt = conn.prepareStatement(selectStudentByRno);
				pstmt.setInt(1, rno1);

				rs = pstmt.executeQuery();
				if (rs.next())
				{
					// Map ResultSet to StudentBean
					int rno = rs.getInt("RNO");
					String name = rs.getString("NAME");
					int std = rs.getInt("STD");
					int marks = rs.getInt("MARKS");

					sbean = new StudentBean(rno, name, std, marks);
				} else
				{
					System.out.println("Student record not found for Roll Number: " + rno1);
				}
			} else
			{
				System.out.println("Database connection failed.");
			}
		} catch (SQLException e)
		{
			System.err.println("Error while fetching student record: " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			// Close resources to avoid memory leaks
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();					
				}	
				if (conn != null)
				{
					conn.close();
				}	
				
			} catch (SQLException e)
			{
				System.err.println("Error while closing resources: " + e.getMessage());
			}
		}
		return sbean; // Return StudentBean or null if not found
	}

}
	//	public Object getStudentByRno(int rno1)
	//	{
	//		String selectStudentByRno = "SELECT * FROM ST_CRUD WHERE RNO = ?";
	//		Connection conn = DBConnection.getConnection();
	//		PreparedStatement pstmt = null;
	//		ResultSet rs = null;
	//		StudentBean sbean = null;
	//
	//		if (conn != null)
	//		{
	//			try
	//			{
	//				pstmt = conn.prepareStatement(selectStudentByRno);
	//
	//				pstmt.setInt(1, rno1);
	//
	//				rs = pstmt.executeQuery();
	//				if (rs.next())
	//				{
	//					int rno = rs.getInt(1);
	//					String name = rs.getString(2);
	//					int std = rs.getInt(3);
	//					int marks = rs.getInt(4);
	//
	//					sbean = new StudentBean(rno, name, std, marks);
	//
	//					return sbean;
	//				} else
	//				{
	//					System.out.println("Student record not found");
	//					return false;
	//				}
	//			} catch (SQLException e)
	//			{
	//				e.printStackTrace();
	//			}
	//		} else
	//		{
	//			System.out.println("Db not conncected");
	//		}
	//		return false;
	//	}
