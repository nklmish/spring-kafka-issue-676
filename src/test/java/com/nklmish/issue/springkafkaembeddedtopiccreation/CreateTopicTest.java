package com.nklmish.issue.springkafkaembeddedtopiccreation;

import org.apache.kafka.clients.consumer.Consumer;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

public class CreateTopicTest {

    private static final String SOME_TOPIC = "bar";

    static {
        KafkaEmbeddedHolder.getKafkaEmbedded().addTopics(SOME_TOPIC);
    }

    private static KafkaEmbedded embeddedKafka = KafkaEmbeddedHolder.getKafkaEmbedded();

    private static Consumer<String, String> consumer;

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @BeforeClass
    public static void setUp() throws Exception {
        Map<String, Object> configs = KafkaTestUtils.consumerProps("whatever", "false", embeddedKafka);
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(configs);
        consumer = cf.createConsumer();
        //This line will throw assertion error
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, SOME_TOPIC);
    }

    @Test
    public void testFoo() {
        System.out.println("done");
    }
}
