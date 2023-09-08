package com.sgg.springboot3.boot.record;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: AlbumsRecord
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 16:20
 * @Version 1.0
 */

public record AlbumsRecord(Integer userId, Integer id, String title) {
}
