package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private UserRepository userRepo;

    //Ability to serve to controller's insert cart details api call
    public Cart insertCart(CartDTO cartdto) {
        // check the already in cart or not
//        List<Cart> bookAvilable = cartRepo.findByBookId(cartdto.getBookID());
//        System.out.println(bookAvilable);
//        List<Cart> userAvailable = cartRepo.findByUserID(cartdto.getUserID());
        // if not available in cart they are procid
        Optional<Book> book = bookRepo.findById(cartdto.getBookID());
        Optional<User> user = userRepo.findById(cartdto.getUserID());

//        if (bookAvilable.isPresent() && userAvailable.isPresent() && bookAvilable.get().getCartID().equals(userAvailable.get().getCartID())) {
//           throw new BookStoreException("User is already in cart please add only this Item");
//        }
        //else
        if (book.isPresent() && user.isPresent()) {
            if (cartdto.getQuantity() < book.get().getQuantity()) {
                Cart newCart = new Cart(cartdto.getQuantity(), book.get(), user.get());
                cartRepo.save(newCart);
                log.info("Cart record inserted successfully");
                //book.get().setQuantity(book.get().getQuantity() - cartdto.getQuantity());
                bookRepo.save(book.get());
                return newCart;
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    //Ability to serve to controller's retrieve all cart records api call
    public List<Cart> getAllCartRecords() {
        List<Cart> cartList = cartRepo.findAll();
        log.info("All cart records retrieved successfully");
        return cartList;
    }

    //Ability to serve to controller's retrieve cart record by id api call
    public Cart getCartRecord(Integer id) {
        Optional<Cart> cart = cartRepo.findById(id);
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            log.info("Cart record retrieved successfully for id " + id);
            return cart.get();
        }
    }

    //Ability to serve to controller's update cart record by id api call
    public Cart updateCartRecord(Integer id, CartDTO dto) {
        Optional<Cart> cart = cartRepo.findById(id);
        Optional<Book> book = bookRepo.findById(dto.getBookID());
        Optional<User> user = userRepo.findById(dto.getUserID());
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() <= book.get().getQuantity()) {
                    Cart newCart = new Cart(id, dto.getQuantity(), book.get(), user.get());
                    cartRepo.save(newCart);
                    log.info("Cart record updated successfully for id " + id);
                    //book.get().setQuantity(book.get().getQuantity() -(dto.getQuantity() -cart.get().getQuantity()));
                    bookRepo.save(book.get());
                    return newCart;
                } else {
                    throw new BookStoreException("Requested quantity is not available");
                }
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
    }

    //Ability to serve to controller's delete cart record by id api call
    public Cart deleteCartRecord(Integer id) {
        Optional<Cart> cart = cartRepo.findById(id);
        Optional<Book> book = bookRepo.findById(cart.get().getBook().getBookID());
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            //book.get().setQuantity(book.get().getQuantity() + cart.get().getQuantity());
            bookRepo.save(book.get());
            cartRepo.deleteById(id);
            log.info("Cart record deleted successfully for id " + id);
            return cart.get();
        }
    }

    //Ability to serve to controller's update quantity of books in cart api call
    public Cart updateQuantity(Integer id, Integer quantity) {
        Optional<Cart> cart = cartRepo.findById(id);
        Optional<Book> book = bookRepo.findById(cart.get().getBook().getBookID());
        if (cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        } else {
            if (quantity < book.get().getQuantity()) {
                cart.get().setQuantity(quantity);
                cartRepo.save(cart.get());
                log.info("Quantity in cart record updated successfully");
                //  book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                bookRepo.save(book.get());
                return cart.get();
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        }
    }

    //Created service method which serves controller api to delete all quantity
    public List<Cart> deleteAllFromCart() {
        cartRepo.deleteAll();
        return cartRepo.findAll();
    }

    public Cart updateCartRecordByUserID(Integer id, Integer quantity) {
//        List<Cart> cart = cartRepo.findByUserID(id);
//        Optional<Book> book = bookRepo.findById(cart.listIterator().next().getBook().getBookID());
//        if (cart.isEmpty()) {
//            throw new BookStoreException("Cart Record doesn't exists");
//        } else {
//            if (quantity < book.get().getQuantity()) {
//                cart.get().setQuantity(quantity);
//                cartRepo.save(cart.get());
//                log.info("Quantity in cart record updated successfully");
//                //  book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
//                bookRepo.save(book.get());
        return new Cart();
//            } else {
//                throw new BookStoreException("Requested quantity is not available");
//            }
//        }
//
    }

}
