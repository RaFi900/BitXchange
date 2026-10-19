package com.example.bitxchange.controller;

import com.example.bitxchange.model.AssetSnapshot;
import com.example.bitxchange.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssetController
{
   private final AssetService assetService;

   public AssetController(AssetService assetService)
   {
       this.assetService = assetService;
   }

   @GetMapping("/asset")
   public String showAssetPage(Model model)
   {
       AssetSnapshot snapshot = assetService.calculateCureentAssets();
       model.addAttribute("totalValue", snapshot.getTotalValue());
       model.addAttribute("coinHoldings", snapshot.getCoinHoldings());
       return "asset";
   }
}
