package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserInsertDTO dto){
        UserDTO newDto = userService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO UserDTO = userService.findById(id);
        return ResponseEntity.ok().body(UserDTO);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO UserDTO){
        UserDTO = userService.update(id, UserDTO);
        return ResponseEntity.ok().body(UserDTO);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
