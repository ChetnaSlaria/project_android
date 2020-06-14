package com.sachtech.datingapp.cometChat;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;


public final class CometConstants {
   private static int CALL = 3;
   private static final int SENDERTEXTMESSAGE = 0;
   private static int RECEIVERTEXTMESSAGE = 1;
   @NotNull
   private static String CallListener = "call_listener";
   @NotNull
   private static String MessageListener = "message_listener";
   public static final CometConstants INSTANCE;

   public final int getCALL() {
      return CALL;
   }

   public final int getSENDERTEXTMESSAGE() {
      return SENDERTEXTMESSAGE;
   }

   public final int getRECEIVERTEXTMESSAGE() {
      return RECEIVERTEXTMESSAGE;
   }

   @NotNull
   public final String getCallListener() {
      return CallListener;
   }

   @NotNull
   public final String getMessageListener() {
      return MessageListener;
   }

   private CometConstants() {
   }

   static {
      CometConstants var0 = new CometConstants();
      INSTANCE = var0;
      CALL = 3;
      RECEIVERTEXTMESSAGE = 1;
      CallListener = "call_listener";
      MessageListener = "message_listener";
   }
}
