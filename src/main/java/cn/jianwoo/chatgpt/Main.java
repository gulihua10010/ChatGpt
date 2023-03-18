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
import cn.hutool.setting.dialect.Props;
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
    private static final String LANG_EN = "en";
    private static final String LANG_ZH_CN = "zh_cn";
    private static Props props = new Props("i18n/zh_cn.properties");

    public static void main(String[] args)
    {
        ArgsBO bo = parseAgrs(args);
        System.out.println(bo);
        if (LANG_EN.equals(bo.getLang()))
        {
            props = new Props("i18n/en.properties");
        }
        printTitle(props.getProperty("TIPS1"));
        print(props.getProperty("TIPS2"));
        print(props.getProperty("TIPS3"));
        print(props.getProperty("TIPS3_1"));
        print(props.getProperty("TIPS3_2"));
        print(props.getProperty("TIPS3_3"));
        print(props.getProperty("TIPS6"));
        print(props.getProperty("TIPS7"));
        print(props.getProperty("TIPS8"));
        print(props.getProperty("TIPS9"));
        if (StrUtil.isBlank(bo.getApiKey()))
        {
            printErr(props.getProperty("ERR1"));
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
            print(props.getProperty("TIPS10"));

            try
            {
                service.models();
            }
            catch (ApiException e)
            {
                if ("400001".equals(e.getCode()))
                {
                    printErr(props.getProperty("ERR2"));
                    System.exit(1);
                }
                else
                {
                    throw e;
                }
            }
            print(props.getProperty("TIPS11"));
            List<MessageReq> messages = new ArrayList<>();
            String prompt = "";
            while (true)
            {
                prompt = getInput(props.getProperty("TIPS12"));
                if (StrUtil.isBlank(prompt))
                {
                    printErr(props.getProperty("ERR3"));
                    continue;

                }
                if ("exit".equals(prompt))
                {
                    print(props.getProperty("TIPS13"));
                    System.exit(0);
                }
                CompletionReq req = CompletionReq.builder().model(Model.GPT_35_TURBO.getName()).build();
                messages.add(MessageReq.builder().role(Role.USER.getName()).content(prompt).build());
                req.setMessages(messages);
                print(props.getProperty("TIPS14"));

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
            printErr(props.getProperty("ERR4"));
            printErr(props.getProperty("ERR5") + e.getMessage());

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
                        printErr(props.getProperty("ERR6"));
                        System.exit(1);
                    }
                }
                else
                {
                    bo.setProxyPort(7890);
                }
                if (bo.getProxyPort() < 0 || bo.getProxyPort() > 65535)
                {
                    printErr(props.getProperty("ERR6"));
                    System.exit(1);
                }
            }
            else if ("-lang".equals(arr[0]))
            {
                if (LANG_EN.equals(arr[1]))
                {
                    bo.setLang(LANG_EN);
                }
                else
                {
                    bo.setLang(LANG_ZH_CN);

                }
            }
        }
        if (bo.getContext() == null)
        {
            bo.setContext(false);
        }
        if (bo.getLang() == null)
        {
            bo.setLang(LANG_ZH_CN);
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


    private static void printTitle(String msg)
    {
        System.out.format("\33[33;1m%s\33[0m%n", msg);
    }


    private static void print(String msg)
    {
        System.out.format("\33[32;6m%s\33[0m%n", msg);
    }


    private static void printErr(String msg)
    {
        System.out.format("\33[31;6m%s\33[0m%n", msg);
    }
}
