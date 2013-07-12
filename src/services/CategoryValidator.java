package services;

import java.util.ArrayList;
import java.util.List;

import model.Category;
import factory.CategoryFactory;

/**
 * @author Karl Merkli
 */
public class CategoryValidator extends Validator{

	public Category Category;
	
	protected List<String> fError;
	
	public CategoryValidator(Category aCategory){
		this.Category = aCategory;
		this.fError = new ArrayList<String>();
	}
	
	@Override
	public boolean passes() {
		this.fError.clear();
		
		if (this.Category.name.isEmpty()){
			this.fError.add("Name must not be empty.");
		} else{
			List<Category> lList = new CategoryFactory().all();
			
			for (Category lCategory : lList) {
				if  ( (lCategory.name.equalsIgnoreCase(this.Category.name)) && (lCategory.id != this.Category.id) ){
					this.fError.add("Name already in use.");
					break;
				}
			}
		}
		
		return this.fError.isEmpty();
	}

	@Override
	public List<String> error() {
		return this.fError;
	}
}
