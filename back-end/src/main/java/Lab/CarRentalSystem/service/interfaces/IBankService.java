package Lab.CarRentalSystem.service.interfaces;

import java.util.List;

import Lab.CarRentalSystem.dto.Bank.BankDTO;
import Lab.CarRentalSystem.models.Bank;

public interface IBankService {

    Bank register(BankDTO bankDTO);

    List<Bank> getAllBanks();

    Bank getBankById(Long id);

    Bank updateBank(Long id, BankDTO bankDTO);

    void deleteBank(Long id);
}
