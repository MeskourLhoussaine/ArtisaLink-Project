package ma.artisanat.profile_service.client;

import ma.artisanat.profile_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:9999/api/users")
public interface UserClient {
    @GetMapping("/id/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @GetMapping("/username/{username}")
    UserDTO getUserByUsername(@PathVariable("username") String username);
}

