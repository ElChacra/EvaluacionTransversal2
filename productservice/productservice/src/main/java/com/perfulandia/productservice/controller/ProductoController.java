package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.assembler.ProductoAssembler;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.service.ProductoService;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService servicio;
    private final ProductoAssembler assembler;
    private final RestTemplate restTemplate;

    public ProductoController(ProductoService servicio, ProductoAssembler assembler, RestTemplate restTemplate) {
        this.servicio = servicio;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    // HATEOAS: listar todos
    @GetMapping
    public CollectionModel<EntityModel<Producto>> listar() {
        List<EntityModel<Producto>> productos = servicio.listar().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    // HATEOAS: guardar nuevo
    @PostMapping
    public EntityModel<Producto> guardar(@RequestBody Producto producto) {
        Producto guardado = servicio.guardar(producto);
        return assembler.toModel(guardado);
    }

    // HATEOAS: buscar por id
    @GetMapping("/{id}")
    public EntityModel<Producto> buscar(@PathVariable long id) {
        Producto p = servicio.bucarPorId(id);
        return assembler.toModel(p);
    }

    // HATEOAS: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/" + id, Usuario.class);
    }
}
