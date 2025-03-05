package com.macedo.issdemo;

import com.pushtechnology.diffusion.client.Diffusion;
import com.pushtechnology.diffusion.client.features.control.topics.TopicControl;
import com.pushtechnology.diffusion.client.session.Session;
import com.pushtechnology.diffusion.client.topics.details.TopicType;

/*sets up an admin session and creates three topics*/
public class ISSServer {

    private final Session session;

    ISSServer(){
        session = Diffusion.sessions()
                .principal("admin")
                .password("password")
                .open("ws://localhost:8080");

        createTopics();
    }

    private void createTopics(){

        final TopicControl topicControl = session.feature(TopicControl.class);
        topicControl.addTopic("iss/position/longitude", TopicType.DOUBLE);
        topicControl.addTopic("iss/position/latitude", TopicType.DOUBLE);
        topicControl.addTopic("iss/timestamp", TopicType.INT64);
    }

    Session getSession() {
        return session;
    }

}
