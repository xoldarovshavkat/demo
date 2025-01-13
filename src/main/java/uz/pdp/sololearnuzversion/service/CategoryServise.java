package uz.pdp.sololearnuzversion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.Category;
import uz.pdp.sololearnuzversion.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServise {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllItems() {
        return categoryRepository.findAll();
    }

    public CategoryRepository getItemById(Long id) {
        return (CategoryRepository) categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public CategoryRepository createItem(Category category) {
        Category item = new Category();
        item.setName(category.getName());
        item.setDescription(category.getDescription());
        return (CategoryRepository) categoryRepository.save(item);
    }

    public Category updateItem(Long id, Category category) {
        Category existingItem = (Category) getItemById(id);
        existingItem.setName(category.getName());
        existingItem.setDescription(category.getDescription());
        return categoryRepository.save(existingItem);
    }

    public void deleteItem(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
