package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.shared.market.dto.MarketMemberDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSupport marketSupport;
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;
    private final MarketCreateProductUseCase marketCreateProductUseCase;
    private final MarketCreateCartUseCase marketCreateCartUseCase;
    private final MarketCreateOrderUseCase marketCreateOrderUseCase;


    @Transactional
    public MarketMember syncMember(MemberDto memberDto) {
        return marketSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional(readOnly = true)
    public long productsCount() {
        return marketSupport.productsCount();
    }

    @Transactional(readOnly = true)
    public MarketMember findMemberByUsername(String username) {
        return marketSupport.findMemberByUsername(username);
    }

    @Transactional
    public Product createProduct(MarketMember seller, String sourceTypeCode, long sourceID, String name, String description, int price, int salePrice) {
        return marketCreateProductUseCase.createProduct(seller, sourceTypeCode, sourceID, name, description, price, salePrice);
    }

    @Transactional
    public Cart createCart(MarketMemberDto memberDto) {
        return marketCreateCartUseCase.createCart(memberDto);
    }

    @Transactional(readOnly = true)
    public Cart findCartByCustomer(MarketMember member) {
        return marketSupport.findCartByCustomer(member);
    }

    @Transactional(readOnly = true)
    public Product findProductById(long id) {
        return marketSupport.findProductById(id);
    }

    @Transactional(readOnly = true)
    public long ordersCount() {
        return marketSupport.ordersCount();
    }

    @Transactional
    public Order createOrder(Cart cart) {
        return marketCreateOrderUseCase.createOrder(cart);
    }
}
