package com.example.xddemo.tonglian;

public class SybConstants {
	//正式环境测试参数
	public static final String SYB_ORGID = "";//集团/机构模式下该参数不为空，且appid与key是与次参数对应
	public static final String SYB_CUSID = "990440148166000";
	public static final String SYB_APPID = "00000003";
	public static final String SYB_MD5_APPKEY = "a0ea3fa20dbd7bb4d5abf1d59d63bae8";
	public static final String SYB_APIURL = "https://vsp.allinpay.com/apiweb/unitorder";//生产环境
	public static final String SIGN_TYPE = "SM2";
	public static final String SYB_RSACUSPRIKEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO0HpPUP+eHk//Ba6ZOePvoZVDpOCRtt943oeVfCTllye43bqja1jVIaebX0MgX+yPYnWIQIOJ9ubSH0R4iyY9y1/HR00qkUpfW3/0usBPt9qn7r0xtFHerhVCd4dT2rKb2Oc5IhKOg05cw/BmMFohMkFsqt0jlrUXI8zJOlLIcxAgMBAAECgYA9lt/pAYa3iK5sQOMyhUrt54j4QXCiXPeXOxHUmNuM6G9sU+itoI0hCVoYymP5JNQJCf45CH3WB3Z5/SRdQ6Uoo1cjao6cCohPLxMSfJglsZCHckPH53o25RKEza4njIgKC+yN7HAhanKymhw/yYQ6i0aXq38zFIk8djMtE7R6xQJBAP6jvNy7UhPKO5rxGFKR+MvvbO3qnYH6x0jZCGY3FlxuGfbavueOiFtMeK67FuDv683dcUKi+M48yR4kH5CfIusCQQDuS9KF6mlm3kHAiZWgVhE8VVNYGpRLCRDgAKm4InGmvk5mUv+O1yAtAFVAEHWIgD4awC7Eqf1YFrSF/It9HV9TAkEAsXiU7JJxhfFw0XAvL30lFZ1tIfReinSp6A+7VuIV552k4vNaEjC4wEjv43fpXiRZCEXJ5lOHbNXYpfUvOrBuuQJAOpow8rf8Jc0g1G3Be0XPRUwii/c1YuKe4Meo9VybIIuKkkV1Dba/9fEwBepGTURkgYWjur+nSyOCT7UUxLcVewJAPLig8dVfKpsiNwYuveEYMcFaO5xoRuiB7v+CMmvxpuuK+rrFS+d7RdmwDbnBiDV4JkTgFObUiGvB7MtS+LGfhw==";
	public static final String SYB_RSATLPUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCm9OV6zH5DYH/ZnAVYHscEELdCNfNTHGuBv1nYYEY9FrOzE0/4kLl9f7Y9dkWHlc2ocDwbrFSm0Vqz0q2rJPxXUYBCQl5yW3jzuKSXif7q1yOwkFVtJXvuhf5WRy+1X5FOFoMvS7538No0RpnLzmNi3ktmiqmhpcY/1pmt20FHQQIDAQAB";
//	/**商户sm2私钥,用于向通联发起请求前进行签名**/
	public static final String SYB_SM2PPRIVATEKEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgjj4Rk+b0YjwO+UwXofnHf4bK+kaaY5Btkd8nMP2VimmgCgYIKoEcz1UBgi2hRANCAAQqlALW4qGC3bP1x3wo5QsKxaCMEZJ2ODTTwOQ+d8UGU7GoK/y/WMBQWf5upMnFU06p5FxGooXYYoBtldgm03hq";
//	/**通联平台sm2公钥，用于请求返回或者通联通知的验签**/
	public static final String SYB_SM2TLPUBKEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEBQicgWm0KAMqhO3bdqMUEDrKQvYg8cCXHhdGwq7CGE6oJDzJ1P/94HpuVdBf1KidmPxr7HOH+0DAnpeCcx9TcQ==";

