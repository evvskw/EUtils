package it.evvsk.eUtils.Utils;

import it.evvsk.eUtils.Core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class UpdateChecker {

	public String fetchLatestVersion() throws Exception {
		final HttpURLConnection connection = (HttpURLConnection) new URL("https://api.github.com/repos/evvskw/EUtils/releases/latest").openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

		final int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			final String response = readResponse(connection);
			return parseVersionFromResponse(response);
		} else {
			Core.getInstance().getLogger().warning("Update check failed. HTTP response code: " + responseCode);
			return null;
		}
	}

	private String readResponse(final HttpURLConnection connection) throws Exception {
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
			final StringBuilder response = new StringBuilder(1024);
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			return response.toString();
		}
	}

	private String parseVersionFromResponse(final String response) {
		final String tag = "\"tag_name\":\"";
		final int startIndex = response.indexOf(tag);
		if (startIndex != -1) {
			final int versionStart = startIndex + tag.length();
			final int versionEnd = response.indexOf('"', versionStart);
			if (versionEnd > versionStart) {
				return response.substring(versionStart, versionEnd);
			}
		}
		Core.getInstance().getLogger().warning("Failed to parse version: tag not found or invalid format.");
		return null;

	}

}
