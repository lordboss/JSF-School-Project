package services;

import java.util.List;

/**
 * @author Karl Merkli
 */
public abstract class Validator {
	
	public abstract boolean passes();
	public abstract List<String> error();
}
