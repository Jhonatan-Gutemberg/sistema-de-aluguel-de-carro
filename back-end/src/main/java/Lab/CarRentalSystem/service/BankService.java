package Lab.CarRentalSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.dto.Bank.BankDTO;
import Lab.CarRentalSystem.mappers.SystemUserMapper;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.repository.BankRepository;
import Lab.CarRentalSystem.service.interfaces.IBankService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankService implements IBankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank register(BankDTO bankDTO) {
        Bank bank = SystemUserMapper.bankDtoToModel(bankDTO);
        return bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBankById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.orElse(null);
    }

    @Override
    public Bank updateBank(Long id, BankDTO bankDTO) {
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank existingBank = optionalBank.get();
            existingBank.setName(bankDTO.name());
            existingBank.setAddress(bankDTO.address());
            existingBank.setLimitValue(bankDTO.limitValue());
            existingBank.setPassword(bankDTO.password());

            return bankRepository.save(existingBank);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}
