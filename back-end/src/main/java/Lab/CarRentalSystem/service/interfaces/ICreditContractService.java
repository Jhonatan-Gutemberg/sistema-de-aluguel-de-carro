package Lab.CarRentalSystem.service.interfaces;

import java.util.List;

import Lab.CarRentalSystem.dto.CreditContract.CreditContractDTO;
import Lab.CarRentalSystem.models.CreditContract;

public interface ICreditContractService {

    CreditContract register(CreditContractDTO creditContractDTO);

    List<CreditContract> getAllCreditContracts();

    CreditContract getCreditContractById(Long id);

    // CreditContract updateCreditContract(Long id, CreditContractDTO
    // creditContractDTO);

    boolean deleteCreditContract(Long id);

    List<CreditContract> getCreditContractsByUserId(Long userId);

    CreditContract toggleApprovalStatus(Long id);
}
