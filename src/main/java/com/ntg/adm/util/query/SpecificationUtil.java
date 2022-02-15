package com.ntg.adm.util.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
	public static <T> Specification<T> bySearchQuery(SearchQuery searchQuery, Class<T> clazz) {
		
		return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicates = null;
			
			List<SearchFilter> searchFilters = searchQuery.getSearchFilter();
			
			if (searchFilters != null && !searchFilters.isEmpty()) {
				predicates = new ArrayList();
				for (final SearchFilter searchFilter : searchFilters) {
					addPredicates(predicates, searchFilter, cb, root);
				}
			}
			
			if (predicates == null || predicates.isEmpty()) {
				return cb.conjunction();
			}
			
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	public static <T> void addPredicates(List<Predicate> predicates, SearchFilter searchFilter, CriteriaBuilder criteriaBuilder, Root<T> root) {
		String property = searchFilter.getProperty();
		
		if(!(property == null || property.isEmpty() || property.isBlank())) {
			Path expression = root.get(property);
			addPredicate(predicates, searchFilter, criteriaBuilder, expression);
		}
	}

	public static void addPredicate(List<Predicate> predicates, SearchFilter searchFilter, CriteriaBuilder cb, Path expression) {
		switch (searchFilter.getOperator()) {
		case "=":
			predicates.add(cb.equal(expression, searchFilter.getValue()));
			break;
		case "LIKE":
			predicates.add(cb.like(expression, MessageFormat.format("%{0}%", searchFilter.getValue())));
			break;
		case "IN":
			predicates.add(cb.in(expression).value(searchFilter.getValue()));
			break;
		case ">":
			predicates.add(cb.greaterThan(expression, (Comparable) searchFilter.getValue()));
			break;
		case "<":
			predicates.add(cb.lessThan(expression, (Comparable) searchFilter.getValue()));
			break;
		case ">=":
			predicates.add(cb.greaterThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
			break;
		case "<=":
			predicates.add(cb.lessThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
			break;
		case "!":
			predicates.add(cb.notEqual(expression, searchFilter.getValue()));
			break;
		case "IsNull":
			predicates.add(cb.isNull(expression));
			break;
		case "NotNull":
			predicates.add(cb.isNotNull(expression));
			break;
		default:
			System.out.println("Predicate is not matched");
			throw new IllegalArgumentException(searchFilter.getOperator() + " is not a valid predicate");
		}
	}
}
