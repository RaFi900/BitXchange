package com.example.bitxchange.service;

import com.example.bitxchange.model.News;
import com.example.bitxchange.repository.NewsRepository;
import jakarta.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

//    private final String API_KEY = "${NEWSAPI_KEY}";
    @Value("${newsapi.key}")
    private String apiKey;

    private final String API_URL = "https://newsapi.org/v2/everything?q=crypto&apiKey=" + apiKey;



    public List<News> getLatestNews() {
        return newsRepository.findTop5ByOrderByPublishedAtDesc(); //or sort by date,5ta show korbe
    }

    public void fetchAndSaveNewsFromAPI()
    {
        //using external API to fetch and save news
        try
        {
            String apiUrl = "https://newsapi.org/v2/everything?q=crypto&apiKey=" + apiKey;
            RestTemplate restTemplate = new RestTemplate();
            String jsonString= restTemplate.getForObject(apiUrl, String.class);

            JSONObject json = new JSONObject(jsonString);
            JSONArray articles = json.getJSONArray("articles");

            for (int i=0; i < articles.length(); i++)
            {
                JSONObject article = articles.getJSONObject(i);

                String title = article.getString("title");
                String content= article.optString("description");
                String url = article.getString("url");
                String source = article.getJSONObject("source").getString("name");
                String publishedAtStr = article.getString("publishedAt");

                LocalDateTime publishedAt = LocalDateTime.parse(publishedAtStr.replace("z",""), DateTimeFormatter.ISO_DATE_TIME);

                News news =new News(title,content,url,source,publishedAt);
                newsRepository.save(news);
            }
        }
        catch (Exception e)
        {
            System.err.println("Error fetching news : " + e.getMessage());
        }

    }
    @PostConstruct
    public void initNews() {
        fetchAndSaveNewsFromAPI();
    }
    //(3600000 ms=1hour)
    @Scheduled(fixedRate = 3600000)
    public void scheduledNewsUpdate() {
        System.out.println("Fetching latest news from API...");
        fetchAndSaveNewsFromAPI();
    }
}

