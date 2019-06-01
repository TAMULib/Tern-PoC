package edu.tamu.app.model.validation;

import edu.tamu.weaver.validation.model.InputValidationType;
import edu.tamu.weaver.validation.validators.BaseModelValidator;
import edu.tamu.weaver.validation.validators.InputValidator;

public class CustomProcessorValidator extends BaseModelValidator {

    public CustomProcessorValidator() {
    	
    	String nameProperty = "name";
        this.addInputValidator(new InputValidator(InputValidationType.required, "A Custom Processor requires a name", nameProperty, true));
        this.addInputValidator(new InputValidator(InputValidationType.minlength, "Names must be at least 2 charachters long.", nameProperty, 2));
        
        String logicProperty = "logic";
        this.addInputValidator(new InputValidator(InputValidationType.required, "A Custom Processor requires logic", logicProperty, true));
        this.addInputValidator(new InputValidator(InputValidationType.minlength, "Custom Processor logic must be at least 2 charachters long.", logicProperty, 9));
        //this.addInputValidator(new InputValidator(InputValidationType.pattern, "A Custom Processor's logic cannot contain a return statement", logicProperty, ));
        
    }
}