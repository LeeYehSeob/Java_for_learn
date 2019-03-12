package net.product.model;


import java.util.ArrayList;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAOImpl extends DAOBase implements ProductDAO{


	@Override
	public int create(ProductVO vo) throws SQLException {
		String code = vo.getP_code();
		String pname = vo.getP_name();
		int cost = vo.getCost();
		int pnum = vo.getPnum();
		int inum = vo.getInum();
		int sale = vo.getSale();
		String gcode = vo.getG_code();
		
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		
		int result=statement.executeUpdate("INSERT INTO product VALUES ('"+code+"','"+pname+"',"+cost+" ,"+pnum+" ,"+inum+" ,"+sale+" , '"+gcode+"')");
		conn.commit();
		super.closeDBResources(null, statement, conn);
		
		return result;
	}


	@Override
	public ProductVO readOne(ProductVO vo_) throws SQLException {
		ProductVO vo = new ProductVO();
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		String code = vo_.getP_code();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE code = '"+code+"'");
		if(resultSet.next()) {
			vo.setP_code(resultSet.getString("code"));
			vo.setP_name(resultSet.getString("pname"));
			vo.setCost(resultSet.getInt("cost"));
			vo.setPnum(resultSet.getInt("pnum"));
			vo.setInum(resultSet.getInt("inum"));
			vo.setSale(resultSet.getInt("sale"));
			vo.setG_code(resultSet.getString("gcode"));
		}
		return vo;
	}


	@Override
	public ArrayList<ProductVO> readList() throws SQLException {
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		
		
		while(resultSet.next()) {
			ProductVO vo = new ProductVO();
			vo.setP_code(resultSet.getString("code"));
			vo.setP_name(resultSet.getString("pname"));
			vo.setCost(resultSet.getInt("cost"));
			vo.setPnum(resultSet.getInt("pnum"));
			vo.setInum(resultSet.getInt("inum"));
			vo.setSale(resultSet.getInt("sale"));
			vo.setG_code(resultSet.getString("gcode"));
			
			list.add(vo);
		}
		
		super.closeDBResources(resultSet, statement, conn);
		return list;
	}
	
	
	public ArrayList<GroupcodeVO> readGroupcodeList( )throws SQLException {
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		ArrayList<GroupcodeVO> list = new ArrayList<GroupcodeVO>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM groupcode");
		
		
		
		while(resultSet.next()) {
			GroupcodeVO vo = new GroupcodeVO();
			vo.setG_code(resultSet.getString("gcode"));
			vo.setG_name(resultSet.getString("gname"));
			
			list.add(vo);
		}
		super.closeDBResources(resultSet, statement, conn);
		return list;
	}

	@Override
	public int update(ProductVO vo) throws SQLException {
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		
		String code = vo.getP_code();
		String pname = vo.getP_name();
		int cost = vo.getCost();
		int pnum = vo.getPnum();
		int inum = vo.getInum();
		int sale = vo.getSale();
		String gcode = vo.getG_code();
		
		int result = statement.executeUpdate(
						"UPDATE product "+
						"SET pname='"+pname+"',cost='"+cost+"',pnum='"+pnum+"',inum='"+inum+"',sale='"+sale+"',gcode='"+gcode+"'"+ 
						"WHERE code='"+code+"'");
		conn.commit();
		super.closeDBResources(null, statement, conn);
		return result;
	}


	@Override
	public int delete(ProductVO vo) throws SQLException {
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		String code = vo.getP_code();
		int result = statement.executeUpdate("DELETE FROM product WHERE code='"+code+"'");
		conn.commit();
		super.closeDBResources(null, statement, conn);
		return result;
	}
	
	public int checkGroupcodeTable()throws SQLException {
		Connection conn = super.getConnection();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("SLELCT count(gcode) FROM groupcode");
		resultSet.next();
		int result = resultSet.getInt(1);
		
		super.closeDBResources(resultSet, statement, conn);
		return result;
	}
}
