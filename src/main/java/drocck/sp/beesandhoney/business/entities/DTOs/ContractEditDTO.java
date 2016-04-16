package drocck.sp.beesandhoney.business.entities.DTOs;

import drocck.sp.beesandhoney.business.entities.Contract;

/**
 * Created by Robert Wilk
 * on 4/1/2016.
 */
public class ContractEditDTO {

    private ContractCreateDTO contractCreateDTO;
    private Contract contract;

    public ContractEditDTO(ContractCreateDTO contractCreateDTO, Contract contract) {
        this.contractCreateDTO = contractCreateDTO;
        this.contract = contract;
    }

    public ContractCreateDTO getContractCreateDTO() {
        return contractCreateDTO;
    }

    public void setContractCreateDTO(ContractCreateDTO contractCreateDTO) {
        this.contractCreateDTO = contractCreateDTO;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
