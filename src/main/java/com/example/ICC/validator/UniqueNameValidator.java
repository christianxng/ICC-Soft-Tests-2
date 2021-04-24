package com.example.ICC.validator;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {


    private final EntityManager manager;

    public UniqueNameValidator(EntityManager manager) {
        this.manager = manager;
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select s from Stock s where s.name = :name ");
        query.setParameter("name",obj);
        return query.getResultList().isEmpty();
    }
}
