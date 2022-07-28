package com.guocz.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author guocz
 * @date 2022/7/28 10:30
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Boy {

    private Girl girl;
}
