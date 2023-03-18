package cn.jianwoo.chatgpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import cn.jianwoo.openai.chatgptapi.auth.OpenAiAuth;
import cn.jianwoo.openai.chatgptapi.bo.CompletionReq;
import cn.jianwoo.openai.chatgptapi.bo.MessageReq;
import cn.jianwoo.openai.chatgptapi.constants.Model;
import cn.jianwoo.openai.chatgptapi.constants.Role;
import cn.jianwoo.openai.chatgptapi.exception.ApiException;
import cn.jianwoo.openai.chatgptapi.service.PostApiService;
import cn.jianwoo.openai.chatgptapi.service.impl.ChatGptApiPost;

/**
 * ChatGpt API服务
 *
 * @blog https://jianwoo.cn
 * @author gulihua
 * @github https://github.com/gulihua10010/
 * @bilibili 顾咕咕了
 * @date 2023-02-22 14:32
 */
public class Main
{
    public static void main(String[] args)
    {
        ArgsBO bo = parseAgrs(args);
//        System.out.println(bo);
        System.out.format("\33[32;6mChatGPT - 一个用命令行进行AI聊天的小工具~\33[0m%n");
        System.out.format("\33[32;6m用法: java -jar chatGpt.jar -api=<API_KEY> -context=false -proxy=<PROXY>\33[0m%n");
        System.out.format("\33[32;6m说明: api-----------你的 API Key(必填)\33[0m%n");
        System.out.format(
                "\33[32;6m     context-------是否记住上下文(可选,默认否),如果为true,需要把之前所有的对话都作为请求体进行发送,这样token消耗就会很快!\33[0m%n");
        System.out.format("\33[32;6m     proxy---------代理(可选)\33[0m%n");
        System.out.format("\33[32;6mGithub: https://github.com/gulihua10010/ChatGpt~\33[0m%n");
        System.out.format("\33[32;6m博客 'https://jianwoo.cn\33[0m%n");
        System.out.format("\33[32;6m请按两次回车进行绘画请求\33[0m%n");
        System.out.format("\33[32;6m输入exit退出会话\33[0m%n");
        if (StrUtil.isBlank(bo.getApiKey()))
        {
            System.out.format("\33[31;6m参数api不能为空!\33[0m%n");
            System.exit(1);
        }

        try
        {
            // 验证 API
            Proxy proxy = null;
            if (bo.getProxyHsot() != null)
            {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(bo.getProxyHsot(), bo.getProxyPort()));
            }
            PostApiService service = new ChatGptApiPost(new OpenAiAuth(bo.getApiKey(), proxy));
            System.out.println();
            System.out.println("验证中...");
            try
            {
                service.models();
            }
            catch (ApiException e)
            {
                if ("400001".equals(e.getCode()))
                {
                    System.out.format(
                            "\33[31;6mAPI Key 错误! 请到 https://platform.openai.com/account/api-keys 检查您的API Key是否有效!\33[0m%n");
                    System.exit(1);
                }
                else
                {
                    throw e;
                }
            }
            System.out.println("API Key验证成功!");
            List<MessageReq> messages = new ArrayList<>();
            String prompt = "";
            while (true)
            {
                prompt = getInput("\n你:");
                if (StrUtil.isBlank(prompt))
                {
                    System.out.format("\33[31;6m请输入问题或消息哦~\33[0m%n");
                    continue;

                }
                if ("exit".equals(prompt))
                {
                    System.out.format("\33[32;6m退出成功~\33[0m%n");
                    System.exit(0);
                }
                CompletionReq req = CompletionReq.builder().model(Model.GPT_35_TURBO.getName()).build();
                messages.add(MessageReq.builder().role(Role.USER.getName()).content(prompt).build());
                req.setMessages(messages);
                System.out.format("\33[35;6mChatGpt:\33[0m%n");

                AtomicBoolean isContinue = new AtomicBoolean(false);
                StringBuilder answer = new StringBuilder();
                service.completionsChatStream(req, res -> {
                    // 回调方法
                    if (res != null)
                    {
                        if ("\n\n".equals(res.getChatContent()))
                        {
                            return;
                        }
                        isContinue.set(res.getDone());
                        answer.append(res.getChatContent());
                        System.out.print(res.getChatContent());
                    }
                });
                while (!isContinue.get())
                {
                    Thread.sleep(500);
                }
                if (bo.getContext())
                {
                    messages.add(
                            MessageReq.builder().role(Role.ASSISTANT.getName()).content(answer.toString()).build());
                }

            }

        }
        catch (Exception e)
        {
            System.out.format("\33[31;6m运行出错!!\33[0m%n");
            System.out.format("\33[31;6m错误信息:" + e.getMessage() + "\33[0m%n");

        }

    }


    private static ArgsBO parseAgrs(String[] args)
    {
        List<String> params = Arrays.asList(args);
        ArgsBO bo = new ArgsBO();
        for (String arg : params)
        {
            String[] arr = arg.split("=");
            if ("-apiKey".equals(arr[0]))
            {
                bo.setApiKey(arr[1]);
            }
            else if ("-context".equals(arr[0]))
            {
                if ("true".equalsIgnoreCase(arr[1]))
                {
                    bo.setContext(true);
                }
                else
                {
                    bo.setContext(false);
                }
            }
            else if ("-proxy".equals(arr[0]))
            {
                String proxy = arr[1];
                String[] proxyArr = proxy.split(":");
                bo.setProxyHsot(proxyArr[0]);
                if (proxyArr.length > 1)
                {
                    try
                    {
                        bo.setProxyPort(Integer.parseInt(proxyArr[1]));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.format("\33[31;6m代理端口号不是正确的端口!\33[0m%n");
                        System.exit(1);
                    }
                }
                else
                {
                    bo.setProxyPort(7890);
                }
                if (bo.getProxyPort() < 0 || bo.getProxyPort() > 65535)
                {
                    System.out.format("\33[31;6m代理端口号不是正确的端口!\33[0m%n");
                    System.exit(1);
                }
            }
        }
        if (bo.getContext() == null)
        {
            bo.setContext(false);
        }
        return bo;
    }


    private static String getInput(String prompt)
    {
        System.out.format("\33[34;6m" + prompt + "\33[0m%n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();
        String line;
        try
        {
            while ((line = reader.readLine()) != null && !line.isEmpty())
            {
                lines.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines.stream().collect(Collectors.joining("\n"));
    }

}
