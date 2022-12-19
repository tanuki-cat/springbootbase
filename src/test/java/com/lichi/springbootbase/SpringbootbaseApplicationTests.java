package com.lichi.springbootbase;

import com.lichi.springbootbase.response.ApiResponse;
import com.lichi.springbootbase.response.enums.ApiResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringbootbaseApplicationTests {
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

}
