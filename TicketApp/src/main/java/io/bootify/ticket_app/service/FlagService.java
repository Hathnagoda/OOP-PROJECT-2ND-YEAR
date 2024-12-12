package io.bootify.ticket_app.service;

import io.bootify.ticket_app.domain.Flag;
import io.bootify.ticket_app.repos.FlagRepository;
import io.bootify.ticket_app.util.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FlagService {
    private final FlagRepository flagRepository;
    // Constructor injection of the FlagRepository
    public FlagService(FlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }
    // Switch the flag ON by setting its status to 1
    @Transactional
    public Flag switchON() {
        Long val = Long.valueOf(1);
        Flag flag1 = flagRepository.getById(val);

        flag1.setStatus(1);
        return flagRepository.save(flag1);
    }

    @Transactional
    public Flag switchOFF() {
        Long val = Long.valueOf(1);
        Flag flag1 = flagRepository.getById(val);
        flag1.setStatus(0);
        return flagRepository.save(flag1);
    }
    // Get the current status of the flag
    public Flag getStatus() {
        Long val = Long.valueOf(1);
        System.out.println("Hi" + val);
        Flag flag1 = flagRepository.findById(val)
                .orElseThrow(NotFoundException::new);
        ;

        return flag1;
    }

}
