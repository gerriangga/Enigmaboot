package com.enima.enigmaboot.specification;

import com.enima.enigmaboot.dto.ProductSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.entities.Products;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Products> getSpecification(ProductSearchDTO productSearchDTO) {
        return new Specification<Products>() {
                @Override
                public Predicate toPredicate (Root < Products > root, CriteriaQuery < ?>criteriaQuery, CriteriaBuilder criteriaBuilder){
                    List<Predicate> predicates = new ArrayList<>();

                    if (!(productSearchDTO.getSearchProductName() == null)) {
                        Predicate productNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productSearchDTO.getSearchProductName() + "%");
                        predicates.add(productNamePredicate);
                    }
                    if (!(productSearchDTO.getSearchProductPrice() == null)) {
                        Predicate productPricePredicate = criteriaBuilder.like(root.get("productPrice"), "%" + productSearchDTO.getSearchProductPrice() + "%");
                        predicates.add(productPricePredicate);
                    }
                    if (!(productSearchDTO.getSearchProductStock() == null)) {
                        Predicate productStockPredicate = criteriaBuilder.like(root.get("stock"), "%" + productSearchDTO.getSearchProductStock() + "%");
                        predicates.add(productStockPredicate);
                    }

                    Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                    return criteriaBuilder.and(arrayPredicates);
                }
        };
    }
}
