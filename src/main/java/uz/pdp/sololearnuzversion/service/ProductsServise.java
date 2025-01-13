package uz.pdp.sololearnuzversion.service;

import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.Category;
import uz.pdp.sololearnuzversion.entity.Products;
import uz.pdp.sololearnuzversion.repository.CategoryRepository;
import uz.pdp.sololearnuzversion.repository.ProductRespository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductsServise {

    private final ProductRespository productRepository;
    private final CategoryRepository categoryRepository;

    public <ProductRepository> ProductsServise(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = (ProductRespository) productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductsServise(ProductRespository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // CREATE: Add a new product
    @Transactional
    public Products createProduct(String name, String description, Double price, Long categoryId, Integer stock, String imageUrl) {
        // Check if the category exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " does not exist"));

        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }

        Products product = new Products(name, description, price, category, stock, imageUrl);
        return productRepository.save(product);
    }

    // READ: Get all products
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    // READ: Get a product by ID
    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found"));
    }

    // UPDATE: Update an existing product
    @Transactional
    public Products updateProduct(Long id, String name, String description, Double price, Long categoryId, Integer stock, String imageUrl) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found"));

        if (name != null && !name.isBlank()) {
            product.setName(name);
        }

        if (description != null) {
            product.setDescription(description);
        }

        if (price != null && price > 0) {
            product.setPrice(price);
        }

        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " not found"));
            product.setCategory(category);
        }

        if (stock != null && stock >= 0) {
            product.setStock(stock);
        }

        if (imageUrl != null) {
            product.setImageUrl(imageUrl);
        }

        return productRepository.save(product);
    }

    // DELETE: Delete a product by ID
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }

    // Additional business logic: Get products by category
    public List<Products> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " not found"));
        return productRepository.findByCategory(category);
    }

    // Additional business logic: Check stock level

    public boolean isProductInStock(Long id) {
        Products product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found"));
        return product.getStock() > 0;
    }
}
