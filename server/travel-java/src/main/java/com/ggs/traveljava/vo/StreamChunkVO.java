package com.ggs.traveljava.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamChunkVO {
    private String type = "chunk";
    private String content;

    public static StreamChunkVO of(String content) {
        StreamChunkVO vo = new StreamChunkVO();
        vo.setContent(content);
        return vo;
    }
}
