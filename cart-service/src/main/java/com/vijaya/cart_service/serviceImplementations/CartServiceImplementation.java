package com.vijaya.cart_service.serviceImplementations;

import com.netflix.discovery.converters.Auto;
import com.vijaya.cart_service.Models.Cart;
import com.vijaya.cart_service.Models.CartItem;
import com.vijaya.cart_service.Repositories.CartItemRepository;
import com.vijaya.cart_service.Repositories.CartRepository;
import com.vijaya.cart_service.feign.ItemClient;
import com.vijaya.cart_service.payloads.CartDTO;
import com.vijaya.cart_service.payloads.CartItemDTO;
import com.vijaya.cart_service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImplementation implements CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ItemClient itemClient;

    @Override
    public CartDTO addToCart(Long userId, Long itemId, Integer quantity) {

        // Fetch price from Item-Service
        Double price =  itemClient.getItemPrice(itemId).doubleValue();
        Double lineTotal = price * quantity;

        // Find existing cart for user
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart();
            cart.setDiscount(0.0);
            cart.setTotalAmount(0.0);
            cart.setUserId(userId);
            cartRepository.save(cart);
        }


        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItemId(itemId);
        cartItem.setPrice(price);
        cartItem.setQuantity(quantity);
        cartItem.setTotal(lineTotal);
        cartItemRepository.save(cartItem);

        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(cartItem);

        Double newTotal = cartItemRepository.sumLineTotalByCartId(cart.getCartId());
        cart.setTotalAmount(newTotal);

        Cart savedCart =  cartRepository.save(cart);
        List<CartItemDTO> itemDTOs = savedCart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getId(),
                        item.getItemId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getTotal(),
                        cart.getCartId()
                )).toList();
        return new CartDTO(
                cart.getCartId(),
                cart.getUserId(),
                cart.getTotalAmount(),
                cart.getDiscount() != 0.0 ? cart.getDiscount() : 0.0,
                itemDTOs
        );
    }

    @Override
    public CartDTO getCart(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart();
            cart.setDiscount(0.0);
            cart.setTotalAmount(0.0);
            cart.setUserId(userId);
            cartRepository.save(cart);
        }

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getId(),
                        item.getItemId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getTotal(),
                        cart.getCartId()
                )).toList();
        return new CartDTO(
                cart.getCartId(),
                cart.getUserId(),
                cart.getTotalAmount(),
                cart.getDiscount() != 0.0 ? cart.getDiscount() : 0.0,
                itemDTOs
        );
    }

    @Override
    public String updateQuantity(Long itemId) {


        return "SuccessFully updated";
    }
}
