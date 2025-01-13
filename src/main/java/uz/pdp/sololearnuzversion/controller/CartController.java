package uz.pdp.sololearnuzversion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.entity.Card;
import uz.pdp.sololearnuzversion.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CardService cartService;

    public CartController(CardService cartService) {
        this.cartService = cartService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Card> addToCart(@RequestBody Card cart) {
        return ResponseEntity.ok(cartService.addToCart(cart));
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Card>> getAllCartItems() {
        return ResponseEntity.ok(cartService.getAllCartItems());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCartItemById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartItemById(id));
    }

    // Read by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Card>> getCartItemsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItemsByUserId(userId));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCartItem(
            @PathVariable Long id,
            @RequestBody Card updatedCart) {
        return ResponseEntity.ok(cartService.updateCartItem(id, updatedCart));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
