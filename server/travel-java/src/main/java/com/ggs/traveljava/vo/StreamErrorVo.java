package com.ggs.traveljava.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamErrorVo {
    private String error;

    public static StreamErrorVo of(String error) {
        StreamErrorVo vo = new StreamErrorVo();
        vo.setError(error);
        return vo;
    }
}
