package lpoo.estudiodanca.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errors = new HashMap<>();
	
	public ValidationExcpetion(String msg) {
		super(msg);
	}
	public Map<String, String> getErrors(){
		return errors;
	}
	
	public void addError(String fieldName, String errorMessege) {
		errors.put(fieldName, errorMessege);
	}
}
