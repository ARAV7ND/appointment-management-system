package com.xyz.springdemo.appointmentmanagementsystem.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
    private static Logger logger = LoggerFactory.getLogger(Logging.class);

    @Before("com.xyz.springdemo.appointmentmanagementsystem.aspect.PointCutExpression.find()")
    public void beforeFinding(){
        logger.info("\n=========> Finding from service layer");
    }

    @After("com.xyz.springdemo.appointmentmanagementsystem.aspect.PointCutExpression.find()")
    public void afterFinding(){
        logger.info("\n=========> Found from service layer");
    }

    @Before("com.xyz.springdemo.appointmentmanagementsystem.aspect.PointCutExpression.save()")
    public void beforeSaving(){
        logger.info("\n=====> saving into database");
    }

    @Before("com.xyz.springdemo.appointmentmanagementsystem.aspect.PointCutExpression.delete()")
    public void beforeDelete(){
        logger.info("\n=====> Deleting from the database");
    }

}
