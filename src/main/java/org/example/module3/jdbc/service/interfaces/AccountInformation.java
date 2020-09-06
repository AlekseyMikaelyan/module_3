package org.example.module3.jdbc.service.interfaces;

public interface AccountInformation {
    void setInformationAboutAccountToCsvFile(Long accountId, String from, String to) throws Exception;

    Long getSaldo(Long accountId, String from, String to);

}
