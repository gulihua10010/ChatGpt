package cn.jianwoo.chatgpt;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author gulihua
 * @Description
 * @date 2023-03-18 02:24
 */
@Data
@ToString
public class ArgsBO implements Serializable
{
    /** API Key */
    private String apiKey;
    /** 是否记住上下文 */
    private Boolean context;
    /** 代理主机 */
    private String proxyHsot;
    /** 代理端口 */
    private Integer proxyPort;

}
