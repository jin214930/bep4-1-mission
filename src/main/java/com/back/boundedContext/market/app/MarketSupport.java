package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.boundedContext.market.out.CartRepository;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.boundedContext.market.out.ProductRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketSupport {
    private final ProductRepository productRepository;
    private final MarketMemberRepository marketMemberRepository;
    private final CartRepository cartRepository;

    public long productsCount() {
        return productRepository.count();
    }

    public MarketMember findMemberByUsername(String username) {
        return marketMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 username입니다.")
        );
    }

    public Cart findCartByCustomer(MarketMember member) {
        return cartRepository.findByCustomer(member).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 장바구니입니다.")
        );
    }

    public Product findProductById(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 상품입니다.")
        );
    }
}
