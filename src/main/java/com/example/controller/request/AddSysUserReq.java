package com.example.controller.request;

import lombok.Data;

/**
 * @author zhaolei
 * Create: 2020/2/25 14:06
 * Modified By:
 * Description:
 */
@Data
public class AddSysUserReq {
    private String userName;
    private String password;
    private String phone;
    private String mailBox;
}
