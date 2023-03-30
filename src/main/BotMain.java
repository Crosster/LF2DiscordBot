package main;

import javax.security.auth.login.LoginException;

import event.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotMain {

	private Dotenv config;
	private JDA jda;
	public Dotenv getConfig() {
		return config;
	}
	public JDA getJda() {
		return jda;
	}
	
	private void botInitailzed() throws LoginException{
		config = Dotenv.configure().load();
		String DISCORD_BOT_TOKEN = config.get("DISCORD_BOT_TOKEN");
		jda = JDABuilder.createDefault(DISCORD_BOT_TOKEN)
				.setStatus(OnlineStatus.ONLINE)
				.setActivity(Activity.playing("LF2.exe"))
				.enableIntents(GatewayIntent.MESSAGE_CONTENT)
				.build();
	}
	public static void main(String[] args) throws LoginException {
        BotMain bot = new BotMain();
        bot.botInitailzed();
        bot.getJda().addEventListener(new MessageListener());
        
        
        
        
        
    }

}