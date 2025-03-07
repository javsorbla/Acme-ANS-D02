
package acme.entities.airport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidEmail;
import acme.client.components.validation.ValidString;
import acme.client.components.validation.ValidUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Airport extends AbstractEntity {
	// Serialisation version --------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------

	@Mandatory
	@ValidString(max = 50) // min a 0 por default, se pone uno distinto?
	@Automapped // Debe ser unico?
	String						name;

	@Mandatory
	@ValidString(pattern = "^[A-Z]{3}$")
	@Column(unique = true)
	String						iataCode;

	@Mandatory
	@Valid
	@Automapped
	OperationalScope			operationalScope;

	@Mandatory
	@ValidString(max = 50) // min=1 porque es mandatory?
	@Automapped
	String						city;

	@Mandatory
	@ValidString(max = 50) // min=1 porque es mandatory?
	@Automapped
	String						country;

	@Optional
	@ValidUrl
	@Column(unique = true) // De verdad tiene que ser unico?
	String						website;

	@Optional
	@ValidEmail // min=0 por ser opcional, por defecto es 1
	@Column(unique = true)
	String						email;

	@Optional
	@ValidString(pattern = "^\\+?\\d{6,15}$")
	@Column(unique = true)
	String						contactPhone;

	//Runways?

	//Derived attributes-------------------------------------------------

	// Relationships -----------------------------------------------------
}
