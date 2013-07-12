package factory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

/**
 * @author Karl Merkli
 */
public class CategoryFactory extends Factory implements ModelFactory<Category>{

	public CategoryFactory() {
		this.fTableName = new Category().getTable();
	}
	
	@Override
	public List<Category> all() {
		ArrayList<Category> lList = new ArrayList<Category>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Category lCategory = new Category();
				lCategory.id = lResult.getInt("id");
				lCategory.name = lResult.getString("name");
				
				lList.add(lCategory);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public Category find(int aId) {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, aId);
			ResultSet lResult = lStatement.executeQuery();
			lResult.first();
			
			Category lCategory = new Category();
			lCategory.id = lResult.getInt("id");
			lCategory.name = lResult.getString("name");
			
			return lCategory;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public List<Category> where(String aCol, String aOperator, String aValue) {
		ArrayList<Category> lList = new ArrayList<Category>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where " + aCol + " " + aOperator + " ?");
			lStatement.setString(1, aValue);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Category lCategory = new Category();
				lCategory.id = lResult.getInt("id");
				lCategory.name = lResult.getString("name");
				
				lList.add(lCategory);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}
}
