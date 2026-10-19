package com.example.bitxchange.service;

import com.example.bitxchange.model.Merchant;
import com.example.bitxchange.repository.MerchantRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService
{
  private MerchantRepository merchantRepository;

    @PostConstruct
    public void initData() {
        if (merchantRepository.count() == 0) {
            merchantRepository.saveAll(List.of(
                    new Merchant(null, "CryptoBoss", "Bank Transfer", "2349670853", 1.00, 200000),
                    new Merchant(null, "TradeHero", "bKash/Nagad", "01675954726", 1.01, 200000)
            ));
        }
    }


    @Autowired
    public  MerchantService(MerchantRepository merchantRepository)
    {
        this.merchantRepository = merchantRepository;
    }

    public List<Merchant> getAllMerchants()
    {
        return merchantRepository.findAll();
    }

    public Optional<Merchant> getMerchantById(String id)
    {
        return merchantRepository.findById(id);
    }
}
