package com.bankingapp.transation.application.rest.transaction;

import com.bankingapp.transation.domain.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/make")
    public ResponseEntity<TransactionDTO> makeATransaction(@Valid  @RequestBody TransactionDTO dto) {
        try {
            return transactionService
                    .makeATransaction(dto.getOrigin(), dto.getDestiny(), new BigDecimal(dto.getAmount()))
                    .map(transaction -> ResponseEntity.ok(TransactionTransformer.transactionToTransactionDTO(transaction)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null));
        } catch (Exception e) {
            //LOG message using Log4J
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/origin/{origin}")
    public ResponseEntity<List<TransactionDTO>> findTransactionsByOriginAccount(@PathVariable String origin) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionByOriginAccount(origin)
                    .stream().map(TransactionTransformer::transactionToTransactionDTO).toList());
        } catch (Exception e) {
            //LOG message using Log4J
            return ResponseEntity.internalServerError().build();
        }
    }


}
