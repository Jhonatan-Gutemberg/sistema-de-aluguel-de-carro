package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Lab.CarRentalSystem.dto.CreditContract.CreditContractDTO;
import Lab.CarRentalSystem.mappers.OrderMapper;
import Lab.CarRentalSystem.models.CreditContract;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.repository.CreditContractRepository;
import Lab.CarRentalSystem.repository.CustomerRepository;
import Lab.CarRentalSystem.service.interfaces.ICreditContractService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CreditContractService implements ICreditContractService {

    @Autowired
    private CreditContractRepository creditContractRepository;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CreditContract register(CreditContractDTO creditContractDTO) {
        System.out.println(creditContractDTO);

        Customer customer = customerRepository.findById(creditContractDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        CreditContract creditContract = OrderMapper.toEntity(creditContractDTO);
        creditContractRepository.save(creditContract);

        customerRepository.save(customer);

        if (customer.getCreditCards() == null) {
            customer.setCreditCards(new ArrayList<>());
        }

        customer.getCreditCards().add(creditContract.getId());

        return creditContract;
    }

    @Override
    public List<CreditContract> getAllCreditContracts() {
        return creditContractRepository.findAll();
    }

    @Override
    public CreditContract getCreditContractById(Long id) {
        Optional<CreditContract> creditContract = creditContractRepository.findById(id);
        return creditContract.orElse(null);
    }

    // @Override
    // public CreditContract updateCreditContract(Long id, CreditContractDTO
    // creditContractDTO) {
    // Optional<CreditContract> optionalCreditContract =
    // creditContractRepository.findById(id);
    // if (optionalCreditContract.isPresent()) {
    // CreditContract existingCreditContract = optionalCreditContract.get();
    // existingCreditContract.setAmount(creditContractDTO.amount());

    // existingCreditContract.setOrder(orderRepository.findById(creditContractDTO.order().id()).orElse(null));
    // existingCreditContract.setBank(bankRepository.findById(creditContractDTO.bank().id()).orElse(null));

    // creditContractRepository.save(existingCreditContract);
    // return existingCreditContract;
    // } else {
    // return null;
    // }
    // }

    @Override
    public boolean deleteCreditContract(Long id) {
        Optional<CreditContract> optionalCreditContract = creditContractRepository.findById(id);
        if (optionalCreditContract.isPresent()) {
            creditContractRepository.delete(optionalCreditContract.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CreditContract> getCreditContractsByUserId(Long userId) {
        Optional<SystemUser> userOptional = systemUserService.getUserById1(userId);

        if (userOptional.isPresent() && userOptional.get() instanceof Customer) {
            Customer customer = (Customer) userOptional.get();
            return creditContractRepository.findByIdIn(customer.getCreditCards());
        }
        return new ArrayList<>();
    }

    @Override
    public CreditContract toggleApprovalStatus(Long id) {
        Optional<CreditContract> optionalContract = creditContractRepository.findById(id);
        if (optionalContract.isPresent()) {
            CreditContract contract = optionalContract.get();
            contract.setApproved(!contract.isApproved());
            creditContractRepository.save(contract);
            return contract;
        } else {
            throw new EntityNotFoundException("Credit contract not found");
        }
    }

}
