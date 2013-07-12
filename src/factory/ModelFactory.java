package factory;

import java.util.List;

/**
 * @author Karl Merkli
 */
public interface ModelFactory<ModelClass> {

	/**
	 * Get model
	 * @return 
	 */
	List<ModelClass> all();
	
	/**
	 * Get model with id
	 * @param aId
	 * @return
	 */
	ModelClass find(int aId);
	
	/**
	 * Get row with specific value
	 * @param aCol
	 * @param aOperator
	 * @param aValue
	 * @return
	 */
	List<ModelClass> where(String aCol, String aOperator, String aValue);
}
