package com.spring.model;

public class Password {

    private static CharSequence password;

    public Password() {
    }

    public Password(CharSequence password) {
        this.password = password;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Password{");
        sb.append("Password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
