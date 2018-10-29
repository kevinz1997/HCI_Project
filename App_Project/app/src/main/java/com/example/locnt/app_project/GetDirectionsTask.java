package com.example.locnt.app_project;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetDirectionsTask {
    private String mRequest;

    public GetDirectionsTask(String _mRequest) {
        this.mRequest = _mRequest;
    }

    public ArrayList<LatLng> testDirection() {
        ArrayList<LatLng> ret = new ArrayList<LatLng>();
        try {
            URL url;
            url = new URL(mRequest);

            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");

            Directions results = new Gson().fromJson(reader, Directions.class);
            Directions.Route[] routes = results.getRoutes();
            Directions.Leg[] leg = routes[0].getLegs();
            Directions.Leg.Step[] steps = leg[0].getSteps();
            for (Directions.Leg.Step step : steps) {
                LatLng latlngStart = new LatLng(step.getStart_location().getLat(),
                        step.getStart_location().getLng());

                LatLng latlngEnd = new LatLng(step.getEnd_location().getLat(),
                        step.getEnd_location().getLng());

                ret.add(latlngStart);
                ret.add(latlngEnd);
            }
            return ret;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
