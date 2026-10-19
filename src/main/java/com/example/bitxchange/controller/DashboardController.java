package com.example.bitxchange.controller;

import com.example.bitxchange.model.MarketCoin;
import com.example.bitxchange.service.AssetCalculationService;
import com.example.bitxchange.service.NewsService;
import com.example.bitxchange.service.WatchListCoinService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController
{

    private WatchListCoinService watchListCoinService;


    private NewsService newsService;



    private AssetCalculationService assetCalculationService;

    public DashboardController(WatchListCoinService watchListCoinService, NewsService newsService, AssetCalculationService assetCalculationService)
    {
        this.watchListCoinService = watchListCoinService;
        this.newsService = newsService;
        this.assetCalculationService = assetCalculationService;
    }

    @GetMapping("/dashboard")
  public String showDashboard(HttpSession session , Model model)
  {
      String userEmail = (String) session.getAttribute("userEmail");
      if (userEmail == null)
      {
          return "redirect:/login";
      }

      List<MarketCoin> watchlist = watchListCoinService.getUserWatchListWithPrice(userEmail);
      System.out.println("Watchlist size: " + watchlist.size());
      model.addAttribute("watchList",watchlist);

      model.addAttribute("newsList", newsService.getLatestNews());
      String totalValue = assetCalculationService.getTotalAssetValue(userEmail);
      model.addAttribute("totalAssetValue", totalValue);
      return "dashboard";
  }

  @PostMapping("/watchlist/add/{symbol}")
  @ResponseBody
  public void addToWatchList(@PathVariable String symbol , HttpSession session)
  {
      String userEmail = (String) session.getAttribute("userEmail");
      if (userEmail != null) {
          watchListCoinService.addToWatchList(symbol, userEmail);
      }
  }

  @PostMapping("/watchlist/remove/{symbol}")
  @ResponseBody
  public void removeFromWatchList(@PathVariable String symbol, HttpSession session)
  {
      String userEmail = (String) session.getAttribute("userEmail");
      if (userEmail != null)
      {
          watchListCoinService.removeFromWatchList(symbol, userEmail);
      }
  }
}
