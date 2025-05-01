package ma.artisanat.post_service.client;


import ma.artisanat.post_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:9999/api/users")
public interface UserClient {
    @GetMapping("/id/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
/*
    @GetMapping("/username/{username}")
    UserDTO getUserByUsername(@PathVariable("username") String username);

    @PutMapping("/users/{id}/names")
    void updateUserNames(@PathVariable("id") Long id, @RequestBody UserDTO userDTO);

*/

}