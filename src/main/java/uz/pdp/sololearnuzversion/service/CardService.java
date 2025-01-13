package uz.pdp.sololearnuzversion.service;

import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.Card;
import uz.pdp.sololearnuzversion.repository.CardRepository;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cartRepository;

    public CardService(CardRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Create
    public Card addToCart(Card cart) {
        return cartRepository.save(cart);
    }

    // Read all
    public List<Card> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Read by ID
    public Card getCartItemById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found!"));
    }

    // Read by User ID
    public List<Card> getCartItemsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Update
    public Card updateCartItem(Long id, Card updatedCart) {
        Card existingCart = getCartItemById(id);
        existingCart.setProductId(updatedCart.getProductId());
        existingCart.setQuantity(updatedCart.getQuantity());
        return cartRepository.save(existingCart);
    }

    // Delete
    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }
}
