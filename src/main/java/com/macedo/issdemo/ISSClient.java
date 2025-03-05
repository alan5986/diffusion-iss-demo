package com.macedo.issdemo;

import com.pushtechnology.diffusion.client.Diffusion;
import com.pushtechnology.diffusion.client.features.Topics;
import com.pushtechnology.diffusion.client.session.Session;
import com.pushtechnology.diffusion.client.topics.details.TopicSpecification;

/*sets up a client session and subscribes to the three topics*/
public class ISSClient {

    private final Session session;
    private String longitude;
    private String latitude;
    private String timestamp;

    public ISSClient(){

        session = Diffusion.sessions()
                .principal("client")
                .password("password")
                .open("ws://localhost:8080");

        subscribe(session);

    }

    public void subscribe(Session session){

        session.feature(Topics.class)
        .addStream("iss/position/longitude", Double.class, new Topics.ValueStream.Default<Double>(){
            public void onValue(String topicPath, TopicSpecification topicSpec, Double oldValue, Double newValue) {
                longitude = newValue.toString();
            }
        });

        session.feature(Topics.class).subscribe("iss/position/longitude");

        session.feature(Topics.class)
        .addStream("iss/position/latitude", Double.class, new Topics.ValueStream.Default<Double>() {
            public void onValue(String topicPath, TopicSpecification topicSpec, Double oldValue, Double newValue) {
                latitude = newValue.toString();
            }
        });

        session.feature(Topics.class).subscribe("iss/position/latitude");

        session.feature(Topics.class)
        .addStream("iss/timestamp", Long.class, new Topics.ValueStream.Default<Long>() {
            public void onValue(String topicPath, TopicSpecification topicSpec, Long oldValue, Long newValue) {
                timestamp = newValue.toString();
            }
        });

        session.feature(Topics.class).subscribe("iss/timestamp");
    }

    public void showISSInfo(){
        System.out.println("Current ISS position:");
        System.out.println("Longitude: " + longitude + ", Latitude: " + latitude);
    }
}
