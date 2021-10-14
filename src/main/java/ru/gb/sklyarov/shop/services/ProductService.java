package ru.gb.sklyarov.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.sklyarov.shop.dtos.CommentDto;
import ru.gb.sklyarov.shop.entities.Comment;
import ru.gb.sklyarov.shop.entities.Product;
import ru.gb.sklyarov.shop.exceptions.ResourceNotFoundException;
import ru.gb.sklyarov.shop.repositories.ProductRepository;
import ru.gb.sklyarov.shop.ws.products.ProductWs;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CommentService commentService;
    private final UserService userService;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProductsByPrice(Double minPriceLimit, Double maxPriceLimit) {
        if (minPriceLimit == null && maxPriceLimit == null) {
            return new ArrayList<>();
        }
        if (minPriceLimit != null && maxPriceLimit != null) {
            return productRepository.findAllByPriceBetween(minPriceLimit, maxPriceLimit);
        }

        if (minPriceLimit != null) {
            return productRepository.findAllByPriceIsGreaterThanEqual(minPriceLimit);
        }

        return productRepository.findAllByPriceIsLessThanEqual(maxPriceLimit);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

//    SOAP

    public Optional<ProductWs> findByIdSoap(Long id) {
        return productRepository.findById(id).map(this::productToProductWs);
    }

    public List<ProductWs> findAllSoap() {
        return productRepository.findAll().stream().map(this::productToProductWs).collect(Collectors.toList());
    }

    private ProductWs productToProductWs(Product product) {
        ProductWs productWs = new ProductWs();
        productWs.setId(product.getId());
        productWs.setTitle(product.getTitle());
        productWs.setPrice(product.getPrice());
        return productWs;
    }
//    public static final Function<Product, ProductWs> productToProductWs = product ->{
//        ProductWs productWs = new ProductWs();
//        productWs.setId(product.getId());
//        productWs.setTitle(product.getTitle());
//        productWs.setPrice(product.getPrice());
//        return productWs;
//    };

    public List<Comment> findCommentsByUserAndProduct(Principal principal, Long productId) {
        return commentService.findCommentsByUserAndProduct(userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User " + principal.getName() + " not found in the database.")),
                productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + productId + " not found in the repository!")));
    }

    public List<Comment> findCommentsByProduct(Long productId) {
        return commentService.findCommentsByProduct(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id: " + productId + " not found in the repository!")));
    }

    public void saveComment(CommentDto commentDto, Principal principal) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setProduct(productRepository.findById(commentDto.getProduct_id()).orElseThrow(() -> new ResourceNotFoundException("Product ID: " + commentDto.getProduct_id() + " not found")));
        comment.setUser(userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User " + principal.getName() + " not found in the database.")));
        commentService.save(comment);
    }

    public boolean findPurchase(Long productId, Principal principal) {
        long userId = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User " + principal.getName() + " not found in the database.")).getId();
        return productRepository.productInPurchase(userId, productId) != 0;
    }
}
