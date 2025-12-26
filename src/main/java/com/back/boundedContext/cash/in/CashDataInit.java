package com.back.boundedContext.cash.in;

import com.back.boundedContext.cash.app.CashFacade;
import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class CashDataInit {
    private final CashDataInit self;
    private final CashFacade cashFacade;

    public CashDataInit(@Lazy CashDataInit self, CashFacade cashFacade) {
        this.self = self;
        this.cashFacade = cashFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner cashInitDataRunner() {
        return _ -> {
            self.makeBaseWallets();
        };
    }

    @Transactional
    public void makeBaseWallets() {
        CashMember user1 = cashFacade.findMemberByUsername("user1");
        CashMember user2 = cashFacade.findMemberByUsername("user2");

        Wallet user1Wallet = cashFacade.findWalletByHolder(user1);
        Wallet user2Wallet = cashFacade.findWalletByHolder(user2);

        if (user1Wallet.hasBalance()) return;

        user1Wallet.credit(150000, CashLog.EventType.충전__무통장입금);
        user1Wallet.credit(100000, CashLog.EventType.충전__무통장입금);
        user1Wallet.credit(50000, CashLog.EventType.충전__무통장입금);

        if (user2Wallet.hasBalance()) return;

        user2Wallet.credit(150000, CashLog.EventType.충전__무통장입금);
    }
}
