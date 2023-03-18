# ChatGPT Command line client
<p align=center>
  <img src="logo.png" alt="logo.png" width="200" />
</p>

[中文](README.md)

<p align=center>

A command line client tool based on ChatGPT 3.5 + Java 8. If you feel good, please go to Star.
</p>
<p align="center">
    	<img src="https://img.shields.io/hexpm/l/plug.svg" ></img>
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
        <img src="https://img.shields.io/badge/hutools--all-5.4.3-brightgreen" ></img>
        <img src="https://img.shields.io/badge/okhttp--sse-3.14.9-orange" ></img>

</p>

**Chatgpt wechat mini-program is now online! Small program search "微 AI 聊天", look for this logo icon can be, welcome to experience~**
## Project introduction
This project based on ChatGPT development of a command line client, support continuous dialogue!

## Project Github

Currently, the project is hosted on **Gitee** and **Github** * platforms. Welcome to **Star** and **Fork** * support ~

- Gitee地址：https://gitee.com/gulihua/ChatGpt
- Github地址：https://github.com/gulihua10010/ChatGpt


## Other ChatGPt-related projects, if you think you can support them
- Wechat mini-program based on uni-app:https://github.com/gulihua10010/wxmini-chatgpt
- Wechat mini-program server API program:https://github.com/gulihua10010/chatGptApi
- openAi Auth SDK:https://github.com/gulihua10010/openAiAuth
- chatGpt API SDK:https://github.com/gulihua10010/chatGptApiSdk
- chatGpt command line client:https://github.com/gulihua10010/ChatGpt
   
## Fast start
The Java(jdk1.8+) environment needs to be configured
```bash
java -jar chatGpt.jar -apiKey=sk-*************************************** -context=false -proxy=127.0.0.1:7890 -lang=zh_cn
```
### 用法说明
```
api-----------Your API Key(required)
context-------Whether to remember the context (optional, default false), if true, you need to send all previous conversations as the body of the request, so the token consumption is fast!
proxy---------Http proxy(optional)
lang----------Language(optional, default Chinese)
Enter [exit] to exit the session
```
### Demo
```bash
java -jar chatGpt.jar -apiKey=sk-*************************************** -context=false -proxy=127.0.0.1:7890 -lang=zh_cn
🤖ChatGPT - A command line AI chat tool ~🤖
Usage: java -jar chatGpt.jar -api=<API_KEY> -context=false -proxy=<PROXY> -lang=zh_cn
Description: api-----------Your API Key(required)
             context-------Whether to remember the context (optional, default false), if true, you need to send all previous conversations as the body of the request, so the token consumption is fast!
             proxy---------Http proxy(optional)
             lang----------Language(optional, default Chinese)
Github: https://github.com/gulihua10010/ChatGpt~
Blog: https://jianwoo.cn
Please press Enter twice for a session request
Enter [exit] to exit the session

Verification in progress...
API Key verification successful!

You:
hi

ChatGpt:
Hello! How can I assist you today?
You:
exit
```

## Introduction
ChatGPT is a world-renowned conversational AI model developed by OpenAI, known for generating human-like responses to various prompts and queries. With its cutting-edge capabilities, ChatGPT is a valuable asset for chatbots, virtual assistants, and other natural language processing applications.

The ChatGPT API is a powerful tool that allows developers to integrate the ChatGPT model into their own applications. However, to use this API, users need to have an OpenAI API key and pay for usage.

## The Open ChatGPT Initiative
ChatGPT represents a new height in the development of artificial intelligence and human technology. It has completely altered the way in which we acquire knowledge and elevated the writing and expression abilities of many. With its formidable language comprehension and text generation capabilities, ChatGPT has become a tool of productivity and creativity for humanity.

However, when I share ChatGPT with the general public, those without an AI background, the response I receive is not just one of praise and wonder but also of scepticism and doubt.

Some claim to have seen many chatbots before and that ChatGPT is just one among many. The chatbots of the past were unable to understand the intent behind human expression. But ChatGPT is not like them. Its powerful language comprehension and generation capabilities make it an indispensable tool for the future.

Others believe that artificial intelligence cannot surpass human wisdom, that it is merely learning from the data created by humans. Regardless of the truth, artificial intelligence does not need to surpass the creativity of all humanity to affect our daily lives. In fact, as long as artificial intelligence surpasses the capabilities of each individual, it can better assist people and bring a transformative impact to our work and way of life.

