package cl.duoc.ms_quest.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class GoldRequestDto {

    @NotNull
    @Min(1)
    int amountToAdd;
}
