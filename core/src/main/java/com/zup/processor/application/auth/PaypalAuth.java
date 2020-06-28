package com.zup.processor.application.auth;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PaypalAuth {

  private PayPalEnvironment environment =
      new PayPalEnvironment.Sandbox(
          System.getProperty("PAYPAL_CLIENT_ID") != null
              ? System.getProperty("PAYPAL_CLIENT_ID")
              : "AURHD219kiLWAAwX0QDRcWzsHRLN63CMZ7HU6b0UIRWhimn5zSzZRccTFtBV3BlA96UTDxNLrhli4edI",
          System.getProperty("PAYPAL_CLIENT_SECRET") != null
              ? System.getProperty("PAYPAL_CLIENT_SECRET")
              : "EP6yRM_qGja4GgLVOcB7JRMXEGDOWCTld0bz2vDuBOBUVv-JZdVgRkJ9JTzX7VWds0svsfHSKg7tQfta");

  PayPalHttpClient client = new PayPalHttpClient(environment);

  protected String getMailToSendTransactions() {
    return System.getProperty("PAYPAL_MAIL_INVOICE") != null
        ? System.getProperty("PAYPAL_MAIL_INVOICE")
        : "evertonsegur5@gmail.com";
  }

  public PayPalHttpClient client() {
    return this.client;
  }
}