Still others claim that ChatGPT produces erroneous content, fabricates false information, or engages in seemingly logical but erroneous reasoning and that we need to stop using it. But doing so is like throwing out the baby with the bathwater. ChatGPT is indeed useful because it simplifies the process of finding information, as fact-checking is always easier than searching for information from scratch. Moreover, as artificial intelligence technology is increasingly being applied, we should become familiar with ChatGPT and other AI tools, understand in which areas they are prone to errors, and better avoid them rather than simply shunning them.

Yet others believe that using ChatGPT to automatically obtain answers impedes the development of critical thinking and problem-solving abilities. However, using ChatGPT does not mean delegating human thought to machines or stopping human thinking. ChatGPT cannot automatically provide profound insights or perceptive ideas. In fact, when it comes to uncommon questions, ChatGPT can only provide general and trite opinions. The key is for the user to provide the opinions and for ChatGPT to create a coherent article.

Another argument claims that the content created by ChatGPT is soulless as it lacks consciousness in its creation. However, the correctness of this argument is not important. The most important point is that artificial intelligence technology will undoubtedly become increasingly advanced in the future. Imagine the world twenty years from now, where artificial intelligence is a ubiquitous part of daily life. Looking back at ChatGPT, people may have different feelings. We must now familiarise ourselves with artificial intelligence technology because it is becoming more deeply integrated into our daily lives.

Now, let us take a closer look at the technology behind ChatGPT: natural language processing. Even within the NLP field itself, many people have not yet realised the disruptive impact of ChatGPT.

The evolution of research methodologies of natural language processing, from rule-based methods to machine learning to deep learning, is a testament to the complexity of human languages. The complexity determines that natural language processing is not as simple as writing a few rules. It requires constant improvement and exploration. Now, coreference resolution, dependency parsing, and part-of-speech tagging have all been discarded by natural language processing. In the future, even named entity recognition and grammatical error correction will seem comical. With ChatGPT revolutionising the NLP landscape, NLP researchers must actively familiarise themselves with this cutting-edge technology to embrace this new era.
## Environment construction

### Development tool

|  Tools  |         Description         |                 Website                 |
|:-------:|:---------------------------:|:---------------------------------------:|
|  IDEA   |    Java development IDE     | https://www.jetbrains.com/idea/download |
| Postman |     API debugging tool      |        https://www.postman.com/         |

### Development environment

| Tools  | Version |                                       Download                                       |
| :---: |:-------:|:------------------------------------------------------------------------------------:|
|  JDK  |   1.8   | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Maven | 3.3.0+  |                               http://maven.apache.org/                               |
| Nginx |  1.10   |                          http://nginx.org/en/download.html                           |


## Contribute code
Open source projects cannot do without everyone's support. If you have a good idea and meet some bugs ** and fix them, you are welcome to submit **Pull Request** and participate in open source contribution

1. **fork** this project to your own **repo**
2. clone the past projects in your repository to your local location
3. Modify the code
4. **commit** and then **push** to your own library
5. Launch **PR** (**pull request**) request and submit it to **Nacos** branch
6. Wait for authors to merge


## Privacy policy
1. This project attaches great importance to privacy and is committed to protecting users' privacy. The server program does not store or log the account password, accessToken, API-Key, user information, etc., which can be safely used.
   Developers give high priority to the security of your API key /Access Token/ account information and handle it with great care. Your key will be stored exclusively in your applet/browser and will never be shared with any third party entity. It is used only for the intended use for which it is authorized and not for any other unauthorized use. The source code for the project can be examined to verify this claim. If you do not trust this project, you can use the API-Key method and delete the relevant API-Key from the official website at any time.
2. But, OpenAI API will be according to their data/data use policy (https://openai.com/policies/privacy-policy) will be reserved for 30 days.

## Disclaimer
1. This program is only for entertainment, and only for testing and study! Commercial use of the source code is prohibited. The legality, accuracy, completeness and validity of the source code cannot be guaranteed. Please make your own judgment according to the circumstances.
2. The Developer shall not be responsible for any problems caused by any user actions, including but not limited to any loss or damage caused by any script errors.
3. Do not use any content of this project for commercial or illegal purposes, or you will be liable for the consequences.
4. Users who view this site in any way should read this statement carefully. The author reserves the right to change or add to this disclaimer at any time.
   You are deemed to have accepted this disclaimer once you have used and operated the program (including but not limited to testing, deploying, running locally or on a server).

## Open source protocol

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0.html)

## Admire

** Server ** and ** domain name ** and other services purchase and renewal will ** produce a certain cost **, in order to ** maintain the normal operation of the project **, if you think this project ** helpful **, welcome friends to give some support
