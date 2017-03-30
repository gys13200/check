package org.prototype.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * Created by gys on 2017/3/28.
 */

@SpringBootApplication
@ServletComponentScan
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
