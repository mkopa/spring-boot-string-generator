package mkopa.controllers;

import mkopa.models.RandomString;
import mkopa.models.RandomStringDao;
import mkopa.services.KafkaSender;
import mkopa.utils.RandomStringOptions;

import mkopa.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/api/random")
public class RandomStringController {

    @Autowired
    private RandomStringDao _randomStringDao;

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RandomString> get() {
        List<RandomString> result = _randomStringDao.getAll();
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<RandomString> create(@RequestBody RandomStringOptions request) {
        StringGenerator stringGenerator = new StringGenerator();
        if (request.length > 100) {
            request.length = 100;
        }
        List<RandomString> result = stringGenerator.generate(request);

        for (RandomString randomString : result) {
            kafkaSender.send(randomString.getRandomString());
        }

        return result;
    }

}