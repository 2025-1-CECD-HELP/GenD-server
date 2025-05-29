package com.cecd.help.core.util;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FcmUtil {

    public void sendMessage(String title, String body, String token, Long workspaceId) {
        Map<String, String> putData = new HashMap<>();
        putData.put("workspaceId", String.valueOf(workspaceId));
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .putAllData(putData)
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }
        System.out.println("message " + response);
    }

}
