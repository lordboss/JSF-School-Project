package beans;

import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.Post;
import services.Auth;
import services.PostValidator;
import factory.PostFactory;

@ManagedBean
@ViewScoped
public class PostBean {
	protected String title;
	protected String content;
	protected String category_id;
	
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

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
	public String create() {
		return "../index.xhtml";
	}
	
	public String edit() {
		return "../index.xhtml";
	}
	
	public void validate(ComponentSystemEvent event) {
		FacesContext lFacesContext = FacesContext.getCurrentInstance();
		UIComponent lComponents = event.getComponent();

		// get title
		UIInput lTitle = (UIInput) lComponents.findComponent("title");
		String lTitleString = lTitle.getLocalValue() == null ? "" : lTitle.getLocalValue().toString();
		
		// get content
		UIInput lContent = (UIInput) lComponents.findComponent("content");
		String lContentString = lContent.getLocalValue() == null ? "" : lContent.getLocalValue().toString();
		String lContentId = lContent.getClientId();
		
		// get category
		UIInput lCategory = (UIInput) lComponents.findComponent("category");
		String lCategoryString = lCategory.getLocalValue() == null ? "" : lCategory.getLocalValue().toString();
		
		Post lPost = new Post();
		lPost.title = lTitleString;
		lPost.content = lContentString;
		lPost.category_id = Integer.parseInt(lCategoryString);
		lPost.user_id = Auth.user().id;
		
		PostValidator lValidator = new PostValidator(lPost);
		if (!lValidator.passes()) {
			for (String lMsg : lValidator.error()) {
				FacesMessage msg = new FacesMessage(lMsg);
				lFacesContext.addMessage(lContentId, msg);
			}
			lFacesContext.renderResponse();
		} else {
			lPost.save();
		}
	}

	public List<Post> getAllPosts(){
		PostFactory lFactory = new PostFactory();
		List lList = lFactory.all();
		Collections.reverse(lList); // show newest post first
		return lList;
	}
}