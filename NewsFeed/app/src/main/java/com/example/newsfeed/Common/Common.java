package com.example.newsfeed.Common;

import com.example.newsfeed.Interface.IconBetterIdeaService;
import com.example.newsfeed.Interface.NewsService;
import com.example.newsfeed.Remote.IconBetterIdeaClient;
import com.example.newsfeed.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL="https://newsapi.org/";
    public static final String API_KEY = "c45a48c6060c462bb5bbbe7b393ea4fc";

    //to get the list of newspapers
    public static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    //to get the icons for the newspapaers

    public static IconBetterIdeaService getIconService(){

        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }


    //https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=c45a48c6060c462bb5bbbe7b393ea4fc

    public static String getAPIUrl( String source, String apiKey)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                     .append("&apiKey=")
                     .append(apiKey)
                     .toString();
    }

}
