package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() throws Exception{
        // inicia antes de todo teste.
        // serve para adicionar dados antes de ta execuntando testes
    }

    @AfterEach
    public void termDown(){
//executa depois de cada teste, usado geralmente para remover dados que foram adicionados.
    }
    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
         productRepository.deleteById(1L);

        Optional<Product> result = productRepository.findById(1L);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
           productRepository.deleteById(1000L);
        });
    }

    @Test
    public void deveBuscarProductPorId(){
        Optional<Product> productOptional = productRepository.findById(1L);
        Assertions.assertTrue(productOptional.isPresent());

    }

    @Test
    public void deveRetornarExcessaoAoBuscarProdutoComIdInvalido(){
        Optional<Product> productOptional = productRepository.findById(10000L);
        Assertions.assertTrue(productOptional.isEmpty());
    }

}
