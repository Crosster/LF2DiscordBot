package event.feature;

import java.net.Proxy;
import java.util.Arrays;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;

import io.github.cdimascio.dotenv.Dotenv;

public class OpenAIChatgpt {

//	Proxy proxy = Proxys.http("127.0.0.1", 1080);
	

	public String chat(String s) {
		Dotenv config = Dotenv.configure().load();
		String CHATGPT_API_KEY = config.get("CHATGPT_API_KEY");		

		ChatGPT chatGPT = ChatGPT.builder()
				.apiKey(CHATGPT_API_KEY)
//	            .proxy(proxy)
//	            .apiHost("https://api.openai.com/") //反向代理地址
				.build()
				.init();
		
		s = s.replaceFirst("@邪鬼 ","");
		System.out.println(s);
		String result = chatGPT.chat(s);
		System.out.println(result);
		System.out.println("Done");
		return result;
		
		
		
	}

	public String streamChat(String s) {
		// 国内需要代理 国外不需要
		Proxy proxy = Proxys.http("127.0.0.1", 1080);

		ChatGPTStream chatGPTStream = ChatGPTStream.builder()
				.timeout(600)
				.apiKey("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").proxy(proxy)
				.apiHost("https://api.openai.com/")
				.build()
				.init();

		ConsoleStreamListener listener = new ConsoleStreamListener();
		Message message = Message.of("写一段七言绝句诗，题目是：火锅！");
		ChatCompletion chatCompletion = ChatCompletion.builder()
				.messages(Arrays.asList(message))
				.build();
		chatGPTStream.streamChatCompletion(chatCompletion, listener);
		return null;
	}

}
