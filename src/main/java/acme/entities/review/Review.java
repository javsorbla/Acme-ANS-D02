
package acme.entities.review;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.entities.airline.Airline;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends AbstractEntity {
	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Mandatory Attributes -------------------------------------------------------------

	@Mandatory
	@ValidString(max = 50) // min = 1 porque es obligatorio?
	@Automapped
	private String				name;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	@Mandatory
	@ValidString(max = 50) // min=1 porque es obligatorio?
	@Automapped
	private String				subject;

	@Mandatory
	@ValidString(max = 255)  // max=255 no hace falta
	@Automapped
	private String				text;

	// @Optional Attributes -------------------------------------------------------------

	@Optional
	@ValidNumber(min = 0, max = 10)
	@Automapped
	private double				score; //double o Double?

	@Optional
	//@Valid  no funciona con boolean
	@Automapped
	private boolean				recommended; //boolean o Boolean?

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne
	private Airline				airline; // sobra?

	//	@Mandatory
	//	@Valid
	//	@ManyToOne
	//	private Service				service; sobra?

	//	@Mandatory
	//	@Valid
	//	@ManyToOne
	//	private Airport				airport; sobra?
}