	//测试环境调试参数
//	public static final String SYB_ORGID = "";
//	public static final String SYB_CUSID = "990581007426001";
//	public static final String SYB_APPID = "00000051";
//	public static final String SYB_MD5_APPKEY = "allinpay888";
//	public static final String SYB_APIURL = "https://test.allinpaygd.com/apiweb/unitorder";//生产环境
//	public static final String SIGN_TYPE = "MD5";
	/**商户RSA私钥,用于向通联发起请求前进行签名**/
//	public static final String SYB_RSACUSPRIKEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK5aIo+E1eyWwEIgMB8ZEZRAaWjSAglmfKVhzy8N1eLjAlqPjJgOCqXGEYt/r61AyIjCCJiYVDTHzcqstmbBU7HKpYjTsquCLjRWcL/fhMwMGBSg2bP5mqw5locSOz1gtRujmd3kZo9OIJuWtfG2+wgPPdKUdGZS+5K8WtWCF4z1AgMBAAECgYAPvvqvkPzb9tpqrmsCJ/qvM6kBazP9Ytjfe8ehFYQLT1qrUJsPMXdzNMHpYhD82eSyeymZFGrIcIIMq4/2lD+pYOMQTMGGjoVb2wnQhZFqPdgjXgOQ90E43X69jD3p5F8CuKVNa13I4l3iyfzlVIL780JPdJdug7yKEFdSeOQZUQJBAONlFpIqz87pbnwzfgO5kRTbbI7DcyObb8OEeCK3VlGB3r9P4NoMEDaXm+HnIdv53gnFq+xgbREWUt2nFq9dSUUCQQDESOIdSvIBc3KQTYR+cnlQTH0SOvm0Tlx4KekBCLxTFAFyBqnOBLdVyQb6Z1wxGz855AjnNbHy1rFhUYQ6hPfxAkAIRZUcnBITJMqwGe9rk0SDzbeVOebmVLEsG5WDLcgmDuNbcjxrsiSk178D6LSCnARHtrkaUCenh3hcN8fLeUlBAkABNP2G9pYEYkRbFM7yxBtw3feK7Cfq7uxspL1VD0uxKxdTLy1OIgNKmMDdO1N6zdMWtQtE+LSObLmMgqbQgU7RAkBFX5kl4+B3k+/aCYB/ndqd1nQIr4SNAtLFJDtlW2xah9W2lQL/7KQDT4o4dUMY51m7Bu61SAmKtralv7Hf25yf";
//	/**通联平台RSA公钥，用于请求返回或者通联通知的验签**/
//	public static final String SYB_RSATLPUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYXfu4b7xgDSmEGQpQ8Sn3RzFgl5CE4gL4TbYrND4FtCYOrvbgLijkdFgIrVVWi2hUW4K0PwBsmlYhXcbR+JSmqv9zviVXZiym0lK3glJGVCN86r9EPvNTusZZPm40TOEKMVENSYaUjCxZ7JzeZDfQ4WCeQQr2xirqn6LdJjpZ5wIDAQAB";
//
//	/**商户sm2私钥,用于向通联发起请求前进行签名**/
//	public static final String SYB_SM2PPRIVATEKEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgjj4Rk+b0YjwO+UwXofnHf4bK+kaaY5Btkd8nMP2VimmgCgYIKoEcz1UBgi2hRANCAAQqlALW4qGC3bP1x3wo5QsKxaCMEZJ2ODTTwOQ+d8UGU7GoK/y/WMBQWf5upMnFU06p5FxGooXYYoBtldgm03hq";
//	/**通联平台sm2公钥，用于请求返回或者通联通知的验签**/
//	public static final String SYB_SM2TLPUBKEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE/BnA8BawehBtH0ksPyayo4pmzL/u1FQ2sZcqwOp6bjVqQX4tjo930QAvHZPJ2eez8sCz/RYghcqv4LvMq+kloQ==";
	
}
