package exercise.specification;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

// BEGIN
@Component
public class ProductSpecification {
	public static Specification<Product> build(ProductParamsDTO params) {
		return productCategory(params.getCategoryId())
				.and(priceFrom(params.getPriceGt()))
				.and(priceTo(params.getPriceLt()))
				.and(ratingGt(params.getRatingGt()))
				.and(titleCont(params.getTitleCont()));
	}

	public static Specification<Product> productCategory(Long categoryId) {
		return (root, query, cb) -> categoryId == null ? cb.conjunction() :
				cb.equal(root.get("category").get("id"), categoryId);
	}

	public static Specification<Product> priceFrom(Integer priceGt) {
		return (root, query, cb) -> priceGt == null ? cb.conjunction() :
				cb.gt(root.get("price"), priceGt);
	}

	public static Specification<Product> priceTo(Integer priceLt) {
		return (root, query, cb) -> priceLt == null ? cb.conjunction() :
				cb.lt(root.get("price"), priceLt);
	}


/*	public static Specification<Product> priceBetween(Integer priceGt, Integer priceLt) {
		return (root, query, cb) -> priceGt == null && priceLt == null ? cb.conjunction() :
				cb.between(root.get("price"), priceGt == null ? 0 : priceGt,
						priceLt == null ? Integer.MAX_VALUE : priceLt);
	}*/

	public static Specification<Product> ratingGt(Double rating) {
		return (root, query, cb) -> rating == null ? cb.conjunction() :
				cb.gt(root.get("rating"), rating);
	}

	public static Specification<Product> titleCont(String titleCont) {
		return (root, query, cb) -> titleCont == null ? cb.conjunction() :
				cb.like(cb.lower(root.get("title")), cb.lower(cb.literal("%" + titleCont + "%")));
	}
}
// END
