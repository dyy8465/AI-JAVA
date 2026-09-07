package com.ggs.traveljava.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data
public class ChatRequestDTO {
    @NotBlank(message = "输入不能为空")
    private String message;

}
