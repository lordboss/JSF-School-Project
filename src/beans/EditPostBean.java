package beans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.Post;
import services.PostValidator;
import factory.PostFactory;

@ManagedBean
@ViewScoped
public class EditPostBean {
	protected String id;
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

	public EditPostBean() {
		Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.id = (String) requestMap.get("id");
		Post lPost = new PostFactory().find(Integer.parseInt(id));
		this.title = lPost.title;
		this.content = lPost.content;
		this.category_id = Integer.toString(lPost.category_id);
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
		
		Post lPost = new PostFactory().find(Integer.parseInt(id));
		lPost.title = lTitleString;
		lPost.content = lContentString;
		lPost.category_id = Integer.parseInt(lCategoryString);
		
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
}