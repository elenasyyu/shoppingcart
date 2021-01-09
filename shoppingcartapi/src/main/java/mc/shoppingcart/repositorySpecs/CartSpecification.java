package mc.shoppingcart.repositorySpecs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import mc.shoppingcart.entity.Cart;

public class CartSpecification {
	private static Specification<Cart> isMatchedName(String name) {
		return (root, query, cb) -> {
			if (StringUtils.isEmpty(name)) {
				return null;
			} else {
				return cb.equal(root.get("name"), name);
			}
		};
	}
	
	private static Specification<Cart> orderById(final boolean descOrdering) {
		return (root, query, cb) -> {
			query.orderBy((descOrdering == true) ? cb.desc(root.get("id")) : cb.asc(root.get("id")));
			return cb.isNotNull(root.get("id"));
		};
	}
	
	public static Specification<Cart> ByName(final String name) {
		Specification<Cart> spec = Specification.where(null);
		
		spec = spec.and(isMatchedName(name));
		spec = spec.and(orderById(false));
		
		return spec;
	}
}
