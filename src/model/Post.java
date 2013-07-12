package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petebevin.markdown.MarkdownProcessor;

/**
 * @author Karl Merkli
 */
public class Post extends Model{
	
	public String title;
	public String content;
	public String content_compiled;
	public int user_id = 0;
	public int category_id = 0;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_compiled() {
		return content_compiled;
	}

	public void setContent_compiled(String content_compiled) {
		this.content_compiled = content_compiled;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public Post() {
		super();
		this.fTableName = "posts";
	}
	
	@Override
	protected boolean beforeSave(){
		MarkdownProcessor lMarkdown = new MarkdownProcessor();
		this.content_compiled = lMarkdown.markdown(this.content);
		return super.beforeSave();
	}
	
	@Override
	protected void saveAction() {
		String lSql = "";
		
		try {
			lSql = "insert into " + this.fTableName + "(title, content, content_compiled, user_id, category_id) VALUES(?,?,?,?,?)";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.title);
			lStatement.setString(2, this.content);
			lStatement.setString(3, this.content_compiled);
			lStatement.setInt(4, this.user_id);
			lStatement.setInt(5, this.category_id);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void updateAction() {
		String lSql = "";
		
		try {
			lSql = "update " + this.fTableName + " set title = ?, content = ?, content_compiled = ?, user_id = ?, category_id = ? where id = ?";
			PreparedStatement lStatement = fConnection.prepareStatement(lSql);
			lStatement.setString(1, this.title);
			lStatement.setString(2, this.content);
			lStatement.setString(3, this.content_compiled);
			lStatement.setInt(4, this.user_id);
			lStatement.setInt(5, this.category_id);
			lStatement.setInt(6, this.id);
			lStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public User user(){
		try {
			ResultSet lResult = this.hasOne("users", this.user_id);
			lResult.first();
			
			User lUser = new User();
			lUser.id = lResult.getInt("id");
			lUser.name = lResult.getString("name");
			lUser.email = lResult.getString("email");
			lUser.username = lResult.getString("username");
			lUser.password = lResult.getString("password");
			lUser.role_id = lResult.getInt("role_id");
			
			return lUser;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return null;
	}
	
	public Category category(){
		try {
			ResultSet lResult = this.hasOne("categories", this.category_id);
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
}
