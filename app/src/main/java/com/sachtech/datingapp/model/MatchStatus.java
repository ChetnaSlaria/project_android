// MatchStatus.java
package com.sachtech.datingapp.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class MatchStatus {
   @NotNull
   private static String PENDING = "pending";
   @NotNull
   private static String ACCEPTED = "accepted";
   @NotNull
   private static String DECLINED = "declined";
   public static final MatchStatus INSTANCE;

   @NotNull
   public final String getPENDING() {
      return PENDING;
   }

   @NotNull
   public final String getACCEPTED() {
      return ACCEPTED;
   }

   @NotNull
   public final String getDECLINED() {
      return DECLINED;
   }

   private MatchStatus() {
   }

   static {
      MatchStatus var0 = new MatchStatus();
      INSTANCE = var0;
      PENDING = "pending";
      ACCEPTED = "accepted";
      DECLINED = "declined";
   }
}




