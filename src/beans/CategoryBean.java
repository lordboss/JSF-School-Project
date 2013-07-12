package beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.Category;
import services.CategoryValidator;
import factory.CategoryFactory;

@ManagedBean
@ViewScoped
public class CategoryBean {
	protected String name;
	
	/**
	 * Getter & Setter 
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String create() {
		return "../index.xhtml";
	}
	
	public String edit() {
		return "../index.xhtml";
	}
	
	//
	public void validate(ComponentSystemEvent event) {
		FacesContext lFacesContext = FacesContext.getCurrentInstance();
		UIComponent lComponents = event.getComponent();
		
		// get name
		UIInput lName = (UIInput) lComponents.findComponent("name");
		String lNameString = lName.getLocalValue() == null ? "" : lName.getLocalValue().toString();
		String lNameId = lName.getClientId();
		  
		Category lCategory = new Category();
		lCategory.name = lNameString;
		
		CategoryValidator lValidator = new CategoryValidator(lCategory);
		if (!lValidator.passes()) {
			for (String lMsg : lValidator.error()) {
				FacesMessage msg = new FacesMessage(lMsg);
				lFacesContext.addMessage(lNameId, msg);
			}
			lFacesContext.renderResponse();
		} else {
			lCategory.save();
		}
	}
	
	/**
	 * Model 
	 */
	public List<Category> getAllCategories(){
		CategoryFactory lFactory = new CategoryFactory();
		return lFactory.all();
	}
}
