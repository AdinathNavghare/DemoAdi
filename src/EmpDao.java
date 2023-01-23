import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			// Class.forName("com.mysql.cj.jdbc.Driver");
			// con = DriverManager.getConnection("jdbc:mysql:localhost:3306/work", "root",
			// "root");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=work", "sa", "root");
			// System.out.println("load driver class");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static List<Emp> getAllEmployees() {
		List<Emp> list = new ArrayList<Emp>();
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from emp");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static Emp getEmpId(int id) {
		Emp e = new Emp();
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from emp where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			// TODO: handle exception
		}
		return e;
	}

	public static int update(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			// System.out.println("details=" + e);
			PreparedStatement ps = con
					.prepareStatement("update emp set name=?,password=?,email=?,country=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void delete(int id) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from emp where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean loginValidate(LoginBean bean) {
		boolean status = false;
		try {
			Connection con = EmpDao.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from users where username = ? and password = ? ");
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			// System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int save(Emp emp) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into emp(id,name,password,email,country) values(?,?,?,?,?)");
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getPassword());
			ps.setString(4, emp.getEmail());
			ps.setString(5, emp.getCountry());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

}
