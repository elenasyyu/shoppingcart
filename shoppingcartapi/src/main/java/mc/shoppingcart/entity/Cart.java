package mc.shoppingcart.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CART")
public class Cart implements Serializable {
	private static final long serialVersionUID = -6205364458625455917L;

	public Cart () {	
	}
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "sequence-generator")
	@GenericGenerator(
		name = "sequence-generator",
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
			@Parameter(name = "sequence_name", value = "CART_SEQ"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1")
		}
	)
	private int id;
	
	@NotEmpty
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> cartItems = new HashSet<CartItem>();	
}
