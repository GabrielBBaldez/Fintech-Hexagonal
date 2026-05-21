package br.com.gabriel.fintech.wallet_service.adapter.in.rest.exception;

import br.com.gabriel.fintech.wallet_service.domain.exception.InsufficientBalanceException;
import br.com.gabriel.fintech.wallet_service.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class WalletExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    public ProblemDetail handleNotFound(WalletNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                "Wallet not found: " + ex.getWalletId()
        );
        problem.setTitle("Wallet Not Found");
        return problem;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ProblemDetail handleInsuficientBalance(InsufficientBalanceException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_CONTENT,
                "Cannot debit %s from wallet %s: current balance is %s".formatted(
                        ex.getAttemptAmount().amount(),
                        ex.getWalletId(),
                        ex.getCurrentBalance().amount()
                )
        );
        problem.setTitle("Insufficient Balance");
        return problem;
    }
}
