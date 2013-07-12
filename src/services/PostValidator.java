package services;

import java.util.ArrayList;
import java.util.List;

import factory.PostFactory;

import model.Post;

/**
 * @author Karl Merkli
 */
public class PostValidator extends Validator{

	public Post Post;
	
	protected List<String> fError;
	
	public PostValidator(Post aPost){
		this.Post = aPost;
		this.fError = new ArrayList<String>();
	}
	
	@Override
	public boolean passes() {
		this.fError.clear();
		
		// title
		if (this.Post.title.isEmpty()){
			this.fError.add("Title must not be empty.");
		} else{
			List<Post> lList = new PostFactory().all();
			
			for (Post lPost : lList) {
				if  ( (lPost.title.equalsIgnoreCase(this.Post.title)) && (lPost.id != this.Post.id) ){
					this.fError.add("Title already taken.");
					break;
				}
			}
		}
		
		// content
		if (this.Post.content.isEmpty()){
			this.fError.add("Content must not be empty.");
		}
		
		return this.fError.isEmpty();
	}

	@Override
	public List<String> error() {
		return this.fError;
	}
}
