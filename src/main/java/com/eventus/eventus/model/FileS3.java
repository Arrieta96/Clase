package com.eventus.eventus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileS3 {

    private String fileUrl;
    private String fileName;

}
