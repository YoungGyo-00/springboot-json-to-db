package com.example.jsontodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.File;

@SpringBootApplication
@EnableJpaAuditing
public class JsontodbApplication {

    public static void main(String[] args) {
        /*String pathName = "\\\\172.16.150.135\\2_팀 공유 폴더\\2) 학습 DATAset_Superb AI\\" +
                "Export 다운로드 버전\\22XX 2022-03-28 10_42_11";
        File dir = new File(pathName);
        File files[] = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.getName().equals("meta")){
                File metaDir = new File(pathName + "\\meta");
                File metaFiles[] = metaDir.listFiles();
                for (int j = 0; j < metaFiles.length; j++) {
                    System.out.println(metaFiles[j]);
                }
            }
        }*/

        SpringApplication.run(JsontodbApplication.class, args);
    }

}
