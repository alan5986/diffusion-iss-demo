package com.macedo.issdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pushtechnology.diffusion.client.features.TopicUpdate;
import com.pushtechnology.diffusion.client.session.Session;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*grabs JSON data from ISS api and updates the topics*/
public class DataHandler {

    private Session session;
    private Double longitude;
    private Double latitude;
    private Long timestamp;
    private URL url = new URL("http://api.open-notify.org/iss-now.json");
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, Object> data;
    private Map<String, String> position;

    DataHandler(Session session) throws IOException {
        this.session = session;
    }

    private void getData() throws IOException {

        data = mapper.readValue(url, HashMap.class);
        position = (Map<String, String>) data.get("iss_position");

        longitude = Double.parseDouble((String) position.get("longitude"));
        latitude = Double.parseDouble((String) position.get("latitude"));
        timestamp = Long.parseLong(data.get("timestamp").toString());
    }

    public void updateTopics() throws IOException {
        getData();
        session.feature(TopicUpdate.class).set("iss/position/longitude", Double.class, longitude);
        session.feature(TopicUpdate.class).set("iss/position/latitude", Double.class, latitude);
        session.feature(TopicUpdate.class).set("iss/timestamp", Long.class, timestamp);
    }
}
