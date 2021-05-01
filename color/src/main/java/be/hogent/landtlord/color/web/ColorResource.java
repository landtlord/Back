package be.hogent.landtlord.color.web;

import be.hogent.landtlord.color.service.ColorService;
import be.hogent.landtlord.color.service.dto.Color;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ColorResource {

     @Autowired
     private ColorService colorService;

    @GetMapping("colors/{id}")
    public ResponseEntity<Color> getById(@PathVariable Long id){
        return ResponseEntity.ok(colorService.getById(id));
    }
}
