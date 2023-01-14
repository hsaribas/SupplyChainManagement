package com.scm.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SCMResponse {

    private String message;

    boolean success;
}
