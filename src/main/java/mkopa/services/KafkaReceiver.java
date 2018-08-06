package mkopa.services;

import mkopa.models.RandomString;
import mkopa.models.RandomStringDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private RandomStringDao _randomStringDao;

    @KafkaListener(topics = "${kafka.topic}")
    public void listen(@Payload String message) {
        RandomString randomString = new RandomString(message);
        _randomStringDao.save(randomString);
        LOG.info("received message='{}'", message);
    }

}
