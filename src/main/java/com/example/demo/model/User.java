//사용자 정보를 나타내는 모델클래스

package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
//@Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode 어노테이션을 한꺼번에 설정해주는 어노테이션
public class User {
    @Id
    private Integer uid;
    private String user_id;
    private String user_name;
    private String password;
    private String email;
    private String phone;
    private String company_name;
    private String address;
}
