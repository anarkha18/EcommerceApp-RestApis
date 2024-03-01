//package com.ecommerceapp.utils;
//
//import com.ecommerceapp.repos.CategoryRepo;
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//@Component
//public class CategoryIDGenerator implements IdentifierGenerator {
//    private static final String PREFIX = "CAT";
//    private final CategoryRepo categoryRepo;
//
//    @Autowired
//    public CategoryIDGenerator(@Lazy CategoryRepo categoryRepo) {
//        this.categoryRepo = categoryRepo;
//    }
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
//        Long count = categoryRepo.count();
//        Long nextValue = count + 1;
//        return PREFIX + String.format("%02d", nextValue);
//    }
//
//}
//
//@GenericGenerator(
//        name = "category_id_generator",
//        strategy = "com.ecommerceapp.utils.CategoryIDGenerator"
//)
//@GeneratedValue(generator = "category_id_generator")