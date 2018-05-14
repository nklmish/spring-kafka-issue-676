package com.nklmish.issue.springkafkaembeddedtopiccreation;

import org.apache.kafka.common.KafkaException;
import org.junit.runner.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public final class KafkaEmbeddedHolder {

	private static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, false);

	private static boolean started;

	public static KafkaEmbedded getKafkaEmbedded() {
		if (!started) {
			try {
				kafkaEmbedded.before();
			}
			catch (Exception e) {
				throw new KafkaException(e);
			}
			started = true;
		}
		return kafkaEmbedded;
	}

	private KafkaEmbeddedHolder() {
		super();
	}

}