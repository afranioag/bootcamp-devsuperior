package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductDTO dto){
        dto = productService.save(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> list = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO ProductDTO = productService.findById(id);
        return ResponseEntity.ok().body(ProductDTO);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO ProductDTO){
        ProductDTO = productService.update(id, ProductDTO);
        return ResponseEntity.ok().body(ProductDTO);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
