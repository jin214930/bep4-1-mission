package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.cash.out.WalletRepository;
import com.back.global.exception.DomainException;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;


    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        CashMember member = new CashMember(
                memberDto.getId(),
                memberDto.getCreatedDate(),
                memberDto.getModifiedDate(),
                memberDto.getUsername(),
                "",
                memberDto.getNickname(),
                memberDto.getActiveScore()
        );

        return cashMemberRepository.save(member);
    }

    @Transactional
    public Wallet createWallet(CashMember holder) {
        Wallet wallet = new Wallet(holder);

        return walletRepository.save(wallet);
    }

    public CashMember findMemberByUsername(String username) {
        return cashMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 username입니다.")
        );
    }

    public Wallet findWalletByHolder(CashMember holder) {
        return walletRepository.findByHolder(holder).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 회원입니다.")
        );
    }
}
