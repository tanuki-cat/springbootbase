package com.lichi.springbootbase.auth.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: token对象
 * @author: lychee
 * @date: 2022/12/19 12:46
 * @version: 1.0
 * @since: 2022/12/19
 */
@Data
public class AccessToken {

    private String loginAccount;
    private String token;
    private Date expiration;


    public AccessToken(Builder builder) {
        this.loginAccount = builder.loginAccount;
        this.token = builder.token;
        this.expiration = builder.expiration;
    }

    public static AccessToken.Builder builder() {
        return new AccessToken.Builder();
    }


    public static class Builder {
        private String loginAccount;
        private String token;
        private Date expiration;
        private Date createTime;

        public Builder(String loginAccount, String token, Date expiration) {
            this.loginAccount = loginAccount;
            this.token = token;
            this.expiration = expiration;
        }

        public Builder() {

        }

        public Builder loginAccount(String loginAccount) {
            this.loginAccount = loginAccount;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public AccessToken build() {
            return new AccessToken(this);
        }
    }
}
