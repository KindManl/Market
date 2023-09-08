package spring;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parsing.Citilink;
import parsing.Product;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@RestController
public class Controller {

    static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private SearchService searchService;

    @CrossOrigin(origins = {"http://localhost:3000",
                            "http://51.250.108.33:3000/"})
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @CrossOrigin(origins = {"http://localhost:3000",
                            "http://51.250.108.33:3000/"})
    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String toSearch,
                                         @RequestParam(required = false) String login,
                                         @RequestParam(required = false, name = "lp") Integer low_price,
                                         @RequestParam(required = false, name = "hp") Integer high_price,
                                         @RequestParam(required = false) Boolean price_order,
                                         @RequestParam(required = false) Boolean name_order,
                                         @RequestParam(required = false, defaultValue = "0") Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer page_size,
                                         @RequestParam(required = false) Float rating,
                                         @RequestParam(required = false) String market) {
        return searchService.getProductsResponse(toSearch, login, low_price, high_price, price_order, name_order, page, page_size, rating, market);
    }

    @CrossOrigin(origins = {"http://localhost:3000",
                            "http://51.250.108.33:3000/"})
    @GetMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        return JSON.toJSONString(Main.dbWorker.findUser(login, password));
    }

    @CrossOrigin(origins = {"http://localhost:3000",
                            "http://51.250.108.33:3000/"})
    @GetMapping("/registration")
    public String registration( @RequestParam String login,
                                @RequestParam String password) {
        if (Main.dbWorker.addUser(login, password))                                     
            return JSON.toJSONString(Main.dbWorker.findUser(login, password));
        return JSON.toJSONString("Данный пользователь уже зарегистрирован!");
    }

    @CrossOrigin(origins = {"http://localhost:3000",
                            "http://51.250.108.33:3000/"})
    @GetMapping("/getHistory")
    public String getHistory(@RequestParam String login) {
        List<String> result = Main.dbWorker.findHistory(login);
        if (result == null)
            return JSON.toJSONString("");
        return JSON.toJSONString(result);
    }
}
