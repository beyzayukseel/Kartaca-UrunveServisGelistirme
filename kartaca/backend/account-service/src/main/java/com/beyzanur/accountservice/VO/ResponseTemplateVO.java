package com.beyzanur.accountservice.VO;

import com.beyzanur.accountservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private Optional<User> user;
    private Calendar calendar;
}
