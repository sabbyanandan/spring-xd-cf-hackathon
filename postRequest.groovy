/**
 * Simple implementation of a Spring Integration transformer. The payload gets added with 'curl' with the respective REST API along with required credentials and attributes necessary for completing the POST/GET/PUT requests.
 *
 */

def payload = ["curl", "https://api.<YOUR_DOMAIN>.com/v2/apps/<YOUR_APP_GUID>", "-d", "{\"instances\": <NO_OF_INSTANCES>}", "-X", "PUT", "-H", "Authorization: bearer <PASSPHRASE>", "-H", "Content-Type: application/x-www-form-urlencoded", "-k"].execute().text

return payload