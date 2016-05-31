/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.paypal;

import java.math.BigDecimal;

import org.appcelerator.kroll.KrollModule;

import android.net.Uri;

import com.paypal.android.sdk.payments.PayPalConfiguration;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.kroll.common.Log;

import com.paypal.android.sdk.payments.PayPalItem;

import java.math.BigDecimal;

@Kroll.module(name = "Paypal", id = "ti.paypal")
public class PaypalModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "PaypalModule";

	public String clientIdSandbox;
	public String clientIdProduction;
	public static String CLIENT_ID;

	public static String CONFIG_ENVIRONMENT;

	@Kroll.constant
	public static final int ENVIRONMENT_SANDBOX = 0;
	@Kroll.constant
	public static final int ENVIRONMENT_PRODUCTION = 1;
	@Kroll.constant
	public static int PAYMENT_INTENT_SALE = 0;
	@Kroll.constant
	public static int PAYMENT_INTENT_AUTHORIZE = 1;
	@Kroll.constant
	public static int PAYMENT_INTENT_ORDER = 2;

	public PaypalModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
	}

	@Kroll.method
	public void initialize(KrollDict args) {
		if (args.containsKeyAndNotNull("clientIdSandbox")) {
			clientIdSandbox = TiConvert.toString(args.get("clientIdSandbox"));
		}
		if (args.containsKeyAndNotNull("clientIdProduction")) {
			clientIdProduction = TiConvert.toString(args
					.get("clientIdProduction"));
		}
		if (args.containsKeyAndNotNull("environment")) {
			CONFIG_ENVIRONMENT = "" + TiConvert.toInt(args.get("environment"));
		}
		if (CONFIG_ENVIRONMENT == "" + ENVIRONMENT_SANDBOX) {
			CLIENT_ID = "0";
		}
		if (CONFIG_ENVIRONMENT == "" + ENVIRONMENT_PRODUCTION) {
			CLIENT_ID = "1";
		}

	}

	@Kroll.method
	public PayPalConfiguration createConfiguration(KrollDict args) {
		String merchantName="", merchantPrivacyPolicyURL="", merchantUserAgreementURL="", locale="en";
		if (args.containsKeyAndNotNull("merchantName")) {
			merchantName = TiConvert.toString(args.get("merchantName"));
		} else
			Log.d(LCAT, "merchantName is missing");

		if (args.containsKeyAndNotNull("merchantPrivacyPolicyURL")) {
			merchantPrivacyPolicyURL = TiConvert.toString(args
					.get("merchantPrivacyPolicyURL"));
		} else
			Log.d(LCAT, "merchantPrivacyPolicyURL is missing");

		if (args.containsKeyAndNotNull("merchantUserAgreementURL")) {
			merchantUserAgreementURL = TiConvert.toString(args
					.get("merchantUserAgreementURL"));
		} else
			Log.d(LCAT, "merchantUserAgreementURL is missing");

		if (args.containsKeyAndNotNull("locale")) {
			locale = TiConvert.toString(args.get("locale"));
		} else
			Log.d(LCAT, "locale is missing");

		PayPalConfiguration config = new PayPalConfiguration()
				.environment(PaypalModule.CONFIG_ENVIRONMENT)
				.clientId(PaypalModule.CLIENT_ID).merchantName(merchantName)
				.merchantPrivacyPolicyUri(Uri.parse(merchantPrivacyPolicyURL))
				.merchantUserAgreementUri(Uri.parse(merchantUserAgreementURL));
		return config;
	}

	@Kroll.method
	public PayPalItem createPaymentItem(KrollDict args) {
		String name = "", sku = "", currency = "EU";
		BigDecimal price = new BigDecimal(0);
		int quantify = 1;
		if (args.containsKeyAndNotNull("name")) {
			name = TiConvert.toString(args.get("name"));
		} else
			Log.d(LCAT, "name is missing");
		if (args.containsKeyAndNotNull("price")) {
			price = new BigDecimal(TiConvert.toString(args.get("price")));
		} else
			Log.d(LCAT, "price is missing");
		if (args.containsKeyAndNotNull("sku")) {
			sku = TiConvert.toString(args.get("sku"));
		} else
			Log.d(LCAT, "sku is missing");
		if (args.containsKeyAndNotNull("quantify")) {
			quantify = TiConvert.toInt(args.get("quantify"));
		} else
			Log.d(LCAT, "quantify is missing");
		if (args.containsKeyAndNotNull("currency")) {
			currency = TiConvert.toString(args.get("currency"));
		} else
			Log.d(LCAT, "currency is missing");
		return new PayPalItem(name, quantify, price, currency, sku);
	}
}
