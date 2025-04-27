package Controller.Service;

import Controller.dto.AccountsDto;

import java.util.List;

public interface AccountserviceSvc {
    public List<AccountsDto> getAccountBalance(AccountsDto accountDto);

}
