package selfbot.utils;


import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import selfbot.SelfBot;

/**
 * @file CommandParser.java
 * @author Blue
 * @version 0.1
 * @brief Parse the commands given to the bot to the right form withs arguments
 */
public class CommandParser {

    public class CommandContainer {
        public final String raw;
        public final String noprefix;
        public final String[] split_noprefix;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public CommandContainer(String raw, String noprefix, String[] split_noprefix, String invoke, String[] args, MessageReceivedEvent event) {
            this.raw = raw;
            this.noprefix = noprefix;
            this.split_noprefix = split_noprefix;
            this.invoke = invoke;
            this.args = args;
            this.event = event;
        }
    }
    public CommandContainer parse(String str, MessageReceivedEvent event) {
        ArrayList<String> split = new ArrayList<String>();
        String raw = str;
        String noprefix;
        try {
            noprefix = raw.replaceFirst(SelfBot.getPrefix(), "");
        } catch (PatternSyntaxException e) {
            noprefix = raw.replaceFirst("\\" + SelfBot.getPrefix(), "");
        }
        String[] split_noprefix = noprefix.split(" ");
        for(String s: split_noprefix) {split.add(s);}
        String invoke = split.get(0);
        String[] args = new String[split.size() -1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(raw, noprefix, split_noprefix, invoke, args, event);
    }
}
