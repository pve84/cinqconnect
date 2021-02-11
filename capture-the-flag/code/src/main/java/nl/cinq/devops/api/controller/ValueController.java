package nl.cinq.devops.api.controller;

import java.util.Random;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils; 

@RestController
@RequestMapping("/api/v1")
public class ValueController {
  @GetMapping("/int")
  public int getRandomInt() {
    final Random pseudo = new Random();
    return pseudo.nextInt();
  }

  @GetMapping("/flag")
  public String getFlagArt() {
    Resource resource = new ClassPathResource("test.properties");
    
    try {
      InputStream inputStream = resource.getInputStream();
      byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
      String data = new String(bdata, StandardCharsets.UTF_8);
      return "<pre>"+data+"</pre>";
    } catch (IOException e) {
        return e.getMessage();
    }
  }
}