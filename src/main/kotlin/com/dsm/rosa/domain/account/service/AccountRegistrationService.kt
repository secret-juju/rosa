package com.dsm.rosa.domain.account.service

import com.dsm.rosa.domain.account.domain.Account
import com.dsm.rosa.domain.account.external.provider.AccountProviderFactory
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.domain.account.repository.AccountRepository
import com.dsm.rosa.global.attribute.OAuth2Type
import org.springframework.stereotype.Service

@Service
class AccountRegistrationService(
    private val accountRepository: AccountRepository,
) {

    fun registerAccount(oAuth2Token: String, oAuth2Type: OAuth2Type): OAuth2AuthenticationResponse {
        val accountInformation = AccountProviderFactory
            .getAccountProvider(oAuth2Type)
            .searchAccount(oAuth2Token)

        saveAccount(
            accountEmail = accountInformation.accountId,
            accountName = accountInformation.accountName,
        )

        return accountInformation
    }

    private fun saveAccount(accountEmail: String, accountName: String) {
        accountRepository.save(
            Account(
                email = accountEmail,
                name = accountName,
            )
        )
    }
}