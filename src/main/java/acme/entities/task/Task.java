
package acme.entities.task;

import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.entities.maintenanceRecord.MaintenanceRecord;
import acme.realms.Technician;

public class Task extends AbstractEntity {
	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@Automapped
	private TaskType			type;

	@Mandatory
	@ValidString(max = 255)
	private String				description;

	@Mandatory
	@ValidNumber(min = 0, max = 10)
	@Automapped
	private Integer				priority;

	@Mandatory
	@ValidNumber(min = 0.)
	@Automapped
	private Double				estimatedDuration;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne
	private Technician			technician;

	@Mandatory
	@Valid
	@ManyToOne
	private MaintenanceRecord	maintenanceRecord;
}
