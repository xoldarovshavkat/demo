package uz.pdp.sololearnuzversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.entity.Category;
import uz.pdp.sololearnuzversion.service.CategoryServise;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class CategoryController {
    @Autowired
    private CategoryServise itemService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok((Category) itemService.getItemById(id));
    }

    @PostMapping
    public ResponseEntity<Category> createItem(@RequestBody Category category) {
        return ResponseEntity.ok((Category) itemService.createItem(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateItem(@PathVariable Long id, @RequestBody Category itemDTO) {
        return ResponseEntity.ok(itemService.updateItem(id, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
