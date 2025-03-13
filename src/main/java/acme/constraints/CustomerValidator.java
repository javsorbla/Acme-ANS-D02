
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.principals.DefaultUserIdentity;
import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.StringHelper;
import acme.realms.customer.Customer;
import acme.realms.customer.CustomerRepository;

public class CustomerValidator extends AbstractValidator<ValidCustomer, Customer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	CustomerRepository repository;

	// Initialiser ------------------------------------------------------------


	@Override
	public void initialise(final ValidCustomer annotation) {
		assert annotation != null;
	}

	// AbstractValidator interface --------------------------------------------

	@Override
	public boolean isValid(final Customer customer, final ConstraintValidatorContext context) {
		// HINT: value can be null
		assert context != null;

		boolean result;

		if (customer == null || customer.getIdentifier() == null || customer.getIdentity() == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else if (StringHelper.isBlank(customer.getIdentifier()))
			super.state(context, false, "identifier", "javax.validation.constraints.NotBlank.message");
		else {

			boolean uniqueCustomer;
			Customer existingCustomer;

			existingCustomer = this.repository.findCustomerByIdentifier(customer.getIdentifier());
			uniqueCustomer = existingCustomer == null || existingCustomer.equals(customer);
			super.state(context, uniqueCustomer, "identifier", "acme.validation.customer.identifier.duplicated.message");

			boolean containsInitials;
			DefaultUserIdentity identity = customer.getIdentity();
			char nameFirstLetter = identity.getName().charAt(0);
			char surnameFirstLetter = identity.getSurname().charAt(0);
			String initials = "" + nameFirstLetter + surnameFirstLetter;
			// Solution without using the framework helper
			//containsInitials = customer.getIdentifier().charAt(0) == nameFirstLetter && customer.getIdentifier().charAt(1) == surnameFirstLetter;
			containsInitials = StringHelper.startsWith(customer.getIdentifier(), initials, false); //Checks if identifier starts with the 2 initials
			super.state(context, containsInitials, "identifier", "acme.validation.customer.identifier.message");
		}

		result = !super.hasErrors(context);
		return result;
	}
}
