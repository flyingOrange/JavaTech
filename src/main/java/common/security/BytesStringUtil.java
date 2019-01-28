package common.security;

import org.apache.commons.lang3.StringUtils;

class BytesStringUtil {

	static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString().toUpperCase();

	}

	static byte[] hexStringToBytes(String hexString) {
		if (StringUtils.isBlank(hexString)) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {

			int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;

	}

}