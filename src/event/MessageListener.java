package event;

import event.feature.OpenAIChatgpt;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
//		非群組成員不回應
		if (!event.isFromGuild())
			return;
//		機器人不回應
		if (event.getAuthor().isBot())
			return;

//		
		Message message = event.getMessage();
		String contentRaw = message.getContentRaw();
//		String contentDisplay = message.getContentDisplay();
		String contentStripped = message.getContentStripped();

//		@提及機器人時 使用chatGpt回復
		if (event.getMessage().getMentions().getMembers().contains(event.getGuild().getSelfMember())) {
			System.out.println(message);
			String chatGPTresult = new OpenAIChatgpt().chat(contentStripped);

			event.getChannel().sendMessage(chatGPTresult).queue();
			System.out.println("MessageReceivedEvent done");

		}

//		echo BOT
		String command = "sayj"; // 你希望 bot 能夠偵測的起始關鍵字
		if (contentRaw.startsWith(command)) {
			String messageExecuted = contentRaw.substring(command.length()).trim(); // 取得起始關鍵字之後的內容，並移除前後空白
			event.getChannel().sendMessage(messageExecuted).queue(); // 回覆訊息
		}
	}

}