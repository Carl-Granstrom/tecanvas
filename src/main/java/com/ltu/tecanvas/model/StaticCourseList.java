package com.ltu.tecanvas.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class StaticCourseList {
    List<URL> urlList;
    URL grafiska = null;
    URL dataKommunikation = null;

    public List<URL> getList(){
        try{
            URL grafiska = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q043gZ6y5Y120276Y75Y.json");
            URL dataKommunikation = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q053gZ6y5Y220976Y75Y.json");
        }
        catch(MalformedURLException e){
            System.out.println(e);
        }
        urlList.add(grafiska);
        urlList.add(dataKommunikation);

        return urlList;
    }

}
