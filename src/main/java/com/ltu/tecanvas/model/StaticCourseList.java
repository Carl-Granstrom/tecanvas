package com.ltu.tecanvas.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticCourseList {
    Map<String, URL> urlList = new HashMap<>();
    URL grafiska = null;
    URL dataKommunikation = null;
    URL affarsSystem = null;
    URL routingSwitching = null;


    public Map<String, URL> getList(){
        try{
            grafiska = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q043gZ6y5Y120276Y75Y.json");
            dataKommunikation = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q053gZ6y5Y220976Y75Y.json");
            affarsSystem = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv5Q053gZ6y5Y220676Y75Y.json");
            routingSwitching = new URL(
                    "https://cloud.timeedit.net/ltu/web/schedule1/ri167XQQ565Z50Qv8Q053gZ6y5Y220376Y75Y.json");
        }
        catch(MalformedURLException e){
            System.out.println(e);
        }
        urlList.put("Grafiska användargränssnitt D0026A", grafiska);
        urlList.put("Datakommunikation i informationssystem D0025N", dataKommunikation);
        urlList.put("Routing och Switching D0024D", routingSwitching);
        urlList.put("Affärssystem - ERP D0026E", affarsSystem);

        return urlList;
    }

}
