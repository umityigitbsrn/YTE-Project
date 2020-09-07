package yte.intern.spring.project.commonlyUsed.forDTOs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseMessage {
    public final String message;
    public final MessageType messageType;
}
