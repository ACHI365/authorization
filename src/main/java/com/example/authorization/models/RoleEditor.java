package com.example.authorization.models;

import com.example.authorization.security.UserRole;

import java.beans.PropertyEditorSupport;

public class RoleEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        UserRole role = switch (text) {
            case "student" -> UserRole.STUDENT;
            case "admin" -> UserRole.ADMIN;
            default -> null;
        };
        this.setValue(role);
    }
}
