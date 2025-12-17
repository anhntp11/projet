package fr.orleans.m1s1miage.group4.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        // H2 in-memory, giữ DB sống trong suốt vòng đời JVM
        "spring.datasource.url=jdbc:h2:mem:monapp_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=root",
        "spring.datasource.password=root",

        "spring.jpa.hibernate.ddl-auto=create-drop",

        "spring.jpa.show-sql=false"
})
class BackendApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context.containsBean("corsConfigurer")).isTrue();
    }
}
