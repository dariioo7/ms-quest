package cl.duoc.ms_quest.client;

import cl.duoc.ms_quest.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user", url = "http://localhost:8090/api/v1/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
