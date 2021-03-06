package th.ac.ku.bankaccount.controller;

import org.springframework.web.bind.annotation.*;
import th.ac.ku.bankaccount.data.BankAccountRepository;
import th.ac.ku.bankaccount.data.TransactionRepository;
import th.ac.ku.bankaccount.model.BankAccount;
import th.ac.ku.bankaccount.model.Transaction;

import java.util.List;

@RestController
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    private BankAccountRepository repository;
    private TransactionRepository Treanrepository;

    public BankAccountRestController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> getAllCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }


    @GetMapping
    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable int id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount) {
        BankAccount record = repository.save(bankAccount);
        repository.flush();
        return record;
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable int id,
                              @RequestBody BankAccount bankAccount,
                              @RequestBody  Transaction transaction) {
        BankAccount record = repository.findById(id).get();
        record.setBalance(bankAccount.getBalance());
        repository.save(record);
        return record;
    }

    @DeleteMapping("/{id}")
    public BankAccount delete(@PathVariable int id) {
        BankAccount record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }

    @PostMapping("/trasaction")
    public BankAccount changeBalance(@RequestBody Transaction transaction){
        System.out.println(transaction);
        BankAccount record = repository.findById(transaction.getBankId()).get();
        if (transaction.getTransactiontype().toLowerCase().equals("withdraw")){
            record.setBalance(record.getBalance() - transaction.getAmount());
        }else if ((transaction.getTransactiontype().toLowerCase().equals("deposit"))){
            record.setBalance(record.getBalance() + transaction.getAmount());
        }
        repository.save(record);
        return record;
    }
}

