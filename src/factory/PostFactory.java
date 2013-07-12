package factory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

/**
 * @author Karl Merkli
 */
public class PostFactory extends Factory implements ModelFactory<Post>{
	
	public PostFactory() {
		this.fTableName = new Post().getTable();
	}
	
	@Override
	public List<Post> all() {
		ArrayList<Post> lList = new ArrayList<Post>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Post lPost = new Post();
				lPost.id = lResult.getInt("id");
				lPost.title = lResult.getString("title");
				lPost.content = lResult.getString("content");
				lPost.content_compiled = lResult.getString("content_compiled");
				lPost.user_id = lResult.getInt("user_id");
				lPost.category_id = lResult.getInt("category_id");
				
				lList.add(lPost);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public Post find(int aId) {
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where id = ?");
			lStatement.setInt(1, aId);
			ResultSet lResult = lStatement.executeQuery();
			lResult.first();
			
			Post lPost = new Post();
			lPost.id = lResult.getInt("id");
			lPost.title = lResult.getString("title");
			lPost.content = lResult.getString("content");
			lPost.content_compiled = lResult.getString("content_compiled");
			lPost.user_id = lResult.getInt("user_id");
			lPost.category_id = lResult.getInt("category_id");
			
			return lPost;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}

	@Override
	public List<Post> where(String aCol, String aOperator, String aValue) {
		ArrayList<Post> lList = new ArrayList<Post>();
		
		try {
			PreparedStatement lStatement = fConnection.prepareStatement("select * from " + this.fTableName + " where " + aCol + " " + aOperator + " ?");
			lStatement.setString(1, aValue);
			ResultSet lResult = lStatement.executeQuery();
			
			while (lResult.next()) {
				Post lPost = new Post();
				lPost.id = lResult.getInt("id");
				lPost.title = lResult.getString("title");
				lPost.content = lResult.getString("content");
				lPost.content_compiled = lResult.getString("content_compiled");
				lPost.user_id = lResult.getInt("user_id");
				lPost.category_id = lResult.getInt("category_id");
				
				lList.add(lPost);
			}
			
			return lList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}
}
