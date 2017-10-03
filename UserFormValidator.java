package halkom.model.validators;

import org.springframework.validation.Validator;

import halkom.web.forms.UserForm;

import org.springframework.validation.Errors;


public class UserFormValidator implements Validator {
   


    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
    	UserForm uf = (UserForm) obj;
        if (uf == null) {
            errors.rejectValue("ime", "error.not-specified", null, "Value required.");
        }
        else {
            
            if (uf.getImeb() == null || ("").equals(uf.getImeb())) {
            	errors.rejectValue("imeb", "error.not-specified", null, "Value required.");
                    
            }
            if (uf.getImed() == null || ("").equals(uf.getImed())) {
            	errors.rejectValue("imed", "error.not-specified", null, "Value required.");
                    
            }
            if (uf.getRacund() == null || ("").equals(uf.getRacunb())) {
            	errors.rejectValue("racund", "error.not-specified", null, "Value required.");
                    
            }
            if (uf.getRacunb() == null || ("").equals(uf.getRacund())) {
            	errors.rejectValue("racunb", "error.not-specified", null, "Value required.");
                    
            }
        }
    }

    

}
