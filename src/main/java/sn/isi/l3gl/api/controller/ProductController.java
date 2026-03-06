package sn.isi.l3gl.api.controller;

import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.listProducts());
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(productService.updateQuantity(id, quantity));
    }

    // GET /api/products/low-stock/count
    @GetMapping("/low-stock/count")
    public ResponseEntity<Long> countLowStock() {
        return ResponseEntity.ok(productService.countLowStockProducts());
    }
}