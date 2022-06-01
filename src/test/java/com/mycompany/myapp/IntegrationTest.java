package com.mycompany.myapp;

import com.mycompany.myapp.DashboardTestApp;
import com.mycompany.myapp.config.EmbeddedMongo;
import com.mycompany.myapp.config.TestSecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { DashboardTestApp.class, TestSecurityConfiguration.class })
@EmbeddedMongo
public @interface IntegrationTest {
}
