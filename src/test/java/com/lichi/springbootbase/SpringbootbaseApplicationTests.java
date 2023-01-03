package com.lichi.springbootbase;

import com.lichi.springbootbase.auth.components.JwtComponent;
import com.lichi.springbootbase.auth.entity.UserDetail;
import com.lichi.springbootbase.auth.enums.RoleEnum;
import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@SpringBootTest
@Slf4j
class SpringbootbaseApplicationTests {
    @Autowired
    private JwtComponent jwtComponent;

    private DataSourceProperties    dataSourceProperties;
    static class Message {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }

    @Test
    void contextLoads() {
        Message message = new Message();
        message.setMessage("hello world");
        ApiResponse<Message> s =  ApiResponse.success("sdjaiosdjo", message);
        ApiResponse<?> s1 = ApiResponse.error(ApiResponseStatusEnum.INTERNAL_SERVER_ERROR,"sdjaiosdjo", message.toString());
        log.info(s.toString());
        log.info(s1.toString());
    }

    @Test
    void testComponent() {
        UserDetail userDetail = new UserDetail();
//        jwtComponent.createAccessToken(userDetail);
    }

    @Test
    void testPassEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String passHash = encoder.encode("123456");
        log.info("this is passHash: {}", passHash);
        boolean matches = encoder.matches("123456", passHash);
        log.info("this is matches: {}", matches);
    }

    @Test
    void testJDBC(){
//        dataSourceProperties.getEmbeddedDatabaseConnection();
    }

    @Test
    void testEnum(){
        long roleCode = RoleEnum.USER.roleCode("USER");
        log.info("this is enum code: {}",roleCode );
    }
}
