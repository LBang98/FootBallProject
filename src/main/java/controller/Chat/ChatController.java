
package controller.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.dto.ChatDto;

@Controller
public class ChatController {
    private List<ChatDto> messages;

    ChatController() {
        messages = new ArrayList<>();
    }

    @RequestMapping("/chat/main")
    public String showMain() {
        return "chat/main";
    }

    @RequestMapping("/chat/addMessage")
    @ResponseBody
    public Map addMessage(String writer, String body) {

        long id = messages.size();
        ChatDto newChatMessage = new ChatDto(id, writer, body);
        messages.add(newChatMessage);

        Map rs = new HashMap<String, Object>();

        rs.put("msg","메시지가 전송되었습니다.");

        return rs;
    }

    @RequestMapping("/chat/getAllMessages")
    @ResponseBody
    public List getAllMessages() {
        return messages;
    }

    @RequestMapping("/chat/getMessages")
    @ResponseBody
    public List getMessages(int from) {
        return messages.subList(from, messages.size());
    }

    @RequestMapping("/chat/clear")
    @ResponseBody
    public String clear() {
        messages.clear();
        return "메시지 전체 삭제";
    }

}