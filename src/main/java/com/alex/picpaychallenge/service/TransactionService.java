package com.alex.picpaychallenge.service;

import com.alex.picpaychallenge.domain.transaction.Transaction;
import com.alex.picpaychallenge.domain.transaction.dto.TransactionDTO;
import com.alex.picpaychallenge.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    private final RestTemplate restTemplate;

    public TransactionService(TransactionRepository transactionRepository, UserService userService, NotificationService notificationService, RestTemplate restTemplate) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.notificationService = notificationService;
        this.restTemplate = restTemplate;
    }

    public Transaction createTransaction(TransactionDTO transactionDTO) {
        var payee = userService.findUserById(transactionDTO.payee());
        var payer = userService.findUserById(transactionDTO.payer());

        userService.validateUser(payer, transactionDTO.value());

        if (!authorizeTransaction()) {
            throw new RuntimeException("Transaction not authorized.");
        }

        Transaction transaction = new Transaction();
        transaction.setValue(transactionDTO.value());
        transaction.setPayee(payee);
        transaction.setPayer(payer);
        transaction.setTimestamp(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.value()));
        payee.setBalance(payee.getBalance().add(transactionDTO.value()));
        userService.saveUser(payer);
        userService.saveUser(payee);

        notificationService.sendNotification(payer, String.format("Transaction to %s completed successfully!", payee.getName()));
        notificationService.sendNotification(payee, String.format("Transaction received from %s", payer.getName()));

        return transactionRepository.save(transaction);
    }


    public boolean authorizeTransaction() {
        var response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String message = (String) response.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        return false;
    }
}
