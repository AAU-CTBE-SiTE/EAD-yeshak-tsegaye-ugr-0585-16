package com.site.student_management.dto;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}