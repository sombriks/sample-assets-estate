package sample.assets.estate;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@ActiveProfiles("test")
class AssetsEstateApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(AssetsEstateApplicationTests.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String result = passwordEncoder.encode("1234");
        LOG.info(result);
        assertThat(result, notNullValue());
    }

}
