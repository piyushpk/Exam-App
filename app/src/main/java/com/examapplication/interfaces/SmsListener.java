package com.examapplication.interfaces;


public interface SmsListener
{
     void messageReceived(String sender, String messageText);
}
