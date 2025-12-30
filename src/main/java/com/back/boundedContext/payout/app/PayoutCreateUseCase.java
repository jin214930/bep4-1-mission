package com.back.boundedContext.payout.app;

import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutCreateUseCase {
    public void createPayout(PayoutMemberDto memberDto) {
        log.debug("createPayout: {}", memberDto.getId());
    }
}
