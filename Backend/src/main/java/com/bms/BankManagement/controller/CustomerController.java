package com.bms.BankManagement.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.BankManagement.entity.AccountEntity;
import com.bms.BankManagement.entity.BeneficiaryEntity;
import com.bms.BankManagement.entity.CustomerEntity;
import com.bms.BankManagement.model.AccountResponseModel;
import com.bms.BankManagement.model.Beneficiary;
import com.bms.BankManagement.model.BeneficiaryResponseModel;
import com.bms.BankManagement.model.Customer;
import com.bms.BankManagement.model.ResponseModel;
import com.bms.BankManagement.model.Transaction;
import com.bms.BankManagement.services.AccountService;
import com.bms.BankManagement.services.BeneficiaryService;
import com.bms.BankManagement.services.CustomerService;
import com.bms.BankManagement.services.TransactionService;
import com.bms.BankManagement.shared.CustomerDto;

@RestController
@RequestMapping("/")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	AccountService accountService;

	@Autowired
	BeneficiaryService beneficiaryService;

	@Autowired
	TransactionService transactionService;

	@PostMapping("/bank/register")
	public ResponseEntity<ResponseModel> postCustomers(@RequestBody Customer customer, HttpServletRequest request) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
		CustomerDto c = customerService.createCustomer(customerDto, request);

		ResponseModel returnVal = modelMapper.map(c, ResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);

	}

	// view account details
	@GetMapping("/bank/user/getaccount")
    @PreAuthorize("hasRole('User')")
	public AccountResponseModel getAccountDetails(String id, HttpServletRequest request, HttpServletResponse res) {

		CustomerEntity customerEntity = customerService.getCustomerDetailsByCustomerId(Long.parseLong(id));

		AccountEntity accountDetails = accountService.findByCustomerEntity(customerEntity);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AccountResponseModel account = modelMapper.map(accountDetails, AccountResponseModel.class);

		return account;
	}

	// view status of your account
	@GetMapping("/bank/viewaccountstatus")
	public ResponseEntity<String> getAccountStatus(String id, HttpServletRequest request) {
		String status = customerService.findStatus(Long.parseLong(id), request);
		System.out.println(id);
		if (status == null) {
			return ResponseEntity.status(400).body(null);

		}
		return ResponseEntity.status(200).body(status);
	}

	// add beneficiary
	@PostMapping("/bank/user/addbeneficiary/{accountNumber}")
    @PreAuthorize("hasRole('User')")
	public ResponseEntity<BeneficiaryEntity> addBeneficiary(@PathVariable String accountNumber,
			@RequestBody Beneficiary beneficiary) {

		// check if the beneficiary account number is present in the database
		AccountEntity accountEntity = accountService.findByAccountNumber(Long.parseLong(accountNumber));
		Long beneficiaryAccountNumber = beneficiary.getbAccountNumber();
		AccountEntity ifExistsEntity = accountService.findByAccountNumber(beneficiaryAccountNumber);

		if (ifExistsEntity == null || beneficiaryAccountNumber == Long.parseLong(accountNumber)) {
			return ResponseEntity.status(400).body(null);
		}
		BeneficiaryEntity returnVal = beneficiaryService.createBeneficiary(accountEntity, beneficiary);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnVal);

		// return ResponseEntity.ok(Map.of("message", "Beneficiary added!"));

	}

