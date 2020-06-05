package com.vehicle.business.module;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("bus_sec_user")
@Setter
@Getter
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String userName;

    @TableField("password")
    private String passWord;


}
