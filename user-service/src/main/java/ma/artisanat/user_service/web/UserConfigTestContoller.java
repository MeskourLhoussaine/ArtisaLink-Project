/*package ma.artisanat.user_service.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserConfigTestContoller {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;
    @Value("${user.params.x1}")
    private String x1;
    @Value("${user.params.y2}")
    private String y2;
    @GetMapping("/params")
    public Map<String, String> getParams() {
        return Map.of("p1", p1, "p2", p2, "x1", x1, "y2", y2);

    }
}
*/