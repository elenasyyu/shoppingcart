package mc.shoppingcart.repositorySpecs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import mc.shoppingcart.entity.Product;

public class ProductSpecification {
	private static Specification<Product> isMatchedName(String name) {
		return (root, query, cb) -> {
			if (StringUtils.isEmpty(name)) {
				return null;
			} else {
				return cb.equal(root.get("name"), name);
			}
		};
	}
	
	private static Specification<Product> orderById(final boolean descOrdering) {
		return (root, query, cb) -> {
			query.orderBy((descOrdering == true) ? cb.desc(root.get("id")) : cb.asc(root.get("id")));
			return cb.isNotNull(root.get("id"));
		};
	}
	
	public static Specification<Product> ByName(final String name) {
		Specification<Product> spec = Specification.where(null);
		
		spec = spec.and(isMatchedName(name));
		spec = spec.and(orderById(false));
		
		return spec;
	}
}
