package cl.duoc.ms_quest.client;

import cl.duoc.ms_quest.dto.GoldRequestDto;
import cl.duoc.ms_quest.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-user", url = "http://localhost:8090/api/v1/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @PatchMapping("/{userId}/gold")
    ResponseEntity<Void> updateGoldUser(
            @PathVariable("userId") Long userId,
            @RequestBody GoldRequestDto goldPayload
    );

}