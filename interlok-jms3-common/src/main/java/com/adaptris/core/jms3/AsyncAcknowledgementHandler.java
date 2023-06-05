package com.adaptris.core.jms3;

import jakarta.jms.CompletionListener;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

public class AsyncAcknowledgementHandler implements AcknowledgementHandler, CompletionListener {

  @Override
  public void acknowledgeMessage(JmsActorConfig actor, Message message) throws JMSException {
    // do nothing
  }

  @Override
  public void rollbackMessage(JmsActorConfig actor, Message message) {
    // do nothing
  }

  @Override
  public void onCompletion(Message message) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onException(Message message, Exception exception) {
    // TODO Auto-generated method stub
    
  }

}