//get all beneficiary
	@GetMapping("/bank/user/getallbeneficiary/{accountNumber}")
    @PreAuthorize("hasRole('User')")
	public ResponseEntity<List<Object[]>> getAllBeneficiary(@PathVariable String accountNumber) {
		List<Object[]> returnValue = beneficiaryService.getAllBeneficiary(Long.parseLong(accountNumber));
		if (returnValue.isEmpty()) {
			return ResponseEntity.status(400).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}

	// edit beneficiary details
	@PutMapping("/bank/user/editbeneficiary/{bId}")
    @PreAuthorize("hasRole('User')")
	public BeneficiaryResponseModel updateBeneficiaryDetails(@PathVariable String bId,
			@RequestBody BeneficiaryResponseModel bResModel) {
		BeneficiaryResponseModel returnValue = new BeneficiaryResponseModel();

		BeneficiaryEntity benEntity = beneficiaryService.getBeneficiaryByBeneficiaryId(Long.parseLong(bId));

		bResModel.setbAccountType(benEntity.getAccountType());
		BeanUtils.copyProperties(bResModel, benEntity);
		BeneficiaryEntity updateBeneficiary = beneficiaryService.updateBeneficiary(Long.parseLong(bId), benEntity);
		BeanUtils.copyProperties(updateBeneficiary, returnValue);

		return returnValue;
	}

	// delete a beneficiary
	@DeleteMapping(path = "/bank/user/deletebeneficiary/{beneficiaryId}")
    @PreAuthorize("hasRole('User')")
	public ResponseEntity<HttpStatus> deleteBeneficiary(@PathVariable String beneficiaryId) {

		return this.beneficiaryService.deleteBeneficiary(beneficiaryId);
	}

	// make a transaction i.e transfer money in beneficiary's account
	@PostMapping("/bank/user/transaction/{accountNumber}")
    @PreAuthorize("hasRole('User')")
	public ResponseEntity<?> makeTransaction(@PathVariable String accountNumber, @RequestBody Transaction transaction,
			HttpServletRequest request) {

		Double amountTransferred = transaction.getTransactionAmount();
		Long beneficiaryAccountNumber = transaction.getBAccountNumber();

		AccountEntity accountEntity = accountService.findByAccountNumber(Long.parseLong(accountNumber));
		AccountEntity beneficiaryAccountEntity = accountService.findByAccountNumber(beneficiaryAccountNumber);

		Double balanceInBeneficiaryAccount = beneficiaryAccountEntity.getBalance();
		Double balanceInAccount = accountEntity.getBalance();
		if (balanceInAccount >= amountTransferred) {
			balanceInAccount = balanceInAccount - amountTransferred;
			accountService.updateBalance(balanceInAccount, accountEntity);
			accountService.updateBalance(balanceInBeneficiaryAccount + amountTransferred, beneficiaryAccountEntity);
			transactionService.createTransaction(transaction, accountEntity, "WITHDRAW", request);
			return ResponseEntity.ok(Map.of("message", "Transaction Successfull!"));

		}
		return (ResponseEntity<?>) ResponseEntity.badRequest().body("Balance in your account is " + balanceInAccount);

	}

	// deposit money in my account
	@PostMapping("/bank/user/deposit/{accountNumber}")
    @PreAuthorize("hasRole('User')")

	public ResponseEntity<?> depositMoney(@PathVariable String accountNumber, String amount,
			HttpServletRequest request) {
		AccountEntity accountEntity = accountService.findByAccountNumber(Long.parseLong(accountNumber));
		accountService.updateBalance(Double.parseDouble(amount), accountEntity);
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount(Double.parseDouble(amount));

		transactionService.createTransaction(transaction, accountEntity, "DEPOSIT", request);
		return ResponseEntity.ok(Map.of("message", "Money deposited successfuly"));

	}

	// view transaction history
	@GetMapping("/bank/user/viewtransactions/{accountNumber}")
	public List<Object[]> viewTransactions(@PathVariable String accountNumber, HttpServletRequest request) {

		List<Object[]> transactions = transactionService.getTransactions(Long.parseLong(accountNumber), request);

		return transactions;

	}

	// get balance
	@GetMapping("/bank/user/getbalance/{accountNumber}")
    @PreAuthorize("hasRole('User')")
	public ResponseEntity<Double> getBalance(@PathVariable String accountNumber) {
		AccountEntity accountEntity = this.accountService.findByAccountNumber(Long.parseLong(accountNumber));
		if (accountEntity == null) {
			return ResponseEntity.status(400).body(null);

		}
		Double returnValue = accountEntity.getBalance();
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}

	@PostConstruct
	public void init() {
		customerService.initRoleAndUser();
	}

}
