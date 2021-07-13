package com.enima.enigmaboot.specification;

import com.enima.enigmaboot.dto.CustomerSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customers> getSpecification(CustomerSearchDTO customerSearchDTO){
        return new Specification<Customers>() {
            @Override
            public Predicate toPredicate(Root<Customers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!(customerSearchDTO.getSearchCustomerFirstName() == null)){
                    Predicate customerFirstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + customerSearchDTO.getSearchCustomerFirstName() + "%");
                    predicates.add(customerFirstNamePredicate);
                }
                if (!(customerSearchDTO.getSearchCustomerLastName() == null)){
                    Predicate customerLastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + customerSearchDTO.getSearchCustomerLastName() + "%");
                    predicates.add(customerLastNamePredicate);
                }
                if(!(customerSearchDTO.getSearchCustomerDateOfBirth() == null)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String modifiedDateFormat = sdf.format(customerSearchDTO.getSearchCustomerDateOfBirth());

                    Predicate createDatePredicate = criteriaBuilder.equal(criteriaBuilder.function("TO_CHAR", String.class, root.get("dateOfBirth"),
                            criteriaBuilder.literal("yyyy-MM-dd")), modifiedDateFormat);
                    predicates.add(createDatePredicate);
                }

                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
