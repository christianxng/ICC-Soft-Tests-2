package com.example.ICC.validator;

import com.example.ICC.model.request.StockRequest;
import com.example.ICC.service.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class UniqueNameValidatorTest {

    @PersistenceContext
    private EntityManager manager;
    private UniqueNameValidator validator;
    private ConstraintValidatorContext context;

    @Autowired
    private  StockService service;


    @BeforeEach
    public void setUp(){
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new UniqueNameValidator(manager);
    }

    @Test
    public void UniqueName(){
        boolean valid = validator.isValid("Test Name", context);
        Assertions.assertTrue(valid);
    }

    @Test
    @Transactional
    public void NoUniqueName(){
        StockRequest stockRequest = new StockRequest("Test Name", List.of(15.1,15.2,15.3));
        service.create(stockRequest);
        boolean valid = validator.isValid("Test Name", context);
        Assertions.assertFalse(valid);
    }


}